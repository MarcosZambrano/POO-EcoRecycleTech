package modelo;

public class ResiduoMetal extends Residuo {
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