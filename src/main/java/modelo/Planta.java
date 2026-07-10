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
        contenedores.put("Vidrio", new ContenedorVidrio(80));      // el vidrio pesa más por volumen
        contenedores.put("Plástico", new ContenedorPlastico(50));
        contenedores.put("Papel", new ContenedorPapel(40));         // el papel ocupa más espacio, pesa menos
        contenedores.put("Metal", new ContenedorMetal(100));        // el metal es denso, contenedor más resistente
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
}