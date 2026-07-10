package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Planta {
    private List<IResiduo> cintaTransportadora = new ArrayList<>();
    private Map<String, Contenedor> contenedores = new HashMap<>();
    private int totalDescartados;

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

    public int getTotalDescartados() {
        return totalDescartados;
    }

    public void simularEntradaResiduo() {
        IResiduo residuo = ResiduoFactory.crearResiduoAleatorio();
        cintaTransportadora.add(residuo);
    }

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

    public void guardarEstadoActual() {
        PersistenciaEstado.guardarEstado(contenedores);
    }

    public IResiduo obtenerSiguienteResiduo() {
        if (cintaTransportadora.isEmpty()) {
            return null;
        }
        return cintaTransportadora.get(0);
    }

    public Map<String, Double> obtenerPorcentajesLlenado() {
        Map<String, Double> porcentajes = new HashMap<>();
        for (Map.Entry<String, Contenedor> entrada : contenedores.entrySet()) {
            porcentajes.put(entrada.getKey(), entrada.getValue().getPorcentajeLlenado());
        }
        return porcentajes;
    }

    public void vaciarContenedor(String tipo) {
        Contenedor contenedor = contenedores.get(tipo);
        if (contenedor != null) {
            contenedor.vaciar();
        }
    }
}