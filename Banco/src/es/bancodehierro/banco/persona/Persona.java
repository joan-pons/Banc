/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.persona;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author guillem
 */
public class Persona {
    private String nombre;
    private String apellidos;
    private String dni;
    private String poblacion;
    private String direccion;
    private Date fechaNacimiento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona(String nombre, String apellidos, String dni, String poblacion, String direccion, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.poblacion = poblacion;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }
    
}
