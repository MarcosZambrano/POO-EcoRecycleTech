package modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encargada de la persistencia de estado de la planta entre ejecuciones.
 * Guarda y recupera el nivel de llenado de los contenedores en un archivo
 * JSON (estado_planta.json), usando Gson y un DTO intermedio (ContenedorDTO)
 * para evitar los problemas de deserialización que surgen al reconstruir
 * jerarquías de herencia directamente desde JSON.
 */
public class PersistenciaEstado {
    private static final String NOMBRE_ARCHIVO = "estado_planta.json";

    /**
     * Guarda el estado actual de todos los contenedores en estado_planta.json,
     * sobrescribiendo el contenido anterior del archivo.
     *
     * @param contenedores mapa de contenedores actuales de la planta, indexado por tipo
     * @throws RuntimeException si ocurre un error al escribir o cerrar el archivo
     */
    public static void guardarEstado(Map<String, Contenedor> contenedores) {
        List<ContenedorDTO> listaDTO = new ArrayList<>();

        for (Map.Entry<String, Contenedor> entrada : contenedores.entrySet()) {
            String tipo = entrada.getKey();
            Contenedor contenedor = entrada.getValue();
            listaDTO.add(new ContenedorDTO(tipo, contenedor.getCapacidadMaxima(), contenedor.getNivelActual()));
        }

        Gson gson = new Gson();
        String json = gson.toJson(listaDTO);

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(NOMBRE_ARCHIVO);
            fileWriter.write(json);
        } catch(IOException e) {
            throw new RuntimeException("No se pudo escribir en el estado persistente (archivo JSON)", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("No se pudo cerrar el archivo de estado", e);
                }
            }
        }
    }

    /**
     * Carga el estado previamente guardado de los contenedores desde
     * estado_planta.json. Si el archivo no existe (primera ejecución de
     * la aplicación), devuelve un mapa vacío sin interrumpir el programa.
     *
     * @return mapa con el nivel de llenado guardado por tipo de contenedor,
     *         o un mapa vacío si no existe estado previo
     */
    public static Map<String, Double> cargarEstado(){
        Map<String, Double> nivelesGuardados = new HashMap<>();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(NOMBRE_ARCHIVO);
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<ContenedorDTO>>() {}.getType();
            List<ContenedorDTO> listaDTO = gson.fromJson(fileReader, tipoLista);

            if (listaDTO != null) {
                for (ContenedorDTO contenedorDTO : listaDTO) {
                    nivelesGuardados.put(contenedorDTO.getTipo(), contenedorDTO.getNivelActual());
                }
            }
        } catch(IOException e) {
            System.out.println("No se encontró estado guardado previo. Se inicia con contenedores vacíos.");
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new RuntimeException("No se pudo cerrar el archivo de estado", e);
                }
            }
        }

        return nivelesGuardados;
    }
}