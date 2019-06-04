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

public class Consumer extends Observable implements Runnable {

    private final Drop drop;
    public String name;

    public String getName() {
        return name;
    }

    public Consumer(Drop drop, String name) {
        this.drop = drop;
        this.name = name;
    }

    public void run() {
        Random random = new Random();
        Puerto puerto;
        for (puerto = drop.take(); puerto != null; puerto = drop.take()) {
            Recursos.recogerPasajeros(puerto);
            Barco barco = null;
            for (int i = 0; i < Recursos.listBarco.size(); i++) {
                if (puerto.getIdBarco() == Recursos.listBarco.get(i).getIdBarco()) {
                    barco = Recursos.listBarco.get(i);
                }
            }

            String barco_name = barco.getNombreBarco();
            this.setChanged();
            if (TimeCounter.cambiarUnidades) {
                this.notifyObservers("19LAC -" + name + "- The boat " + barco_name
                        + " (with ID=" + puerto.getIdBarco() + ") has been filled"
                        + " with " + (puerto.getNumPasajeros()/10) + " people in tens, the day " + puerto.getFecha());

            } else {
                this.notifyObservers("19LAC -" + name + "- The boat " + barco_name
                        + " (with ID=" + puerto.getIdBarco() + ") has been filled"
                        + " with " + puerto.getNumPasajeros() + " people the day " + puerto.getFecha());

            }
            this.clearChanged();
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
            }
        }
        System.out.println("19LAC Final consumer " + this.name);
        this.setChanged();
        this.notifyObservers(null);
        this.clearChanged();
    }

}
