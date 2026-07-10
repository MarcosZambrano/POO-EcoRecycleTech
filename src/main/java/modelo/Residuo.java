package modelo;

import java.time.LocalDateTime;

public abstract class Residuo implements IResiduo {
    protected String id;
    protected double peso;
    protected int nivelToxicidad; // 0-10 de Toxicidad
    protected LocalDateTime fecha_ingreso;

    protected Residuo(String id, double peso, int nivelToxicidad) {
        this.id = id;
        this.peso = peso;
        this.nivelToxicidad = nivelToxicidad;
        this.fecha_ingreso = LocalDateTime.now();
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public double getPeso() {
        return peso;
    }

    public int getNivelToxicidad() {
        return nivelToxicidad;
    }

    public LocalDateTime getFechaIngreso() {
        return fecha_ingreso;
    }

    /**
     * Determina si, bajo las condiciones actuales del residuo,
     * puede ser procesado directamente por la planta.
     * Cada subclase implementa su propia lógica (polimorfismo).
     *
     * @return true si el residuo es reciclable, false en caso contrario
     */
    public abstract boolean esReciclable();
}