package modelo;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class LogManager {
    private static final String NOMBRE_ARCHIVO = "recycle.log";

    public static void registrarDeposito(Residuo residuo) {
        String linea = residuo.getFechaIngreso() + " | ID: " + residuo.getID() + " | Tipo: " + residuo.getTipo() + " | Peso: " + String.format("%.2f", residuo.getPeso()) + " kg";
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true)));
            writer.println(linea);
        } catch(IOException e) {
            throw new RuntimeException("No se pudo escribir en el log", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}