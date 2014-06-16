/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.persona;

import es.bancodehierro.banco.enumeraciones.EnumCargo;
import java.util.Date;

/**
 * Clase empleado paragenerar objetos de tipo empleado con toda la informacion
 * referente a un empleado
 *
 * @author Guillem
 */
public class Empleado extends Persona {

    private int idEmpleado;
    private EnumCargo cargo;
    private int sucursal;

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

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

    /**
     * Constructor basico de empleado con todos sus parametros
     *
     * @param cargo El cargo del empleado
     * @param nombre El nombre del empleado
     * @param apellido1 El primer apellido de un empleado
     * @param apellido2 El segundo apellido de un empleado
     * @param dni El dni del empleado (identificador unico)
     * @param poblacion Poblacion del empleado (debe existir)
     * @param direccion Direccion del empleado
     * @param fechaNacimiento Fecha de nacimiento del empleado
     * @param tlf Telefono del empleado
     */
    public Empleado(EnumCargo cargo, String nombre, String apellido1, String apellido2, String dni, String poblacion, String direccion, Date fechaNacimiento, String tlf, int sucursal) {
        super(nombre, apellido1, apellido2, dni, poblacion, direccion, fechaNacimiento, tlf);
        this.cargo = cargo;
        this.sucursal = sucursal;
    }
}
