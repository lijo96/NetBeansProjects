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
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dam213
 */
public class Client extends Subject implements Runnable {

    Info info;
    Socket socketCliente;
    String frase;
    String msg;
    public boolean envio;

    byte[] mensaje;
    InputStream flujoLectura;
    DataInputStream flujoDI;

    public Client(Socket socketCliente, String frase) {
        this.socketCliente = socketCliente;
        this.frase = frase;

    }

    @Override
    public void run() {
        OutputStream flujoSalida;
        DataOutputStream flujo;

        try {
            flujoSalida = socketCliente.getOutputStream();
            flujo = new DataOutputStream(flujoSalida);
            flujo.writeInt(frase.length());
            flujo.writeBytes(frase);

            info = new Info(socketCliente.getLocalAddress().getHostAddress(),
                    socketCliente.getLocalPort(),
                    socketCliente.getLocalAddress().getCanonicalHostName(),
                    socketCliente.getLocalAddress().getHostName(),
                    socketCliente.getInetAddress().getHostAddress(),
                    socketCliente.getPort(),
                    socketCliente.getInetAddress().getCanonicalHostName(),
                    socketCliente.getInetAddress().getHostName(),
                    new Date());
            envio = true;
            notifyObservers();

            //Recepción del mensaje
            flujoLectura = socketCliente.getInputStream();
            flujoDI = new DataInputStream(flujoLectura);
            int i = flujoDI.readInt();
            mensaje = new byte[i];
            flujoDI.readFully(mensaje);
            //Mostrar recibido junto la fecha/hora de recepción
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(new String(mensaje) + " Fecha/hora de recepción: " + dateFormat.format(date));
            msg = new String(mensaje) + "-19LAC Date/time of reception: " + dateFormat.format(date);

            socketCliente.close();
            envio = false;
            notifyObservers();
        } catch (UnknownHostException e) {
            System.out.println("Host reference not solved");
        } catch (IOException e) {
            System.out.println("Communication error");
        } catch (SecurityException e) {
            System.out.println("Communication not allowed");
        }
    }

}
