/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ev;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dam213
 */
public class HandlerRecpMsj extends Subject implements Runnable {

    private final Socket comunicaCliente;
    byte[] mensaje;
    InputStream flujoLectura;
    DataInputStream flujo;

    OutputStream flujoSalida;
    DataOutputStream flujoDO;

    Info info;
    String msg;
//    boolean actualizarTabla;

    HandlerRecpMsj(Socket comunicaCliente) {
        this.comunicaCliente = comunicaCliente;
    }

    @Override
    public void run() {
        try {
            flujoLectura = comunicaCliente.getInputStream();
            flujo = new DataInputStream(flujoLectura);
            int i = flujo.readInt();
            mensaje = new byte[i];
            flujo.readFully(mensaje);
            //Se muestra también la fecha/hora de recepción
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(new String(mensaje) + " Fecha/hora de recepción: " + dateFormat.format(date));

            info = new Info(comunicaCliente.getInetAddress().getHostAddress(),
                    comunicaCliente.getPort(),
                    comunicaCliente.getInetAddress().getCanonicalHostName(),
                    comunicaCliente.getInetAddress().getHostName(),
                    comunicaCliente.getLocalAddress().getHostAddress(),
                    comunicaCliente.getLocalPort(),
                    comunicaCliente.getLocalAddress().getCanonicalHostName(),
                    comunicaCliente.getLocalAddress().getHostName(),
                    new Date());
            msg = new String(mensaje) + " Date/time of reception: " + dateFormat.format(date);
            notifyObservers();

            Thread.sleep(5000);

            flujoSalida = comunicaCliente.getOutputStream();
            flujoDO = new DataOutputStream(flujoSalida);
            String frase = "Received!";
            flujoDO.writeInt(frase.length());
            flujoDO.writeBytes(frase);
            comunicaCliente.close();
        } catch (IOException e) {
            System.out.println("Communication Error");
        } catch (SecurityException e) {
            System.out.println("Communication not allowed");
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}


