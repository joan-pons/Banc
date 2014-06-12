/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.Tarjeta;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bernadi
 */
public class Credito extends Tarjeta {

    private Double limite;
    private Double saldo;

    public Credito(Double limite, Double saldo, int codigoTarjeta, Cliente titular, CuentaCorriente cuenta, String tipo) {
        super(codigoTarjeta, titular, cuenta, tipo);
        this.limite = limite;
        this.saldo = saldo;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateTime = date.toString();
       // MovimientoTarjeta m = new MovimientoTarjeta('I', dateTime, concepto, importe, this.codigoTarjeta);
        return true;
    }
}
