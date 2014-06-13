package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Debito extends Tarjeta {

    private Double saldo;

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

    @Override
    public String toString() {
        return "Debito{" + "saldo=" + saldo + '}';
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean pagar(double importe, String concepto) {
        MovimientoTarjeta m = new MovimientoTarjeta(codigoTarjeta, "PAGAR", importe, concepto, "DEBITO");
        return true;
    }

    public Boolean ingresar(double importe, String concepto) {
        MovimientoTarjeta m = new MovimientoTarjeta(codigoTarjeta, "INGRESAR", importe, concepto, "CREDITO");
        return true;
    }

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
