package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.conexion.Conexion;
import java.sql.SQLException;

public class MovimientoTarjeta {

    private String codigoTarjeta;
    private int codigo;
    private String operacion;
    private String fecha;
    private double importe;
    private String concepto;
    private String tipoTarjeta;
    
    public MovimientoTarjeta(String codigoTarjeta, int codigo, String operacion, String fecha, double importe, String concepto, String tipo) {
        this.codigoTarjeta = codigoTarjeta;
        this.codigo = codigo;
        this.operacion = operacion;
        this.fecha = fecha;
        this.importe = importe;
        this.concepto = concepto;
        this.tipoTarjeta = tipo;
    }

    public MovimientoTarjeta(String codigoTarjeta, String operacion, String fecha, double importe, String concepto, String tipo) {
        this.codigoTarjeta = codigoTarjeta;
        this.operacion = operacion;
        this.fecha = fecha;
        this.importe = importe;
        this.concepto = concepto;
        this.tipoTarjeta = tipo;
        try {
            if (tipo == "DEBITO") {
                Conexion.conectar().createStatement().executeUpdate("INSERT INTO movimiento_tarjeta_debito VALUES ('"
                        + codigoTarjeta + "',"
                        + "null,'"
                        + operacion + "',"
                        + "null,"
                        + importe + ",'"
                        + concepto + "')");
            } else {
                Conexion.conectar().createStatement().executeUpdate("INSERT INTO movimiento_tarjeta_credito VALUES ('"
                        + codigoTarjeta + "',"
                        + "null,'"
                        + operacion + "',"
                        + "null,"
                        + importe + ",'"
                        + concepto + "')");
            }
        } catch (SQLException e) {

        }
    }

}
