
package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Debito extends Tarjeta {

    private Double saldo;

    public Debito(Double saldo, String codigoTarjeta, String codigoTitular, String codigoCuentaCorriente, int codigoSucursal, String tipo, String fechaTarjeta) {
        super(codigoTarjeta, codigoTitular, codigoCuentaCorriente, codigoSucursal, tipo, fechaTarjeta);
        this.saldo = saldo;
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
    
    public Debito(String codigoTarjeta){
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
    
}
