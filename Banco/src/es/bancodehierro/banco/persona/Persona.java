/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.persona;
import java.util.Date;

/**
 * Clase Persona para generar englobar metodos y variables comunes entre
 * empleado y cliente
 *
 * @author Guillem
 */
public class Persona {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private String poblacion;
    private String direccion;
    private Date fechaNacimiento;
    private String tlf;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Constructor basico de persona con todos sus parametros que sera llamado por sus subclases.
     *
     * @param nombre El nombre de la persona
     * @param apellido1 El primer apellido de una persona
     * @param apellido2 El segundo apellido de una persona
     * @param dni El dni de la persona (identificador unico)
     * @param poblacion Poblacion de la persona (debe existir)
     * @param direccion Direccion de la persona
     * @param fechaNacimiento Fecha de nacimiento de la persona
     * @param tlf Telefono de la persona
     */
    public Persona(String nombre, String apellido1, String apellido2, String dni, String poblacion, String direccion, Date fechaNacimiento, String tlf) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.poblacion = poblacion;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.tlf = tlf;
    }

}
