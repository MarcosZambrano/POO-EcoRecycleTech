package modelo;

/**
 * Contenedor de almacenamiento específico para residuos de papel/cartón.
 */
public class ContenedorPapel extends Contenedor {

    /**
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     */
    public ContenedorPapel(double capacidadMaxima) {
        super(capacidadMaxima);
    }

    /**
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     * @param nivelActual nivel de llenado con el que se restaura el contenedor, en kilogramos
     */
    public ContenedorPapel(double capacidadMaxima, double nivelActual) {
        super(capacidadMaxima, nivelActual);
    }

    /**
     * @return el tipo de residuo que este contenedor acepta ("Papel")
     */
    public String getTipoAceptado() {
        return "Papel";
    }
}