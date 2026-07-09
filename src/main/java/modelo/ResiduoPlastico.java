package modelo;

public class ResiduoPlastico extends Residuo {
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