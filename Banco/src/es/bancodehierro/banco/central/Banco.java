/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.central;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.excepciones.ClienteException;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.menu.GestionaMenu;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.persona.Persona;
import es.bancodehierro.banco.prestamo.Prestamo;
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

   public boolean agregarCuentaCorriente(Sucursal sucursal) throws CuentaCorrienteException, SQLException{
       CuentaCorriente cc= new CuentaCorriente(GestionaMenu.llegirCadena("Introduce un IBAN: "), GestionaMenu.llegirCadena("Introduce una Oficina: "), GestionaMenu.llegirCadena("Introduce un DC: "), GestionaMenu.llegirCadena("Introduce un Numero de Cuenta: "), 0);
        Statement st = Conexion.conectar().createStatement();
        boolean resultado = false;
        String function="{? = call INSERCIO_CCB(?,?,?)}";
        //ResultSet comp = st.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "' AND CODIGO_SCC =" + sucursal.getCodi());
       
        //if (comp.next()) {
            CallableStatement cS = Conexion.conectar().prepareCall(function);
            cS.registerOutParameter(1, java.sql.Types.INTEGER);
            cS.setString(2, cc.muestraCC());
            cS.setInt(3, sucursal.getCodi());
            cS.setInt(4, 0);
            
            
            //ResultSet rs = cS.executeQuery();
            cS.executeQuery();
           cS.close();
           st.close();
            
            // + cc.muestraCC() + "','" + sucursal.getCodi() + "'," + 0 + ",SYSTIMESTAMP)}
            
            
//ResultSet rs = st.executeQuery(resutado+":=ESBORRAR_CCB('"+cc.muestraCC()+"')");

            // + cc.muestraCC() + "','" + sucursal.getCodi() + "'," + 0 + ",SYSTIMESTAMP)}
//ResultSet rs = st.executeQuery(resutado+":=ESBORRAR_CCB('"+cc.muestraCC()+"')");
            //ResultSet rs = st.executeQuery("INSERT INTO CUENTA_CORRIENTE VALUES('" + cc.muestraCC() + "','" + sucursal.getCodi() + "',0," + "SYSTIMESTAMP);");
            return true;

        /*} else {
            throw new CuentaCorrienteException();
        }*/
    }


    public boolean eliminarCuentaCorriente(CuentaCorriente cc, Sucursal sucursal) throws CuentaCorrienteException, SQLException {
       
        /*Statement sel = (Statement) Conexion.conectar();

        ResultSet comp = sel.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "','" + sucursal.getCodi() + "';");

        if (comp.next()) {
            Statement st = (Statement) Conexion.conectar();

            ResultSet rs = st.executeQuery("DELETE FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "'&&'" + sucursal.getCodi() + "';");

            return true;

        } else {
            throw new CuentaCorrienteException();
        }*/
        
        String function="{? = call ESBORRAR_CCB(?)}";
        //ResultSet comp = st.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "' AND CODIGO_SCC =" + sucursal.getCodi());
       
        //if (comp.next()) {
            CallableStatement cS = Conexion.conectar().prepareCall(function);
            cS.registerOutParameter(1, java.sql.Types.INTEGER);
            cS.setString(2, cc.muestraCC());
            
            
            
            //ResultSet rs = cS.executeQuery();
            cS.executeQuery();
            cS.close();
             return true;
    }

    public ArrayList<CuentaCorriente> mostrarCuentaCorriente() throws SQLException {
        /*String resultado = null;
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

        return null;*/
        
         ArrayList<CuentaCorriente> aCC= new ArrayList<>();
        CuentaCorriente cuentaCorriente = null;
        //String resultado = null;
        
        try (Statement st = Conexion.conectar().createStatement()) {
           
            String consultaSaldo = "SELECT * FROM CUENTA_CORRIENTE";
            ResultSet rs = st.executeQuery(consultaSaldo);
            for(; rs.next();){
                 cuentaCorriente = new CuentaCorriente("","","",rs.getString("NUMERO_CC"), rs.getDouble("IMPORTE_CC"));
                   // resultado = resultado + "\n" + cuentaCorriente.toString();
                    aCC.add(cuentaCorriente);
            }
            //rs.next();
           /* if (rs.getInt(1) == 0) {
                throw new CuentaCorrienteException("ERROR: NO HAY CUENTA CORRIENTE");
            } else{
               return rs.getDouble(1);
            } */
            //filas = st.executeUpdate(insertTitular);
        }
        return aCC;
    }

    public ArrayList<CuentaCorriente> mostrarCuentaCorriente(Sucursal sucursal) throws SQLException {
        ArrayList<CuentaCorriente> aCC= new ArrayList<>();
        CuentaCorriente cuentaCorriente = null;
        //String resultado = null;
        
        try (Statement st = Conexion.conectar().createStatement()) {
           
            String consultaSaldo = "SELECT * FROM CUENTA_CORRIENTE WHERE CODIGO_SCC='"+sucursal.getCodi()+"'";
            ResultSet rs = st.executeQuery(consultaSaldo);
            for(; rs.next();){
                 cuentaCorriente = new CuentaCorriente("","","",rs.getString("NUMERO_CC"), rs.getDouble("IMPORTE_CC"));
                   // resultado = resultado + "\n" + cuentaCorriente.toString();
                    aCC.add(cuentaCorriente);
            }
            //rs.next();
           /* if (rs.getInt(1) == 0) {
                throw new CuentaCorrienteException("ERROR: NO HAY CUENTA CORRIENTE");
            } else{
               return rs.getDouble(1);
            } */
            //filas = st.executeUpdate(insertTitular);
        }
        return aCC;
    }

    public ArrayList<CuentaCorriente> mostrarCuentaCorriente(Cliente cliente) throws SQLException {
          ArrayList<CuentaCorriente> aCC= new ArrayList<>();
        CuentaCorriente cuentaCorriente = null;
         try (Statement st = Conexion.conectar().createStatement()) {
           
            String consultaSaldo = "SELECT * FROM CUENTA_CORRIENTE CC INNER JOIN CLIENTE_CUENTA_CORRIENTE CCC ON CC.NUMERO_CC=CCC.NUMERO_CC WHERE CCC.DNI_CLIENTE_CC='"+cliente.getDni()+"'";
            ResultSet rs = st.executeQuery(consultaSaldo);
            for(; rs.next();){
                 cuentaCorriente = new CuentaCorriente("","","",rs.getString("NUMERO_CC"), rs.getDouble("IMPORTE_CC"));
                   // resultado = resultado + "\n" + cuentaCorriente.toString();
                    aCC.add(cuentaCorriente);
            }
            //rs.next();
           /* if (rs.getInt(1) == 0) {
                throw new CuentaCorrienteException("ERROR: NO HAY CUENTA CORRIENTE");
            } else{
               return rs.getDouble(1);
            } */
            //filas = st.executeUpdate(insertTitular);
        }
        return aCC;
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
     * MÉTODO INSERTAR PRESTAMO. Se pide el DNI del cliente para ver si existe y
     * se consulta las cuentas que tiene ese cliente para despues seleccionar
     * sobre qual hacer los cambios. Tambien se van pidiento otros parametros
     * para crear el objeto Prestamo.
     *
     * @author Rafel Sastre, Miquel Vallespir i Pau Riera.
     * @param empleado Se tiene que passar un objeto Empleado para registrar los
     * cambios.
     */
    public void insertarPrestamo(Empleado empleado) throws ClienteException, CuentaCorrienteException, SQLException {
        Connection conexion = Conexion.conectar();
        ArrayList<CuentaCorriente> listCC = null;
        Banco b = new Banco();// al meter en banco desaparece

        String dniCliente = GestionaMenu.llegirCadena("Introduce DNI cliente.");

        Cliente cliente = b.devuelveCliente(dniCliente);

        if (cliente == null) {
            throw new ClienteException("El cliente con +" + dniCliente + " no ha sido encontrado.");
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
        int duracionPrestamo = GestionaMenu.llegirSencer("Introdueix durade prestec mesos");
        String fechaFinal = GestionaMenu.llegirCadena("Introdueix fecha final (dd-mm-yyyy)");

        //Prestamo presta = new Prestamo(0, "", null, null, importePrestado, tasaInteresAnual, empleado, listCC.get(opcioSeleccionade));
        Prestamo presta = new Prestamo(0, importePrestado, duracionPrestamo, empleado, listCC.get(opcioSeleccionada));
        try {
            Statement st = conexion.createStatement();
            int filesAfectades = st.executeUpdate(presta.insertarPrestamo());
            System.out.println(filesAfectades + ", files afectades.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getErrorCode() + ", " + ex.getLocalizedMessage());
        }
        presta.toString();

    }

    /**
     * PENDIENTE DE PASAR A BANCO. Menú. Método de eliminar Préstamo Elimina el
     * préstamo. Coge el código del préstamo.
     *
     * @author Jaume Mayol
     * @param presta
     * @see es.bancodehierro.banco.prestamo.Prestamo
     */
    public void eliminarPrestamo(Prestamo presta) {
        Connection conexio = Conexion.conectar();
        try {
            Statement st = conexio.createStatement();
            int filesAfectades = st.executeUpdate(presta.eliminarPrestamo());
            System.out.println(filesAfectades + ", files afectades.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getErrorCode() + ", " + ex.getLocalizedMessage());
        }

    }

}
