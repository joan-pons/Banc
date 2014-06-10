/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.central;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.persona.Persona;
import es.bancodehierro.banco.sucursal.Sucursal;

/**
 *
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel
 * Angel Cànaves
 */
public class Banco {

    public boolean agregarCuentaCorriente() {
        return true;
    }

    public boolean modificarCuentaCorriente() {
        return true;
    }

    public boolean eliminarCuentaCorriente() {
        return true;
    }

    public CuentaCorriente mostrarCuentaCorriente() {
        return null;
    }

    public CuentaCorriente mostrarCuentaCorriente(Sucursal sucursal) {
        return null;
    }

    public CuentaCorriente mostrarCuentaCorriente(Cliente cliente) {
        return null;
    }

    //Part del grup de Guillem Arrom, Rotger, Pedro i François
    public Persona devuelvePersona(String dni) {
        return null;
    }

    public Cliente devuelveCliente(String dni) {
        return null;
    }

    public Empleado devuelveEmpleado(String dni) {
        return null;
    }
    
    
    //Fi de la part del grup de Guillem Arrom, Rotger, Pedro i François
}
