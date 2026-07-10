package vista;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Vista principal del sistema, implementada en Java Swing. Muestra el
 * estado de la cinta transportadora, el porcentaje de llenado de cada
 * contenedor con alertas visuales de color, y expone botones y controles
 * para que el Controlador capture las interacciones del usuario. No
 * contiene lógica de negocio: solo pinta y captura eventos.
 */
public class VentanaPrincipal extends JFrame {

    private JButton botonSimular;
    private JButton botonProcesar;
    private JButton botonVaciar;
    private JComboBox<String> comboContenedores;

    private Map<String, JProgressBar> barrasContenedores = new LinkedHashMap<>();
    private JTextArea areaCinta;
    private JLabel labelDescartados;

    private static final String[] TIPOS = {"Vidrio", "Plástico", "Papel", "Metal"};

    /**
     * Construye la ventana principal, configurando el layout general
     * (BorderLayout) y ensamblando los paneles de botones, contenido
     * central y pie de página.
     */
    public VentanaPrincipal() {
        super("EcoRecycle Tech - Panel de Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(construirPanelBotones(), BorderLayout.NORTH);
        add(construirPanelCentral(), BorderLayout.CENTER);
        add(construirPanelSur(), BorderLayout.SOUTH);

        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    /**
     * Construye el panel superior con los botones de acción y el
     * combo para seleccionar el tipo de contenedor a vaciar.
     *
     * @return el panel de botones ya configurado
     */
    private JPanel construirPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout());

        botonSimular = new JButton("Simular Entrada de Residuo");
        botonProcesar = new JButton("Procesar Residuo");
        botonVaciar = new JButton("Vaciar Contenedor");
        comboContenedores = new JComboBox<>(TIPOS);

        panel.add(botonSimular);
        panel.add(botonProcesar);
        panel.add(new JLabel("Contenedor:"));
        panel.add(comboContenedores);
        panel.add(botonVaciar);

        return panel;
    }

    /**
     * Construye el panel central, que contiene el área de texto de la
     * cinta transportadora y la cuadrícula con las barras de progreso
     * de los 4 contenedores.
     *
     * @return el panel central ya configurado
     */
    private JPanel construirPanelCentral() {
        JPanel panelCentral = new JPanel(new BorderLayout());

        areaCinta = new JTextArea(6, 30);
        areaCinta.setEditable(false);
        JScrollPane scrollCinta = new JScrollPane(areaCinta);
        scrollCinta.setBorder(BorderFactory.createTitledBorder("Cinta Transportadora"));
        panelCentral.add(scrollCinta, BorderLayout.NORTH);

        JPanel panelContenedores = new JPanel(new GridLayout(TIPOS.length, 1, 5, 5));
        panelContenedores.setBorder(BorderFactory.createTitledBorder("Contenedores"));

        for (String tipo : TIPOS) {
            JPanel filaContenedor = new JPanel(new BorderLayout(10, 0));
            JLabel etiqueta = new JLabel(tipo);
            etiqueta.setPreferredSize(new Dimension(80, 20));

            JProgressBar barra = new JProgressBar(0, 100);
            barra.setStringPainted(true);
            barrasContenedores.put(tipo, barra);

            filaContenedor.add(etiqueta, BorderLayout.WEST);
            filaContenedor.add(barra, BorderLayout.CENTER);
            panelContenedores.add(filaContenedor);
        }

        panelCentral.add(panelContenedores, BorderLayout.CENTER);
        return panelCentral;
    }

    /**
     * Construye el panel inferior con la etiqueta del contador de
     * residuos descartados.
     *
     * @return el panel inferior ya configurado
     */
    private JPanel construirPanelSur() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelDescartados = new JLabel("Residuos descartados: 0");
        panel.add(labelDescartados);
        return panel;
    }

    // Métodos públicos para que el Controlador actualice la Vista

    /**
     * Actualiza el contenido del área de la cinta transportadora,
     * mostrando una línea por cada residuo en espera.
     *
     * @param lineas líneas de texto a mostrar, una por residuo en la cinta
     */
    public void actualizarCinta(List<String> lineas) {
        areaCinta.setText("");
        for (String linea : lineas) {
            areaCinta.append(linea + "\n");
        }
    }

    /**
     * Actualiza el valor y el color de la barra de progreso de un
     * contenedor específico. El color cambia según el nivel de llenado:
     * verde por debajo de 70%, naranja entre 70% y 89%, rojo desde 90%.
     *
     * @param tipo tipo de contenedor a actualizar (ej. "Vidrio")
     * @param porcentaje porcentaje de llenado a mostrar, entre 0 y 100
     */
    public void actualizarContenedor(String tipo, int porcentaje) {
        JProgressBar barra = barrasContenedores.get(tipo);
        if (barra != null) {
            barra.setValue(porcentaje);
            barra.setString(porcentaje + "%");

            if (porcentaje >= 90) {
                barra.setForeground(Color.RED);
            } else if (porcentaje >= 70) {
                barra.setForeground(Color.ORANGE);
            } else {
                barra.setForeground(Color.GREEN);
            }
        }
    }

    /**
     * Actualiza la etiqueta del contador de residuos descartados.
     *
     * @param total total acumulado de residuos descartados
     */
    public void actualizarDescartados(int total) {
        labelDescartados.setText("Residuos descartados: " + total);
    }

    /**
     * Muestra un mensaje emergente de advertencia al usuario.
     *
     * @param mensaje texto a mostrar en el cuadro de diálogo
     */
    public void mostrarAlerta(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @return el tipo de contenedor actualmente seleccionado en el combo
     */
    public String getTipoSeleccionado() {
        return (String) comboContenedores.getSelectedItem();
    }

    // Getters de los botones, para que el Controlador les agregue sus listeners

    /**
     * @return el botón "Simular Entrada de Residuo"
     */
    public JButton getBotonSimular() {
        return botonSimular;
    }

    /**
     * @return el botón "Procesar Residuo"
     */
    public JButton getBotonProcesar() {
        return botonProcesar;
    }

    /**
     * @return el botón "Vaciar Contenedor"
     */
    public JButton getBotonVaciar() {
        return botonVaciar;
    }
}