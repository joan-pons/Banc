/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.central;

import es.bancodehierro.banco.cc.ControlCC;
import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.enumeraciones.EnumCargo;
import es.bancodehierro.banco.excepciones.ClienteException;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.excepciones.EmpleadoException;
import es.bancodehierro.banco.excepciones.SucursalException;
import es.bancodehierro.banco.menu.GestionaMenu;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.persona.Empleado;
import es.bancodehierro.banco.persona.Persona;
import es.bancodehierro.banco.prestamo.Prestamo;
import es.bancodehierro.banco.sucursal.Sucursal;
import es.bancodehierro.banco.tarjeta.Credito;
import es.bancodehierro.banco.tarjeta.Debito;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel
 * Angel Cànaves
 */
public class Banco {

    /**
     * Agregación de una cuenta corriente en la base de datos.
     * @param sucursal La sucursal a la que pertenece la cuenta corriente
     * @return Un boolean con el resultado de la consulta.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
   public boolean agregarCuentaCorriente(Sucursal sucursal) throws SQLException, ClienteException{
       CuentaCorriente cc= new CuentaCorriente(ControlCC.controlIBAN(), ControlCC.controlOficina(), ControlCC.controlDc(), ControlCC.controlCC(), 0);
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
            cc.agregarTitular(sucursal);
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


   /**
    * Elimina la cuenta corriente que se le pasa por parámetro.
    * @param sucursal La surcursal a la que pertenece la cuenta corriente.
    * @return Un boolean con el resultado de la sentencia.
    * @throws SQLException En el caso que haya fallado alguna sentencia a la
    * base de datos.
    */
    public boolean eliminarCuentaCorriente(Sucursal sucursal) throws SQLException, ClienteException {
       String dni = GestionaMenu.llegirCadena("Introduce el DNI: ");
        Statement st = Conexion.conectar().createStatement();
        String consulta = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE = '" + dni + "'";
        ResultSet rs = st.executeQuery(consulta);
        Cliente titular = null;
        if (Banco.comprobarCliente(dni)) {
            titular = new Cliente(null, null, null, dni, null, null, null, null);
        }
        /*Statement sel = (Statement) Conexion.conectar();

        ResultSet comp = sel.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "','" + sucursal.getCodi() + "';");

        if (comp.next()) {
            Statement st = (Statement) Conexion.conectar();

            ResultSet rs = st.executeQuery("DELETE FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "'&&'" + sucursal.getCodi() + "';");

            return true;

        } else {
            throw new CuentaCorrienteException();
        }*/
         CuentaCorriente cc= new CuentaCorriente(ControlCC.controlIBAN(), ControlCC.controlOficina(), ControlCC.controlDc(), ControlCC.controlCC(), 0);
       
         String function="{? = call esborrar_client_CCB(?,?,?,?)}";
        //ResultSet comp = st.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "' AND CODIGO_SCC =" + sucursal.getCodi());
       
        //if (comp.next()) {
            CallableStatement cS = Conexion.conectar().prepareCall(function);
            cS.registerOutParameter(1, java.sql.Types.INTEGER);
            cS.setString(2, cc.muestraCC());
            cS.setString(3, dni);
            cS.setInt(4, sucursal.getCodi());
            cS.setInt(5, GestionaMenu.llegirSencer("Posicion titular: "));
            
            
            
            //ResultSet rs = cS.executeQuery();
            cS.executeQuery();
           
         
         
         function="{? = call ESBORRAR_CCB(?)}";
        //ResultSet comp = st.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "' AND CODIGO_SCC =" + sucursal.getCodi());
       
        //if (comp.next()) {
            cS = Conexion.conectar().prepareCall(function);
            cS.registerOutParameter(1, java.sql.Types.INTEGER);
            cS.setString(2, cc.muestraCC());
            
            
            
            //ResultSet rs = cS.executeQuery();
            cS.executeQuery();
            cS.close();
             return true;
    }

    /**
     * Método que devuelve las cuentas corrientes de la base de datos.
     * @return La lista de todas las cuentas corrientes de la base de datos.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
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

    /**
     * Método que devuelve las cuentas corrientes asociadas a la sucursal.
     * @param sucursal La sucursal de la que queremos saber sus cuentas corrientes.
     * @return La lista con las cuentas corrientes de la sucursal.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
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

    /**
     * Método que devuelve las cuentas corrientes asociadas a un cliente.
     * @param cliente El cliente del que queremos saber sus cuentas corrientes.
     * @return La lista con las cuentas corrientes asociadas a un cliente.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
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
    /**
     * Metodo que devuelve un objeto cliente con los datos del cliente cuyo dni
     * es el que le pasas por parametro
     *
     * @param dniCliente DNI del cliente al cual se devolvera toda la
     * informacion
     * @return Objeto cliente con toda la informacion
     * @throws SQLException Cuando hay un error inesperado de bdd
     * @throws ClienteException Cuando el cliente no ha sido encontrado
     */
    public static Cliente devuelveCliente(String dniCliente) throws SQLException, ClienteException {
        Connection conexion = Conexion.conectar();
        comprobarCliente(dniCliente);
        Statement st = conexion.createStatement();
        ResultSet rs1 = st.executeQuery("SELECT * FROM CLIENTE WHERE DNI =" + dniCliente);//FALTA BDD
        int idclient = rs1.getInt("idClient");
        String nombre = rs1.getString("nombre");
        String apellido1 = rs1.getString("apellido1");
        String apellido2 = rs1.getString("apellido2");
        String poblacion = rs1.getString("poblacion");
        String direccion = rs1.getString("direccion");
        Date fn = rs1.getDate("fechaNacimiento");
        String telefono = rs1.getString("telefono");
        Cliente cliente = new Cliente(nombre, apellido1, apellido2, dniCliente, poblacion, direccion, fn, telefono);
        return cliente;
    }

    /**
     * Metodo que devuelve un objeto empleado con los datos del empleatdo cuyo
     * dni es el que le pasas por parametro
     *
     * @param dniEmpleado DNI del empleado al cual se devolvera toda la
     * informacion
     * @return Objeto cliente con toda la informacion
     * @throws SQLException Cuando hay un error inesperado de bdd
     * @throws EmpleadoException Cuando el empleado no ha sido encontrado
     */
    public static Empleado devuelveEmpleado(String dniEmpleado) throws SQLException, EmpleadoException {
        Connection conexion = Conexion.conectar();
        comprobarEmpleado(dniEmpleado);
        EnumCargo car = null;
        Statement st = conexion.createStatement();
        ResultSet rs1 = st.executeQuery("SELECT * FROM V_EMPLEAT WHERE DNI_PERSONA ='" + dniEmpleado + "'");
        rs1.next();
        int idEmpleado = rs1.getInt("CODIGO_TRABAJADOR");
        String nombre = rs1.getString("NOMBRE_PERSONA");
        String cargo = rs1.getString("CARGO_TRABAJADOR");
        String apellido1 = rs1.getString("PRIMER_APELLIDO_PERSONA");
        String apellido2 = rs1.getString("SEGUNDO_APELLIDO_PERSONA");
        String poblacion = rs1.getString("NOMBRE_POBLACION");
        String direccion = rs1.getString("DIRECCION_PERSONA");
        Date fn = rs1.getDate("FECHA_NACIMIENTO_PERSONA");
        String telefono = rs1.getString("TELEFONO_PERSONA");
        int sucursal = rs1.getInt("CODIGO_SUCURSAL");

        if ("EMPLEADO SUCURSAL".equalsIgnoreCase(cargo)) {
            car = EnumCargo.EMPLEADOSUCURSAL;
        } else if ("DIRECTOR BANCO".equalsIgnoreCase(cargo)) {
            car = EnumCargo.DIRECTORBANCO;
        } else if ("DIRECTOR SUCURSAL".equalsIgnoreCase(cargo)) {
            car = EnumCargo.DIRECTORSUCURSAL;
        }
        Empleado empleado;
        empleado = new Empleado(car, nombre, apellido1, apellido2, dniEmpleado, poblacion, direccion, fn, telefono, sucursal);
        return empleado;
    }

    /**
     *
     * Metodo que devuelve sucursal segun su codigo.
     *
     * @param codigoSucursal Codigo de la sucursal
     * @return la central demanada
     * @throws SucursalException Quan la central solicitada no existeix
     * @throws SQLException Quan hi ha un error en les sentencies SQL
     */
    public static Sucursal devuelveSucursal(int codigoSucursal) throws SucursalException, SQLException {
        Connection conexion = Conexion.conectar();
        comprobarSucursal(codigoSucursal);
        Statement st = conexion.createStatement();
        ResultSet rs1 = st.executeQuery("SELECT * FROM SUCURSAL WHERE CODIGO_SUCURSAL =" + codigoSucursal);
        rs1.next();
        String direccion = rs1.getString("DIRECCION_SUCURSAL");
        int codigoP = rs1.getInt("CP_SUCURSAL");
        int codiPob = rs1.getInt("CODIGO_POBLACION_SUCURSAL");
        int codigoSuc = rs1.getInt("CODIGO_SUCURSAL");
        String telefono = rs1.getString("TELEFONO_SUCURSAL");
        int codCentral = rs1.getInt("CODIGO_CENTRAL_SUCURSAL");
        rs1.close();
        ResultSet rs2 = st.executeQuery("SELECT NOMBRE_POBLACION FROM POBLACION WHERE CODIGO_POBLACION = " + codiPob);
        rs2.next();
        String poblacion = rs2.getString(1);
        rs2.close();
        Sucursal central;
        if (codCentral != 0) {
            central = devuelveSucursal(codCentral);
        } else {
            central = null;
        }
        Sucursal suc = new Sucursal(poblacion, direccion, codigoSuc, codigoP, central, telefono);
        st.close();

        return suc;
    }

    /**
     * Este método se encarga de eliminar los clientes. Crea una conexión con la
     * base de datos. Comprueba gracias al método "comprobarCliente" de que el
     * DNI introducido está asignado a algún cliente para seguidamente
     * eliminarlo. En el caso de que el DNI no atienda a ningún cliente, lanzará
     * una excepción para indicar que no existe tal cliente.
     *
     * @param dniCliente recibe por parámetro un String que debe atender al DNI
     * del cliente que se desee eliminar.
     * @throws ClienteException
     */
    public static void eliminarCliente(String dniCliente) throws ClienteException, SQLException {
        Connection con = Conexion.conectar();
        Statement st = con.createStatement();
        String sql = "DELETE FROM V_CLIENT WHERE DNI_PERSONA ='" + dniCliente + "'";
        if (comprobarCliente(dniCliente)) {
            st.executeUpdate(sql);
            st.close();
        } else {
            st.close();
            throw new ClienteException("No existe el cliente");
        }
    }

    /**
     * Este método se encarga de eliminar los empleados. Crea una conexión con
     * la base de datos. Comprueba gracias al método "comprobarEmpleado" de que
     * el DNI introducido está asignado a algún empleado para seguidamente
     * eliminarlo. En el caso de que el DNI no atienda a ningún empleado,
     * lanzará una excepción para indicar que no existe tal empleado.
     *
     * @param dniEmpleado recibe por parámetro un String que debe atender al DNI
     * del empleado que se desee eliminar.
     * @throws EmpleadoException
     */
    public static void eliminarEmpleado(String dniEmpleado) throws EmpleadoException, SQLException {
        Connection con = Conexion.conectar();
        Statement st = con.createStatement();
        String sql = "DELETE FROM V_EMPLEAT WHERE DNI_PERSONA ='" + dniEmpleado + "'";
        if (comprobarEmpleado(dniEmpleado)) {
            st.executeUpdate(sql);
            st.close();
        } else {
            st.close();
            throw new EmpleadoException("No existe el empleado");
        }
    }

    /**
     * Este método se encarga de eliminar una sucursal indicando su código de
     * referencia. Primero verifica la existencia de la sucursal gracias al
     * método "comprobarSucursal" y en el caso de encontrarse la sucursal,
     * realiza el DETELE en la base de datos"
     *
     * @param sucursal La sucursal a eliminar.
     * @return True si la central se ha eliminat correctement
     * @throws SQLException Avisa cuando la instrucción falla inesperadamente.
     * @throws SucursalException Avisa de un error en relación a la sucursal.
     */
    public static boolean eliminarSucursal(Sucursal sucursal) throws SQLException, SucursalException {
        Connection conexion = Conexion.conectar();
        Statement st = conexion.createStatement();
        String sql = "DELETE FROM SUCURSAL WHERE CODIGO_SUCURSAL =" + sucursal.getCodi();
        if (comprobarSucursal(sucursal.getCodi()) == true) {
            st.executeQuery(sql);
            st.close();
            return true;
        } else {
            st.close();
            throw new SucursalException("No existe la sucursal que deseea eliminar");
        }
    }

    /**
     * Utiliza un objeto de tipo cliente para coger todos los datos para
     * insertarlo en la bdd.
     *
     * @param cliente Objeto del cual sacara todos los datos
     * @throws ClienteException Cuando el cliente ya existe
     * @throws SQLException Si hay un error inesperado con la bdd
     */
    public static int insertarCliente(Cliente cliente) throws ClienteException, SQLException {
        Connection con = Conexion.conectar();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from CLIENTE where DNI_CLIENTE ='" + cliente.getDni() + "'");
        //si es dnitrabajador no existeix
        if (rs.next() == false) {
            st.executeUpdate("insert into V_CLIENT (DNI_PERSONA) values ( '" + cliente.getDni() + "', '" + cliente.getNombre() + "','" + cliente.getApellido1() + "','" + cliente.getApellido2() + "','" + cliente.getFechaNacimiento() + "','" + cliente.getDireccion() + "','" + cliente.getPoblacion() + "', '" + cliente.getTlf() + "', 0)");
            st.close();
            rs.close();
        } else {
            st.close();
            rs.close();
            throw new ClienteException("Error: El cliente ya existe");
        }

        return 0;

    }

    /**
     * Utiliza un objeto de tipo empleado para coger todos los datos para
     * insertarlo en la bdd
     *
     * @param empleado Objeto del cual sacara todos los datos
     * @throws EmpleadoException Cuando el cliente ya existe
     * @throws SQLException Si hay un error inesperado con la bdd
     */
    public static int insertarEmpleado(Empleado empleado) throws EmpleadoException, SQLException {
        Connection con = Conexion.conectar();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from TRABAJADOR where DNI_TRABAJADOR ='" + empleado.getDni() + "'");
        //si es dnitrabajador no existeix
        if (rs.next() == false) {
            st.executeUpdate("insert into V_EMPLEAT values ( 0, '" + empleado.getDni() + "', '" + empleado.getNombre() + "', '" + empleado.getApellido1() + "', '" + empleado.getApellido2() + "', '" + "01-01-1990" + "', '" + empleado.getTlf() + "', '" + empleado.getDireccion() + "', '" + empleado.getPoblacion() + "', '" + empleado.getCargo() + "', " + empleado.getSucursal() + ",'01-01-1990')");
            st.close();
            rs.close();
        } else {
            st.close();
            rs.close();
            throw new EmpleadoException("Error: El empleado ya existe");
        }
        return 0;
    }

    /**
     *
     * Inserta la sucursal que le pasas por parametro.
     *
     * @param sucursal objeto sucursal a insertar
     *
     * @throws SQLException Cuando hay un fallo inesperado de bdd
     * @throws SucursalException cuando la central de la sucursal no es
     * encontrada, no encuentra poblacion o un error desconocido.
     */
    public static int insertaSucursal(Sucursal sucursal) throws SQLException, SucursalException {
        Connection conexion = Conexion.conectar();
        String procedure = "{? = call INSERIR_SUCURSAL (?,?,?,?,?)}";
        CallableStatement statement = conexion.prepareCall(procedure);
        statement.registerOutParameter(1, java.sql.Types.INTEGER);
        statement.setString(2, sucursal.getDireccio());
        statement.setInt(3, sucursal.getCodiPostal());
        Statement st = conexion.createStatement();
        ResultSet rs2 = st.executeQuery("SELECT CODIGO_POBLACION FROM POBLACION WHERE NOMBRE_POBLACION = '" + sucursal.getPoblacio().toUpperCase() + "'");
        rs2.next();
        int codPoblacion = rs2.getInt("CODIGO_POBLACION");
        rs2.close();
        st.close();
        statement.setInt(4, codPoblacion);
        statement.setString(5, sucursal.getTelefono());
        if (sucursal.getCentral() != null) {
            statement.setInt(6, sucursal.getCentral().getCodi());
        } else {
            statement.setInt(6, 0);
        }
        statement.executeQuery();
        int resultat = statement.getInt(1);
        if (resultat > 0) {
            return resultat;
        } else if (resultat == -20001) {
            throw new SucursalException("Central no encontrada");
        } else if (resultat == -20002) {
            throw new SucursalException("Poblacion no encontrada");
        } else {
            throw new SucursalException("Error desconegut");
        }
    }

    /**
     * Coge un objeto sucursal para utilizarlo para coger los datos y
     * sobrescribirlos a la bdd
     *
     * @param sucursal El objeto sucursal con LOS DATOS YA MODIFICADOS
     * @throws java.sql.SQLException Cuando hay un fallo inesperado en la bdd
     */
    public static void modificarSucursal(Sucursal sucursal) throws SQLException {
        Connection conexion = Conexion.conectar();
        Statement st = conexion.createStatement();
        Statement st2 = conexion.createStatement();
        ResultSet rs2 = st2.executeQuery("SELECT CODIGO_POBLACION FROM POBLACION WHERE NOMBRE_POBLACION = '" + sucursal.getPoblacio().toUpperCase() + "'");
        rs2.next();
        int codiPob = rs2.getInt(1);
        rs2.close();
        st2.close();
        conexion.setAutoCommit(false);
        String central = "'";
        if (sucursal.getCentral() == null) {
            central = "'";
        } else {
            central = "',CODIGO_CENTRAL_SUCURSAL = " + sucursal.getCentral().getCodi();
        }
        int resultats = st.executeUpdate("UPDATE SUCURSAL SET "
                + "DIRECCION_SUCURSAL ='" + sucursal.getDireccio()
                + "', CP_SUCURSAL ='" + sucursal.getCodiPostal()
                + "', CODIGO_POBLACION_SUCURSAL =" + codiPob
                + ", TELEFONO_SUCURSAL = ' " + sucursal.getTelefono()
                + central
                + "WHERE CODIGO_SUCURSAL=" + sucursal.getCodi());
        if (resultats != 1) {
            conexion.rollback();
            conexion.setAutoCommit(true);
            st.close();
            throw new SQLException("Se esperaba que solo se modificara un registro");
        }
        conexion.setAutoCommit(true);
        st.close();
    }

    /**
     * Modifica cliente.
     *
     * Coge el dni del objeto cliene y todos sus atributos y lo utiliza para
     * generar una sentencia SQL de actualizacion
     *
     * @param cliente El objeto de donde sacara los datos
     * @throws java.sql.SQLException Cuando hay un fallo en la sentencia SQL o
     * cuando la actualizacion afecta a mas lineas de las esperadas (1)
     */
    public static void modificarCliente(Cliente cliente) throws SQLException {
        Connection conexion = Conexion.conectar();
        Statement st = conexion.createStatement();
        conexion.setAutoCommit(false);
        int resultats = st.executeUpdate("UPDATE V_CLIENT SET "
                + "NOMBRE_PERSONA = " + cliente.getNombre()
                + ", PRIMER_APELLIDO_PERSONA = " + cliente.getApellido1()
                + ", SEGUNDO_APELLIDO_PERSONA = " + cliente.getApellido2()
                + ", FECHA_NACIMIENTO_PERSONA = " + cliente.getFechaNacimiento()
                + ", DIRECCION_PERSONA = " + cliente.getDireccion()
                + ", CODIGO_POBLACION_PERSONA = " + cliente.getPoblacion()
                + ", TELEFONO_PERSONA = " + cliente.getTlf()
                + ", DNI_CLIENTE = " + cliente.getDni()
                // + ", CODIGO_CLIENTE =" + cliente.getIdCliente() 
                + "WHERE DNI_PERSONA=" + cliente.getDni() + " )");
        if (resultats != 1) {
            conexion.rollback();
            conexion.setAutoCommit(true);
            throw new SQLException("Se esperaba que solo se modificara un registro");
        }
        conexion.setAutoCommit(true);
        st.close();
    }

    /**
     * Modifica empleado.
     *
     * Coge el dni del objeto empleado y todos sus atributos y lo utiliza para
     * generar una sentencia SQL de actualizacion
     *
     * @param empleado El objeto de donde sacara los datos
     * @throws java.sql.SQLException Cuando hay un fallo en la sentencia SQL o
     * cuando la actualizacion afecta a mas lineas de las esperadas (1)
     */
    public static void modificarEmpleado(Empleado empleado) throws SQLException {
        Connection conexion = Conexion.conectar();
        Statement st = conexion.createStatement();
        conexion.setAutoCommit(false);
        int resultats = st.executeUpdate("UPDATE V_CLIENT SET "
                + "NOMBRE_PERSONA = " + empleado.getNombre()
                + ", PRIMER_APELLIDO_PERSONA = " + empleado.getApellido1()
                + ", SEGUNDO_APELLIDO_PERSONA = " + empleado.getApellido2()
                + ", FECHA_NACIMIENTO_PERSONA = " + empleado.getFechaNacimiento()
                + ", DIRECCION_PERSONA = " + empleado.getDireccion()
                + ", CODIGO_POBLACION_PERSONA = " + empleado.getPoblacion()
                + ", TELEFONO_PERSONA = " + empleado.getTlf()
                + ", DNI_CLIENTE = " + empleado.getDni()
                // + ", CODIGO_CLIENTE =" + empleado.getIdCliente() 
                + "WHERE DNI_PERSONA=" + empleado.getDni() + " )");
        if (resultats != 1) {
            conexion.rollback();
            conexion.setAutoCommit(true);
            throw new SQLException("Se esperaba que solo se modificara un registro");
        }
        conexion.setAutoCommit(true);
        st.close();
    }

    /**
     *
     * @param dniCliente recibe un parámetro String (dni) el cual indica el DNI
     * del cliente que se quiere comprobar si existe como tal
     * @return True si el cliente existe
     * @throws SQLException cuando hay un error inesperado de BDD
     * @throws ClienteException cuando el cliente no ha sido encontrado
     */
    public static boolean comprobarCliente(String dniCliente) throws SQLException, ClienteException {
        Connection conexion = Conexion.conectar();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("select count(DNI_PERSONA) from V_CLIENT where DNI_PERSONA = '" + dniCliente + "'");
        rs.next();
        int a = rs.getInt(1);
        if (a == 1) {
            return true;
        } else  {
            throw new ClienteException("Cliente no encontrado");
        }
    }

    /**
     * @param dniEmpleado recibe un parámetro String (dni) el cual indica el DNI
     * del empleado que se quiere comprobar si existe como tal
     *
     * @return true si existe el empleado
     * @throws SQLException Si hubo un error inesperado con la bdd
     * @throws EmpleadoException Si el empleado no ha sido encontrado
     */
    public static boolean comprobarEmpleado(String dniEmpleado) throws SQLException, EmpleadoException {
        Connection con = Conexion.conectar();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(DNI_PERSONA) FROM V_EMPLEAT WHERE DNI_PERSONA ='" + dniEmpleado + "'");
        rs.next();
        int a = rs.getInt(1);
        if (a == 1) {
            return true;
        } else {
            throw new EmpleadoException("Empleado no encontrado");

        }
    }

    /**
     * Metode que comprueba si una Sucursal existe en la base de datos de la
     * conexion
     *
     * @param codigoSucursal Codigo de la sucursal que queremos comprobar si
     * existe en la BDD
     * @return devuelve un true si la sucursal se ha encontrado
     * @throws SucursalException Lanza esta excepcion cuando no se encuentra
     * ninguna sucursal
     * @throws SQLException Lanza esta excepcion cuando no está bien formada la
     * consulta
     */
    public static boolean comprobarSucursal(int codigoSucursal) throws SucursalException, SQLException {
        Connection conexion = Conexion.conectar();
        Statement st = conexion.createStatement();
        //Aqui posam la query que faci falta
        ResultSet rs = st.executeQuery("select count(CODIGO_SUCURSAL) from SUCURSAL where CODIGO_SUCURSAL =" + codigoSucursal);
        rs.next();
        int a = rs.getInt(1);
        if (a == 1) {
            return true;
        } else {
            throw new SucursalException("Sucursal no encontrado");
        }
    }
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
     * Menú. Método de eliminar Préstamo Elimina el
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

    /**
     * Metodo para insertar una tarjeta en la base de datos.
     * @param codigoCliente DNI del cliente.
     * @param cuentaCorriente Codigo de cuenta corriente.
     * @param sucursal Codigo de sucursal conjunto a la cuenta corriente.
     * @param limite Limite (€) de la tarjeta.
     * @param tipo Si es débito o crédito.
     * @return 
     */
    public Boolean altaTarjeta(String codigoCliente, String cuentaCorriente, int sucursal, Double limite, String tipo) {
        if (tipo.toUpperCase() == "DEBITO") {
            Debito d = new Debito(codigoCliente, cuentaCorriente, sucursal);
        } else {
            Credito c = new Credito(codigoCliente, cuentaCorriente, sucursal, limite);
        }
        return null;
    }

    /**
     * Método para eliminar una tarjeta de la base de datos.
     * @param codigoTarjeta Código de la tarjeta.
     * @return 
     */
    public Boolean eliminarTarjeta(String codigoTarjeta) {
        try {
            Statement st = Conexion.conectar().createStatement();
            String query = "DELETE FROM tarjeta WHERE codigoTarjeta = " + codigoTarjeta;
            st.executeUpdate(query);
            st.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en la conexion");
            return false;
        }
    }
    
    

}
