/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.conexion.Conexion;
import static es.bancodehierro.banco.menu.GestionaMenu.gestionarMenu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillem Arrom, Guillem Rotger, Pedro Lladó, François
 */
public abstract class Principal {
    private static Connection conexio = Conexion.conectar();
    private static final int MENU_PRONCIPAL_PREFIX = 69000;
    private static final int MENU_PRINCIPAL_CC = 69000;
    private static final int MENU_PRINCIPAL_PRESTAMO = 69001;
    private static final int MENU_PRINCIPAL_TARJETA = 69002;
    private static final int MENU_PRINCIPAL_SUCURSAL = 69003;
    private static final int MENU_PRINCIPAL_SALIR = 69004;
    
    
    public static void menuPrincipal() throws SQLException{
        String[] opciones = {"Cunta corriente", "Prestamo", "Tarjeta","Sucursal","Salir"};
        boolean menu = true;
        do {
            int op = gestionarMenu("Menu Principal", opciones, "Elige una opción", MENU_PRONCIPAL_PREFIX);
            switch (op) {
                case MENU_PRINCIPAL_CC:
                    MenuCuentaCorriente.menuCC();
                    break;

                case MENU_PRINCIPAL_PRESTAMO:
                    MenuPrestamo.menuPres();
                    break;

                case MENU_PRINCIPAL_TARJETA:
                    //Aqui debe llamar al metodo principal del menu de tarjeta
                    break;
                case MENU_PRINCIPAL_SUCURSAL:
                    //Aqui debe llamar al metodo principal del menu de sucursal
                    MenuSucursal.menu();
                    break;
                case MENU_PRINCIPAL_SALIR:
                    Conexion.desconectar();
                    menu=false;
                    System.out.println("Sortint...");
                    break;
            }
        } while (menu);
    }
    public static void main(String[] args) throws SQLException {
        System.out.println("BIENVENIDO AL BANCO DE HIERRO");
        String dni = GestionaMenu.llegirCadena("inserta tu DNI");
        try(Statement st = conexio.createStatement()){
            ResultSet rs = st.executeQuery("SELECT CODIGO_TRABAJADOR FROM TRABAJADOR WHERE DNI_TRABAJADOR = "+dni);
            System.out.println(rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuPrincipal();
    }

}
