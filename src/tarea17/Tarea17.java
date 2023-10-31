package tarea17;

import java.io.*;

public class Tarea17 {
    public static void main(String[] args) throws IOException {
        // Configuracion ProcessBuilder utilizando el comando "nslookup"
        ProcessBuilder pb = new ProcessBuilder("nslookup");
        try {
            InputStreamReader isrin = new InputStreamReader(System.in, "UTF-8");
            BufferedReader brin = new BufferedReader(isrin);
            String linea;
            System.out.println("Introduce el nombre del dominio");
            Process p = null;
            while ((linea = brin.readLine()) != null && linea.length() != 0) {
                // Iniciar el proceso "nslookup"
                p = pb.start();
                try {
                    // Configurar la salida del proceso para enviar la entrada del usuario
                    OutputStream osp = p.getOutputStream();
                    OutputStreamWriter oswp = new OutputStreamWriter(osp, "UTF-8");
                    oswp.write(linea);
                    oswp.close();

                    // Leer la salida del proceso "nslookup"
                    InputStream isp = p.getInputStream();
                    InputStreamReader isrp = new InputStreamReader(isp, "UTF-8");
                    BufferedReader br = new BufferedReader(isrp);
                    String linea2;
                    while ((linea2 = br.readLine()) != null) {
                        // Mostrar la salida del proceso "nslookup"
                        System.out.println(linea2);
                    }
                } catch (UnsupportedEncodingException e) {
                    System.out.println(e.getMessage());
                }
            }
                try {
                    // Esperar a que el proceso hijo termine
                    p.waitFor();
                    System.out.println("Proceso hijo termino correctamente");
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());;
                }

        }catch (IOException e){
            System.out.println("Error: E/S");
            System.out.println(e.getMessage());
        }
    }
}



