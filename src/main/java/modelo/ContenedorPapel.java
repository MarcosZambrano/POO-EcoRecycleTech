package modelo;

public class ContenedorPapel extends Contenedor {

    public ContenedorPapel(double capacidadMaxima) {
        super(capacidadMaxima);
    }

    public ContenedorPapel(double capacidadMaxima, double nivelActual) {
        super(capacidadMaxima, nivelActual);
    }

    public String getTipoAceptado() {
        return "Papel";
    }
}