/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam213
 */
public class ServerUDP extends Subject implements Runnable {

    String ultimaRecepcion, ultimaImg;
    ArrayList<String> imgs;
    boolean continuar;
    Random al;
    int index;
    
    private long FIVE_SECONDS = 5000;
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;

    public ServerUDP() {
        al = new Random();
        continuar = true;
       
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            in = new BufferedReader(new FileReader("banners.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file. Serving time instead.");
        }
        
        imgs= new ArrayList<>();
        
        while(moreQuotes){
            String img = getNextQuote();
            if (img != null)
                imgs.add(img);
        }
        
    }

    public void run() {
        while (continuar) {
            try {
                byte[] buf = new byte[256];

                // construct quote
                if(index>=imgs.size())
                    index = 0;
                String dString = imgs.get(index++);
                buf = dString.getBytes();

                // send it
                InetAddress group = InetAddress.getByName("224.0.0.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);
                ultimaImg = dString;
                //Mostrar recibido junto la fecha/hora de recepción
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                System.out.println(" Fecha/hora de envío: " + dateFormat.format(date));
                ultimaRecepcion = " Fecha/hora de recepción: " + dateFormat.format(date);
                notifyObservers();

                // sleep for a while
                try {
                    Thread.sleep(FIVE_SECONDS);
                } catch (InterruptedException e) {
                }
            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }

    protected String getNextQuote() {
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }
    
    public String getUltimaRecepcion() {
        return ultimaRecepcion;
    }

    public String getUltimaImg() {
        return ultimaImg;
    }
}
