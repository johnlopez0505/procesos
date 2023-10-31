package tareaExamen;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ResolucionPingGrep {
    public static void main(String[] args) {
        ProcessBuilder pbPing = new ProcessBuilder("ping","www.google.com","-c2");
        ProcessBuilder pbGrep = new ProcessBuilder("grep","rtt");
        try {
            Process procPing = pbPing.start();
            Process procGrep = pbGrep.start();
            Scanner inDesdePing = new Scanner(procPing.getInputStream());
            PrintWriter outParaGrep = new PrintWriter(procGrep.getOutputStream());
            while (inDesdePing.hasNextLine()){
                outParaGrep.println(inDesdePing.nextLine());
            }
            System.out.println((procPing.waitFor() == 0) ? "Proceso Ping Termino correctamente"
                    : "Error con el proceso Ping");
            inDesdePing.close();
            outParaGrep.close();
            Scanner inDesdeGrep = new Scanner(procGrep.getInputStream());
            System.out.println("Mostramos la búsqueda del Grep: ");
            while (inDesdeGrep.hasNextLine()){
                System.out.println(inDesdeGrep.nextLine());
            }
            inDesdeGrep.close();
            int codigo = procGrep.waitFor();
            System.out.println((codigo == 0) ? "Proceso Grep termino correctamente"
                    : "Error con el proceso Grep");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
