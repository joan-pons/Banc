
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.enumeraciones.EnumCargo;
import es.bancodehierro.banco.excepciones.EmpleadoException;
import es.bancodehierro.banco.excepciones.SucursalException;
import es.bancodehierro.banco.menu.GestionaMenu;
import es.bancodehierro.banco.persona.Empleado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillem
 */
public class MenuEmpleado {

    private static final int MENU_EMPLEADO_PREFIX = 70000;
    private static final int MENU_EMPLEADO_CREAR = 70000;
    private static final int MENU_EMPLEADO_LISTAR = 70001;
    private static final int MENU_EMPLEADO_MODIFICAR = 70002;
    private static final int MENU_EMPLEADO_ELIMINAR = 70003;
    private static final int MENU_EMPLEADO_VOLVER = 70004;
    /**
     * Metodo que pide el cargo del trabajador. 
     * Le muestra al usuario los cargos para los trabajadores. Luego le pide que introducza
     * el cargo del nuevo empleado.
     * @return devuelve una enumeracion de Cargo
     */
    public static EnumCargo pideCargo() {
        String[] carregs = {"EMPLEADO", "DIRECTOR BANCO", "DIRECTOR SUCURSAL"};
        switch (GestionaMenu.gestionarMenu("Que cargo tiene?", carregs, "", 0)) {
            case 0:
                return EnumCargo.EMPLEADOSUCURSAL;
            case 1:
                return EnumCargo.DIRECTORBANCO;
            case 2:
                return EnumCargo.DIRECTORSUCURSAL;
            default:
                return null;
        }
    }
    /**
     * Método que pide todos los datos de un empleado y crea un objecto empleado con ellos.
     * Pide cada campo del empleado para así introducirlo en el nuevo objeto empleado. 
     * @throws EmpleadoException Devuelve esta excepcion si ya existe el empleado desde 
     * el metodo insertarEmpleado. 
     * @throws java.sql.SQLException Cuando hay un error inesperado de bdd
     * @throws es.bancodehierro.banco.excepciones.SucursalException Cuando la sucursal no ha sido encontrada
     * 
     */
    public static void insertarEmpleado() throws EmpleadoException, SQLException, SucursalException {
        String nombre = GestionaMenu.llegirCadena("Introduce el nombre: ");
        String apellido1 = GestionaMenu.llegirCadena("Introduce el primer apellido: ");
        String apellido2 = GestionaMenu.llegirCadena("Introduce el segundo appelido: ");
        EnumCargo cargo = pideCargo();
        String dni = GestionaMenu.llegirCadena("Introduce el DNI: ");
        String poblacio = GestionaMenu.llegirCadena("Introduce la poblacion: ");
        String direccion = GestionaMenu.llegirCadena("Introduce la direccion: ");
        String telefono = GestionaMenu.llegirCadena("Inserta telefono: ");
        int sucursal = GestionaMenu.llegirSencer("En que sucursal trabaja?");
        Banco.comprobarSucursal(sucursal);
        Date data = new Date();
        Empleado empleado = new Empleado(cargo, nombre, apellido1, apellido2, dni, poblacio, direccion, data, telefono, sucursal);
        Banco.insertarEmpleado(empleado);
        System.out.println("Empleado introducido");
    }
    /**
     * Pide el dni del empleado y lo borra de la base de datos.
     * Primero pide el dni del empleado que queremos borrar, luego comprueba si existe
     * algun empleado con ese dni.
     * @throws SQLException tira la excepción si la consulta a la base de datos no está
     * bien hecha.
     * @throws EmpleadoException Lanza la excepción si el empleado no existe 
     */
    public static void eliminarEmpleado() throws SQLException, EmpleadoException {
        String dniEmpleado = GestionaMenu.llegirCadena("Introduce el DNI: ");
        Banco.comprobarEmpleado(dniEmpleado);
        Boolean flag = GestionaMenu.menuSiNo("", "Seguro que quieres borrar el empleado con el dni " + dniEmpleado + "?");
        if (flag) {
            System.out.println("Borrando...");
            Banco.eliminarEmpleado(dniEmpleado);
        } else {
            System.out.println("Saliendo...");
        }

    }
    /**
     * Método que pide el DNI i podremos modificar al Empleado con ese DNI.
     * Pide el DNI del empleado. Luego muestra el empleado y nos pide con una 
     * serie de funciones que queremos modificar con ese empleado.
     * @throws SQLException La devuelve si la consulta no está bien introducida en la base de datos.
     * @throws EmpleadoException Lanzada si el empleado no existe
     */
    public static void modificarEmpleado() throws SQLException, EmpleadoException {
        Empleado empleado = seleccionaEmpleado();
        System.out.print("El empleado es: ");
        mostrarEmpleado(empleado);
        boolean seguir = true;
        String[] menu = {"Modificar nombre", "Modificar primer apellido", "Modificar segundo apellido", "Modificar direccion", "Modificar poblacion", "Modificar telefono", "Nada más"};
        while (seguir) {
            switch (GestionaMenu.gestionarMenu("Que quieres modificar?", menu, "Introduce la opcion", 0)) {
                case 0:
                    String nombre = GestionaMenu.llegirCadena("Introduce nombre: ");
                    empleado.setNombre(nombre);
                    break;
                case 1:
                    String priApellido = GestionaMenu.llegirCadena("Introduce el primer apellido: ");
                    empleado.setApellido1(priApellido);
                    break;
                case 2:
                    String segApellido = GestionaMenu.llegirCadena("Introduce el segundo apellido: ");
                    empleado.setApellido1(segApellido);
                    break;
                case 3:
                    String direccion = GestionaMenu.llegirCadena("Introduce la direccion: ");
                    empleado.setDireccion(direccion);
                    break;
                case 4:
                    String poblacion = GestionaMenu.llegirCadena("Introduce la poblacion: ");
                    empleado.setPoblacion(poblacion);
                    break;
                case 5:
                    String telefono = GestionaMenu.llegirCadena("Introduce el telefono: ");
                    empleado.setTlf(telefono);
                    break;
                case 6:
                    seguir = false;
                    break;
            }
        }
    }
    /**
     * Método que pide el DNI y devuelve el empleado.
     * Primer pide el DNI del empleado, luego comprueba si existe el empleado. Finalmente, 
     * devuelve el empleado 
     * @return devuelve un objeto empleado con el dni introducido
     * @throws SQLException lanzada si la consulta a la base de datos es errónea
     * @throws EmpleadoException lanza si el empleado no existe
     */
    public static Empleado seleccionaEmpleado() throws SQLException, EmpleadoException {
        String dniEmpleado = GestionaMenu.llegirCadena("Introduce el DNI: ");
        Banco.comprobarEmpleado(dniEmpleado);
        return Banco.devuelveEmpleado(dniEmpleado);
    }
    /**
     * Método que muestra el empleado que le pasámos por parámetro
     * @param empleado objeto que le pasamos como parámetro
     * @throws SQLException lanza 
     * @throws EmpleadoException 
     */
    private static void mostrarEmpleado(Empleado empleado) throws SQLException, EmpleadoException {
        if (empleado != null) {
            Connection conexion = Conexion.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM V_EMPLEAT WHERE DNI_PERSONA ='"+empleado.getDni()+"'");
            rs.next();
            System.out.println("El codigo empleado es: " + rs.getInt("CODIGO_TRABAJADOR"));
            System.out.println("Nombre: " + rs.getString("NOMBRE_PERSONA"));
            System.out.println("Primer apellido: " + rs.getString("PRIMER_APELLIDO_PERSONA"));
            System.out.println("Segundo apellido: " + rs.getString("SEGUNDO_APELLIDO_PERSONA"));
            System.out.println("Cargo: " + rs.getString("CARGO_TRABAJADOR"));
            System.out.println("Fecha nacimiento: " + rs.getString("FECHA_NACIMIENTO_PERSONA"));
            System.out.println("Direccion: " + rs.getString("DIRECCION_PERSONA"));
            System.out.println("Telefono: " + rs.getString("TELEFONO_PERSONA"));
            System.out.println("---");
            rs.close();
            st.close();
        } else {
            throw new EmpleadoException("El empleado es null");
        }
    }
    /**
     * Menu de clientes.
     * Nos permite crear empleados, listar empleados, modificar empleados, eliminar empleados y volver al menú principal
     * Recoge las excepciones lanzadas por los metodes que llama desde cada case.
     */
    public static void menuEmpleado(Connection conexion) {
        try {
            String[] menu = {"Crear empleado", "listar empleado/s", "Modificar empleado", "Eliminar empleado"};
            switch (GestionaMenu.gestionarMenu("Menu empleado", menu, "Inserta tu opción: ", MENU_EMPLEADO_PREFIX)) {
                case MENU_EMPLEADO_CREAR:
                    insertarEmpleado();
                    break;
                case MENU_EMPLEADO_LISTAR:
                    Empleado empleado = seleccionaEmpleado();
                    mostrarEmpleado(empleado);
                    break;
                case MENU_EMPLEADO_MODIFICAR:
                    modificarEmpleado();
                    break;
                case MENU_EMPLEADO_ELIMINAR:
                    eliminarEmpleado();
                    break;
                case MENU_EMPLEADO_VOLVER:
                    break;
                default:
                    break;
            }

        } catch (EmpleadoException ex) {
            Logger.getLogger(MenuEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuEmpleado.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SucursalException ex) {
            Logger.getLogger(MenuEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
