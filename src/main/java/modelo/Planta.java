package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase central del Modelo que orquesta la planta de reciclaje: gestiona
 * la cinta transportadora, los contenedores de almacenamiento y las
 * reglas de negocio para procesar y descartar residuos. Al construirse,
 * restaura automáticamente el estado guardado en una ejecución anterior.
 */
public class Planta {
    private List<IResiduo> cintaTransportadora = new ArrayList<>();
    private Map<String, Contenedor> contenedores = new HashMap<>();
    private int totalDescartados;

    /**
     * Construye la planta creando los 4 contenedores base (Vidrio, Plástico,
     * Papel, Metal) con sus capacidades máximas fijas, restaurando el nivel
     * de llenado de cada uno a partir del estado guardado previamente
     * (o en 0.0 si es la primera ejecución de la aplicación).
     */
    public Planta() {
        Map<String, Double> nivelesGuardados = PersistenciaEstado.cargarEstado();

        double nivelVidrio = nivelesGuardados.getOrDefault("Vidrio", 0.0);
        double nivelPlastico = nivelesGuardados.getOrDefault("Plástico", 0.0);
        double nivelPapel = nivelesGuardados.getOrDefault("Papel", 0.0);
        double nivelMetal = nivelesGuardados.getOrDefault("Metal", 0.0);

        contenedores.put("Vidrio", new ContenedorVidrio(80, nivelVidrio));
        contenedores.put("Plástico", new ContenedorPlastico(50, nivelPlastico));
        contenedores.put("Papel", new ContenedorPapel(40, nivelPapel));
        contenedores.put("Metal", new ContenedorMetal(100, nivelMetal));
    }

    /**
     * @return el total acumulado de residuos descartados (no reciclables)
     */
    public int getTotalDescartados() {
        return totalDescartados;
    }

    /**
     * Genera un residuo aleatorio mediante la fábrica y lo agrega al
     * final de la cinta transportadora, simulando su entrada a la planta.
     */
    public void simularEntradaResiduo() {
        IResiduo residuo = ResiduoFactory.crearResiduoAleatorio();
        cintaTransportadora.add(residuo);
    }

    /**
     * Procesa un residuo específico de la cinta: si es reciclable, lo
     * deposita en su contenedor correspondiente y registra el depósito
     * en el log; si no es reciclable, se descarta y se contabiliza.
     * En ambos casos, el residuo se retira de la cinta transportadora,
     * salvo que el depósito falle por exceso de capacidad, en cuyo caso
     * la excepción interrumpe el método antes de retirarlo.
     *
     * @param residuo residuo a procesar, normalmente el primero de la cinta
     * @throws IllegalArgumentException si el contenedor correspondiente no tiene espacio disponible
     */
    public void procesarResiduo(Residuo residuo) {
        if (residuo.esReciclable()) {
            Contenedor contenedor = contenedores.get(residuo.getTipo());
            contenedor.agregarResiduo(residuo.getPeso());
            LogManager.registrarDeposito(residuo);
        } else {
            totalDescartados++;
        }
        cintaTransportadora.remove(residuo);
    }

    /**
     * Guarda el estado actual de todos los contenedores de la planta,
     * delegando la operación a la capa de persistencia.
     */
    public void guardarEstadoActual() {
        PersistenciaEstado.guardarEstado(contenedores);
    }

    /**
     * Obtiene el siguiente residuo a procesar de la cinta transportadora,
     * siguiendo una lógica FIFO (el primero en entrar es el primero en salir).
     *
     * @return el primer residuo de la cinta, o null si la cinta está vacía
     */
    public IResiduo obtenerSiguienteResiduo() {
        if (cintaTransportadora.isEmpty()) {
            return null;
        }
        return cintaTransportadora.get(0);
    }

    /**
     * Calcula el porcentaje de llenado de cada contenedor de la planta.
     *
     * @return mapa con el porcentaje de llenado (0-100) indexado por tipo de contenedor
     */
    public Map<String, Double> obtenerPorcentajesLlenado() {
        Map<String, Double> porcentajes = new HashMap<>();
        for (Map.Entry<String, Contenedor> entrada : contenedores.entrySet()) {
            porcentajes.put(entrada.getKey(), entrada.getValue().getPorcentajeLlenado());
        }
        return porcentajes;
    }

    /**
     * Vacía el contenedor correspondiente al tipo indicado.
     *
     * @param tipo tipo de contenedor a vaciar (ej. "Vidrio")
     */
    public void vaciarContenedor(String tipo) {
        Contenedor contenedor = contenedores.get(tipo);
        if (contenedor != null) {
            contenedor.vaciar();
        }
    }

    /**
     * Construye una representación en texto de cada residuo que espera
     * actualmente en la cinta transportadora, lista para mostrarse en la Vista.
     *
     * @return lista de líneas de texto, una por cada residuo en espera
     */
    public List<String> obtenerLineasCinta() {
        List<String> lineas = new ArrayList<>();
        for (IResiduo residuo : cintaTransportadora) {
            lineas.add(residuo.getID() + " - " + residuo.getTipo() + " ("
                    + String.format("%.2f", residuo.getPeso()) + " kg)");
        }
        return lineas;
    }
}