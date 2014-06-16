/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor 
 */
package es.bancodehierro.banco.menu;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.cc.Movimiento;
import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.enumeraciones.EnumMovimiento;
import es.bancodehierro.banco.excepciones.ClienteException;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.sucursal.Sucursal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel
 * Angel Cànaves
 */
public class MenuCuentaCorriente {

    public static void menuCC() throws SQLException, CuentaCorrienteException, ClienteException {
        CuentaCorriente cC = new CuentaCorriente(GestionaMenu.llegirCadena("Introduce IBAN: "), GestionaMenu.llegirCadena("Introduce Oficina: "), GestionaMenu.llegirCadena("Introduce DC: "), GestionaMenu.llegirCadena("Introduce Cuenta: "), '\0');
        MenuCuentaCorriente menuCC = new MenuCuentaCorriente();
        Sucursal sucursal=new Sucursal(null, null, 35, 0000, null,null);
        Banco banco=new Banco();
        boolean repMenuPrincipal = true;
        for (; repMenuPrincipal;) {
            String[] opciones = {"Operaciones de Importe",
                "Operaciones de Titular",
                "Operaciones de Movimientos",
                "Operaciones de Cuenta Corriente",
                "Salir"};

            int menu = menuCC.mostrarMenu(opciones);
            switch (menu) {
                case 0: {
                    boolean repMenuImporte = true;
                        menuCC.metodoImporte(repMenuImporte, cC,sucursal);
                    break;
                }

                case 1: {
                    boolean repMenuTitular = true;
                    Cliente cliente = new Cliente(null, null, null, "12345678L", null, null, null, null);
                    menuCC.metodoTitular(repMenuTitular,cC,cliente,sucursal);
                    break;
                }

                case 2: {
                    boolean repMenuMovimiento = true;
                    menuCC.metodoMovimiento(repMenuMovimiento, cC);
                    break;
                }
                case 3: {
                    boolean repMenuBanco = true;
                    Cliente cliente = new Cliente(null, null, null, GestionaMenu.llegirCadena("Introduce el dni del cliente: "), null, null, null, null);
                    menuCC.metodoBanco(repMenuBanco, cC,cliente,sucursal,banco);
                    break;
                }
                case 4: {
                    Principal.menuPrincipal();
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
    /**
     * 
     * @return 
     */
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
    /**
     * Metodo que contiene las opciones del menu con las operaciones de Tipo
     * @param repMenuImporte booleano con true o false
     * @param cC Paso de la cuenta corriente
     */
    public void metodoImporte(boolean repMenuImporte, CuentaCorriente cC,Sucursal sucursal) throws SQLException, CuentaCorrienteException{
        for (; repMenuImporte;) {
                        String[] opcionesImp = {"Modificar Importe",
                            "Mostrar Importe",
                            "Volver atrás"};

                        int menuImp = mostrarMenu(opcionesImp);
                        switch (menuImp) {
                            case 0: {
                                //cC.setImporte(GestionaMenu.llegirDouble("Introduce importe:positivo para sumar, negativo para restar"));
                                cC.modificarSaldo(sucursal);
                                break;
                            }
                            case 1: {
                                //System.out.println(cC.getImporte());
                                System.out.println(cC.mostrarSaldo(sucursal)+"€");
                                break;
                            }
                            case 2: {
                                //Retrocede al menu anterior
                                repMenuImporte = false;
                                break;
                            }

                        }
                        if (menuImp != 2) {

                            repMenuImporte = algunaCosaMas();
                        }
                    }
    }
    /**
     * Metodo que contiene las opciones del menu con las operaciones de Importe
     * @param repMenuTitular booleano con true o false
     * @param cC Paso de la cuenta corriente
     * @param cliente Cliente a tratar
     */
    public void metodoTitular(boolean repMenuTitular, CuentaCorriente cC, Cliente cliente,Sucursal sucursal) throws SQLException, CuentaCorrienteException, ClienteException{
        for (; repMenuTitular;) {
                        String[] opcionesTit = {"Agregar Titular",
                            "Modificar Titular",
                            "Eliminar Titular",
                            "Intercambiar Titular",
                            "Volver atrás"};

                        int menuTit = mostrarMenu(opcionesTit);
                        switch (menuTit) {
                            case 0: {
                               //Cliente cliente2 = new Cliente(0, null, null, null, null, null, null, null, null);
                                Cliente clienteOperacion= cliente;
                                try {
                                    cC.agregarTitular(sucursal);
                                } catch (SQLException ex) {
                                    System.err.println(ex.getMessage());
                                }
                                break;
                            }
                            case 1: {
                                Cliente nuevo=new Cliente(null, null, null, "12345789O", null, null, null, null);
                                cC.cambiarTitular(sucursal);
                                break;
                            }
                            case 2: {
                                cC.eliminarTitular(cliente,sucursal);
                                break;
                            }
                            case 3: {
                                try {
                                    cC.intercambiarTitular(sucursal);
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

                            repMenuTitular = algunaCosaMas();;

                        }
                    }
    }
    /**
     * Metodo que contiene las opciones del menu con las operaciones de Movimiento
     * @param repMenuMovimiento booleano con true o false
     * @param cC Paso de la cuenta corriente
     */
    public void metodoMovimiento(boolean repMenuMovimiento,CuentaCorriente cC) throws SQLException{
        for (; repMenuMovimiento;) {
                                    String[] opcionesMovC = {"Mostrar todos los Movimientos",
                                        "Mostrar Movimientos segun Tipo",
                                        "Volver atrás"};

                                    int menuMovC = mostrarMenu(opcionesMovC);
                                    switch (menuMovC) {
                                        case 0: {
                                            for (Movimiento mov : cC.mostrarMovimiento(false)) {

                                                System.out.println(mov);

                                            }
                                            break;
                                        }
                                        case 1: {
                                            //Retrocede al menu anterior
                                            repMenuMovimiento = false;
                                           
                                            break;
                                        }

                                    }
                                    if (menuMovC != 1) {

                                        repMenuMovimiento = algunaCosaMas();
                                        
                                    }
                                break;   
        }
                             

                          
                            
                   
                      
    }

public void metodoBanco(boolean repMenuBanco, CuentaCorriente cC, Cliente cliente,Sucursal sucursal,Banco banco) throws SQLException, CuentaCorrienteException{
        for (; repMenuBanco;) {
                        String[] opcionesTit = {"Agregar Cuenta Corriente",                           
                            "Eliminar Cuenta Corriente",
                            "Mostrar Cuenta Corriente",
                            "Volver atrás"};

                        int menuTit = mostrarMenu(opcionesTit);
                        switch (menuTit) {
                            case 0: {
                               // Cliente cliente = new Cliente(0, null, null, null, null, null, null);
                                banco.agregarCuentaCorriente(sucursal);
                                break;
                            }
                            case 1: {
                                //Cliente cliente = new Cliente(0, null, null, null, null, null, null);
                                banco.eliminarCuentaCorriente(cC, sucursal);
                                break;
                            }
                            case 2: {
                                
                                 boolean repMenuMovimientoIncidencia = true;
                                for (; repMenuMovimientoIncidencia;) {
                                    String[] opcionesMovI = {"Mostrar todas las cuenta corriente",
                                        "Mostrar todas las cuenta corriente de sucursal",
                                        "Mostrar todas las cuenta corriente de cliente",
                                        "Volver atrás"};

                                    int menuMovI = mostrarMenu(opcionesMovI);
                                    switch (menuMovI) {
                                        case 0: {
                                            for (CuentaCorriente pCC : banco.mostrarCuentaCorriente()) {
                                                System.out.println(pCC);
                                            }
                                        break;
                                   
                                        }
                                        case 1: {

                                            for (CuentaCorriente pCC : banco.mostrarCuentaCorriente(sucursal)) {
                                                System.out.println(pCC);
                                            }
                                            break;
                                        }
                                        case 2: {

                                            for (CuentaCorriente pCC : banco.mostrarCuentaCorriente(cliente)) {
                                                System.out.println(pCC);
                                            }
                                            break;
                                        }
                                        case 3: {
                                            //Retrocede al menu anterior
                                            repMenuMovimientoIncidencia = false;
                                            menuTit = 4;
                                            break;
                                        }

                                    }
                                    if (menuMovI != 3) {

                                        repMenuMovimientoIncidencia = algunaCosaMas();
                                        menuTit = 4;
                                    }
                                }
                               
                                //banco.mostrarCuentaCorriente(sucursal);
                                break;
                            }
                            case 3: {
                                //Retrocede al menu anterior
                                repMenuBanco = false;
                                break;
                            }

                        }
                        if (menuTit != 3) {

                            repMenuBanco = algunaCosaMas();;

                        }
                    }
    }
}
