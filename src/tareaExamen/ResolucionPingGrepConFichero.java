package tareaExamen;

import java.io.*;
import java.util.Scanner;


public class ResolucionPingGrepConFichero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el ping");
        String datos = sc.nextLine();

        ProcessBuilder pbPing = new ProcessBuilder("ping", datos, "-c2");
        ProcessBuilder pbGrep = new ProcessBuilder("grep", "rtt","/tmp/salida.out");
        File fichero = new File("/tmp/salida.out");
        pbPing.redirectOutput(fichero);
        try {
            Process pping = pbPing.start();
            int codigo = pping.waitFor();
            String resultado = (codigo == 0) ? "Proceso Ping termino correctamente" : "Error en el proceso Ping";
            System.out.println(resultado);
            Process pGrep = pbGrep.start();
            Scanner in = new Scanner(pGrep.getInputStream());
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
            in.close();
            codigo = pGrep.waitFor();
            System.out.println((codigo == 0) ? "El proceso Grep termino correctamente" : "Error en el proceso Grep");
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
