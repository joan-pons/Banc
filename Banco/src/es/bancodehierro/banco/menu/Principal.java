/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.menu;

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
        
    }
    
}
