import java.io.*;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "ConvertirMayuscula.jar");
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

        }catch (IOException e){
            System.out.println("error");
        }
    }
}
