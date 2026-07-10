package modelo;

/**
 * Objeto de transferencia de datos (DTO) usado para serializar y
 * deserializar el estado de un contenedor mediante Gson. Al ser una
 * clase simple sin lógica de negocio, evita los problemas que surgen
 * al intentar reconstruir jerarquías de herencia (ContenedorVidrio,
 * ContenedorPlastico, etc.) directamente desde JSON.
 */
public class ContenedorDTO {
    String tipo;
    double capacidadMaxima;
    double nivelActual;

    /**
     * @param tipo tipo de residuo que acepta el contenedor (ej. "Vidrio")
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     * @param nivelActual nivel de llenado del contenedor en el momento de guardar, en kilogramos
     */
    public ContenedorDTO(String tipo, double capacidadMaxima, double nivelActual) {
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
        this.nivelActual = nivelActual;
    }

    /**
     * @return el tipo de residuo asociado a este contenedor
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return la capacidad máxima del contenedor, en kilogramos
     */
    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * @return el nivel de llenado guardado del contenedor, en kilogramos
     */
    public double getNivelActual() {
        return nivelActual;
    }
}