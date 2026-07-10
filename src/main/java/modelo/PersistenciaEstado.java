package modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistenciaEstado {
    private static final String NOMBRE_ARCHIVO = "estado_planta.json";

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
