package modelo;

/**
 * Implementa el patrón de diseño Factory para la creación de residuos.
 * Encapsula la lógica de generación aleatoria (tipo, peso, toxicidad e ID),
 * de modo que el resto del sistema (Vista y Controlador) nunca necesite
 * conocer ni instanciar directamente las clases concretas de residuo.
 */
public class ResiduoFactory {
    public static int contador;

    /**
     * Genera un residuo de tipo aleatorio, con peso y nivel de toxicidad
     * también aleatorios dentro de rangos razonables. El identificador
     * único se construye combinando el prefijo del tipo con un contador
     * incremental compartido entre todas las llamadas.
     *
     * @return una instancia concreta de residuo (Vidrio, Plástico, Papel o Metal),
     *         expuesta como IResiduo
     * @throws IllegalStateException si el índice aleatorio generado queda fuera del rango esperado (0-3)
     */
    public static IResiduo crearResiduoAleatorio() {
        contador++;
        int indiceAleatorioResiduo = (int)(Math.random() * 4);
        double peso = 0.5 + (Math.random() * 9.5);
        int nivelToxicidad = (int)(Math.random() * 11);

        switch (indiceAleatorioResiduo) {
            case 0:
                return new ResiduoVidrio("VID-" + contador, peso, nivelToxicidad);
            case 1:
                return new ResiduoPlastico("PLA-" + contador, peso, nivelToxicidad);
            case 2:
                return new ResiduoPapel("PAP-" + contador, peso, nivelToxicidad);
            case 3:
                return new ResiduoMetal("MET-" + contador, peso, nivelToxicidad);
            default:
                throw new IllegalStateException("Índice aleatorio fuera de rango");
        }
    }
}