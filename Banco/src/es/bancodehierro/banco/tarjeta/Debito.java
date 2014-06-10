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

    public Debito(int codigoTarjeta, Cliente titular, CuentaCorriente cuentaCorriente, TipoTarjeta tipoTarjeta) {
        super(codigoTarjeta, titular, cuentaCorriente, tipoTarjeta);
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
}
