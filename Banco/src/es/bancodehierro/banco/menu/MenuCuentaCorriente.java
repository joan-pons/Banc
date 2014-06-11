/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor 
 */
package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.cc.Movimiento;
import es.bancodehierro.banco.enumeraciones.EnumMovimiento;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel
 * Angel Cànaves
 */
public class MenuCuentaCorriente {

    public static void menuCC() {
        CuentaCorriente cC = new CuentaCorriente(GestionaMenu.llegirCadena("Introduce IBAN: "), GestionaMenu.llegirCadena("Introduce Oficina: "), GestionaMenu.llegirCadena("Introduce DC: "), GestionaMenu.llegirCadena("Introduce Cuenta: "), GestionaMenu.llegirDouble("Introduce un Importe"));
        MenuCuentaCorriente menuCC = new MenuCuentaCorriente();
        boolean repMenuPrincipal = true;
        for (; repMenuPrincipal;) {
            String[] opciones = {"Operaciones de Importe",
                "Operaciones de Titular",
                "Operaciones de Movimientos",
                "Salir"};

            int menu = menuCC.mostrarMenu(opciones);
            switch (menu) {
                case 0: {
                    boolean repMenuImporte = true;
                    for (; repMenuImporte;) {
                        String[] opcionesImp = {"Modificar Importe",
                            "Mostrar Importe",
                            "Volver atrás"};

                        int menuImp = menuCC.mostrarMenu(opcionesImp);
                        switch (menuImp) {
                            case 0: {
                                cC.setImporte(GestionaMenu.llegirDouble("Introduce importe:positivo para sumar, negativo para restar"));
                                break;
                            }
                            case 1: {
                                System.out.println(cC.getImporte());
                                break;
                            }
                            case 2: {
                                //Retrocede al menu anterior
                                repMenuImporte = false;
                                break;
                            }

                        }
                        if (menuImp != 2) {

                            repMenuImporte = menuCC.algunaCosaMas();
                        }
                    }
                    break;
                }

                case 1: {
                    boolean repMenuTitular = true;
                    for (; repMenuTitular;) {
                        String[] opcionesTit = {"Agregar Titular",
                            "Modificar Titular",
                            "Eliminar Titular",
                            "Intercambiar Titular",
                            "Volver atrás"};

                        int menuTit = menuCC.mostrarMenu(opcionesTit);
                        switch (menuTit) {
                            case 0: {
                                Cliente cliente = new Cliente(menu, null, null, null, null, null, null);
                                try {
                                    cC.agregarTitular(cliente);
                                } catch (CuentaCorrienteException ex) {
                                    System.err.println(ex.getMessage());
                                }
                                break;
                            }
                            case 1: {

                                break;
                            }
                            case 2: {
                                Cliente cliente = new Cliente(menu, null, null, null, null, null, null);
                                try {
                                    cC.eliminarTitular(cliente);
                                } catch (CuentaCorrienteException ex) {
                                    System.err.println(ex.getMessage());
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    cC.intercambiarTitular();
                                } catch (CuentaCorrienteException ex) {
                                    System.err.println(ex.getMessage());
                                }
                                break;
                            }
                            case 4: {
                                //Retrocede al menu anterior
                                repMenuTitular = false;
                                break;
                            }

                        }
                        if (menuTit != 4) {

                            repMenuTitular = menuCC.algunaCosaMas();;

                        }
                    }
                    break;
                }

                case 2: {
                    boolean repMenuMovimiento = true;
                    for (; repMenuMovimiento;) {
                        String[] opcionesMov = {"Movimientos",
                            "Incidencias",
                            "Volver atrás"};

                        int menuMov = menuCC.mostrarMenu(opcionesMov);
                        switch (menuMov) {
                            case 0: {

                                boolean repMenuMovimientoCorrecto = true;
                                for (; repMenuMovimientoCorrecto;) {
                                    String[] opcionesMovC = {"Mostrar todos los Movimientos",
                                        "Mostrar Movimientos segun Tipo",
                                        "Volver atrás"};

                                    int menuMovC = menuCC.mostrarMenu(opcionesMovC);
                                    switch (menuMovC) {
                                        case 0: {
                                            for (Movimiento mov : cC.mostrarMovimiento(false)) {

                                                System.out.println(mov);

                                            }
                                            break;
                                        }
                                        case 1: {

                                            for (Movimiento mov : cC.mostrarMovimiento(false, menuCC.seleccionTipo())) {

                                                System.out.println(mov);

                                            }
                                            break;
                                        }
                                        case 2: {
                                            //Retrocede al menu anterior
                                            repMenuMovimientoCorrecto = false;
                                            menuMov = 2;
                                            break;
                                        }

                                    }
                                    if (menuMovC != 2) {

                                        repMenuMovimientoCorrecto = menuCC.algunaCosaMas();
                                        menuMov = 2;
                                    }
                                }
                                break;

                            }
                            case 1: {

                                boolean repMenuMovimientoIncidencia = true;
                                for (; repMenuMovimientoIncidencia;) {
                                    String[] opcionesMovI = {"Mostrar todos las Incidencias",
                                        "Mostrar Incidencias segun Tipo",
                                        "Volver atrás"};

                                    int menuMovI = menuCC.mostrarMenu(opcionesMovI);
                                    switch (menuMovI) {
                                        case 0: {
                                            for (Movimiento mov : cC.mostrarMovimiento(true)) {

                                                System.out.println(mov);

                                            }
                                            break;
                                        }
                                        case 1: {

                                            for (Movimiento mov : cC.mostrarMovimiento(true, menuCC.seleccionTipo())) {

                                                System.out.println(mov);

                                            }
                                            break;
                                        }
                                        case 2: {
                                            //Retrocede al menu anterior
                                            repMenuMovimientoIncidencia = false;
                                            menuMov = 2;
                                            break;
                                        }

                                    }
                                    if (menuMovI != 2) {

                                        repMenuMovimientoIncidencia = menuCC.algunaCosaMas();
                                        menuMov = 2;
                                    }
                                }
                                break;

                            }
                            case 2: {
                                //Retrocede al menu anterior
                                repMenuMovimiento = false;
                                break;
                            }

                        }
                        if (menuMov != 2) {

                            repMenuMovimiento = menuCC.algunaCosaMas();

                        }
                    }
                    break;
                }
                case 3: {

                    break;
                }

            }
        }
    }

    /**
     * Metodo para repetir el menu o ir al anterior
     *
     * @return Devuelve un booleano con la opcion elegida
     */
    public boolean algunaCosaMas() {

        String resp;
        char respC = ' ';

        for (; respC != 'N' && respC != 'S';) {
            resp = GestionaMenu
                    .llegirCadena("\n¿Quieres hacer alguna cosa más?S/N: ");
            try {
                respC = resp.toUpperCase().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                respC = ' ';
            }

        }

        return respC != 'N';

    }

    /**
     * Método para mostrar opciones del Menu
     *
     * @param opciones Array de Strings con los valores de las opciones
     * @return Devuelve un int con la opcion elegida
     */
    public int mostrarMenu(String[] opciones) {
        return GestionaMenu.gestionarMenu(
                "\nElegir Opcion:\n\t", opciones,
                "¿Que quieres ejecutar?", 0);
    }

    public EnumMovimiento seleccionTipo() {
        boolean respTipoBoolean = false;
        EnumMovimiento respTipoEnum = null;
        for (; !respTipoBoolean;) {
            System.out.println("\nElige un tipo de Articulo:\n\t0-CUENTA CORRIENTE\n\t1-PRESTAMO\n\t2-TARJETA DEBITO\n\t3-TARJETA CREDITO");
            int respPestescidaInt = GestionaMenu.llegirSencer("\nRespuesta: ");
            if (respPestescidaInt == 0) {
                respTipoEnum = EnumMovimiento.CUENTA_CORRIENTE;
                respTipoBoolean = true;
            } else if (respPestescidaInt == 1) {
                respTipoEnum = EnumMovimiento.PRESTAMO;
                respTipoBoolean = true;
            } else if (respPestescidaInt == 2) {
                respTipoEnum = EnumMovimiento.TARJETA_DEBITO;
                respTipoBoolean = true;
            } else if (respPestescidaInt == 3) {
                respTipoEnum = EnumMovimiento.TARJETA_CREDITO;
                respTipoBoolean = true;
            } else {
                System.out.println("\nERROR:Opción no Válida, Inserta un Valor Correcto");
            }

        }
        return respTipoEnum;
    }

}
