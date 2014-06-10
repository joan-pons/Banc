/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;

/**
 *
 * @author bernadi
 */
public class Debito extends Tarjeta {
    private Double saldo;

    public Debito(Double saldo, int codigoTarjeta, Cliente titular, CuentaCorriente cuenta, TipoTarjeta tipo) {
        super(codigoTarjeta, titular, cuenta, tipo);
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
}
