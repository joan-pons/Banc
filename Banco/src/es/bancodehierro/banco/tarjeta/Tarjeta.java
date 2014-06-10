/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.tarjeta;

/**
 *
 * @author Marc
 */
public abstract class  Tarjeta {

    
    private int codigoTarjeta;
    private Cliente titular;
    private CuentaCorriente cuenta;
    private  TipoTarjeta tipo;
    
    /**
     * Constructor per a nova tarjeta
     * @param codigoTarjeta
     * @param titular
     * @param cuenta
     * @param tipo 
     */
    public Tarjeta(int codigoTarjeta, Cliente titular, CuentaCorriente cuenta, TipoTarjeta tipo) {
        this.codigoTarjeta = codigoTarjeta;
        this.titular = titular;
        this.cuenta = cuenta;
        this.tipo = tipo;
        
        
    }

    public void setCodigoTarjeta(int codigoTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public void setCuenta(CuentaCorriente cuenta) {
        this.cuenta = cuenta;
    }

    public void setTipo(TipoTarjeta tipo) {
        this.tipo = tipo;
    }

    public int getCodigoTarjeta() {
        return codigoTarjeta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public CuentaCorriente getCuenta() {
        return cuenta;
    }

    public TipoTarjeta getTipo() {
        return tipo;
    }
}
