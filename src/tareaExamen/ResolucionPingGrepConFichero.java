package tareaExamen;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResolucionPingGrepConFichero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el ping");
        String datos = sc.nextLine();

        ProcessBuilder pbPing = new ProcessBuilder("ping", datos, "-c2");
        ProcessBuilder pbGrep = new ProcessBuilder("grep", "rtt");
        File fichero = new File("src/fich.txt");
        pbPing.redirectOutput(fichero);
        try {
            Process pping = pbPing.start();
            pping.waitFor();
            System.out.println("El proceso hijo a terminado");
            Process pGrep = pbGrep.start();
            Scanner in = new Scanner(pGrep.getInputStream());
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            ProcessBuilder pbPings = new ProcessBuilder("ping", "www.google.com", "-c", "2");
            Process procPing = pbPings.start();

            ProcessBuilder pbGreps = new ProcessBuilder("grep", "rtt");
            Process procGrep = pbGreps.start();

            Scanner inDesdePing = new Scanner(procPing.getInputStream());
            PrintWriter outParaGrep = new PrintWriter(procGrep.getOutputStream());

            ExecutorService executor = Executors.newFixedThreadPool(2);

            executor.execute(() -> {
                while (inDesdePing.hasNextLine()) {
                    outParaGrep.println(inDesdePing.nextLine());
                    outParaGrep.flush();
                }
                outParaGrep.close();
            });

            executor.execute(() -> {
                Scanner inDesdeGrep = new Scanner(procGrep.getInputStream());
                while (inDesdeGrep.hasNextLine()) {
                    System.out.println(inDesdeGrep.nextLine());
                }
            });

            executor.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
