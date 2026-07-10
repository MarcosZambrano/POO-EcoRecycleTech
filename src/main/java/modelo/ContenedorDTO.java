package modelo;

public class ContenedorDTO {
    String tipo;
    double capacidadMaxima;
    double nivelActual;

    public ContenedorDTO(String tipo, double capacidadMaxima, double nivelActual) {
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
        this.nivelActual = nivelActual;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getNivelActual() {
        return nivelActual;
    }
}