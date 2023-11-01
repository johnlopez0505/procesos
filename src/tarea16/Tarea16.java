package tarea16;

import java.io.PrintWriter;
import java.util.Scanner;

public class Tarea16 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("nslookup");
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        System.out.println("Introduce el nombre de dominio");
        Scanner sc = new Scanner(System.in);
        String linea;
        Process p = null;
        try {
            while(((linea = sc.nextLine()) != null) && linea.length() != 0) {
                p = pb.start();
                PrintWriter out = new PrintWriter(p.getOutputStream());
                out.println(linea);
                out.close();
            }
        try {
            p.waitFor();
            System.out.println("Soy el proceso padre y ha muerto el hijo");
        } catch (InterruptedException e) {
            System.out.println("Se ha interrumpido el proceso");
        }
        } catch (Exception e) {
            System.out.println("Algo ha petado");
        }
    }
}
