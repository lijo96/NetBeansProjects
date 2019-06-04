/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ev;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author dam213
 */
public class ServerTCP implements Runnable {

    private final int poolSize = 2;

    Observer observador;
    
    boolean fin;
    Info info;
    private final ServerSocket socketServidor;
    String msg;
    public final ExecutorService pool;

    public ServerTCP(ServerSocket socketServidor, Observer contenedor) {
        this.socketServidor = socketServidor;
        fin = false;
        this.pool = Executors.newFixedThreadPool(poolSize);
        this.observador = contenedor;

    }

    @Override
    public void run() {
        try {
            for (;;) {
                HandlerRecpMsj handler = new HandlerRecpMsj(socketServidor.accept());
                handler.registerObserver(observador);
                pool.execute(handler);
            }
            
        } catch (IOException e) {
            System.out.println("Communication error");
            System.exit(0);
        } catch (SecurityException e) {
            System.out.println("Communication not allowed due to security reasons");
            System.exit(0);
        }
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

}
