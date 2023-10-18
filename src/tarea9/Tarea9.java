package tarea9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tarea9 {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;

        try {
            process = runtime.exec("ping " + args[0]);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (int i = 0; i < 10; i++) {
                System.out.println("Saludo desde PSP 23/24 " + in.readLine());
            }

        } catch (IOException e) {
            System.out.println("No pudimos correr el ping desde nuestra clase");
            System.exit(-1);
        }

        // Dormir al padre durante 3 segundos
        try {
            Thread.sleep(3000);

            // Matar al proceso hijo
            process.destroy();
            System.out.println("El proceso hijo ha sido terminado.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

       // System.exit(0);
    }
}
