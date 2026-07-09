package modelo;

public class ContenedorMetal extends Contenedor {

    public ContenedorMetal(double capacidadMaxima) {
        super(capacidadMaxima);
    }

    public ContenedorMetal(double capacidadMaxima, double nivelActual) {
        super(capacidadMaxima, nivelActual);
    }

    public String getTipoAceptado() {
        return "Metal";
    }
}