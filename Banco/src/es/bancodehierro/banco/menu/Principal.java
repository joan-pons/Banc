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
public abstract class Principal {

    private static final int MENU_PRONCIPAL_PREFIX = 69000;
    private static final int MENU_PRINCIPAL_CC = 69000;
    private static final int MENU_PRINCIPAL_PRESTAMO = 69001;
    private static final int MENU_PRINCIPAL_TARJETA = 69002;
    private static final int MENU_PRINCIPAL_SUCURSAL = 69003;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("BIENVENIDO AL BANCO DE HIERRO");
        String dni = GestionaMenu.llegirCadena("inserta tu DNI");
        String[] opciones = {"Cunta corriente", "Opcion2", "Opcion3"};
        int op = gestionarMenu("Menu Principal", opciones, "Elige una opción", MENU_PRONCIPAL_PREFIX);
        switch (op) {
            case MENU_PRINCIPAL_CC:
                //MenuCuentaCorriente.menuCC;
                break;

            case MENU_PRINCIPAL_PRESTAMO:
                //Aqui debe llamar al metodo principal del menu de prestamo
                break;

            case MENU_PRINCIPAL_TARJETA:
                //Aqui debe llamar al metodo principal del menu de tarjeta
                break;
            case MENU_PRINCIPAL_SUCURSAL:
                //Aqui debe llamar al metodo principal del menu de sucursal
                MenuSucursal.menu();
                break;
        }
    }

}
