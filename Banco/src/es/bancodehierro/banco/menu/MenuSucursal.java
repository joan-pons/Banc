/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.sucursal.Sucursal;

/**
 *
 * @author Guillem Arrom, Guillem Rotger, Pedro Lladó, François
 */
public abstract class MenuSucursal {

    private static final int MENU_SUCURSAL_PREFIX = 70000;
    private static final int MENU_SUCURSAL_CREAR = 70000;
    private static final int MENU_SUCURSAL_LISTAR = 70001;
    private static final int MENU_SUCURSAL_MODIFICAR = 70002;
    private static final int MENU_SUCURSAL_ELIMINAR = 70003;

    private static void crearSucursal() {
        String poblacio = GestionaMenu.llegirCadena("Introdueix poblacio");
        String direccio = GestionaMenu.llegirCadena("Introdueix direccio");
        int codiPostal = GestionaMenu.llegirSencer("Introdueix el codi postal");
        
        Sucursal sucursal = new Sucursal(poblacio, direccio, 0, codiPostal, null);
    }

    private static void llistarSucursal() {

    }

    private static void modificarSucursal() {

    }

    private static void eliminarSucursal() {

    }

    public static void menu() {

        String[] menu = {"Crear sucursal", "Llistar sucursal", "Modificar sucursal", "Eliminar sucursal"};
        switch (GestionaMenu.gestionarMenu("Menu sucursal", menu, null, MENU_SUCURSAL_PREFIX)) {
            case MENU_SUCURSAL_CREAR:
                crearSucursal();
                break;
            case MENU_SUCURSAL_LISTAR:
                llistarSucursal();
                break;
            case MENU_SUCURSAL_MODIFICAR:
                modificarSucursal();
                break;
            case MENU_SUCURSAL_ELIMINAR:
                eliminarSucursal();
                break;
            default:
                break;
        }

    }

}
