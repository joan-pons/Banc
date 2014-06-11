package es.bancodehierro.banco.menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import banc.Conexion;
import es.bancodehierro.banco.menu.GestionaMenu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author antonio
 */
public class MenuTarjeta {

    private static Connection conexio = Conexion.conectar();

    public static void altaTarjeta() {
        //Obtener el cliente.
        int codigoCliente = GestionaMenu.llegirSencer("Introdueix el codi del client.");
        int clienteEncontrado = 0;
        while (clienteEncontrado == 0) {
            try {
                Statement st = conexio.createStatement();
                String selectCliente = "select count(*) from Cliente where idCliente=" + codigoCliente;
                ResultSet rs = st.executeQuery(selectCliente);
                rs.next();
                clienteEncontrado = rs.getInt(1);
                st.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
            }
            if (clienteEncontrado == 1) {
                System.out.println("Cliente encontrado.");
            } else {
                System.out.println("Cliente no encontrado, introduzca un codigo de cliente existente.");
            }
        }

        //Obtener cuenta corriente.
        int codigoSucursal = GestionaMenu.llegirSencer("Introduzca el código de sucursal");
        int codigoCuenta = GestionaMenu.llegirSencer("Introduzca el código de cuenta corriente");
        int cuentaEncontrada = 0;
        while (cuentaEncontrada == 0) {
            try {
                Statement st = conexio.createStatement();
                String selectCuenta = "select count(*) from CuentaCorriente where codigo_scc=" + codigoSucursal +" and numero_cc="+codigoCuenta;
                ResultSet rs = st.executeQuery(selectCuenta);
                rs.next();
                cuentaEncontrada = rs.getInt(1);
                st.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
            }
            if (cuentaEncontrada == 1) {
                System.out.println("Cuenta encontrada.");
            } else {
                System.out.println("Cuenta no encontrada, introduzca un codigo sucursal y cuenta existente.");
            }
        }
        
        //
        
    }

    public static void eliminarTarjeta() {

    }

    public static void pagar() {

    }

    public static void ingresarDebito() {

    }

    public static void verMovimientos() {

    }

    public static void ejecutarMenu() {
        boolean flag = true;
        do {
            System.out.println("Opcion 1: Dar de alta una tarjeta.");
            System.out.println("Opcion 2: Eliminar una tarjeta.");
            System.out.println("Opcion 3: Realizar un pago.");
            System.out.println("Opcion 4: Ingresar (sólo débito).");
            System.out.println("Opcion 5: Ver movimientos tarjeta.");
            System.out.println("Opcion 6: Salir.");
            int opcion = GestionaMenu.llegirSencer("Introduce la opción: ");
            switch (opcion) {
                case 1: {
                    altaTarjeta();
                    break;
                }
                case 2: {
                    eliminarTarjeta();
                    break;
                }
                case 3: {
                    pagar();
                    break;
                }
                case 4: {
                    ingresarDebito();
                    break;
                }
                case 5: {
                    verMovimientos();
                    break;
                }
                case 6: {
                    flag = false;
                    break;
                }
            }

        } while (flag);
    }

}
