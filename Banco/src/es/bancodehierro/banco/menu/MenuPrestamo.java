package es.bancodehierro.banco.menu;

/**
 *
 * @author Miquel Vallespir, Rafel Sastre, Pau Riera, Jaume Mayol, Tomeu Moranta
 */
public class MenuPrestamo {

    public static void muestraMenu() {

        String[] opcions = {"Insertar Prestamo"};

        int opcionSeleccionada = GestionaMenu.gestionarMenu("Prestamo", opcions, "Insertar opcion:", 0);

        switch (opcionSeleccionada) {
            case 1:

                break;
        }

    }

}
