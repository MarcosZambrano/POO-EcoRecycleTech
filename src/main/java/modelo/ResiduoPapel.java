package modelo;

/**
 * Representa un residuo de tipo papel o cartón. Es el material más
 * sensible a la contaminación de los cuatro contemplados en el sistema:
 * no es reciclable si su nivel de toxicidad supera 3.
 */
public class ResiduoPapel extends Residuo {

    /**
     * @param id identificador único del residuo
     * @param peso peso en kilogramos
     * @param nivelToxicidad nivel de toxicidad (0-10)
     */
    public ResiduoPapel(String id, double peso, int nivelToxicidad) {
        super(id, peso, nivelToxicidad);
    }

    @Override
    public String getTipo() {
        return "Papel";
    }

    @Override
    public boolean esReciclable() {
        if (nivelToxicidad > 3) {
            return false;
        } else {
            return true;
        }
    }
}