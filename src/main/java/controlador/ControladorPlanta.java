package controlador;

import modelo.IResiduo;
import modelo.Planta;
import modelo.Residuo;
import vista.VentanaPrincipal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

public class ControladorPlanta {

    private Planta planta;
    private VentanaPrincipal vista;

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

    private void manejarSimular() {
        planta.simularEntradaResiduo();
        actualizarVista();
    }

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

    private void manejarVaciar() {
        String tipoSeleccionado = vista.getTipoSeleccionado();
        planta.vaciarContenedor(tipoSeleccionado);
        actualizarVista();
    }

    private void actualizarVista() {
        Map<String, Double> porcentajes = planta.obtenerPorcentajesLlenado();
        for (Map.Entry<String, Double> entrada : porcentajes.entrySet()) {
            vista.actualizarContenedor(entrada.getKey(), entrada.getValue().intValue());
        }

        vista.actualizarDescartados(planta.getTotalDescartados());
    }
}