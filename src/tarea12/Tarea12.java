package tarea12;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Tarea12 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso:  <ruta> <extensión> <tiempo en segundos>");
            System.exit(1);
        }

        String ruta = args[0];
        String extension = args[1];
        int tiempoEspera = Integer.parseInt(args[2]) * 1000; // Convierte segundos a milisegundos

        ProcessBuilder processBuilder = new ProcessBuilder("find", ruta, "-name", "*." + extension);
        System.out.println("Como proceso actual, voy a ejecutar un comando find por un tiempo" +
                "mediante otro proceso\n");
        processBuilder.inheritIO(); //El proceso actual y el nuevo, utilizan las mismas E/S
        processBuilder.redirectErrorStream(true);// para que no salgan las salidas y errores mezclados
        try {
            Process p = processBuilder.start();//Lanzamos el nuevo proceso
            if (!p.waitFor(tiempoEspera, TimeUnit.MILLISECONDS)) {
                p.destroy();//matamos al proceso hijo.
                /*
                    whaitfor() devuelve true si el nuevo proceso
                    ha terminado el mismo antes del tiempo indicado
                */
                System.out.println("El proceso lanzado no ha finalizado a tiempo su ejecucion\n");
            }else
                System.out.println("El proceso lanzado ha finalizado a tiempo su ejecucion");
        } catch (IOException  e) {
            System.out.println("Error al intentar lanzar un nuevo proceso. pedimos informacion detallada\n");
            e.printStackTrace();
            System.exit(1);//error en el proceso
        }catch(InterruptedException e){
            System.out.println("El proceso ha sido interrumpido mediante interrumpción\n");
            System.exit(2);
        }
    }
}

