package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.prestamo.Prestamo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol, Tomeu Moranta
 */
public class MenuPrestamo {

    public static void menuPres() {

        String[] opcions = {"Insertar Prestamo", "Editar Prestamo", "Eliminar Prestamo", "Atras"};
        int opcionSeleccionada;

        do {

            opcionSeleccionada = GestionaMenu.gestionarMenu("Prestamo", opcions, "Insertar opcion:", 1);

            switch (opcionSeleccionada) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    break;
            }

        } while (!(opcionSeleccionada == 4));
    }

    /**
     * PENDIENTE DE PASAR A BANCO.
     *
     * @author Rafel Sastre y Miquel Vallespir Castello
     * @param presta
     */
    public boolean insertarPrestamo(Empleado empleado) {
        Connection conexion = Conexion.conectar();
        ArrayList<CuentaCorriente> listCC = null;
        Banco b = new Banco();// al meter en banco desaparece

        String dniCliente = GestionaMenu.llegirCadena("Introduce DNI cliente.");

        Cliente cliente = b.devuelveCliente(dniCliente);

        if (cliente == null) {
            System.err.println("El cliente no existe!!");
            return false;
        } else {
            listCC = b.mostrarCuentaCorriente(cliente);
        }

        if (listCC == null) {
            System.err.println("Este cliente no tiene ninguna cuenta asociada.");
            return false;
        }

        String[] opcions = new String[listCC.size()];
        int i = 0;
        for (CuentaCorriente cc : listCC) {

            opcions[i] = cc.muestraCC();
            i++;

        }

        int opcioSeleccionade = GestionaMenu.gestionarMenu("Prestamo", opcions, "Insertar opcion:", 0);
        double importePrestado = GestionaMenu.llegirDouble("Introdueix el total a prestar");
        double tasaInteresAnual = GestionaMenu.llegirDouble("La tasa de interes anual");
        

        Prestamo presta = new Prestamo(0, "", null, null, importePrestado, tasaInteresAnual, empleado, listCC.get(opcioSeleccionade));

        try {
            Statement st = conexion.createStatement();
            int filesAfectades = st.executeUpdate(presta.insertarPrestamo());
            System.out.println(filesAfectades + ", files afectades.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getErrorCode() + ", " + ex.getLocalizedMessage());
            return false;
        }
        presta.toString();

        return true;
    }
    
    /**
     * PENDIENTE DE PASAR A BANCO.
     * Menú. Método de modificar Préstamo.
     * Modifica el préstamo pasándole un objeto de Préstamo.
     * 
     * @author Pau Riera
     * @param presta 
     */
    public void updatePrestamo(Prestamo presta) {
        Connection conexio = Conexion.conectar();
        try {
            Statement st = conexio.createStatement();
            int filesAfectades = st.executeUpdate(presta.updatePrestamo());
            System.out.println(filesAfectades + ", files afectades.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getErrorCode() + ", " + ex.getLocalizedMessage());
        }
        
    }
    
    /**
     * PENDIENTE DE PASAR A BANCO.
     * Menú. Método de eliminar Préstamo
     * Elimina el préstamo. Coge el código del préstamo.
     * @author Jaume Mayol
     * @param presta
     * @see es.bancodehierro.banco.prestamo.Prestamo
     */
    public void eliminarPrestamo(Prestamo presta) {
        Connection conexio = Conexion.conectar();
        try {
            Statement st = conexio.createStatement();
            int filesAfectades = st.executeUpdate(presta.eliminarPrestamo());
            System.out.println(filesAfectades + ", files afectades.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getErrorCode() + ", " + ex.getLocalizedMessage());
        }

    }

}
