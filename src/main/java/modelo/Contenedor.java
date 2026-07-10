package modelo;

/**
 * Representa un contenedor de almacenamiento para un tipo de residuo
 * específico dentro de la planta. Controla la capacidad máxima y el
 * nivel actual de llenado, validando que no se exceda su capacidad.
 */
public class Contenedor {
    protected double capacidadMaxima;
    protected double nivelActual;

    /**
     * Constructor para un contenedor nuevo, vacío.
     *
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     */
    public Contenedor(double capacidadMaxima) {
        this(capacidadMaxima, 0); // reutiliza el otro constructor
    }

    /**
     * Constructor para restaurar un contenedor con un nivel ya existente (persistencia).
     *
     * @param capacidadMaxima capacidad máxima del contenedor, en kilogramos
     * @param nivelActual nivel de llenado con el que se restaura el contenedor, en kilogramos
     */
    public Contenedor(double capacidadMaxima, double nivelActual) {
        this.capacidadMaxima = capacidadMaxima;
        this.nivelActual = nivelActual;
    }

    /**
     * Agrega el peso de un residuo al nivel actual del contenedor.
     *
     * @param pesoResiduo peso del residuo a depositar, en kilogramos
     * @throws IllegalArgumentException si el nuevo peso supera la capacidad máxima del contenedor
     */
    public void agregarResiduo(double pesoResiduo) {
        double nuevoPeso = nivelActual + pesoResiduo;
        if (nuevoPeso > capacidadMaxima) {
            throw new IllegalArgumentException("Has excedido la capacidad máxima del contenedor");
        }

        nivelActual = nuevoPeso;
    }

    /**
     * Calcula el porcentaje de llenado actual del contenedor.
     *
     * @return el porcentaje de llenado, entre 0 y 100
     */
    public double getPorcentajeLlenado() {
        double porcentaje = (nivelActual / capacidadMaxima) * 100;
        return porcentaje;
    }

    /**
     * Vacía completamente el contenedor, reiniciando su nivel actual a cero.
     */
    public void vaciar() {
        nivelActual = 0;
    }

    /**
     * @return la capacidad máxima del contenedor, en kilogramos
     */
    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * @return el nivel actual de llenado del contenedor, en kilogramos
     */
    public double getNivelActual() {
        return nivelActual;
    }
}