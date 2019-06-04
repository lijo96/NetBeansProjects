/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouf1_19lac;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author crist
 */
public class Recursos {

    private static final String[] fechas_list = {"03-10-2016", "17-04-2017", "08-02-2018","23-05-2018", "18-02-2018","07-07-2017"};
    private static final String[] nombres_barcos_list = {"A Coruña-Santander", "Vigo-Barcelona", "Algeciras-Bilbao"};

    public static List<Puerto> list_puerto = new ArrayList();

    public static void addJornada(Puerto jornada) {
        list_puerto.add(jornada);
    }

    private static int start = 1;

    //volatile
    static volatile List<Barco> listBarco = new ArrayList();

    public static int getStart() {
        return start;
    }

    public static void setStart(int start) {
        Recursos.start = start;
    }

    public synchronized static Puerto getPuerto() {

        Puerto estacion = null;
        Random random = new Random();

        if (start < 10) {
            estacion = new Puerto(random.nextInt(3) + 1, random.nextInt(400) + 1, fechas_list[random.nextInt(6)]);
            start++;
        }
        return estacion;
    }

    private synchronized static boolean comprobarBarco(int id) {

        for (int i = 0; i < listBarco.size(); i++) {
            if (listBarco.get(i).getIdBarco() == id) {
                return true;
            } else {
            }
        }
        return false;
    }

    static synchronized void recogerPasajeros(Puerto est) {
        int id = est.getIdBarco();
        int pasajeros = est.getNumPasajeros();

        boolean existe = comprobarBarco(id);

        if (!existe) {
            String nombre = recogerNombre();
            Barco barco = new Barco(id, pasajeros, nombre);
            listBarco.add(barco);
        } else {
            for (int i = 0; i < listBarco.size(); i++) {
                if (listBarco.get(i).getIdBarco() == id) {
                    listBarco.get(i).setNumPasajeros(pasajeros);
                }
            }
        }

    }

    private static String recogerNombre() {
        Random random = new Random();
        int posicion = 0;
        String nombre;

        posicion = random.nextInt(3);
        nombre = nombres_barcos_list[posicion];

        return nombre;
    }
}
