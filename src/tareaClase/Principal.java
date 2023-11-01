package tareaClase;

import java.io.*;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("java","-jar","src/tareaClase/ConvertirMayuscula.jar");
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa cadena a convertir");
        String linea = sc.nextLine();

        try {
            Process p = pb.start();
            OutputStream osp = p.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(osp);
            osw.write(linea);
            osw.close();

            InputStream isp = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(isp);
            BufferedReader br = new BufferedReader(isr);
            String linea2 = br.readLine();
            System.out.println(linea2);
            br.close();

            p.waitFor();
        }catch (IOException | InterruptedException e){
            e.getStackTrace();
        }
    }
}