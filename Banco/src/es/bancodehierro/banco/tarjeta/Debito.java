package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 *@author bernadi,marc,cristian,alejandro
 * Clase Debito, subclase de Tarjeta
 */
public class Debito extends Tarjeta {
    
    private Double saldo;
/**
 * Metodo para la agregacion en la base de datos de una tarjeta de debito,
 * pasa los parametros necesarios al constructor de la superclasse
 * @param codigoTitular
 * @param codigoCuentaCorriente
 * @param codigoSucursal 
 */
    public Debito(String codigoTitular, String codigoCuentaCorriente, int codigoSucursal) {
        super(null, codigoTitular, codigoCuentaCorriente, codigoSucursal, null, null);
        try {
            Conexion.conectar().createStatement().executeUpdate("INSERT INTO v_tarjeta_debito VALUES ("
                    + "null"
                    + ",'" + codigoTitular + "'"
                    + ",'" + codigoCuentaCorriente
                    + "'," + codigoSucursal
                    + ",null)");
        } catch (SQLException e) {

        }
    }
/**
 * Constructor para recuperar una tarjeta de credito ya existente en la base de datos, crea un objeto tarjeta.
 * @param codigoTarjeta 
 */
    public Debito(String codigoTarjeta) {
        super(codigoTarjeta, "CREDITO");
        try {
            ResultSet select = Conexion.conectar().createStatement().executeQuery("SELECT * FROM v_tarjeta_debito WHERE codigo_tarjeta = '" + codigoTarjeta + "'");
            while (select.next()) {
                this.saldo = select.getDouble(7);
            }
        } catch (SQLException e) {

        }
    }
/**
 * metodo para recuperar un string con informacion del objeto
 * @return 
 */
    @Override
    public String toString() {
        return "Debito{" + "saldo=" + saldo + '}';
    }
/**
 * metodo para recuperar la informacion del atributo saldo
 * @return 
 */
    public Double getSaldo() {
        return saldo;
    }
/**
 * metodo para especificar el valor de atributo saldo
 * @param saldo 
 */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
/**
 * metodo para pagar con una tarjeta de credito, crea un objeto movimientotarjeta, pasandole los parametros necesarios
 * @param importe
 * @param concepto
 * @return 
 */
    public Boolean pagar(double importe, String concepto) {
        MovimientoTarjeta m = new MovimientoTarjeta(codigoTarjeta, "PAGAR", importe, concepto, "DEBITO");
        return true;
    }
/**
 * metodo realizar un ingreso por tarjeta de cfedito, crea un objeto movimiento tarjeta pasandole los parametros necesarios
 * @param importe
 * @param concepto
 * @return 
 */
    public Boolean ingresar(double importe, String concepto) {
        MovimientoTarjeta m = new MovimientoTarjeta(codigoTarjeta, "INGRESAR", importe, concepto, "CREDITO");
        return true;
    }
/**
 * metodo para ver los movimientos de una tarjeta de debito
 */
    public void verMovimientosDebito() {
        try {
            ResultSet rs = Conexion.conectar().createStatement().executeQuery("SELECT CODIGO_TARJETA,CODIGO_MTD,OPERACION_MTD,to_char(FECHA_MTD),IMPORTE_MTD,CONCEPTO_MTD FROM MOVIMIENTO_TARJETA_DEBITO WHERE CODIGO_TARJETA='" + codigoTarjeta + "'");
            while (rs.next()) {
                System.out.println("Codigo tarjeta: " + rs.getString(1) + ", Codigo Movimiento: " + rs.getInt(2) + ", Operacion: " + rs.getString(3) + ", Fecha: " + rs.getString(4) + ", Importe: " + rs.getDouble(5) + ", Concepto: " + rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }

    }

}
