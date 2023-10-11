package tarea5;

import java.io.IOException;

public class EjecutarComandoGedit {
    public static void main(String[] args) {
       abrirGenit();
    }
    public static void abrirGenit() {
        try {
            Process pGedit = Runtime.getRuntime().exec("gedit");
            System.out.println(pGedit);
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }
}
