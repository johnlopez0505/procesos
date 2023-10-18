package tarea8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tarea8 {
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
        if (process!= null) {
            process.destroy();
            System.out.println("Me he cargado el ping.....");
        }
        try {
            System.out.println("Ahora esperare a que acabe mi proceso ping");
            process.waitFor();
            System.out.println("Ya no existe mi proceso ping");
        }catch (InterruptedException e) {
            System.out.println("No pudimos esperar por que termino");
            System.exit(-1);
        }
        System.out.println("Estado de termino: " + process.exitValue());
        System.exit(0);

    }
}
