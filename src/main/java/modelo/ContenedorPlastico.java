package modelo;

public class ContenedorPlastico extends Contenedor {

    public ContenedorPlastico(double capacidadMaxima) {
        super(capacidadMaxima);
    }

    public ContenedorPlastico(double capacidadMaxima, double nivelActual) {
        super(capacidadMaxima, nivelActual);
    }

    public String getTipoAceptado() {
        return "Plástico";
    }
}