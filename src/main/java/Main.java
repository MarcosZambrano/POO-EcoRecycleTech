import controlador.ControladorPlanta;
import modelo.Planta;
import vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        Planta planta = new Planta();
        VentanaPrincipal vista = new VentanaPrincipal();
        ControladorPlanta controlador = new ControladorPlanta(planta, vista);

        vista.setVisible(true);
    }
}