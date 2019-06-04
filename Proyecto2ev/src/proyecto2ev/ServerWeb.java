/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ev;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam213
 */
public class ServerWeb implements Runnable {

    public void run() {
        for (;;) {
            Socket comunicaCliente;
            ServerSocket socketServidor;
            OutputStream flujoSalida;
            DataOutputStream flujo;
            try {
                socketServidor = new ServerSocket(8091);
                comunicaCliente = socketServidor.accept();
                flujoSalida = comunicaCliente.getOutputStream();
                flujo = new DataOutputStream(flujoSalida);

                String paginaWeb = "HTTP/1.0 200 OK\r\n Connection: close\r\nServer: ServidorWebModuloPSP v0\r\nContent-Type: text/html\r\n\r\n"
                        + obtenerArchivoHTML("web.html");

                flujo.writeBytes(paginaWeb);
                flujo.flush();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerWeb.class.getName()).log(Level.SEVERE, null, ex);
                }
                comunicaCliente.close();
                socketServidor.close();
            } catch (IOException e) {
            } catch (SecurityException e) {
            }
        }
    }

    private String obtenerArchivoHTML(String nombre) {
        String resultado = "";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(nombre));
            try {
                String linea = in.readLine();
                while (linea != null) {
                    resultado += linea;
                    linea = in.readLine();
                }
                in.close();
            } catch (IOException e) {
                System.err.println("IOException occurred in server.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file.");
        }
        return resultado;
    }
}
