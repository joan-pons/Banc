package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import oracle.sql.TIMESTAMP;

public class Tarjeta {

    String codigoTarjeta;
    private String codigoTitular;
    private String codigoCuentaCorriente;
    private int codigoSucursal;
    private String tipo;
    private String fechaTarjeta;

    public Tarjeta(String codigoTarjeta, String codigoTitular, String codigoCuentaCorriente, int codigoSucursal, String tipo, String fechaTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
        this.codigoTitular = codigoTitular;
        this.codigoCuentaCorriente = codigoCuentaCorriente;
        this.codigoSucursal = codigoSucursal;
        this.tipo = tipo;
        this.fechaTarjeta = fechaTarjeta;
    }
    
    public Tarjeta(String codigoTarjeta, String tipo){
        this.codigoTarjeta = codigoTarjeta;
        try {
            ResultSet select = Conexion.conectar().createStatement().executeQuery("SELECT * FROM v_tarjeta_credito WHERE codigo_tarjeta = '" + codigoTarjeta + "'");
            while (select.next()) {
                this.codigoTitular = select.getString(2);
                this.codigoCuentaCorriente = select.getString(3);
                this.codigoSucursal = select.getInt(4);
                this.tipo = tipo;
                this.fechaTarjeta = new SimpleDateFormat("yyyy/MM/dd").format(select.getTimestamp(5));
            }
        } catch (SQLException e) {

        }
    }

  

    public String getFechaTarjeta() {
        return fechaTarjeta;
    }

    public void setFechaTarjeta(String fechaTarjeta) {
        this.fechaTarjeta = fechaTarjeta;
    }

    public String getCodigoTarjeta() {
        return codigoTarjeta;
    }

    public void setCodigoTarjeta(String codigoTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
    }

    public String getCodigoTitular() {
        return codigoTitular;
    }

    public void setCodigoTitular(String codigoTitular) {
        this.codigoTitular = codigoTitular;
    }

    public String getCodigoCuentaCorriente() {
        return codigoCuentaCorriente;
    }

    public void setCodigoCuentaCorriente(String codigoCuentaCorriente) {
        this.codigoCuentaCorriente = codigoCuentaCorriente;
    }

    public int getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
