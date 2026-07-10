package modelo;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Encargada de registrar el histórico de depósitos exitosos en un
 * archivo de texto (recycle.log). Cada línea registra fecha/hora,
 * ID, tipo y peso del residuo depositado. Escribe siempre en modo
 * append, de modo que el histórico se conserve entre ejecuciones.
 */
public class LogManager {
    private static final String NOMBRE_ARCHIVO = "recycle.log";

    /**
     * Registra en recycle.log el depósito exitoso de un residuo.
     *
     * @param residuo residuo que fue depositado correctamente en su contenedor
     * @throws RuntimeException si ocurre un error de escritura en el archivo
     */
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