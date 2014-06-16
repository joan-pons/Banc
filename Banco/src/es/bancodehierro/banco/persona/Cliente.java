/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.persona;

import java.util.Date;

/**
 * Clase cliente paragenerar objetos de tipo cliente con toda la informacion
 * referente a un cliente
 *
 * @author Guillem
 */
public class Cliente extends Persona {

    private int idCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Constructor basico de cliente con todos sus parametros
     *
     * @param nombre El nombre del cliente
     * @param apellido1 El primer apellido de un cliente
     * @param apellido2 El segundo apellido de un cliente
     * @param dni El dni del cliente (identificador unico)
     * @param poblacion Poblacion del cliente (debe existir)
     * @param direccion Direccion del cliente
     * @param fechaNacimiento Fecha de nacimiento del cliente
     * @param tlf Telefono del cliente
     */
    public Cliente(String nombre, String apellido1, String apellido2, String dni, String poblacion, String direccion, Date fechaNacimiento, String tlf) {
        super(nombre, apellido1, apellido2, dni, poblacion, direccion, fechaNacimiento, tlf);
        this.idCliente = idCliente;
    }

}
