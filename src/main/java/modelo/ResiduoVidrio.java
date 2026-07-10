package modelo;

/**
 * Representa un residuo de tipo vidrio. Es un material tolerante al
 * reciclaje, ya que puede fundirse y reutilizarse sin degradarse:
 * no es reciclable solo si su nivel de toxicidad supera 8.
 */
public class ResiduoVidrio extends Residuo {

    /**
     * @param id identificador único del residuo
     * @param peso peso en kilogramos
     * @param nivelToxicidad nivel de toxicidad (0-10)
     */
    public ResiduoVidrio(String id, double peso, int nivelToxicidad) {
        super(id, peso, nivelToxicidad);
    }

    @Override
    public String getTipo() {
        return "Vidrio";
    }

    @Override
    public boolean esReciclable() {
        if (nivelToxicidad > 8) {
            return false;
        } else {
            return true;
        }
    }
}