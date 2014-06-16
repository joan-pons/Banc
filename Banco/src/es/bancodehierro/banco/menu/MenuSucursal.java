/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.excepciones.SucursalException;
import es.bancodehierro.banco.sucursal.Sucursal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe que contiene lo referente a las acciones sobre sucursales se
 * ejecutaran en este menu.
 *
 * @author Guillem Arrom, Guillem Rotger, Pedro Lladó, François
 */
public abstract class MenuSucursal {

    private static final int MENU_SUCURSAL_PREFIX = 70000;
    private static final int MENU_SUCURSAL_CREAR = 70000;
    private static final int MENU_SUCURSAL_LISTAR = 70001;
    private static final int MENU_SUCURSAL_MODIFICAR = 70002;
    private static final int MENU_SUCURSAL_ELIMINAR = 70003;
    private static final int MENU_SUCURSAL_LISTARTOT = 70004;
    private static final int MENU_SUCURSAL_VOLVER = 70005;
          
    /**
     * Hace los pasos necesarios y pide la informacion necesaria para dar de
     * alta una sucursal y pasar la informacion a un metodo de banco que la
     * inserte en la bdd.
     *
     * @throws SQLException Cuando ha ocurrido un error inesperado de SQL
     */
    private static void crearSucursal() throws SQLException, SucursalException {
        Sucursal central;
        String poblacio = GestionaMenu.llegirCadena("Mete poblacion ");
        String direccio = GestionaMenu.llegirCadena("Mete direccion ");
        String telefono = GestionaMenu.llegirCadena("Inserte telefono ");
        int codiPostal;
        while (true) {
            codiPostal = GestionaMenu.llegirSencer("Mete el codigo postal (xxxxx)");
            if (codiPostal < 99999) {
                break;
            }
            System.out.println("Codigo postal incorrecto (xxxxx");
        }
        boolean flagCentral = true;
        do {
            if (GestionaMenu.menuSiNo("", "Tiene central?")) {
                int codiSuc = GestionaMenu.llegirSencer("Cual es el codigo de sucursal?");

                central = Banco.devuelveSucursal(codiSuc);

            } else {
                flagCentral = false;
                central = null;
            }
        } while (flagCentral);

        Sucursal sucursal = new Sucursal(poblacio, direccio, 0, codiPostal, central, telefono);
        try {
            System.out.println("El codigo de la sucursal inserida ha sido " + Banco.insertaSucursal(sucursal));
        } catch (SucursalException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * Te pide el codigo de sucursal y te devuelve la sucursal del codigo que
     * has pedido
     *
     * @return La sucursal solicitada
     * @throws SQLException Cuando la sentencia SQL ha fallado inesperadamente
     */
    private static Sucursal seleccionaSucrusal() throws SQLException {
        Sucursal sucursal;
        boolean flag = true;
        do {
            int codiSuc = GestionaMenu.llegirSencer("Cual es el codigo de sucursal?");
            try {
                sucursal = Banco.devuelveSucursal(codiSuc);
                flag = false;
            } catch (SucursalException ex) {
                sucursal = null;
                flag = true;
                System.out.println("Sucursal no encontrada");
            }
        } while (flag);
        return sucursal;
    }

    /**
     * Enseña toda la informacion de la sucursal
     * 
     * @param sucursal El objeto sucursal de donde cogera la informacion
     * @throws SucursalException Si la sucursal es null
     */
    private static void mostrarSucursal(Sucursal sucursal) throws SucursalException {
        if (sucursal != null) {
            System.out.println("El codigo es: " + sucursal.getCodi());
            System.out.println("---");
            System.out.println("La direccion es: " + sucursal.getDireccio() + " poblacion " + sucursal.getPoblacio() + " CP: " + sucursal.getCodiPostal());
            System.out.println("---");
            if (sucursal.getCentral() != null) {
                System.out.println("La central de la sucursal tiene el codigo " + sucursal.getCentral().getCodi());
            } else {
                System.out.println("No tiene central");
            }
            System.out.println("---");
        } else {
            throw new SucursalException("La sucursal que se ha querido mostrar fue null");
        }

    }

    /**
     * Pide todo lo necesario para modificar sucursal y la modifica
     *  
     * @throws SucursalException Cuando hay errores con sucursales
     * @throws SQLException Cuando hay errores SQL
     */
    private static void modificarSucursal() throws SucursalException, SQLException {
        Sucursal sucursal;
        int codiSuc = GestionaMenu.llegirSencer("Cual es el codigo de sucursal que quieres modificar?");
        sucursal = Banco.devuelveSucursal(codiSuc);
        System.out.println("Tu sucursal es");
        mostrarSucursal(sucursal);
        boolean seguir = true;
        String[] menu = {"Modificar poblacion", " Modificar direccion", "Modificar codigo postal", "Modificar central", "Modificar telefono ", "Nada mas"};
        while (seguir) {
            switch (GestionaMenu.gestionarMenu("Que quieres modificar?", menu, "", 0)) {
                case 0:
                    String poblacion = GestionaMenu.llegirCadena("Inserta poblacion: ");
                    sucursal.setPoblacio(poblacion);
                    break;
                case 1:
                    String direccion = GestionaMenu.llegirCadena("Inserta direccion: ");
                    sucursal.setDireccio(direccion);
                    break;
                case 2:
                    int cp = GestionaMenu.llegirSencer("Inserte el nuevo CP: ");
                    sucursal.setCodiPostal(cp);
                    break;
                case 3:
                    Sucursal central = seleccionaSucrusal();
                    sucursal.setCentral(central);
                    break;
                case 4:
                    String telefono = GestionaMenu.llegirCadena("Inserte telefono");
                    sucursal.setTelefono(telefono);
                    break;
                case 5:
                    seguir = false;
                    break;
            }
        }
        Banco.modificarSucursal(sucursal);
    }

    /**
     *
     * Pide que sucursal eliminar y la elimina
     * 
     * @throws SucursalException Si no existe la sucursal
     * @throws SQLException Si hay un error de bdd
     */
    private static void eliminarSucursal() throws SucursalException, SQLException {
        Sucursal sucursal;
        int codiSuc = GestionaMenu.llegirSencer("Cual es el codigo de sucursal que quieres borrar?");
        sucursal = Banco.devuelveSucursal(codiSuc);
        if (GestionaMenu.menuSiNo("", "Seguro que quereis borrar la sucursal " + codiSuc)) {
            if (Banco.eliminarSucursal(sucursal)) {
                System.out.println("Sucursal borrada");
            } else {
                throw new SucursalException("Error al borrar la sucursal.");
            }
        } else {
            System.out.println("cancelant borrada...");
        }

    }

    /**
     * Genera el menu principal
     */
    public static void menu() {
        try {
            String[] menu = {"Crear sucursal", "listar sucursal", "Modificar sucursal", "Eliminar sucursal", "Mostrar todas", "Atrás..."};
            switch (GestionaMenu.gestionarMenu("Menu sucursal", menu, "", MENU_SUCURSAL_PREFIX)) {
                case MENU_SUCURSAL_CREAR:
                    crearSucursal();
                    break;
                case MENU_SUCURSAL_LISTAR:
                    Sucursal sucursal;
                    sucursal = seleccionaSucrusal();
                    mostrarSucursal(sucursal);

                    break;
                case MENU_SUCURSAL_MODIFICAR:

                    modificarSucursal();

                    break;
                case MENU_SUCURSAL_ELIMINAR:
                    eliminarSucursal();
                    break;
                case MENU_SUCURSAL_LISTARTOT:
                    mostrarTodas();
                    break;
                case MENU_SUCURSAL_VOLVER:
                    break;
                default:
                    break;
            }
        } catch (SucursalException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    /**
     *
     * Muestra todas las sucursales de la bdd
     * @throws SQLException cuando hay un error inesperado de bdd
     */
    private static void mostrarTodas() throws SQLException {
        Connection conexion = Conexion.conectar();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(CODIGO_SUCURSAL) FROM SUCURSAL");
        rs.next();
        int maxSuc = rs.getInt(1);
        st.close();
        rs.close();
        Sucursal sucursal;
        if (maxSuc != 0) {
            for (int index = 1; index <= maxSuc; index++) {
                boolean existente = true;
                try {
                    existente = Banco.comprobarSucursal(index);
                } catch (SucursalException ex) {
                    existente = false;
                }
                if (existente) {
                    try {
                        sucursal = Banco.devuelveSucursal(index);
                        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||");
                        mostrarSucursal(sucursal);
                        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||");
                    } catch (SucursalException ex) {
                        System.out.println("Se ha borrado una sucursal mientras se estaba intentando listar.");
                    }
                }
            }
        } else {
            System.out.println("No hay ninguna sucursal!4");
        }

    }

}
