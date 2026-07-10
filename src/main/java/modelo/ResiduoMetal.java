package modelo;

/**
 * Representa un residuo de tipo metal. Extensión SOLID del proyecto
 * (Principio de Abierto/Cerrado): se añade sin modificar ninguna de
 * las clases base ya existentes. Es el material más tolerante al
 * reciclaje gracias al proceso de fundición: no es reciclable solo
 * si su nivel de toxicidad supera 9.
 */
public class ResiduoMetal extends Residuo {

    /**
     * @param id identificador único del residuo
     * @param peso peso en kilogramos
     * @param nivelToxicidad nivel de toxicidad (0-10)
     */
    public ResiduoMetal(String id, double peso, int nivelToxicidad) {
        super(id, peso, nivelToxicidad);
    }

    @Override
    public String getTipo() {
        return "Metal";
    }

    @Override
    public boolean esReciclable() {
        if (nivelToxicidad > 9) {
            return false;
        } else {
            return true;
        }
    }
}