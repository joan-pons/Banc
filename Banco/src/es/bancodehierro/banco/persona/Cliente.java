/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.persona;

import java.util.Date;

/**
 *
 * @author guillem
 */
public class Cliente extends Persona {

    private int idCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(int idCliente, String nombre, String apellido1, String apellido2, String dni, String poblacion, String direccion, Date fechaNacimiento, String tlf) {
        super(nombre, apellido1, apellido2, dni, poblacion, direccion, fechaNacimiento, tlf);
        this.idCliente = idCliente;
    }

}
