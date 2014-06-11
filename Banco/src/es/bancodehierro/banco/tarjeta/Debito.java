/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bernadi
 */
public class Debito extends Tarjeta {

    private Double saldo;

    public Debito(int codigoTarjeta, Cliente titular, CuentaCorriente cuentaCorriente, TipoTarjeta tipoTarjeta) {
        super(codigoTarjeta, titular, cuentaCorriente, tipoTarjeta);
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean pagar(double importe, String concepto) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateTime = date.toString();
        MovimientoTarjeta m = new MovimientoTarjeta('P', dateTime, concepto, importe, this.codigoTarjeta);
        return true;
    }

    public Boolean ingresar(double importe, String concepto) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateTime = date.toString();
        MovimientoTarjeta m = new MovimientoTarjeta('I', dateTime, concepto, importe, this.codigoTarjeta);
        return true;
    }
}
