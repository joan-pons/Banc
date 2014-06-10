package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;

public class GestionTarjetas {

    public Boolean altaTarjeta(int codigoTarjeta, Cliente titular, CuentaCorriente cuentaCorriente, TipoTarjeta tipoTarjeta, Double limite) {
        if(limite==null){
            Debito d = new Debito(codigoTarjeta,titular,cuentaCorriente, tipoTarjeta);
        } else {
            Credito d = new Credito(codigoTarjeta,titular,cuentaCorriente, tipoTarjeta, limite);
        }
        return null;
    }
}
