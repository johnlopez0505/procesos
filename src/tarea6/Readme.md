# Actividad 6

1. Queremos realizar un programa que haga el comando ls -l > "un_fichero.txt"
   Para ello, crear un fichero con File al que pasarás su nombre como argumento a nuestro main.
   Utilizar dos buffers, uno para la redirección del flujo de entrada al padre (lo que le devuelve el proceso hijo al padre) y otro para la escritura en un fichero desde el padre a partir del FileWriter.
   Para probar nuestro ejemplo, debemos poner algo así ./tarea6 fichero2.txt

### Creamos la clase *Tarea6*

```java
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
```


Ejecutamos nuestro programa: `Tarea6`   

Ejecutamos nuestro programa desde la consola con los siguientes comandos
necesita que le pasemos un argumento en este caso: *fichero.txt*

```shell
cd /home/john/Escritorio/procesos/src/      
javac tarea6/Tarea6.java              
java tarea6.Tarea6 fichero.txt

```

![img3](../recursos/img3.png)

Se ha creado nuestro fichero *'fichero.txt'*, lo abrimos y vemos que
tiene la información que le hemos pedido al proceso hijo.


![img4](../recursos/img4.png)