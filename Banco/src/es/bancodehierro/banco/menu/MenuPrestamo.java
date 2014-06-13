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

}
