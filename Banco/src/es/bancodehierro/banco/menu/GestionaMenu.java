/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.menu;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author guillem
 */
public class GestionaMenu {
    /**
     * Et mostra un missatge i es torna la cadena que li fiqui l'usuari.
     *
     * @param prompt Es el missatge que mostrara abans de demanar la cadena
     * @return Torna una String amb la cadena que fiqui l'usuari.
     */
    public static String llegirCadena(String prompt) {
        boolean correcte = false;

        String cadena = "";

        Scanner teclat = new Scanner(System.in);

        do {
            System.out.print(prompt);
            try {
                cadena = teclat.next();
                correcte = true;

            } catch (InputMismatchException e) {
                System.err.println("Cadena incorrecte.");
                teclat.next();
            }
        } while (correcte == false);
        return cadena;
    }

    /**
     * Et mostra un missatge i es torna el sencer que li fiqui l'usuari.
     *
     * @param prompt Es el missatge que mostrara abans de demanar el sencer
     * @return Torna el valor int amb el sencer que fiqui l'usuari.
     */
    public static int llegirSencer(String prompt) {
        boolean correcte = false;

        int sencer = 0;

        Scanner teclat = new Scanner(System.in);

        do {
            System.out.print(prompt);
            try {
                sencer = teclat.nextInt();
                correcte = true;

            } catch (InputMismatchException e) {
                System.err.println("Sencer incorrecte.");
                teclat.next();
            }
        } while (correcte == false);
        return sencer;
    }

    /**
     * Et mostra un missatge i es torna el double que li fiqui l'usuari.
     *
     * @param prompt Es el missatge que mostrara abans de demanar el double
     * @return Torna el valor double amb el decimal que fiqui l'usuari.
     */
    public static double llegirDouble(String prompt) {
        boolean correcte = false;

        Double sencer = 0.0;

        Scanner teclat = new Scanner(System.in);

        // Llegeix un sencer del teclat. Si el que introdueix l'usuari no es pot
        // convertir a sencer l'avisa de l'errada i torna a demanar el sencer.
        do {
            System.out.print(prompt);
            try {
                sencer = teclat.nextDouble();
                correcte = true;

            } catch (InputMismatchException e) {
                System.err.println("Double erroni.");
                teclat.next();
            }
        } while (correcte == false);
        return sencer;
    }

    /**
     * Metode que s'encarrega de gestionar el menu.
     *
     * Li pases un string de Titol que es mostrara enmitj de la pantalla.
     *
     * L'array d'string's han de incloure totes les opcions amb l'unic format de
     * majuscula/minuscula, la resta de format s'encarrega el metode de
     * generar-lo
     *
     * @param titol Titol que es mostrara
     * @param opcions Opcions que pot mostrar el menu
     * @param pregunta La pregunta que es mostrara a l'usuari abans de que
     * introduesqui la opcio
     * @param prefix Es lo que li ficara al principi del return
     * @return torna la opcio que el usuari tria començat per prefix (es a dir
     * el valor 0 sera 0+prefix)
     *
     */
    public static int gestionarMenu(String titol, String[] opcions, String pregunta, int prefix) {
        System.out.println(titol);
        int resultat;
        for (int index = 0; index < opcions.length; index++) {
            System.out.println("[" + (index + 1) + "]--> " + opcions[index]);
        }
        boolean ok;
        do {
            resultat = llegirSencer(pregunta + " ");
            resultat = resultat - 1;
            if ((resultat < opcions.length) && (resultat >= 0)) {
                ok = false;
            } else {
                System.out.println("Ha triat una opcio no contemplada en el menú");
                ok = true;
            }
        } while (ok);
        //S'añadeix un prefix
        resultat = resultat + prefix;
        return resultat;
    }
    /**
     * Mostra un titol i una pregunta per demanar si o no
     * 
     * @param titol el titol que mostrara
     * @param pregunta la pregunta que mostrara
     * @return true si pitja que si, false si pitja que no
     */

    public static boolean menuSiNo(String titol, String pregunta) {
        String[] opcioSiNo = {
            "Si",
            "No"
        };
        int opcio = gestionarMenu(titol, opcioSiNo, pregunta, 0);
        do {
            switch (opcio) {
                case 0:
                    return true;
                case 1:
                    return false;
            }
        } while (opcio == 0 || opcio == 1);
        return false;
    }
    
     /**
     * Metodo para generar una fecha mediante el formato "dd-mm-yyyy", la pasa a objeto "Date" y lo devuelve.
     * 
     * @param fecha String en formato "dd-mm-yyyy" que se usara para transformarlo en fecha.
     * @return Objeto "Date" con la fecha correspondiente.
     */
    public Date setFechaNacimiento(String fecha){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date data = formatter.parse(fecha, new ParsePosition(1));
        return data;
    }
}
