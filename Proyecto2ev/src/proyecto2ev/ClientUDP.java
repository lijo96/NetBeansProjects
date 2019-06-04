/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ev;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dam213
 */
public class ClientUDP extends Subject implements Runnable {

    String ultimaRecepcion, ultimaImg;
    private boolean continuar = true;

    @Override
    public void run() {
        try {
            MulticastSocket socket = new MulticastSocket(4446);
            InetAddress address = InetAddress.getByName("224.0.0.1");
            socket.joinGroup(address);

            DatagramPacket packet;

            // get a few quotes
            while (continuar) {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                ultimaImg = received;
                //Mostrar recibido junto la fecha/hora de recepción
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                ultimaRecepcion = " Fecha/hora de recepción: " + dateFormat.format(date);
                if (continuar) {
                    notifyObservers();
                }
            }

            socket.leaveGroup(address);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUltimaRecepcion() {
        return ultimaRecepcion;
    }

    public String getUltimaImg() {
        return ultimaImg;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

}
