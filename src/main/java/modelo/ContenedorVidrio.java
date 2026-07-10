package modelo;

/**
 * Contenedor de almacenamiento específico para residuos de vidrio.
 */
public class ContenedorVidrio extends Contenedor {

    /**
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     */
    public ContenedorVidrio(double capacidadMaxima) {
        super(capacidadMaxima);
    }

    /**
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     * @param nivelActual nivel de llenado con el que se restaura el contenedor, en kilogramos
     */
    public ContenedorVidrio(double capacidadMaxima, double nivelActual) {
        super(capacidadMaxima, nivelActual);
    }

    /**
     * @return el tipo de residuo que este contenedor acepta ("Vidrio")
     */
    public String getTipoAceptado() {
        return "Vidrio";
    }
}