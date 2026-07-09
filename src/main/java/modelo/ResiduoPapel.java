package modelo;

public class ResiduoPapel extends Residuo {
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