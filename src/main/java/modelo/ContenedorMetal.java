package modelo;

/**
 * Contenedor de almacenamiento específico para residuos de metal.
 * Extensión SOLID del proyecto (Principio de Abierto/Cerrado): se agrega
 * sin necesidad de modificar ninguna de las clases base ya existentes.
 */
public class ContenedorMetal extends Contenedor {

    /**
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     */
    public ContenedorMetal(double capacidadMaxima) {
        super(capacidadMaxima);
    }

    /**
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     * @param nivelActual nivel de llenado con el que se restaura el contenedor, en kilogramos
     */
    public ContenedorMetal(double capacidadMaxima, double nivelActual) {
        super(capacidadMaxima, nivelActual);
    }

    /**
     * @return el tipo de residuo que este contenedor acepta ("Metal")
     */
    public String getTipoAceptado() {
        return "Metal";
    }
}