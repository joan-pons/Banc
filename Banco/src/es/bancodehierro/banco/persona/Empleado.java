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
public class Empleado extends Persona {

    private int idEmpleado;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(String nombre, String apellidos, String dni, String poblacion, String direccion, Date fechaNacimiento) {
        super(nombre, apellidos, dni, poblacion, direccion, fechaNacimiento);
    }

}
