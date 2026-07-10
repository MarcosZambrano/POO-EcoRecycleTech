import controlador.ControladorPlanta;
import modelo.Planta;
import vista.VentanaPrincipal;

/**
 * Punto de entrada de la aplicación EcoRecycle Tech. Instancia el
 * Modelo (Planta), la Vista (VentanaPrincipal) y el Controlador
 * (ControladorPlanta), conectándolos según el patrón MVC, y hace
 * visible la ventana principal al usuario.
 */
public class Main {

    /**
     * Arranca la aplicación: crea la planta (restaurando el estado
     * guardado si existe), crea la ventana principal, conecta ambas
     * mediante el controlador, y muestra la ventana.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Planta planta = new Planta();
        VentanaPrincipal vista = new VentanaPrincipal();
        ControladorPlanta controlador = new ControladorPlanta(planta, vista);

        vista.setVisible(true);
    }
}