package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;

public class GestionTarjetas {

    public Boolean altaTarjeta(Cliente titular, CuentaCorriente cuentaCorriente, TipoTarjeta tipoTarjeta, Double limite) {
        if (limite == null) {
            //Debito d = new Debito(titular, cuentaCorriente, tipoTarjeta);
        } else {
           // Credito d = new Credito(titular, cuentaCorriente, tipoTarjeta, limite);
        }
        return null;
    }

    public Boolean eliminarTarjeta(int codigoTarjeta) {
        
        return null;
    }

    public Boolean ingresarDebito(Double importe, String concepto, int codigoTarjeta) {
        return null;
    }

    public Boolean verMovimientos(int codigoTarjeta) {
        return null;
    }
}
