/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.central;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.persona.Persona;
import es.bancodehierro.banco.sucursal.Sucursal;
import java.sql.CallableStatement;
import java.sql.Connection;
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

    public boolean agregarCuentaCorriente(CuentaCorriente cc, Sucursal sucursal) throws CuentaCorrienteException, SQLException {
        Statement st = Conexion.conectar().createStatement();
        boolean resultado = false;

        ResultSet comp = st.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "','" + sucursal.getCodi() + "';");

        if (comp.next()) {
            CallableStatement cS = Conexion.conectar().prepareCall("{call INSERCIO_CCB('" + cc.muestraCC() + "','" + sucursal.getCodi() + "'," + 0 + ",SYSTIMESTAMP)}");
            ResultSet rs = cS.executeQuery();
//ResultSet rs = st.executeQuery(resutado+":=ESBORRAR_CCB('"+cc.muestraCC()+"')");

            //ResultSet rs = st.executeQuery("INSERT INTO CUENTA_CORRIENTE VALUES('" + cc.muestraCC() + "','" + sucursal.getCodi() + "',0," + "SYSTIMESTAMP);");
            return true;

        } else {
            throw new CuentaCorrienteException();
        }
    }

    public boolean modificarCuentaCorriente() {
        return true;
    }

    public boolean eliminarCuentaCorriente(CuentaCorriente cc, Sucursal sucursal) throws CuentaCorrienteException, SQLException {

        Statement sel = (Statement) Conexion.conectar();

        ResultSet comp = sel.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "','" + sucursal.getCodi() + "';");

        if (comp.next()) {
            Statement st = (Statement) Conexion.conectar();

            ResultSet rs = st.executeQuery("DELETE FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "'&&'" + sucursal.getCodi() + "';");

            return true;

        } else {
            throw new CuentaCorrienteException();
        }
    }

    public ArrayList<CuentaCorriente> mostrarCuentaCorriente() throws SQLException {
        String resultado = null;
        try (Statement st = Conexion.conectar().createStatement()) {
            String consultaTitular = "SELECT * FROM CUENTA_CORRIENTE";
            ResultSet rs = st.executeQuery(consultaTitular);
            ArrayList<CuentaCorriente> cuentaCorriente = new ArrayList<>();
            for (int i = 0; rs.next(); i++) {
                cuentaCorriente.add(new CuentaCorriente(rs.getString("IBAN"), rs.getString("CODIGO_SCC"), rs.getString("dC"), rs.getString("NUMERO_CC"), rs.getDouble("IMPORTE_CC")));
                resultado = resultado + "\n" + cuentaCorriente.get(i).toString();
                //aB.add(biblio);
                //System.out.println(biblio);
            }
        }

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

    public static boolean insertarCliente(Cliente cliente, Connection con) {
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

    public static boolean eliminarSucursal(Sucursal sucursal) {
        return false;
    }

    //Fi de la part del grup de Guillem Arrom, Rotger, Pedro i François

    /**
     * MÉTODO INSERTAR PRESTAMO - PENDIENTE DE PASAR A BANCO.
     *
     * @author Rafel Sastre, Miquel Vallespir i Pau Riera.
     * @param presta
     */
    public void insertarPrestamo(Empleado empleado) throws ClienteException, CuentaCorrienteException {
        Connection conexion = Conexion.conectar();
        ArrayList<CuentaCorriente> listCC = null;
        Banco b = new Banco();// al meter en banco desaparece

        String dniCliente = GestionaMenu.llegirCadena("Introduce DNI cliente.");

        Cliente cliente = b.devuelveCliente(dniCliente);

        if (cliente == null) {
            throw new ClienteException("El cliente con +"+dniCliente+" no ha sido encontrado.");
            //return false;
        } else {
            listCC = b.mostrarCuentaCorriente(cliente);
        }

        if (listCC == null) {
            throw new CuentaCorrienteException("Este cliente no tiene ninguna cuenta asociada.");
            //return false;
        }

        String[] opcions = new String[listCC.size()];
        int i = 0;
        for (CuentaCorriente cc : listCC) {

            opcions[i] = cc.muestraCC();
            i++;

        }

        int opcioSeleccionada = GestionaMenu.gestionarMenu("Prestamo", opcions, "Insertar opcion:", 0);
        double importePrestado = GestionaMenu.llegirDouble("Introdueix el total a prestar");
        String fechaInicio = GestionaMenu.llegirCadena("Introdueix fecha inicial (dd-mm-yyyy)");
        String fechaFinal = GestionaMenu.llegirCadena("Introdueix fecha final (dd-mm-yyyy)");

        //Prestamo presta = new Prestamo(0, "", null, null, importePrestado, tasaInteresAnual, empleado, listCC.get(opcioSeleccionade));
        Prestamo presta = new Prestamo(0, importePrestado, fechaInicio, empleado, fechaFinal, null, listCC.get(opcioSeleccionada));
        try {
            Statement st = conexion.createStatement();
            int filesAfectades = st.executeUpdate(presta.insertarPrestamo());
            System.out.println(filesAfectades + ", files afectades.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getErrorCode() + ", " + ex.getLocalizedMessage());
        }
        presta.toString();

    }

}
