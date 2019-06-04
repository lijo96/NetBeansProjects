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
public class Barco {

    private int idBarco, numPasajeros;
    private String nombreBarco;

    public Barco(int idBarco, int numTotPasajeros, String nombreBarco) {
        this.idBarco = idBarco;
        this.numPasajeros = numTotPasajeros;
        this.nombreBarco = nombreBarco;
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

    public void setNumPasajeros(int TotPasajeros) {
        this.numPasajeros += TotPasajeros;
    }

    public String getNombreBarco() {
        return nombreBarco;
    }

    public void setNombreBarco(String nombreBarco) {
        this.nombreBarco = nombreBarco;
    }
}
