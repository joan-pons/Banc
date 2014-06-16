/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.cc;

import es.bancodehierro.banco.menu.GestionaMenu;

/**
 * Clase con métodos estáticos para hacer el control de los datos introducido para el número de la cuenta corriente.
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel
 * Angel Cànaves
 */
public class ControlCC {
    /**
     * Método para controlar los datos introducidos en el IBAN de la cuenta corriente.
     * @return Un string con el contenido del IBAN.
     */
    public static String controlIBAN(){
        String iban = GestionaMenu.llegirCadena("Introduce un IBAN: ");
        boolean op = true;
        while(op){
            if (iban.length() != 2)
                iban = GestionaMenu.llegirCadena("Introduce un IBAN: ");
            else
                op = false;
        }
        return iban;
    }
    
    /**
     * Método para controlar los datos introducidos en la oficina de la cuenta corriente.
     * @return Un string con el contenido de la oficina.
     */
    public static String controlOficina(){
        String oficina = GestionaMenu.llegirCadena("Introduce una Oficina: ");
        boolean op = true;
        while(op){
            if (oficina.length() != 4)
                oficina = GestionaMenu.llegirCadena("Introduce una Oficina: ");
            else
                op = false;
        }
        return oficina;
    }
    
    /**
     * Método para controlar los datos introducidos en el dc de la cuenta conrriente.
     * @return Un string con el contenido del dc.
     */
    public static String controlDc(){
        String dc = GestionaMenu.llegirCadena("Introduce un Dc: ");
        boolean op = true;
        while(op){
            if (dc.length() != 2)
                dc = GestionaMenu.llegirCadena("Introduce un Dc: ");
            else
                op = false;
        }
        return dc;
    }
    
    /**
     * Método para controlar los datos introducidos en la cuenta de la cuenta corriente.
     * @return Un string con el contenido del cc de la ccuenta corriente.
     */
    public static String controlCC(){
        String cc = GestionaMenu.llegirCadena("Introduce un Numero de CC: ");
        boolean op = true;
        while(op){
            if (cc.length() != 10)
                cc = GestionaMenu.llegirCadena("Introduce un Numero de CC: ");
            else
                op = false;
        }
        return cc;
    }
}
