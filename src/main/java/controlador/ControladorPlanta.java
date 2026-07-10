package controlador;

import modelo.IResiduo;
import modelo.Planta;
import modelo.Residuo;
import vista.VentanaPrincipal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

/**
 * Controlador del sistema, siguiendo el patrón Modelo-Vista-Controlador.
 * Captura las interacciones del usuario sobre la Vista (clics en los
 * botones, cierre de ventana), invoca la lógica correspondiente en el
 * Modelo, y ordena a la Vista que se actualice para reflejar los
 * nuevos datos.
 */
public class ControladorPlanta {

    private Planta planta;
    private VentanaPrincipal vista;

    /**
     * Construye el controlador, conectando el Modelo y la Vista recibidos.
     * Registra los listeners de los 3 botones de acción y del cierre de
     * ventana (para persistir el estado antes de finalizar la aplicación),
     * y refresca la Vista con el estado inicial de la planta.
     *
     * @param planta instancia del Modelo a controlar
     * @param vista instancia de la Vista a actualizar
     */
    public ControladorPlanta(Planta planta, VentanaPrincipal vista) {
        this.planta = planta;
        this.vista = vista;

        this.vista.getBotonSimular().addActionListener(e -> manejarSimular());
        this.vista.getBotonProcesar().addActionListener(e -> manejarProcesar());
        this.vista.getBotonVaciar().addActionListener(e -> manejarVaciar());

        this.vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                planta.guardarEstadoActual();
            }
        });

        actualizarVista();
    }

    /**
     * Maneja el clic en "Simular Entrada de Residuo": genera un residuo
     * nuevo en la planta y refresca la Vista.
     */
    private void manejarSimular() {
        planta.simularEntradaResiduo();
        actualizarVista();
    }

    /**
     * Maneja el clic en "Procesar Residuo": obtiene el siguiente residuo
     * de la cinta y lo procesa. Si no hay residuos esperando, o si el
     * contenedor correspondiente no tiene espacio disponible, muestra
     * una alerta al usuario en lugar de interrumpir la aplicación.
     */
    private void manejarProcesar() {
        IResiduo siguiente = planta.obtenerSiguienteResiduo();

        if (siguiente == null) {
            vista.mostrarAlerta("No hay residuos en la cinta para procesar.");
            return;
        }

        Residuo residuo = (Residuo) siguiente;

        try {
            planta.procesarResiduo(residuo);
        } catch (IllegalArgumentException e) {
            vista.mostrarAlerta(e.getMessage());
        }

        actualizarVista();
    }

    /**
     * Maneja el clic en "Vaciar Contenedor": vacía el contenedor
     * correspondiente al tipo seleccionado en el combo de la Vista.
     */
    private void manejarVaciar() {
        String tipoSeleccionado = vista.getTipoSeleccionado();
        planta.vaciarContenedor(tipoSeleccionado);
        actualizarVista();
    }

    /**
     * Sincroniza la Vista con el estado actual del Modelo: actualiza el
     * porcentaje de llenado de cada contenedor, el contador de residuos
     * descartados y el contenido de la cinta transportadora.
     */
    private void actualizarVista() {
        Map<String, Double> porcentajes = planta.obtenerPorcentajesLlenado();
        for (Map.Entry<String, Double> entrada : porcentajes.entrySet()) {
            vista.actualizarContenedor(entrada.getKey(), entrada.getValue().intValue());
        }

        vista.actualizarDescartados(planta.getTotalDescartados());
        vista.actualizarCinta(planta.obtenerLineasCinta());
    }
}