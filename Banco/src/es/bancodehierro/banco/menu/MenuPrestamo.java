package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.excepciones.ClienteException;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.prestamo.Prestamo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol, Tomeu Moranta
 */
public class MenuPrestamo {

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
                    }
                    break;
                case 2:
                    banco.eliminarPrestamo(null);
                    break;
                default:
                    break;
            }

        } while (!(opcionSeleccionada == 3));
    }

}
