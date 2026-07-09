package modelo;

public class ContenedorVidrio extends Contenedor {

    public ContenedorVidrio(double capacidadMaxima) {
        super(capacidadMaxima);
    }

    public ContenedorVidrio(double capacidadMaxima, double nivelActual) {
        super(capacidadMaxima, nivelActual);
    }

    public String getTipoAceptado() {
        return "Vidrio";
    }
}