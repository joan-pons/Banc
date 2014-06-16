/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.excepciones;

/**
 * Excepcion de existencia en tarjetas.
 * @author grupo tarjetas
 */
public class TarjetaExistenteException extends Exception {

    public TarjetaExistenteException() {
    }

    public TarjetaExistenteException(String msg) {
        super(msg);
    }
    
    
}
