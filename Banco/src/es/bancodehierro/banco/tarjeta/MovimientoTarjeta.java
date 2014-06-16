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
    /**
     * constructor para la creacion de un objeto tarjeta
     * @param codigoTarjeta codigo de la tarjeta
     * @param codigo codigo identificador del moimiento
     * @param operacion tipo de operacion a realizar
     * @param fecha fecha del movimiento (creacion)
     * @param importe importe del movimiento
     * @param concepto concempto del movimiento
     * @param tipo el tipo de tarjeta
     */
    public MovimientoTarjeta(String codigoTarjeta, int codigo, String operacion, String fecha, double importe, String concepto, String tipo) {
        this.codigoTarjeta = codigoTarjeta;
        this.codigo = codigo;
        this.operacion = operacion;
        this.fecha = fecha;
        this.importe = importe;
        this.concepto = concepto;
        this.tipoTarjeta = tipo;
    }
/**
 * constructor que genera un objeto del tipo movimiento tarjeta y posteriormente lo inserta en la base de datos como nueva tarjeta.
 * la propia base de datos debe comprovar si existe ya la tarjeta y hacer las comprobaciones
 * @param codigoTarjeta codigo de la tarjeta
 * @param operacion tipo de operacion
 * @param importe importe de la operacion
 * @param concepto concepto del movimiento
 * @param tipo tipo de tarjeta de credito
 */
    public MovimientoTarjeta(String codigoTarjeta, String operacion, double importe, String concepto, String tipo) {
        this.codigoTarjeta = codigoTarjeta;
        this.operacion = operacion;
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
