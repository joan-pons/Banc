/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.menu;

import static es.bancodehierro.banco.menu.GestionaMenu.gestionarMenu;

/**
 *
 * @author Guillem Arrom, Guillem Rotger, Pedro Lladó, François
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("BIENVENIDO AL BANCO DE HIERRO");
        String dni = GestionaMenu.llegirCadena("inserta tu DNI");
        String[] opciones = {"1. Cunta corriente", "2.Opcion2", "3.Opcion3"};
        int op = gestionarMenu("Menu Principal", opciones, "Elige una opción", 0);
        switch (op){
            case 1:
                MenuCuentaCorriente.menuCC;
            break;
        }
    }
    
}
