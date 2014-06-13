package es.bancodehierro.banco.Tarjeta;

import es.bancodehierro.banco.conexion.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Credito extends es.bancodehierro.banco.tarjeta.Tarjeta {

    private Double limite;
    private Double saldo;

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

    public Double getLimite() {
        return limite;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean pagar(double importe, String concepto) {
        MovimientoTarjeta m = new MovimientoTarjeta(codigoTarjeta,"PAGAR",importe,concepto,"CREDITO");
        return true;
    }
}
