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
    private EnumMovimiento tipo;
    private int codigo;
    private String concepto;
    private double importe;
    private Date fecha;
    private boolean incidencia;

    public int getCodigo() {
        return codigo;
    }

    public String getConcepto() {
        return concepto;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getImporte() {
        return importe;
    }

    public boolean isIncidencia() {
        return incidencia;
    }

    public EnumMovimiento getTipo() {
        return tipo;
    }

    public Movimiento(EnumMovimiento tipo, int codigo, String concepto, double importe, Date fecha, boolean incidencia) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.concepto = concepto;
        this.importe = importe;
        this.fecha = fecha;
        this.incidencia = incidencia;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "Tipo: " + tipo + " ........... Concepto: " + concepto + " Importe: " + importe + "€ Fecha: " + fecha + "Incidencia: " + incidencia + "\n";
    }
    
    
    
    
    
}
