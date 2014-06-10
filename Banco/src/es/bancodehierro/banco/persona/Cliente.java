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

    public Cliente(int idCliente, String nombre, String apellidos, String dni, String poblacion, String direccion, Date fechaNacimiento) {
        super(nombre, apellidos, dni, poblacion, direccion, fechaNacimiento);
        this.idCliente = idCliente;
    }

}
