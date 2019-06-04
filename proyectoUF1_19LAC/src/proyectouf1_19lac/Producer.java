/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouf1_19lac;

/**
 *
 * @author crist
 */
import java.util.Observable;
import java.util.Random;

public class Producer extends Observable implements Runnable {

    private Drop drop;
    public String name;
    public static boolean fin;

    public Producer(Drop drop, String name) {
        this.drop = drop;
        this.name = name;
        Producer.fin = false;
    }

    public void run() {
        Random random = new Random();
        Puerto elemento = Recursos.getPuerto();
        while (elemento != null) {
            Recursos.addJornada(elemento);
            drop.put(elemento);
            this.setChanged();
            if (TimeCounter.cambiarUnidades) {
                this.notifyObservers("19LAC -" + name + "- The boat with ID=" + elemento.getIdBarco() + " has been filled"
                        + " with " + (elemento.getNumPasajeros()/10) + " people in tens, the day " + elemento.getFecha());

            } else {
                this.notifyObservers("19LAC -" + name + "- The boat with ID=" + elemento.getIdBarco() + " has been filled"
                        + " with " + elemento.getNumPasajeros() + " people in tens, the day " + elemento.getFecha());
            }
            this.clearChanged();
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
            }
            elemento = Recursos.getPuerto();
        }
        this.drop.put(elemento);
        System.out.println("19LAC Final producer " + this.name);
        this.setChanged();
        this.notifyObservers(null);
        this.clearChanged();
    }
}
