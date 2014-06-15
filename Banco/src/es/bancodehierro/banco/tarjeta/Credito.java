package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.conexion.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Clase credito, subclase de Tarjeta.
 * @author bernadi,marc,cristian,alejandro
 */
public class Credito extends es.bancodehierro.banco.tarjeta.Tarjeta {

    private Double limite;
    private Double saldo;
/**
 * Metodo para la agregacion en la base de datos de una tarjeta de credito
 * pasa los parametros necesarios al constructor de la superclasse
 * @param codigoTitular
 * @param codigoCuentaCorriente
 * @param codigoSucursal
 * @param limite 
 */
    public Credito(String codigoTitular, String codigoCuentaCorriente, int codigoSucursal, double limite) {
        super(null, codigoTitular, codigoCuentaCorriente, codigoSucursal, null, null);
        this.limite = limite;
        try {
            Conexion.conectar().createStatement().executeUpdate("INSERT INTO v_tarjeta_credito VALUES ("
                    + "null"
                    + ",'" + codigoTitular + "'"
                    + ",'" + codigoCuentaCorriente
                    + "'," + codigoSucursal
                    + ",null,"
                    + limite + ")");
        } catch (SQLException e) {

        }
    }
/**
 * Constructor para recuperar una tarjeta de credito ya existente en la base de datos, crea un objeto tarjeta.credito
 * @param codigoTarjeta 
 */
    public Credito(String codigoTarjeta) {
        super(codigoTarjeta, "CREDITO");
        try {
            ResultSet select = Conexion.conectar().createStatement().executeQuery("SELECT * FROM v_tarjeta_credito WHERE codigo_tarjeta = '" + codigoTarjeta + "'");
            while (select.next()) {
                this.limite = select.getDouble(6);
                this.saldo = select.getDouble(7);
            }
        } catch (SQLException e) {

        }
    }
/**
 * Metodo para recuperar el valor del atributo Limite.
 * @return 
 */
    public Double getLimite() {
        return limite;
    }
/**
 * Metodo para recuperar el valor del atributo Saldo
 * @return 
 */
    public Double getSaldo() {
        return saldo;
    }
/**
 * metodo para recuperar un string con informacion del objeto
 * @return 
 */
    @Override
    public String toString() {
        return "Credito{" + "limite=" + limite + ", saldo=" + saldo + '}';
    }
    
/**
 * Metodo para establecer el valor del atributo limite
 * @param limite 
 */
    public void setLimite(Double limite) {
        this.limite = limite;
    }
/**
 * Metodo para establecer el valor del atributo saldo
 * @param saldo 
 */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
/**
 * Metodo para pagar con la tarjeta de credito, recube el importe y el concepto, crea un objeto MoimientoTarjeta, estableciendo los argumentos.
 * @param importe
 * @param concepto
 * @return 
 */
    public Boolean pagar(double importe, String concepto) {
        MovimientoTarjeta m = new MovimientoTarjeta(codigoTarjeta,"PAGAR",importe,concepto,"CREDITO");
        return true;
    }
/**
 * Metodo para ver los movimientos de una determinada tarjeta de credito
 */
    public void verMovimientosCredito(){
       try {
            ResultSet rs = Conexion.conectar().createStatement().executeQuery("SELECT CODIGO_TARJETA,"
                    + "CODIGO_MTC,"
                    + "OPERACION_MTC,"
                    + "to_char(FECHA_MTC),"
                    + "IMPORTE_MTC,"
                    + "CONCEPTO_MTC "
                    + "FROM MOVIMIENTO_TARJETA_CREDITO "
                    + "WHERE CODIGO_TARJETA='"+codigoTarjeta+"'");
            while(rs.next()){
                System.out.println(
                        "Codigo tarjeta: "+rs.getString(1)+
                        ", Codigo Movimiento: "+rs.getInt(2)+
                        ", Operacion: "+rs.getString(3)+
                        ", Fecha: "+rs.getString(4)+
                        ", Importe: "+rs.getDouble(5)+
                        ", Concepto: "+rs.getString(6));
            }
        } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
        
    }
}
