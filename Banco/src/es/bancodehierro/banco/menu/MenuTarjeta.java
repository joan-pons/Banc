package es.bancodehierro.banco.menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import es.bancodehierro.banco.tarjeta.Credito;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.tarjeta.Debito;
import es.bancodehierro.banco.tarjeta.GestionTarjetas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antonio
 */
public class MenuTarjeta {

    public void altaTarjeta() {
        //Obtener el cliente.
        String codigoCliente = GestionaMenu.llegirCadena("Introdueix el codi del client.");
        int clienteEncontrado = 0;
        while (clienteEncontrado == 0) {
            try {
                Statement st = Conexion.conectar().createStatement();
                String selectCliente = "select count(*) from Cliente where DNI_CLIENTE_TARJETA='" + codigoCliente + "'";
                ResultSet rs = st.executeQuery(selectCliente);
                rs.next();
                clienteEncontrado = rs.getInt(1);
                st.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
            }
            if (clienteEncontrado == 1) {
                System.out.println("Cliente encontrado.");
            } else {
                System.out.println("Cliente no encontrado, introduzca un codigo de cliente existente.");
            }
        }
        //Obtener cuenta corriente.
        int codigoSucursal = GestionaMenu.llegirSencer("Introduzca el código de sucursal");
        String codigoCuenta = GestionaMenu.llegirCadena("Introduzca el código de cuenta corriente");
        int cuentaEncontrada = 0;
        while (cuentaEncontrada == 0) {
            try {
                Statement st = Conexion.conectar().createStatement();
                String selectCuenta = "select count(*) from CuentaCorriente where CODIGO_SUC_TARJETA=" + codigoSucursal + " and NUMERO_CC_TARJETA='" + codigoCuenta + "'";
                ResultSet rs = st.executeQuery(selectCuenta);
                rs.next();
                cuentaEncontrada = rs.getInt(1);
                st.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
            }
            if (cuentaEncontrada == 1) {
                System.out.println("Cuenta encontrada.");
            } else {
                System.out.println("Cuenta no encontrada, introduzca un codigo sucursal y cuenta existente.");
            }
        }

        //pedir tipo
        String tipo;
        do {
            tipo = GestionaMenu.llegirCadena("Introduce el tipo de tarjeta (DEBITO/CREDITO)/");
        } while (tipo.toUpperCase() == "CREDITO" || tipo.toUpperCase() == "DEBITO");

        //pedir limite
        Double limite = 0.0;
        if (tipo.toUpperCase() == "CREDITO") {
            do {
                limite = GestionaMenu.llegirDouble("Introduce el límite de la tarjeta.");
            } while (limite < 0);
        }
        //llamar al metodo de GestionTarjetas
        //GestionTarjetas.altaTarjeta(codigoCliente,codigoSucursal,codigoCuenta,tipo.toUpperCase(),limite);

    }

    /**
     * Metodo que sirve para recoger los datos que luego utilizaremos en el
     * metodo para eliminar una tarjeta de la base de datos.
     */
    public void eliminarTarjeta() {
        String codigoTarjeta;
        boolean existe;
        do {
            codigoTarjeta = GestionaMenu.llegirCadena("Introduce la ID de la tarjeta: ");
            existe = comprobarTarjeta(codigoTarjeta);
        } while (existe);
        String selectTarjeta = "SELECT COUNT(TARJETA_CREDITO.CODIGO_TARJETA_CREDITO), "
                + "COUNT(TARJETA_DEBITO.CODIGO_TARJETA_DEBITO) FROM TARJETA "
                + "ALTER JOIN TARJETA_CREDITO ON TARJETA_CREDITO.CODIGO_TARJETA_CREDITO = TARJETA.CODIGO_TARJETA "
                + "ALTER JOIN TARJETA_DEBITO ON TARJETA_DEBITO.CODIGO_TARJETA_DEBITO = TARJETA.CODIGO_TARJETA"
                + "WHERE TARJETA.CODIGO_TARJETA = '" + codigoTarjeta + "' ";
        Statement st;
        try {
            st = Conexion.conectar().createStatement();
            ResultSet rs;
            rs = st.executeQuery(selectTarjeta);
            rs.next();
            int credit = rs.getInt(1);
            int debit = rs.getInt(2);
            rs.close();
            st.close();
            if (credit == 1) {
                rs = Conexion.conectar().createStatement().executeQuery("SELECT CODIGO_TARJETA,CODIGO_MTC,OPERACION_MTC,to_char(FECHA_MTC),IMPORTE_MTC,CONCEPTO_MTC FROM MOVIMIENTO_TARJETA_CREDITO WHERE CODIGO_TARJETA='" + codigoTarjeta + "'");
                while (rs.next()) {
                    System.out.println("Codigo tarjeta: " + rs.getString(1) + ", Codigo Movimiento: " + rs.getInt(2) + ", Operacion: " + rs.getString(3) + ", Fecha: " + rs.getString(4) + ", Importe: " + rs.getDouble(5) + ", Concepto: " + rs.getString(6));
                }
                rs.close();

            } else {
                rs = Conexion.conectar().createStatement().executeQuery("SELECT CODIGO_TARJETA,CODIGO_MTD,OPERACION_MTD,to_char(FECHA_MTD),IMPORTE_MTD,CONCEPTO_MTD FROM MOVIMIENTO_TARJETA_DEBITO WHERE CODIGO_TARJETA='" + codigoTarjeta + "'");
                while (rs.next()) {
                    System.out.println("Codigo tarjeta: " + rs.getString(1) + ", Codigo Movimiento: " + rs.getInt(2) + ", Operacion: " + rs.getString(3) + ", Fecha: " + rs.getString(4) + ", Importe: " + rs.getDouble(5) + ", Concepto: " + rs.getString(6));
                }
                rs.close();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (GestionaMenu.llegirCadena("Introduce la ID de la tarjeta(SI/NO: ").toUpperCase()=="SI"){
            //eliminarTarjeta(codigoTarjeta);
        }
        
    }

    /**
     * Metodo que sirve para recoger los datos que luego utilizaremos en el
     * metodo para pagar con las tarjetas.
     */
    public void pagar() {
        String codigoTarjeta;
        boolean existe;

        try {
            do {
                codigoTarjeta = GestionaMenu.llegirCadena("Introduce la ID de la tarjeta: ");
                existe = comprobarTarjeta(codigoTarjeta);
            } while (existe);
            Statement st = Conexion.conectar().createStatement();
            String concepto = GestionaMenu.llegirCadena("Introduce el concepto (opcional): ");
            Double importe = GestionaMenu.llegirDouble("Introduce el importe a pagar: ");
            String selectTarjeta = "SELECT COUNT(TARJETA_CREDITO.CODIGO_TARJETA_CREDITO), "
                    + "COUNT(TARJETA_DEBITO.CODIGO_TARJETA_DEBITO) FROM TARJETA "
                    + "ALTER JOIN TARJETA_CREDITO ON TARJETA_CREDITO.CODIGO_TARJETA_CREDITO = TARJETA.CODIGO_TARJETA "
                    + "ALTER JOIN TARJETA_DEBITO ON TARJETA_DEBITO.CODIGO_TARJETA_DEBITO = TARJETA.CODIGO_TARJETA"
                    + "WHERE TARJETA.CODIGO_TARJETA = '" + codigoTarjeta + "' ";
            ResultSet rs;
            rs = st.executeQuery(selectTarjeta);
            rs.next();
            int credit = rs.getInt(1);
            int debit = rs.getInt(2);
            rs.close();
            st.close();

            /*Si resulta que es de credit. */
            if (credit == 1) {
                String selectMaxCredito = "SELECT MAX_CREDITO FROM TARJETA_CREDITO"
                        + "WHERE CODIGO_TARJETA_CREDITO = '" + codigoTarjeta + "' ";
                rs = st.executeQuery(selectMaxCredito);
                rs.next();
                int maxCredit = rs.getInt(1);
                rs.close();
                st.close();
                String selectSaldo = "SELECT SALDO FROM v_targeta_credit "
                        + "WHERE CODIGO_TARJETA = '" + codigoTarjeta + "'";
                rs = st.executeQuery(selectSaldo);
                rs.next();
                int importeAcumulado = rs.getInt(1);
                rs.close();
                st.close();
                /*Sumamos el importe que llevamos acumulado mas el importe que se quiere pagar. */
                Double importeActual = importeAcumulado + importe;
                if (importeActual > maxCredit) {
                    System.out.println("No es pot fer, ja ha superat el credit maxim. ");
                } else {
                    Credito tarjetaCredito = new Credito(codigoTarjeta);
                    tarjetaCredito.pagar(importe, concepto);
                }
            } /*Si resulta que es de debit. */ else if (debit == 1) {
                String selectSaldo = "SELECT SALDO FROM v_targeta_debit"
                        + "WHERE CODIGO_TARJETA = '" + codigoTarjeta + "' ";
                rs = st.executeQuery(selectSaldo);
                rs.next();
                int saldo = rs.getInt(1);
                rs.close();
                st.close();
                Double resultat = saldo - importe;
                if (resultat < 0) {
                    System.out.println("No se puede realizar la operacion, falta saldo.");
                } else {
                    Debito tarjetaDebito = new Debito(codigoTarjeta);
                    tarjetaDebito.pagar(importe, concepto);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }

    }

    /**
     * Metodo que sirve para recoger los datos que luego utilitzaremos en el
     * metodo para ingresar en tarjetas de debito.
     */
    public void ingresarDebito() {
        String codigoTarjeta;
        boolean existe;
        do {
            codigoTarjeta = GestionaMenu.llegirCadena("Introduce la ID de la tarjeta: ");
            existe = comprobarTarjeta(codigoTarjeta);
        } while (existe);
        double importe = GestionaMenu.llegirDouble("Introduce el importe a ingresar: ");
        String concepto = GestionaMenu.llegirCadena("Introduce el concepto (opcional): ");
        Debito tarjetaDebito = new Debito(codigoTarjeta);
        tarjetaDebito.ingresar(importe, concepto);
    }

    /**
     * Metodo que sirve para recoger los datos que luego utilizaremos en el
     * metodo para ver los movimientos de las tarjetas.
     */
    public void verMovimientos() {
        String codigoTarjeta;
        boolean existe;
        do {
            codigoTarjeta = GestionaMenu.llegirCadena("Introduce la ID de la tarjeta: ");
            existe = comprobarTarjeta(codigoTarjeta);
        } while (existe);
        try {
            Statement st = Conexion.conectar().createStatement();
            String selectTarjeta = "SELECT COUNT(TARJETA_CREDITO.CODIGO_TARJETA_CREDITO), "
                    + "COUNT(TARJETA_DEBITO.CODIGO_TARJETA_DEBITO) FROM TARJETA "
                    + "ALTER JOIN TARJETA_CREDITO ON TARJETA_CREDITO.CODIGO_TARJETA_CREDITO = TARJETA.CODIGO_TARJETA "
                    + "ALTER JOIN TARJETA_DEBITO ON TARJETA_DEBITO.CODIGO_TARJETA_DEBITO = TARJETA.CODIGO_TARJETA"
                    + "WHERE TARJETA.CODIGO_TARJETA = '" + codigoTarjeta + "' ";
            ResultSet rs;
            rs = st.executeQuery(selectTarjeta);
            rs.next();
            int credit = rs.getInt(1);
            int debit = rs.getInt(2);
            rs.close();
            st.close();
            if (credit == 1) {
                Credito tarjetaCredito = new Credito(codigoTarjeta);
                tarjetaCredito.verMovimientosCredito();
            } else {
                Debito tarjetaDebito = new Debito(codigoTarjeta);
                tarjetaDebito.verMovimientosDebito();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionTarjetas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Este metodo sirve para comprovar si una tarjeta existe en la base de
     * datos.
     *
     * @param codigoTarjeta El codigo de la tarjeta.
     * @return Devuelve un boolean (false si la encuentra o true si no).
     */
    public Credito devolverTarjetaCredito(String codigoTarjeta) {
        boolean existe;
        do {
            if (codigoTarjeta == null) {
                codigoTarjeta = GestionaMenu.llegirCadena("Introduce la ID de la tarjeta: ");
            }
            existe = comprobarTarjeta(codigoTarjeta);
            if (!existe) {
                System.out.println("ID de tarjeta erróneo, introduzca uno valido.");
            }
        } while (existe);
        Credito tarjetaCredito = new Credito(codigoTarjeta);
        System.out.println(tarjetaCredito);
        return tarjetaCredito;
    }

    public Debito devolverTarjetaDebito(String codigoTarjeta) {
        boolean existe;
        do {
            if (codigoTarjeta == null) {
                codigoTarjeta = GestionaMenu.llegirCadena("Introduce la ID de la tarjeta: ");
            }
            existe = comprobarTarjeta(codigoTarjeta);
            if (!existe) {
                System.out.println("ID de tarjeta erróneo, introduzca uno valido.");
            }
        } while (existe);
        Debito tarjetaDebito = new Debito(codigoTarjeta);
        System.out.println(tarjetaDebito);
        return tarjetaDebito;
    }

    public boolean comprobarTarjeta(String codigoTarjeta) {
        boolean flag = true;
        try {
            Statement st = Conexion.conectar().createStatement();
            String selectTarjeta = "SELECT COUNT(*) FROM TARJETA WHERE CODIGO_TARJETA = '" + codigoTarjeta + "'";
            ResultSet rs = st.executeQuery(selectTarjeta);
            rs.next();
            int existeix = rs.getInt(1);
            if (existeix == 1) {
                System.out.println("Tarjeta encontrada!");
                flag = false;
            } else {
                System.out.println("Tarjeta no encontrada!");
                flag = true;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
        return flag;
    }

    public void ejecutarMenu() {
        boolean flag = true;
        do {
            System.out.println("Opcion 1: Dar de alta una tarjeta.");
            System.out.println("Opcion 2: Eliminar una tarjeta.");
            System.out.println("Opcion 3: Realizar un pago.");
            System.out.println("Opcion 4: Ingresar (sólo débito).");
            System.out.println("Opcion 5: Ver movimientos tarjeta.");
            System.out.println("Opcion 6: Salir.");
            int opcion = GestionaMenu.llegirSencer("Introduce la opción: ");
            switch (opcion) {
                case 1: {
                    altaTarjeta();
                    break;
                }
                case 2: {
                    eliminarTarjeta();
                    break;
                }
                case 3: {
                    pagar();
                    break;
                }
                case 4: {
                    ingresarDebito();
                    break;
                }
                case 5: {
                    verMovimientos();
                    break;
                }
                case 6: {
                    flag = false;
                    break;
                }
            }

        } while (flag);
    }

}
