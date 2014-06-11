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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ArrayList<CuentaCorriente> mostrarCuentaCorriente() {
        return null;
    }

    public ArrayList<CuentaCorriente> mostrarCuentaCorriente(Sucursal sucursal) {
        return null;
    }

    public ArrayList<CuentaCorriente> mostrarCuentaCorriente(Cliente cliente) {
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
    
    public static boolean insertarCliente(Cliente cliente) {
        boolean sortida = false;
        try {
        //no se le pasa

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente where DNI_CLIENTE =" + cliente.getIdCliente());

            if (rs.next() == false) {

                st.executeUpdate("insert into persona values(" + cliente.getDni() + ", " + cliente.getNombre() + ", " + cliente.getApellido1() + ", " + cliente.getApellido2() + ", " + cliente.getFechaNacimiento() + ", " + cliente.getDireccion() + ", " + cliente.getPoblacion() + ", " + cliente.getTlf() + ")");
                st.executeUpdate("insert into cliente value" + cliente.getIdCliente() + ", " + cliente.getDni() + ")");
                 rs.close();
            rs.close();
            st.close();
                return sortida = true;
            } else {
                 rs.close();
            rs.close();
            st.close();
                return sortida = false;

            }

           

        } catch (SQLException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sortida;
    }

    public static boolean insertaSucursal(Sucursal sucursal) {

        return false;
    }

    public static Sucursal devuelveSucursal(int codigo) {
        Sucursal suc = null;
        return suc;
    }

    public static boolean eleiminarSucursal(Sucursal sucursal) {
        return false;
    }
    
    //Fi de la part del grup de Guillem Arrom, Rotger, Pedro i François
}
