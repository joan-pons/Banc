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

    public Empleado(int idEmpleado, EnumCargo cargo, String nombre, String apellidos, String dni, String poblacion, String direccion, Date fechaNacimiento) {
        super(nombre, apellidos, dni, poblacion, direccion, fechaNacimiento);
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
    }



}
