/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ev;


import java.util.ArrayList;

/**
 *
 * @author dam213
 */
public abstract class Subject {
    
    private ArrayList<Observer> registeredObservers;

    public Subject() {
        this.registeredObservers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer) {
        this.registeredObservers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        this.registeredObservers.remove(observer);
    }

    protected void notifyObservers() {
        for (Observer observer : registeredObservers) {
            observer.update(this);
        }
    }
}
