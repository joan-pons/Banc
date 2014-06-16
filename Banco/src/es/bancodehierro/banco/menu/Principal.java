/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.excepciones.ClienteException;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import static es.bancodehierro.banco.menu.GestionaMenu.gestionarMenu;
import java.sql.Connection;
import java.sql.SQLException;


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
    private static final int MENU_PRINCIPAL_EMPLEADO = 69003;
    private static final int MENU_PRINCIPAL_CLIENTE = 69004;
    private static final int MENU_PRINCIPAL_SUCURSAL = 69005;
    private static final int MENU_PRINCIPAL_SALIR = 69006;

    public static void menuPrincipal() throws SQLException, CuentaCorrienteException, ClienteException {
        String[] opciones = {"Cunta corriente", "Prestamo","Empleado", "Cliente",  "Tarjeta", "Sucursal", "Salir"};
        boolean menu = true;
        do {
            int op = gestionarMenu("Menu Principal", opciones, "Elige una opción", MENU_PRONCIPAL_PREFIX);
            switch (op) {
                case MENU_PRINCIPAL_CC:
                    MenuCuentaCorriente.menuCC();
                    break;

                case MENU_PRINCIPAL_PRESTAMO:
                    MenuPrestamo.menuPres(null);
                    break;
                case MENU_PRINCIPAL_EMPLEADO:
                    MenuEmpleado.menuEmpleado();
                    break;
                case MENU_PRINCIPAL_CLIENTE:
                    MenuCliente.menuClientes();
                    break;
                case MENU_PRINCIPAL_TARJETA:
                    MenuTarjeta.ejecutarMenu();
                    break;
                case MENU_PRINCIPAL_SUCURSAL:
                    //Aqui debe llamar al metodo principal del menu de sucursal
                    MenuSucursal.menu();
                    break;
                case MENU_PRINCIPAL_SALIR:
                    Conexion.desconectar();
                    menu = false;
                    System.out.println("Sortint...");
                    break;
            }
        } while (menu);
    }

    public static void main(String[] args) throws SQLException, CuentaCorrienteException, ClienteException {
        System.out.println("BIENVENIDO AL BANCO DE HIERRO");
        //Esto era para inciar sesion...
       // String dni = GestionaMenu.llegirCadena("inserta tu DNI");
       /* try (Statement st = conexio.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT CODIGO_TRABAJADOR FROM TRABAJADOR WHERE DNI_TRABAJADOR = " + dni);
            //System.out.println(rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        menuPrincipal();
    }

}
