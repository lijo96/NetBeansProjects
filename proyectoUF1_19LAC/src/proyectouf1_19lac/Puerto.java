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
public class Puerto {

    private int idBarco, numPasajeros;
    private String fecha;

    public Puerto(int idBarco, int numPasajeros, String fecha) {
        this.idBarco = idBarco;
        this.numPasajeros = numPasajeros;
        this.fecha = fecha;
    }

    public int getIdBarco() {
        return idBarco;
    }

    public void setIdBarco(int idBarco) {
        this.idBarco = idBarco;
    }

    public int getNumPasajeros() {
        return numPasajeros;
    }

    public void setNumPasajeros(int numPasajeros) {
        this.numPasajeros = numPasajeros;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    

}
