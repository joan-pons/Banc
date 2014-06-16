package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.excepciones.ClienteException;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.prestamo.Prestamo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * SUB MENU DEL APARTADO PRESTAMO. Se muestran las opciones de este sub-menu y
 * te pide que introduzcas la opcion. Según la opción elegida ejecuta el método
 * de banco encargado de pedir los datos.
 *
 * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol, Tomeu Moranta
 *
 */
public class MenuPrestamo {

    /**
     * Opcion del menu
     *
     * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol.
     * @param empleado Se pasa el objeto del empleado que hará las opciones.
     */
    public static void menuPres(Empleado empleado) {

        String[] opcions = {"Insertar Prestamo", "Eliminar Prestamo","Ver movimientos préstamo","Comprobar préstamo", "Atras"};
        int opcionSeleccionada;
        Banco banco = new Banco();

        do {

            opcionSeleccionada = GestionaMenu.gestionarMenu("Prestamo", opcions, "Insertar opcion:", 1);
            
            switch (opcionSeleccionada) {
                case 1:
                    try {
                        banco.insertarPrestamo(empleado);
                    } catch (ClienteException ex) {
                        System.err.println("El client no existeix.");
                    } catch (CuentaCorrienteException ex) {
                        System.err.println("La conta corrent no existeix.");
                    } catch (SQLException ex) {
                        System.err.println("Error de conexion.");
                    }
                    break;
                case 2:
                    banco.eliminarPrestamo(null);
                    break;
                case 3:
                    verMovimientosPrestamo(null);
                    break;
                case 4:
                    int numPres = GestionaMenu.llegirSencer("Introduce el código del préstamo: ");
                    comprobarPrestamo(numPres);
                default:

                    break;
            }

        } while (!(opcionSeleccionada == 3));
    }

    public Prestamo getPrest(CuentaCorriente cc) throws SQLException{
         ArrayList<String> resultSet = null;
        try {
            ResultSet rs = Conexion.conectar().createStatement().executeQuery("SELECT CODIGO_PRESTAMO,"
                    + "IMPORTE_PRESTAMO,"
                    + "DURACION_MES_PRESTAMO"
                    + "DNI_TRABAJADOR,"
                    + "FECHA_FIRMA_PRESTAMO,"
                    + "CODIGO_SUC_TARJETA,"
                    + "NUMERO_CC_PRESTAMO"
                    + "FROM PRESTAMO "
                    + "WHERE NUMERO_CC_PRESTAMO='" + cc.muestraCC() + "'");
            while (rs.next()) {
                resultSet.add(
                        "Código préstamo: " + rs.getString(1)
                        + ", Importe: " + rs.getInt(2)
                        + ", Duración: " + rs.getString(3)
                        + ", DNI: " + rs.getString(4)
                        + ", Fecha Firma: " + rs.getDouble(5)
                        + ", Codigo sucursal: " + rs.getString(6)
                        + ", Número CC: " + rs.getString(7));
            } 
           
    } catch (SQLException ex){
            System.err.println(ex.getMessage());
    }
        
        
        return null;
    }
    
    /**
     * Metodo que sirve para recoger los datos que luego utilizaremos en el
     * metodo para ver los movimientos de un préstamo.
     *
     * @param prest prestec a veure els moviments.
     */
    public static void verMovimientosPrestamo(Prestamo prest) {
        int codigoPrestamo;
        boolean existe;
        do {
            codigoPrestamo = GestionaMenu.llegirSencer("Introduce el código del préstamo: ");
            existe = comprobarPrestamo(codigoPrestamo);
        } while (existe);
        try {
            Statement st = Conexion.conectar().createStatement();
            boolean resultat = comprobarPrestamo(codigoPrestamo);
            if (resultat == true) {
                prest.verMovimientosPrestamo();
            } else if (resultat == false) {

            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
    }

    /**
     * Muestra por pantalla la informacion sobre un préstamo y si está.
     *
     * @param codigoPrestamo Codigo del préstamo
     * @return Devuelve un objeto de tipo préstamo.
     *
     */
    public static boolean comprobarPrestamo(int codigoPrestamo) {
        boolean flag = true;
        try {
            Statement st = Conexion.conectar().createStatement();
            String selectPrestamo = "SELECT COUNT(*) FROM PRESTAMO WHERE CODIGO_PRESTAMO = '" + codigoPrestamo + "'";
            ResultSet rs = st.executeQuery(selectPrestamo);
            rs.next();
            int existeix = rs.getInt(1);
            if (existeix == 1) {
                System.out.println("Préstamo encontrado!");
                
                flag = false;
            } else {
                System.out.println("Préstamo no encontrado!");
                flag = true;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
        return flag;
    }
            

}
