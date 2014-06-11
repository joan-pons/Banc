package es.bancodehierro.banco.menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    private static String url = "jdbc:mysql://localhost:3306/computerstore?user=root&password=";
    private static Connection conexio;

    static {
        try {
            conexio = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
    }

    public static void altaTarjeta() {
        int codiClient = GestionaMenu.llegirSencer("Introdueix el codi del client.");
        int clientTrobat=0;
        try {
            Statement st = conexio.createStatement();
            String selectClient = "select count(*) from Cliente where idCliente=" + codiClient;
            ResultSet rs = st.executeQuery(selectClient);
            rs.next();
            clientTrobat= rs.getInt(1);
            st.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
        if (clientTrobat==1) {
            
        }

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
