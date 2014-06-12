/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.central.Banco;
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
        Sucursal central;
        String poblacio = GestionaMenu.llegirCadena("Mete poblacio");
        String direccio = GestionaMenu.llegirCadena("Mete direccio");
        int codiPostal = GestionaMenu.llegirSencer("Mete el codi postal");
        if(GestionaMenu.menuSiNo(null,"Es central?" )){
            int codiSuc = GestionaMenu.llegirSencer("Cual es el codigo de sucursal?");
            central = Banco.devuelveSucursal(codiSuc);
        }else{
            central = null;
        }
        Sucursal sucursal = new Sucursal(poblacio, direccio, 0, codiPostal, central);
        Banco.insertaSucursal(sucursal);
    }

    private static void llistarSucursal() {
        Sucursal sucursal;
        int codiSuc = GestionaMenu.llegirSencer("Cual es el codigo de sucursal?");
        sucursal = Banco.devuelveSucursal(codiSuc);
        System.out.println("El codigo es: " + sucursal.getCodi());
        System.out.println("---");        
        System.out.println("La direccion es: " + sucursal.getDireccio() + " poblacion " + sucursal.getPoblacio() + " CP: "+sucursal.getCodiPostal());
        System.out.println("---");
        if(sucursal.getCentral() != null){
            System.out.println("La central de la sucursal tiene el codigo " + sucursal.getCentral().getCodi());
        }else{
            System.out.println("No tiene central");
        }
        System.out.println("---");   
    }

    private static void modificarSucursal() {

    }

    private static void eliminarSucursal() {
        Sucursal sucursal;
        int codiSuc = GestionaMenu.llegirSencer("Cual es el codigo de sucursal que quieres borrar?");
        sucursal = Banco.devuelveSucursal(codiSuc);
        System.out.println("Seguro que quereis borrar la sucursal " + codiSuc);
        if(Banco.eliminarSucursal(sucursal)){
            System.out.println("Sucursal borrada");
        }else{
            System.out.println("Error al borrar la sucursal.");
        }

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
