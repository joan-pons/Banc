/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.menu;

/**
 *
 * @author antonio
 */
public class MenuTarjeta {

    public static void altaTarjeta() {

    }

    public static void eliminarTarjeta() {

    }

    public static void pagar() {

    }

    public static void ingresarDebito() {

    }

    public static void verMovimientos() {

    }

    public static void main(String[] args) {
        boolean flag=true;
        do {
            System.out.println("Opcion 1: Dar de alta una tarjeta.");
            System.out.println("Opcion 2: Eliminar una tarjeta.");
            System.out.println("Opcion 3: Realizar un pago.");
            System.out.println("Opcion 4: Ingresar (sólo débito).");
            System.out.println("Opcion 5: Ver movimientos tarjeta.");
            System.out.println("Opcion 6: Salir.");
            GestionaMenu.llegirSencer("Introduce la opción: ");         
            
        }while(flag);
    }

}
