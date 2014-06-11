package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.prestamo.Prestamo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol, Tomeu Moranta
 */
public class MenuPrestamo {

    public static void menuPres() {

        String[] opcions = {"Insertar Prestamo"};

        int opcionSeleccionada = GestionaMenu.gestionarMenu("Prestamo", opcions, "Insertar opcion:", 0);

        switch (opcionSeleccionada) {
            case 1:

                break;
        }
    }

    /**
     * PENDIENTE DE PASAR A BANCO Y INDICAR BASE DE DATOS.
     *
     * @author Rafel Sastre.
     * @param presta
     */
    public void insertarPrestamo(Prestamo presta) {
        String url = "jdbc:mysql://localhost:3306/biblioteca?user=root&password=";
        Connection conexio;
        try {
            conexio = DriverManager.getConnection(url);
            Statement st = conexio.createStatement();
            int filesAfectades = st.executeUpdate(presta.insertarPrestamo());
            System.out.println(filesAfectades + ", files afectades.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getErrorCode() + ", " + ex.getLocalizedMessage());
        }

    }

}
