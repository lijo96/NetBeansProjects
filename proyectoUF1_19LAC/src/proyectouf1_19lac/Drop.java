/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouf1_19lac;

import java.util.Observable;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class Drop {

    private ArrayList<Puerto> messagesBuffer;
    private Puerto message;

    private boolean empty = true;

    Drop() {
        this.messagesBuffer = new ArrayList<Puerto>();
        this.empty = true;
    }

    public synchronized Puerto take() {
        // Wait until message is available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("  The InterruptedException Take a consumidor: " + this.messagesBuffer != null ? "Not null" : "Null");
                //Neccessary for the case in which to consumers are interrupted in a row
                if (empty) {
                    empty = !empty;
                }
            }
        }
        // Toggle status.
        empty = true;
        // Notify producer that status has changed.
        notifyAll();
         Puerto message = null;
        if (this.messagesBuffer.size() > 0) {
            message = this.messagesBuffer.get(0);
            this.messagesBuffer.remove(0);
        }
        return message;
    }

   public synchronized void put(Puerto message) {
        // Wait until message has been retrieved.
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("The InterruptedException Put a producer: " + message);
                //Neccessary for the case in which to producers are interrupted in a row
                if (!empty) {
                    empty = !empty;
                }
            }
        }
        // Toggle status.
        empty = false;
        // Store message.
        if (message != null) {
            this.messagesBuffer.add(message);
        }
        // Notify consumer that status has changed.
        notifyAll();
    }
}