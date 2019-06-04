/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouf1_19lac;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crist
 */
public class TimeCounter extends Observable implements Runnable {

    private Thread[] tt;
    private long duracion;
    public static boolean cambiarUnidades;
    private boolean inicio;




    TimeCounter(Thread[] tt) {
        this.tt = tt;
        this.inicio= false;
        this.duracion = 0;
    }

    public long getDuracion() {
        return duracion;
    }

    public boolean isCambiarUnidades() {
        return cambiarUnidades;
    }

    public void setCambiarUnidades(boolean cambiarUnidades) {
        this.cambiarUnidades = cambiarUnidades;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (!this.inicio) {
        }

        for (Thread t : tt) {
            t.start();
        }
        for (Thread t : tt) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeCounter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        long end = System.currentTimeMillis();
        this.duracion = ((end - start));
        this.setChanged();
        this.notifyObservers(this.duracion);
        this.clearChanged();

    }

}
