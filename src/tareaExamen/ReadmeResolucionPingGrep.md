# Tarea Examen
---

1 Implementar el mismo ejercicio llamándolo ResolucionPingGrep.java, sin utilizar un
   fichero, sino mediante una pipe entre los dos procesos. Para ello:
* Crearemos ambos procesos al mismo tiempo después de crear los
   correspondientes ProcessBuilder llamados **pbPing** y **pbGrep**. ¿Dará igual el orden
   de ejecución?, ¿por qué?
* El comando grep se quedará residente en memoria. Recordar el ejercicio
   `nslookup`.
* Creamos dos flujos entre el proceso padre y el proceso **procPing**.
* El `procPing` dará un flujo de entrada al padre donde deberá leer línea a
   línea y mandarlo a un flujo de salida que el padre creará hacia el proceso
   **procGrep**, por tanto:
    * `inDesdePing = new Scanner(procPing.getInputStream());`
    * `outParaGrep = new PrintWriter(procGrep.getOutputStream());`
* Leeremos mientras haya líneas desde el `ping` al padre y escribiremos utilizando
   el método println del objeto **outParaGrep**. ¿Por qué no utilizamos el método
   writer y si el método println? Contestar también a dicha pregunta.
* Después cerraremos el flujo de salida entre el padre y el **procGrep**.
* Ahora nos toca leer la ejecución del comando **procGrep** para verla en el padre,
   por tanto:
    * Crearemos un flujo de entrada llamado inDesdeGrep de la forma
        * `inDesdeGrep = new Scanner(procGrep.getInputStream());`
* Leeremos del flujo de entrada hasta fin de línea.

___

Creamos la clase `ResolucionPingGrep`:
___

```java
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
```

Mostramos la ejecución del programa:
___

![](../recursos/imgPinGrep1.png)

---

# Documentación del Código: ResolucionPingGrep

Este documento describe el funcionamiento del código Java titulado "ResolucionPingGrep," que utiliza dos procesos para 
realizar un comando `ping` a un host y filtrar las estadísticas resultantes utilizando el comando `grep`.

## Resumen

El código ejecuta dos procesos, uno para el comando `ping` y otro para el comando `grep`. Estos procesos están 
interconectados a través de una tubería (pipe) para transferir la salida del `ping` al `grep`. El código procesa los
datos generados por el `ping` y muestra las estadísticas de respuesta utilizando el `grep`.

## Descripción Detallada

1. **Configuración de los Procesos**
    - Se crean dos objetos `ProcessBuilder`: `pbPing` y `pbGrep`. `pbPing` se configura para ejecutar el comando `ping`
      en el host "www.google.com" con la opción "-c2". `pbGrep` se configura para ejecutar el comando `grep` con el 
      patrón "rtt".

2. **Ejecución de los Procesos**
    - Se inician los procesos `procPing` y `procGrep` utilizando los `ProcessBuilder` correspondientes.
    - `procPing` ejecuta el comando `ping` y comienza a generar datos.
    - `procGrep` ejecuta el comando `grep` y espera a recibir datos.

3. **Transferencia de Datos**
    - Se crea un objeto `Scanner` llamado `inDesdePing` que lee la salida del proceso `procPing`. Se utiliza para leer 
      línea por línea los resultados generados por el `ping`.
    - Se crea un objeto `PrintWriter` llamado `outParaGrep` que envía los datos del proceso `procPing` al proceso 
     `procGrep` a través de la tubería (pipe).
    - Se utiliza un bucle `while` para leer y enviar cada línea generada por el `ping` al `grep`.
      En este código, el objetivo principal es leer líneas completas generadas por el proceso procPing y enviar esas 
      líneas al proceso procGrep. Usar println es conveniente en este caso porque agrega automáticamente un carácter 
      de nueva línea (\n) al final de cada línea que se envía. Esto es útil para garantizar que cada línea se envíe 
      de manera independiente y se pueda procesar correctamente en el proceso grep.

4. **Finalización del Proceso Ping**
    - Se verifica si el proceso `procPing` finalizó correctamente utilizando el método `waitFor()`. Se muestra un 
      mensaje apropiado en función del resultado.

5. **Cierre de Flujos y Lectura del Proceso Grep**
    - Se cierran los flujos `inDesdePing` y `outParaGrep`.
    - Se crea un objeto `Scanner` llamado `inDesdeGrep` para leer la salida del proceso `procGrep`.
    - Se muestra el resultado del `grep` con un mensaje "Mostramos la búsqueda del Grep."

6. **Finalización del Proceso Grep**
    - Se verifica si el proceso `procGrep` finalizó correctamente utilizando `waitFor()` y se muestra un mensaje 
      adecuado en función del resultado.

## Consideraciones
- El código captura y maneja las excepciones relacionadas con E/S (`IOException`) y la espera del proceso 
  (`InterruptedException`).

## Conclusiones
Este código demuestra cómo se pueden utilizar procesos en Java para ejecutar comandos de la línea de comandos de forma
simultánea y cómo se puede conectar la salida de un proceso con la entrada de otro proceso utilizando una tubería. 
En este caso, se muestra un ejemplo específico en el que se realiza un `ping` a un host y se filtran las estadísticas 
utilizando `grep`.

___

[Repositorio](https://github.com/johnlopez0505/procesos.git)

___



