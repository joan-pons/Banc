package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class Tarjeta {

    public String codigoTarjeta;
    public String codigoTitular;
    public String codigoCuentaCorriente;
    public int codigoSucursal;
    public String tipo;
    public String fechaTarjeta;
/**
 * Constructor para crear un objeto de la clase Tarjeta
 * @param codigoTarjeta 
 * @param codigoTitular
 * @param codigoCuentaCorriente
 * @param codigoSucursal
 * @param tipo
 * @param fechaTarjeta 
 */
    public Tarjeta(String codigoTarjeta, String codigoTitular, String codigoCuentaCorriente, int codigoSucursal, String tipo, String fechaTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
        this.codigoTitular = codigoTitular;
        this.codigoCuentaCorriente = codigoCuentaCorriente;
        this.codigoSucursal = codigoSucursal;
        this.tipo = tipo;
        this.fechaTarjeta = fechaTarjeta;
    }
    
   /**
    * Constructor para recuperar una tarjeta en concreto de la base de datos, 
    * creara un objeto con las especificaciones de la clase
    * @param codigoTarjeta
    * @param tipo 
    */
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
/**
 * Metodo para devover un String con informacion del objeto
 * @return 
 */
    @Override
    public String toString() {
        return "Tarjeta{" + "codigoTarjeta=" + codigoTarjeta + ", codigoTitular=" + codigoTitular + ", codigoCuentaCorriente=" + codigoCuentaCorriente + ", codigoSucursal=" + codigoSucursal + ", tipo=" + tipo + ", fechaTarjeta=" + fechaTarjeta + '}';
    }
/**
 * Metodo para recuperar la Fecha de la tarjeta, devuelve un String
 * @return 
 */
    public String getFechaTarjeta() {
        return fechaTarjeta;
    }
/**
 * Metodo para establecer un valor al atributo FechaTarjeta
 * @param fechaTarjeta 
 */
    public void setFechaTarjeta(String fechaTarjeta) {
        this.fechaTarjeta = fechaTarjeta;
    }
/**
 * Metodo para recuperar el valor de CodigoTarjeta, devuelve un String
 * @return 
 */
    public String getCodigoTarjeta() {
        return codigoTarjeta;
    }
/**
 * Metodo para establecer el valor del atributo codigoTarjeta
 * @param codigoTarjeta 
 */
    public void setCodigoTarjeta(String codigoTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
    }
/**
 * Metodo para recuperar el valor del atributo codigoTitular, devueve un string
 * @return 
 */
    public String getCodigoTitular() {
        return codigoTitular;
    }
/**
 * Metodo para establecer el valor de codigoTitular
 * @param codigoTitular 
 */
    public void setCodigoTitular(String codigoTitular) {
        this.codigoTitular = codigoTitular;
    }
/**
 * Metodo para recuperar el valor de cuentaCorriente,devuelve un string
 * @return 
 */
    public String getCodigoCuentaCorriente() {
        return codigoCuentaCorriente;
    }
/**
 * Metodo para establecer el valor del atributo codigoCuentaCorriente
 * @param codigoCuentaCorriente 
 */
    public void setCodigoCuentaCorriente(String codigoCuentaCorriente) {
        this.codigoCuentaCorriente = codigoCuentaCorriente;
    }
/**
 * metodo para recuperar el valor de codigoSucursal, devuelve un String
 * @return 
 */
    public int getCodigoSucursal() {
        return codigoSucursal;
    }
/**
 * metodo para establecer el valor del atributo codigoSucursal
 * @param codigoSucursal 
 */
    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }
/**
 * metodo para recuperar el valor del atributo tipo, devuelve un string
 * @return 
 */
    public String getTipo() {
        return tipo;
    }
/**
 * metodo para establecer el valor del atributo tipo
 * @param tipo 
 */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
