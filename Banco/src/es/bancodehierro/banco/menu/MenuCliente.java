/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.excepciones.ClienteException;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.sucursal.Sucursal;
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
public abstract class MenuCliente {

    private static final int MENU_CLIENTE_PREFIX = 70000;
    private static final int MENU_CLIENTE_CREAR = 70000;
    private static final int MENU_CLIENTE_LISTAR = 70001;
    private static final int MENU_CLIENTE_MODIFICAR = 70002;
    private static final int MENU_CLIENTE_ELIMINAR = 70003;
    private static final int MENU_CLIENTE_VOLVER = 70004;

    /**
     * Método que pide todos los datos de un cliente,crea un objecto cliente con ellos y lo introduce en la base de datos.
     * Pide cada campo del empleado para así introducirlo en el nuevo objeto empleado. 
     * @throws EmpleadoException Devuelve esta excepcion si ya existe el empleado desde 
     * el metodo insertarEmpleado. 
     */
    public static void insertarCliente() throws SQLException, ClienteException {
        String dniCliente = GestionaMenu.llegirCadena("Inserta DNI: ");
        String nombreCliente = GestionaMenu.llegirCadena("Inserta el Nombre: ");
        String apellido1 = GestionaMenu.llegirCadena("Inserta el primer apellido: ");
        String apellido2 = GestionaMenu.llegirCadena("Inserta el segundo apellido: ");
        String fN = GestionaMenu.llegirCadena("Inserta la fecha de nacimiento: ");
        Date fechaNacimiento = GestionaMenu.setFechaNacimiento(fN);
        String poblacion = GestionaMenu.llegirCadena("Inserta la población de la persona: ");
        String direccionPersona = GestionaMenu.llegirCadena("Inserta la dirección: ");
        String telefono = GestionaMenu.llegirCadena("Inserta el teléfono: ");
        Cliente cliente = new Cliente(nombreCliente, apellido1, apellido2, dniCliente, poblacion, direccionPersona, fechaNacimiento, telefono);
        Banco.insertarCliente(cliente);
    }

     /**
     * Pide el dni del cliente y lo borra de la base de datos.
     * Primero pide el dni del empleado que queremos borrar, luego comprueba si existe
     * algun empleado con ese dni.
     * @throws SQLException tira la excepción si la consulta a la base de datos no está
     * bien hecha.
     * @throws EmpleadoException Lanza la excepción si el cliente no existe 
     */
    public static void eliminarCliente() throws SQLException, ClienteException {
        String dniCliente = GestionaMenu.llegirCadena("Inserta DNI: ");
        Banco.comprobarCliente(dniCliente);
        Banco.eliminarCliente(dniCliente);

    }

    /**
     * Método que pide el DNI i podremos modificar al cliente con ese DNI.
     * Pide el DNI del empleado. Luego muestra el empleado y nos pide con una 
     * serie de funciones que queremos modificar con ese empleado.
     * @throws SQLException La devuelve si la consulta no está bien introducida en la base de datos.
     * @throws EmpleadoException Lanzada si el empleado no existe
     */
    private static Cliente seleccionaCliente() throws SQLException, ClienteException {
        Cliente cliente;
        String dniCliente = GestionaMenu.llegirCadena("Introduce el DNI del cliente: ");
        Banco.comprobarCliente(dniCliente);
        cliente = Banco.devuelveCliente(dniCliente);
        
        return cliente;
    }

    /**
     * Método que nos muestra el Cliente.
     * Nos pide el DNI del cliente, luego comprueba si existe el Cliente y finalmente
     * nos muestra el cliente con todos sus datos.
     * @throws SQLException lanzada si la consulta a la base de datos no es correcta
     * @throws ClienteException lanza si el cliente no existe
     */
    public static void listarCliente() throws SQLException, ClienteException {
        mostrarCliente(seleccionaCliente());
    }

    /**
     * Método que pide el DNI i podremos modificar al Cliente con ese DNI.
     * Pide el DNI del cliente. Luego muestra el cliente y nos pide con una 
     * serie de funciones que queremos modificar con ese empleado.
     * @throws SQLException La devuelve si la consulta no está bien introducida en la base de datos.
     * @throws EmpleadoException Lanzada si el cliente no existe
     */
    public static void modificarCliente() throws SQLException, ClienteException {
       Cliente cliente = seleccionaCliente();
        System.out.print("El cliente es: ");
        mostrarCliente(cliente);
        boolean seguir = true;
        String[] menu = {"Modificar nombre", "Modificar primer apellido", "Modificar segundo apellido", "Modificar direccion", "Modificar poblacion", "Modificar telefono","Nada más"};
        while (seguir) {
            switch (GestionaMenu.gestionarMenu("Que quieres modificar?", menu, "Introduce la opcion", 0)) {
                case 0:
                    String nombre = GestionaMenu.llegirCadena("Introduce nombre: ");
                    cliente.setNombre(nombre);
                    break;
                case 1:
                    String priApellido = GestionaMenu.llegirCadena("Introduce el primer apellido: ");
                    cliente.setApellido1(priApellido);
                    break;
                case 2:
                    String segApellido = GestionaMenu.llegirCadena("Introduce el segundo apellido: ");
                    cliente.setApellido1(segApellido);
                    break;
                case 3:
                    String direccion = GestionaMenu.llegirCadena("Introduce la direccion: ");
                    cliente.setDireccion(direccion);
                    break;
                case 4:
                    String poblacion = GestionaMenu.llegirCadena("Introduce la poblacion: ");
                    cliente.setPoblacion(poblacion);
                    break;
                case 5:
                    String telefono = GestionaMenu.llegirCadena("Introduce el telefono: ");
                    cliente.setTlf(telefono);
                    break;
                case 6:
                    seguir = false;
                    break;
            }
        }
        Banco.modificarCliente(cliente);
    }

    /**
     * Metodo que muestra un cliente.
     * Método que nos devuelve a un cliente por pantalla.
     * @param cliente objeto cliente que nos va a mostrar por la interfaz
     * @throws SQLException lanzado si la consulta a la base de datos no es correcta
     * @throws ClienteException lanzado si el cliente no existe
     */
    private static void mostrarCliente(Cliente cliente) throws SQLException, ClienteException {
        if (cliente != null) {
            Connection conexion = Conexion.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM V_CLIENT WHERE DNI_PERSONA ='"+cliente.getDni()+"'");
            System.out.println("El codigo cliente es: " + rs.getInt("CODIGO_CLIENTE"));
            System.out.println("Nombre: " + rs.getString("NOMBRE_PERSONA"));
            System.out.println("Primer apellido: " + rs.getString("PRIMER_APELLIDO_PERSONA"));
            System.out.println("Segundo apellido: " + rs.getString("SEGUNDO_APELLIDO_PERSONA"));
            System.out.println("Fecha nacimiento: " + rs.getString("FECHA_NACIMIENTO_PERSONA"));
            System.out.println("Direccion: " + rs.getString("DIRECCION_PERSONA"));
            System.out.println("Poblacion: " + rs.getString("POBLACION_CLIENTE"));
            System.out.println("Telefono: " + rs.getString("TELEFONO_PERSONA"));
            rs.close();
            st.close();
        } else {
            throw new ClienteException("El cliente es null");
        }

    }
    /**
     * Menu de clientes.
     * Nos permite crear clientes, listar clientes, modificar clientes, eliminar clientes y volver al menú principal
     * Recoge las excepciones lanzadas por los metodes que llama
     */
    public static void menuClientes() {
        try {
            String[] menu = {"Crear cliente", "listar cliente/s", "Modificar cliente", "Eliminar cliente"};
            switch (GestionaMenu.gestionarMenu("Menu cliente", menu, "Inserta tu opción: ", MENU_CLIENTE_PREFIX)) {
                case MENU_CLIENTE_CREAR:
                    insertarCliente();
                    break;
                case MENU_CLIENTE_LISTAR:
                    listarCliente();
                    break;
                case MENU_CLIENTE_MODIFICAR:
                    modificarCliente();
                    break;
                case MENU_CLIENTE_ELIMINAR:
                    eliminarCliente();
                    break;
                case MENU_CLIENTE_VOLVER:
                    break;
                default:
                    break;
            }
        } catch (ClienteException ex) {
            ex.getMessage();
        } catch (SQLException ex) {
            Logger.getLogger(MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
