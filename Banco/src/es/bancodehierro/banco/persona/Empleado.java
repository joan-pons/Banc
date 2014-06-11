/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.persona;

import es.bancodehierro.banco.enumeraciones.EnumCargo;
import java.util.Date;

/**
 *
 * @author guillem
 */
public class Empleado extends Persona {

    private int idEmpleado;
    private EnumCargo cargo;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public EnumCargo getCargo() {
        return cargo;
    }

    public void setCargo(EnumCargo cargo) {
        this.cargo = cargo;
    }

    public Empleado(int idEmpleado, EnumCargo cargo, String nombre, String apellido1, String apellido2, String dni, String poblacion, String direccion, Date fechaNacimiento, String tlf) {
        super(nombre, apellido1, apellido2, dni, poblacion, direccion, fechaNacimiento, tlf);
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
    }



}
