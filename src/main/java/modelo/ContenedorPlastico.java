package modelo;

/**
 * Contenedor de almacenamiento específico para residuos de plástico.
 */
public class ContenedorPlastico extends Contenedor {

    /**
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     */
    public ContenedorPlastico(double capacidadMaxima) {
        super(capacidadMaxima);
    }

    /**
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     * @param nivelActual nivel de llenado con el que se restaura el contenedor, en kilogramos
     */
    public ContenedorPlastico(double capacidadMaxima, double nivelActual) {
        super(capacidadMaxima, nivelActual);
    }

    /**
     * @return el tipo de residuo que este contenedor acepta ("Plástico")
     */
    public String getTipoAceptado() {
        return "Plástico";
    }
}