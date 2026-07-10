package modelo;

/**
 * Representa un residuo de tipo plástico. No es reciclable si su
 * nivel de toxicidad supera 5, ya que el plástico se contamina con
 * facilidad y es sensible durante el proceso de reciclaje.
 */
public class ResiduoPlastico extends Residuo {

    /**
     * @param id identificador único del residuo
     * @param peso peso en kilogramos
     * @param nivelToxicidad nivel de toxicidad (0-10)
     */
    public ResiduoPlastico(String id, double peso, int nivelToxicidad) {
        super(id, peso, nivelToxicidad);
    }

    @Override
    public String getTipo() {
        return "Plástico";
    }

    @Override
    public boolean esReciclable() {
        if (nivelToxicidad > 5) {
            return false;
        } else {
            return true;
        }
    }
}