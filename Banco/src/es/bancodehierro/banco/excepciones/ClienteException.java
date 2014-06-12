/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.excepciones;

/**
 * Excepción que se lanza cuando un cliente no sea encontrado.
 * @author Pau Riera
 */
public class ClienteException extends Exception {

    /**
     * Creates a new instance of <code>ClienteNoEncontrado</code> without detail
     * message.
     */
    public ClienteException() {
    }

    /**
     * Constructs an instance of <code>ClienteNoEncontrado</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ClienteException(String msg) {
        super(msg);
    }
}
