/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.excepciones;

/**
 *
 * @author Roberto
 */
public class CuentaCorrienteException extends Exception {

    /**
     * Creates a new instance of <code>CuentaCorrienteException</code> without
     * detail message.
     */
    public CuentaCorrienteException() {
    }

    /**
     * Constructs an instance of <code>CuentaCorrienteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CuentaCorrienteException(String msg) {
        super(msg);
    }
}
