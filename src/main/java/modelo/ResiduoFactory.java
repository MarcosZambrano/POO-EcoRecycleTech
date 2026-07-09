package modelo;

public class ResiduoFactory {
    public static int contador;

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