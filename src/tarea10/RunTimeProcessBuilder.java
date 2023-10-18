package tarea10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunTimeProcessBuilder {
    public static void main(String[] args) {

        try {
            // Crear un objeto ProcessBuilder para ejecutar el comando "ls -l"
            ProcessBuilder processBuilder = new ProcessBuilder("ls","-l");
            // Iniciar el proceso
            Process process = processBuilder.start();

            // Capturar la salida del comando "ls -l"
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            // Leer y mostrar la salida línea por línea
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso hijo termine
            process.waitFor();
            System.out.println("El comando 'ls -l' ha finalizado.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
