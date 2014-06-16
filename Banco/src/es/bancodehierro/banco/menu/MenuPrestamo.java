package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.excepciones.ClienteException;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.persona.Empleado;
import java.sql.SQLException;

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
                default:
                    break;
            }

        } while (!(opcionSeleccionada == 3));
    }

}
