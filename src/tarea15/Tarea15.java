package tarea15;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Tarea15 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso: <ruta> <extensión> <tiempo en segundos>");
            System.exit(1);
        }

        String ruta = args[0];
        String extension = args[1];
        int tiempoEspera = Integer.parseInt(args[2]) * 1000; // Convierte segundos a milisegundos

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("find", ruta, "-name", "*." + extension);
            //processBuilder.inheritIO(); //El proceso actual y el nuevo, utilizan las mismas E/S
            processBuilder.redirectErrorStream(true); // Redirige la salida de error al proceso actual

            Process p = processBuilder.start(); // Lanza el nuevo proceso
            Scanner scanner = new Scanner(new InputStreamReader(p.getInputStream()));
            // Leer la salida del proceso y mostrarla en la consola
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            System.out.println("Como proceso actual, voy a ejecutar un comando find por un tiempo " +
                    "mediante otro proceso\n");

            if (!p.waitFor(tiempoEspera, TimeUnit.MILLISECONDS)) {
                p.destroy(); // Mata el proceso hijo
                System.out.println("El proceso lanzado no ha finalizado a tiempo su ejecución\n");
            } else {
                System.out.println("El proceso lanzado ha finalizado a tiempo su ejecución");
            }


        } catch (IOException e) {
            System.out.println("Error al intentar lanzar un nuevo proceso. Pedimos información detallada\n");
            e.printStackTrace();
            System.exit(1); // Error en el proceso
        } catch (InterruptedException e) {
            System.out.println("El proceso ha sido interrumpido mediante interrupción\n");
            System.exit(2);
        }
    }
}
