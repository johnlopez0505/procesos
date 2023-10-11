package tarea6;

import java.io.*;

public class Tarea6 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: ./tarea6 fichero.txt");
            System.exit(1);
        }

        String filename = args[0];

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ls", "-l");
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            FileWriter fileWriter = new FileWriter(filename);
            String line;
            while ((line = reader.readLine()) != null) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException  e) {
            e.getLocalizedMessage();
        }
    }
}
