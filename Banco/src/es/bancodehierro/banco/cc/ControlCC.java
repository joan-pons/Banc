/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.cc;

import es.bancodehierro.banco.menu.GestionaMenu;

/**
 *
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel
 * Angel Cànaves
 */
public class ControlCC {
    
    public static String ControlIBAN(){
        String iban = null;
        boolean op = true;
        while(op){
            if (iban.length() != 4)
                iban = GestionaMenu.llegirCadena("Introduce un IBAN: ");
            else
                op = false;
        }
        return iban;
    }
    
    public static String ControlOficina(){
        String oficina = null;
        boolean op = true;
        while(op){
            if (oficina.length() != 4)
                oficina = GestionaMenu.llegirCadena("Introduce una Oficina: ");
            else
                op = false;
        }
        return oficina;
    }
    
    public static String ControlDc(){
        String dc = null;
        boolean op = true;
        while(op){
            if (dc.length() != 2)
                dc = GestionaMenu.llegirCadena("Introduce un Dc: ");
            else
                op = false;
        }
        return dc;
    }
    
    public static String ControlCC(){
        String cc = null;
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
