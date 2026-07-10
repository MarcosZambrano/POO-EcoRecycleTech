package modelo;

public class Contenedor {
    protected double capacidadMaxima;
    protected double nivelActual;

    // Constructor para un contenedor nuevo, vacío
    public Contenedor(double capacidadMaxima) {
        this(capacidadMaxima, 0); // reutiliza el otro constructor
    }

    // Constructor para restaurar un contenedor con un nivel ya existente (persistencia)
    public Contenedor(double capacidadMaxima, double nivelActual) {
        this.capacidadMaxima = capacidadMaxima;
        this.nivelActual = nivelActual;
    }

    public void agregarResiduo(double pesoResiduo) {
        double nuevoPeso = nivelActual + pesoResiduo;
        if (nuevoPeso > capacidadMaxima) {
            throw new IllegalArgumentException("Has excedido la capacidad máxima del contenedor");
        }

        nivelActual = nuevoPeso;
    }

    public double getPorcentajeLlenado() {
        double porcentaje = (nivelActual / capacidadMaxima) * 100;
        return porcentaje;
    }

    public void vaciar() {
        nivelActual = 0;
    }

    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getNivelActual() {
        return nivelActual;
    }
}