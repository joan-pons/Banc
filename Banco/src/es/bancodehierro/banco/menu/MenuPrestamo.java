package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.excepciones.ClienteException;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.prestamo.Prestamo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

        String[] opcions = {"Insertar Prestamo", "Eliminar Prestamo", "Atras"};
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
                    verMovimientosPrestamo();
                    break;
                default:

                    break;
            }

        } while (!(opcionSeleccionada == 3));
    }

    /**
     * Metodo que sirve para recoger los datos que luego utilizaremos en el
     * metodo para ver los movimientos de un préstamo.
     *
     */
    public static void verMovimientosPrestamo() {
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
                Prestamo prest = new Prestamo(codigoPrestamo, null, 0, null, null);
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
