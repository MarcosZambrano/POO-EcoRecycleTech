package modelo;

import java.time.LocalDateTime;

/**
 * Clase abstracta que representa un residuo genérico dentro de la planta.
 * Contiene los atributos comunes a todos los tipos de residuo y obliga
 * a cada subclase a definir su propia regla de reciclabilidad.
 */
public abstract class Residuo implements IResiduo {
    protected String id;
    protected double peso;
    protected int nivelToxicidad; // 0-10 de Toxicidad
    protected LocalDateTime fecha_ingreso;

    /**
     * Construye un residuo con sus atributos base. La fecha de ingreso
     * se asigna automáticamente al momento de la creación.
     *
     * @param id identificador único del residuo
     * @param peso peso del residuo en kilogramos
     * @param nivelToxicidad nivel de toxicidad en escala 0 (inocuo) a 10 (muy tóxico)
     */
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

    /**
     * @return el nivel de toxicidad del residuo, en escala 0-10
     */
    public int getNivelToxicidad() {
        return nivelToxicidad;
    }

    /**
     * @return la fecha y hora exacta en que el residuo ingresó a la cinta
     */
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