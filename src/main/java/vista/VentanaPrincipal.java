package vista;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VentanaPrincipal extends JFrame {

    private JButton botonSimular;
    private JButton botonProcesar;
    private JButton botonVaciar;
    private JComboBox<String> comboContenedores;

    private Map<String, JProgressBar> barrasContenedores = new LinkedHashMap<>();
    private JTextArea areaCinta;
    private JLabel labelDescartados;

    private static final String[] TIPOS = {"Vidrio", "Plástico", "Papel", "Metal"};

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

    private JPanel construirPanelSur() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelDescartados = new JLabel("Residuos descartados: 0");
        panel.add(labelDescartados);
        return panel;
    }

    // Métodos públicos para que el Controlador actualice la Vista

    public void actualizarCinta(List<String> lineas) {
        areaCinta.setText("");
        for (String linea : lineas) {
            areaCinta.append(linea + "\n");
        }
    }

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

    public void actualizarDescartados(int total) {
        labelDescartados.setText("Residuos descartados: " + total);
    }

    public void mostrarAlerta(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public String getTipoSeleccionado() {
        return (String) comboContenedores.getSelectedItem();
    }

    // Getters de los botones, para que el Controlador les agregue sus listeners

    public JButton getBotonSimular() {
        return botonSimular;
    }

    public JButton getBotonProcesar() {
        return botonProcesar;
    }

    public JButton getBotonVaciar() {
        return botonVaciar;
    }
}