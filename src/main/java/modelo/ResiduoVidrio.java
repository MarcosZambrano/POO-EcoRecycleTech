package modelo;

public class ResiduoVidrio extends Residuo {
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