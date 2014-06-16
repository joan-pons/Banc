/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.cc;

import es.bancodehierro.banco.enumeraciones.EnumMovimiento;
import java.util.Date;

/**
 *
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel Angel Cànaves
 */
public class Movimiento {
    /**
     * El tipo de movimiento: TARJETA_CREDITO,TARJETA_DEBITO,CUENTA_CORRIENTE,PRESTAMO.
     */
    private EnumMovimiento tipo;
    /**
     * El código de cada movimiento.
     */
    private int codigo;
    /**
     * El concepto que tendrá el movimiento.
     */
    private String concepto;
    /**
     * El importe de cada movimiento.
     */
    private double importe;
    /**
     * La fecha en la que se realiza el movimiento.
     */
    private Date fecha;


    /**
     * Devuelve el valor del código de movimiento.
     * @return Un integer con el código del movimiento.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Devuelve el valor del concepto de movimiento.
     * @return Un string con el concepto del movimiento. 
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Devuelve el valor de la fecha en la que se a realizado el movimiento.
     * @return Un date con la fecha del movimiento.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Devuelve el valor del importe de un movimiento.
     * @return Un double con el importe del movimiento.
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Devuelve el valor del tipo de movimeinto.
     * @return El tipo de movimiento.
     */
    public EnumMovimiento getTipo() {
        return tipo;
    }

    /**
     * Constructor de un movimiento.
     * @param tipo El tipo de movimiento.
     * @param codigo El código de cada movimiento.
     * @param concepto El concepto que tendrá el movimiento.
     * @param importe La fecha en la que se realiza el movimiento.
     * @param fecha La fecha en la que se realiza el movimiento.
     * @param incidencia Si es una incidencia o no.
     */
    public Movimiento(EnumMovimiento tipo, int codigo, String concepto, double importe, Date fecha) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.concepto = concepto;
        this.importe = importe;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "Tipo: " + tipo + " ..... Concepto: " + concepto + " Importe: " + importe + "€ Fecha: " + fecha +  "\n";
    }
    
    
    
    
    
}
