/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.cc;

import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.enumeraciones.EnumMovimiento;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.menu.GestionaMenu;
import es.bancodehierro.banco.menu.MenuCuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.sucursal.Sucursal;
import es.bancodehierro.banco.central.Banco;
import es.bancodehierro.banco.excepciones.ClienteException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel
 * Angel Cànaves
 */
public class CuentaCorriente {

    /**
     * El iban de la cuenta corriente.
     */
    private String iban;
    /**
     * La constante con la entidad de la cuenta corriente.
     */
    private final String ENTIDAD = "2100";
    /**
     * La oficina de la cuenta corriente.
     */
    private String oficina;
    /**
     * El dc de la cuenta corriente.
     */
    private String dC;
    /**
     * La cuenta de la cuenta corriente.
     */
    private String cuenta;
    /**
     * El importe que tendrá la cuenta corriente.
     */
    private double importe;
    /**
     * La lista de movimientos que se generan en la cuenta corriente.
     */
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    /**
     * La lista de titulares que tendrá la cuenta corriente (como máximo 2).
     */
    private HashMap<String, Cliente> titulares = new HashMap<>();

    /**
     * Devuelve una cadena con el valor de la oficina.
     *
     * @return Un String con la oficina a la que pertenece la cuenta corriente.
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * Para modificar la oficina de la cuenta correinte.
     *
     * @param oficina La nueva oficina.
     */
    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    /**
     * Devuelve una cadena con el valor de dc.
     *
     * @return Un string con el dc de la cuenta corriente.
     */
    public String getdC() {
        return dC;
    }

    /**
     * Para modificar el dc de cuanta corriente.
     *
     * @param dC El nuevo dc.
     */
    public void setdC(String dC) {
        this.dC = dC;
    }

    /**
     * Devuelve el valor del importe de la cuenta corriente.
     *
     * @return Un Double con el importe que tiene la cuenta corriente.
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Para modificar el valor de importe.
     *
     * @param importe Un double con el nuevo valor de importe.
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Devuelve la lista de movimientos de la cuenta corriente.
     *
     * @return El ArrayList de movimientos.
     */
    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * Para modificar la lista de movimientos de cuenta corriente.
     *
     * @param movimientos El ArrayList con los movimientos de cuenta corriente.
     */
    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    /**
     * Devuelve la lista de titulares de la cuenta corriente.
     *
     * @return El HashMap con los titulares de cuenta correinte.
     */
    public HashMap<String, Cliente> getTitulares() {
        return titulares;
    }

    /**
     * Para modificar el HashMap de los titulares de cuenta corriente.
     *
     * @param titulares Un HashMap con los nuevos titulares.
     */
    public void setTitulares(HashMap<String, Cliente> titulares) {
        this.titulares = titulares;
    }

    /**
     * Contructor de una cuenta corriente.
     *
     * @param iban Un String con el valor del iban.
     * @param oficina Un string con el valor de la oficina.
     * @param dC Un String con el valor de dc.
     * @param cuenta Un string con el valor de cuenta.
     * @param importe Un double con el valor del importe.
     */
    public CuentaCorriente(String iban, String oficina, String dC, String cuenta, double importe) {
        this.iban = "ES" + iban;
        this.oficina = oficina;
        this.dC = dC;
        this.cuenta = cuenta;
        this.importe = importe;
    }

    /**
     * Muestra la cuenta corriente.
     *
     * @return Un string con la cadena de la cuenta corriente.
     */
    public String muestraCC() {
        return (iban + ENTIDAD + oficina + dC + cuenta);
    }

    /**
     * Agregación de un titular en la cuenta corriente. Si no hay ningún titular
     * lo agregará como titular. En el HashMap de CuentaCorriente y en la base
     * de datos. Si hay un titular asociado a la cuenta, agregará el nuevo como
     * segundo titular. En la HashMap de CuentaCorriente y en la base de datos.
     *
     * @param titular El cliente que se desea asociar a la cuenta corriente.
     * @param sucursal La sucursal a la que pertenece la cuenta corriente.
     * @return El número de filas afectadas.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
    public int agregarTitular(Sucursal sucursal) throws SQLException, ClienteException {
        String dni = GestionaMenu.llegirCadena("Introduce el DNI del titular: ");
        Statement st = Conexion.conectar().createStatement();
        String consulta = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE = '" + dni + "'";
        ResultSet rs = st.executeQuery(consulta);
        rs.next();
        Cliente titular = null;
        if (Banco.comprobarCliente(dni)) {
            titular = new Cliente(null, null, null, dni, null, null, null, null);
        }
        int filas = '\0';
        /* try (Statement st = Conexion.conectar().createStatement()) {
         String consultaTitular = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE='" + titular.getDni() + "'";
         if (st.executeQuery(consultaTitular) != null) {
         String insertTitular = null;
         String consultaCountTitular = "SELECT count(*) FROM CLIENTE_CUENTA_CORRIENTE WHERE NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
         ResultSet rs = st.executeQuery(consultaCountTitular);
         rs.next();
         if (rs.getInt(1) == 0) {
         insertTitular = "INSERT INTO CLIENTE_CUENTA_CORRIENTE (DNI_CLIENTE_CC,NUMERO_CC,CODIGO_SUCURSAL,FECHA_CREACION,POSICIO) VALUES('" + titular.getDni() + "','" + muestraCC() + "','" + sucursal.getCodi() + "'," + "SYSTIMESTAMP" + ",1";

         } else if (rs.getInt(1) == 1) {
         insertTitular = "INSERT INTO CLIENTE_CUENTA_CORRIENTE (DNI_CLIENTE_CC,NUMERO_CC,CODIGO_SUCURSAL,FECHA_CREACION,POSICIO) VALUES('" + titular.getDni() + "','" + muestraCC() + "','" + sucursal.getCodi() + "'," + "SYSTIMESTAMP" + ",2";

         } else {
         throw new CuentaCorrienteException("Ya tiene dos titulares, no puedes añadir más");
         }
         filas = st.executeUpdate(insertTitular);
                
         }
         }
         if (!titulares.containsValue(titular)) {
         if (!titulares.containsKey("Titular")) {
         titulares.put("Titular", titular);
         } else if (!titulares.containsKey("Segundo")) {
         titulares.put("Segundo", titular);
         } else {
         throw new CuentaCorrienteException("Error: Límite de titulares alcanzado.");
         }
         } else {
         throw new CuentaCorrienteException("Error: Titular, " + titular.getDni() + ", ya asociado a está cuenta.");
         }
         */

        String procedure = "begin inserir_client_CCB(?,?,?,?,?,?); end;";
        //ResultSet comp = st.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "' AND CODIGO_SCC =" + sucursal.getCodi());

        //if (comp.next()) {
        CallableStatement cS = Conexion.conectar().prepareCall(procedure);
        // cS.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        cS.setString(1, muestraCC());
        cS.setInt(2, rs.getInt("CODIGO_CLIENTE"));
        cS.setString(3, titular.getDni());
        cS.setInt(4, sucursal.getCodi());
        cS.setInt(5, GestionaMenu.llegirSencer("Posicion: "));
        cS.registerOutParameter(6, java.sql.Types.INTEGER);

        //ResultSet rs = cS.executeQuery();
        cS.executeQuery();
        cS.close();

        return filas;

    }

    /**
     * Eliminación de un cliente de la cuenta corriente. Si se elimina el
     * titular y existe un segundo titular, el segundo cliente pasará a ser el
     * titular de la cuenta corriente.
     *
     * @param cliente El cliente que se desea eliminar de la cuenta corriente.
     * @param sucursal La sucursal a la que pertenece la cuenta corriente.
     * @return El número de filas afectadas.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
    public int eliminarTitular( Sucursal sucursal) throws SQLException, ClienteException {
        String dni = GestionaMenu.llegirCadena("Introduce el DNI: ");
        Statement st = Conexion.conectar().createStatement();
        String consulta = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE = '" + dni + "'";
        ResultSet rs = st.executeQuery(consulta);
        Cliente titular = null;
        if (Banco.comprobarCliente(dni)) {
            titular = new Cliente(null, null, null, dni, null, null, null, null);
        }
        int filas = '\0';
        /*try (Statement st = Conexion.conectar().createStatement()) {
         String consultaTitular = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE='" + cliente.getDni() + "'";
         if (st.executeQuery(consultaTitular) != null) {
         String insertTitular = null;
         String borrarTitular = null;
         String consultaCountTitular = "SELECT count(*) FROM CLIENTE_CUENTA_CORRIENTE WHERE NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
         ResultSet rs = st.executeQuery(consultaCountTitular);
         rs.next();
         if (rs.getInt(1) == 1) {
         System.err.println("No tienes mas titulares, antes da de baja la cuenta");
         //insertTitular = "INSERT INTO CLIENTE_CUENTA_CORRIENTE (DNI_CLIENTE_CC,NUMERO_CC,CODIGO_SUCURSAL,FECHA_CREACION,POSICIO) VALUES('" + cliente.getDni() + "','" + muestraCC() + "','" + sucursal.getCodi() + "'," + "SYSTIMESTAMP" + ",2";

         } else if (rs.getInt(1) > 1) {
         String consultaPosicionTitular = "SELECT POSICIO FROM CLIENTE_CUENTA_CORRIENTE WHERE DNI_CLIENTE_CC='" + cliente.getDni();
         rs = st.executeQuery(consultaPosicionTitular);
         rs.next();
         if (rs.getInt(1) == 1) {
         intercambiarTitular(sucursal);

         }
         borrarTitular = "DELETE FROM CLIENTE_CUENTA_CORRIENTE WHERE POSICIO= 2";
         }
         filas = st.executeUpdate(borrarTitular);
         System.out.println("Filas borradas: " + filas);
         }
         }

         if (titulares.containsValue(cliente)) {
         if (titulares.get("Segundo").equals(cliente)) {
         titulares.remove(cliente);
         } else if (titulares.get("Titular").equals(cliente) && titulares.containsKey("Segundo")) {
         titulares.remove(cliente);
         Cliente nuevoTitular = titulares.get("Segundo");
         titulares.put("Titular", nuevoTitular);
         titulares.remove("Segundo");
         } else if (titulares.containsValue(cliente) && titulares.containsKey("Titular") && !titulares.containsKey("Segundo")) {
         throw new CuentaCorrienteException("Error: No puede desvincular a todos los titulares de la cuenta.");
         }
         } else {
         throw new CuentaCorrienteException("Error: Ya existe en esta cuenta.");
         }
         */

        String function = "{? = call esborrar_client_CCB(?,?,?,?)}";
        //ResultSet comp = st.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "' AND CODIGO_SCC =" + sucursal.getCodi());

        //if (comp.next()) {
        CallableStatement cS = Conexion.conectar().prepareCall(function);
        cS.registerOutParameter(1, java.sql.Types.INTEGER);
        cS.setString(2, muestraCC());
        cS.setString(3, titular.getDni());
        cS.setInt(4, sucursal.getCodi());
        cS.setDouble(5, GestionaMenu.llegirDouble("Posicion: "));

        //ResultSet rs = cS.executeQuery();
        cS.executeQuery();
        System.out.println(cS.getInt(1));
        cS.close();

        return filas;
    }

    /**
     * Intercambio de titular a segundo titular y viceversa.
     *
     * @param sucursal La sucursal a la que pertenece la cuenta corriente.
     * @return El número de filas afectadas.
     * @throws CuentaCorrienteException En caso que solo haya un cliente
     * asociado en la cuenta corriente.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
    public int intercambiarTitular(Sucursal sucursal) throws CuentaCorrienteException, SQLException {
        int filas = '\0';
        try (Statement st = Conexion.conectar().createStatement()) {
            String insertTitular = null;
            String consultaCountTitular = "SELECT count(*) FROM CLIENTE_CUENTA_CORRIENTE WHERE NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
            ResultSet rs = st.executeQuery(consultaCountTitular);
            rs.next();
            if (rs.getInt(1) == 0) {
                throw new CuentaCorrienteException("ERROR");
            } else if (rs.getInt(1) == 1) {
                throw new CuentaCorrienteException("ERROR: solo hay un titular");
            } else {
                insertTitular = "UPDATE CLIENTE_CUENTA_CORRIENTE SET POSICIO= 3 WHERE POSICIO=1 AND NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
                filas = st.executeUpdate(insertTitular);
                insertTitular = "UPDATE CLIENTE_CUENTA_CORRIENTE SET POSICIO= 1 WHERE POSICIO=2 AND NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
                filas = filas + st.executeUpdate(insertTitular);
                insertTitular = "UPDATE CLIENTE_CUENTA_CORRIENTE SET POSICIO= 2 WHERE POSICIO=3 AND NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
                filas = filas + st.executeUpdate(insertTitular);
            }
            //filas = st.executeUpdate(insertTitular);
        }
        /*
         Cliente auxiliar = titulares.get("Titular");
         eliminarTitular(titulares.get("Titular"), sucursal);
         agregarTitular(auxiliar, sucursal);
         */
        return filas;
    }

    /**
     * Cambio de titulares de la cuenta corriente.
     *
     * @param viejo El cliente que se desea desvincular.
     * @param nuevo El cliente que se desea vincular a la cuenta corriente.
     * @param sucursal La sucursal a la que pertenece la cuenta corriente.
     * @throws CuentaCorrienteException En el caso que solo haya un titular
     * asiciado a la cuenta corriente.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
    public int cambiarTitular(Sucursal sucursal) throws CuentaCorrienteException, SQLException, ClienteException {
        String dni = GestionaMenu.llegirCadena("Introduce el DNI del cliente viejo: ");
        Statement st = Conexion.conectar().createStatement();
        String consulta = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE = '" + dni + "'";
        ResultSet rs = st.executeQuery(consulta);
        rs.next();
        Cliente viejo = null;
        if (Banco.comprobarCliente(dni)) {
            viejo = new Cliente(null, null, null,dni, null, null, null, null);
        }
        dni = GestionaMenu.llegirCadena("Introduce el DNI del cliente nuevo: ");
        consulta = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE = '" + dni + "'";
        rs = st.executeQuery(consulta);
        rs.next();
        Cliente nuevo = null;
        if (Banco.comprobarCliente(dni)) {
            nuevo = new Cliente(null, null, null, dni, null, null, null, null);
        }
        /*if (!titulares.containsKey("Segundo")) {
         agregarTitular(nuevo, sucursal);
         eliminarTitular(viejo, sucursal);
         } else {
         if (viejo.equals(titulares.get("Titular"))) {
         eliminarTitular(viejo, sucursal);
         agregarTitular(nuevo, sucursal);
         intercambiarTitular(sucursal);
         } else {
         eliminarTitular(viejo, sucursal);
         agregarTitular(nuevo, sucursal);
         }
         }*/
        int filas = '\0';
        String insertTitular = null;
        String consultaCountTitular = "SELECT count(*) FROM CLIENTE_CUENTA_CORRIENTE WHERE NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
        rs = st.executeQuery(consultaCountTitular);
        rs.next();
        if (rs.getInt(1) == 0) {
            throw new CuentaCorrienteException("ERROR");
        } else if (rs.getInt(1) == 1) {
            insertTitular = "UPDATE CLIENTE_CUENTA_CORRIENTE SET DNI_CLIENTE_CC='" + nuevo.getDni() + "' WHERE POSICIO=1 AND NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();

        } else {
            MenuCuentaCorriente mCC = new MenuCuentaCorriente();
            boolean puesto = false;
            int menuPuesto = '\0';
            for (; !puesto;) {
                String[] tipos = {"Puesto 1",
                    "Puesto 2"};

                menuPuesto = mCC.mostrarMenu(tipos);
                if (menuPuesto == 0 || menuPuesto == 1) {

                    puesto = true;
                }

            }
            insertTitular = "UPDATE CLIENTE_CUENTA_CORRIENTE SET DNI_CLIENTE_CC='" + nuevo.getDni() + "' WHERE POSICIO=" + (menuPuesto + 1) + " AND NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();

        }
        filas = st.executeUpdate(insertTitular);
        //filas = st.executeUpdate(insertTitular);
        st.close();
        return filas;
        /*
         Cliente auxiliar = titulares.get("Titular");
         eliminarTitular(titulares.get("Titular"), sucursal);
         agregarTitular(auxiliar, sucursal);
         */
    }

    /**
     * Devuelve los movimientos de la cuenta corriente.
     *
     * @param incidencia true en el caso que quieran las incidencias, false si
     * quieren los movimientos.
     * @return Un ArrayList con los movimientos filtrados.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
    public ArrayList<Movimiento> mostrarMovimiento() throws SQLException {
        /* if (incidencia == true) {
         if (incidencias.isEmpty()) {
         return incidencias;
         } else {
         throw new CuentaCorrienteException("Error: No existen incidencias en su cuenta corriente.");
         }
         } else {
         if (movimientos.isEmpty()) {
         return movimientos;
         } else {
         throw new CuentaCorrienteException("Error: No existen movimientos en su cuenta corriente.");
         }
         }*/

        //ArrayList<Movimiento> aM = new ArrayList<>();
        Movimiento movimiento = null;
        //String resultado = null;

        try (Statement st = Conexion.conectar().createStatement()) {
           
                movimientos.clear();
                String consultaCC = "SELECT * FROM MOVIMIENTO_CUENTA_CORRIENTE";
                ResultSet rs = st.executeQuery(consultaCC);

                for (; rs.next();) {
                    movimiento = new Movimiento(EnumMovimiento.CUENTA_CORRIENTE, rs.getInt("CODIGO_MCC"), rs.getString("CONCEPTO_MCC"), rs.getDouble("IMPORTE_MCC"), rs.getTimestamp("FECHA_MCC"));
                    // resultado = resultado + "\n" + cuentaCorriente.toString();
                    movimientos.add(movimiento);
                }

                String consultaPrestamo = "SELECT * FROM MOVIMIENTO_PRESTAMO";
                rs = st.executeQuery(consultaPrestamo);

                for (; rs.next();) {
                    movimiento = new Movimiento(EnumMovimiento.PRESTAMO, rs.getInt("CODIGO_MP"), rs.getString("CONCEPTO_MP"), rs.getDouble("IMPORTE_MP"), rs.getTimestamp("FECHA_MP"));
                    // resultado = resultado + "\n" + cuentaCorriente.toString();
                    movimientos.add(movimiento);
                }

                String consultaCredito = "SELECT * FROM MOVIMIENTO_TARJETA_CREDITO";
                rs = st.executeQuery(consultaCredito);

                for (; rs.next();) {
                    movimiento = new Movimiento(EnumMovimiento.TARJETA_CREDITO, rs.getInt("CODIGO_TARJETA"), rs.getString("CONCEPTO_MTC"), rs.getDouble("IMPORTE_MTC"), rs.getTimestamp("FECHA_MTC"));
                    // resultado = resultado + "\n" + cuentaCorriente.toString();
                    movimientos.add(movimiento);
                }

                String consultaDebito = "SELECT * FROM MOVIMIENTO_TARJETA_DEBITO";
                rs = st.executeQuery(consultaDebito);

                for (; rs.next();) {
                    movimiento = new Movimiento(EnumMovimiento.TARJETA_DEBITO, rs.getInt("CODIGO_TARJETA"), rs.getString("CONCEPTO_MTD"), rs.getDouble("IMPORTE_MTD"), rs.getTimestamp("FECHA_MTD"));
                    // resultado = resultado + "\n" + cuentaCorriente.toString();
                    movimientos.add(movimiento);
                }

            

        }
        return movimientos;
    }
        //rs.next();
        /* if (rs.getInt(1) == 0) {
     throw new CuentaCorrienteException("ERROR: NO HAY CUENTA CORRIENTE");
     } else{
     return rs.getDouble(1);
     } */
    //filas = st.executeUpdate(insertTitular);

    /**
     * Muestra todos los movimientos o las incidencias de la cuenta corriente
     * filtradas por el tipo de movimiento.
     *
     * @param tipo El tipo de movimiento que desean.
     * @return Un ArrayList con los movimientos filtrados.
     * @throws java.sql.SQLException
     */
    public ArrayList<Movimiento> mostrarMovimiento(EnumMovimiento tipo) throws SQLException {
        Movimiento movimiento = null;
        ResultSet rs = null;
        try (Statement st = Conexion.conectar().createStatement()) {

            if (tipo == EnumMovimiento.CUENTA_CORRIENTE) {
                movimientos.clear();
                String consultaCC = "SELECT * FROM MOVIMIENTO_CUENTA_CORRIENTE";
                rs = st.executeQuery(consultaCC);

                for (; rs.next();) {
                    movimiento = new Movimiento(EnumMovimiento.CUENTA_CORRIENTE, rs.getInt("CODIGO_MCC"), rs.getString("CONCEPTO_MCC"), rs.getDouble("IMPORTE_MCC"), rs.getTimestamp("FECHA_MCC"));
                    // resultado = resultado + "\n" + cuentaCorriente.toString();
                    movimientos.add(movimiento);
                }

            } else if (tipo == EnumMovimiento.PRESTAMO) {
                movimientos.clear();
                String consultaPrestamo = "SELECT * FROM MOVIMIENTO_PRESTAMO";
                rs = st.executeQuery(consultaPrestamo);

                for (; rs.next();) {
                    movimiento = new Movimiento(EnumMovimiento.PRESTAMO, rs.getInt("CODIGO_MP"), rs.getString("CONCEPTO_MP"), rs.getDouble("IMPORTE_MP"), rs.getTimestamp("FECHA_MP"));
                    // resultado = resultado + "\n" + cuentaCorriente.toString();
                    movimientos.add(movimiento);
                }
            } else if (tipo == EnumMovimiento.TARJETA_CREDITO) {
                movimientos.clear();
                String consultaCredito = "SELECT * FROM MOVIMIENTO_TARJETA_CREDITO";
                rs = st.executeQuery(consultaCredito);

                for (; rs.next();) {
                    movimiento = new Movimiento(EnumMovimiento.TARJETA_CREDITO, rs.getInt("CODIGO_TARJETA"), rs.getString("CONCEPTO_MTC"), rs.getDouble("IMPORTE_MTC"), rs.getTimestamp("FECHA_MTC"));
                    // resultado = resultado + "\n" + cuentaCorriente.toString();
                    movimientos.add(movimiento);
                }

            } else if (tipo == EnumMovimiento.TARJETA_DEBITO) {
                movimientos.clear();
                String consultaDebito = "SELECT * FROM MOVIMIENTO_TARJETA_DEBITO";
                rs = st.executeQuery(consultaDebito);

                for (; rs.next();) {
                    movimiento = new Movimiento(EnumMovimiento.TARJETA_DEBITO, rs.getInt("CODIGO_TARJETA"), rs.getString("CONCEPTO_MTD"), rs.getDouble("IMPORTE_MTD"), rs.getTimestamp("FECHA_MTD"));
                    // resultado = resultado + "\n" + cuentaCorriente.toString();
                    movimientos.add(movimiento);
                }

            }

        }
        return movimientos;
    }
    /*  ArrayList<Movimiento> movimientoFiltrado = new ArrayList<>();
     if (incidencia == true) {
     if (incidencias.isEmpty()) {
     for (Movimiento inci : movimientos) {
     if (inci.getTipo() == tipo) {
     movimientoFiltrado.add(inci);
     }
     }
     } else {
     throw new CuentaCorrienteException("Error: No existen incidencias en su cuenta corriente.");
     }
     } else {
     if (movimientos.isEmpty()) {
     for (Movimiento movi : movimientos) {
     if (movi.getTipo() == tipo) {
     movimientoFiltrado.add(movi);
     }
     }
     } else {
     throw new CuentaCorrienteException("Error: No existen movimientos en su cuenta corriente.");
     }
     }

     if (movimientoFiltrado.isEmpty()) {
     return movimientoFiltrado;
     } else {
     throw new CuentaCorrienteException("Error: No se han encontrados movimientos.");
     }
     */

    @Override
    public String toString() {
        return "CuentaCorriente: " + "Cuenta: " + muestraCC() + "\tImporte: " + importe;
    }

    /**
     * Para modificar el saldo de la cuenta corriente.
     *
     * @param sucursal La sucursal a la que pertenece la cuenta corriente.
     * @return Devuelve true en caso que haya ido bien o false en caso
     * contrario.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     */
    public boolean modificarSaldo(Sucursal sucursal) throws SQLException {
        String function = "{? = call INSERTAR_MOVIMIENTO_CCB(?,?,?,?,?)}";
        //ResultSet comp = st.executeQuery("SELECT * FROM CUENTA_CORRIENTE WHERE NUMERO_CC = '" + cc.muestraCC() + "' AND CODIGO_SCC =" + sucursal.getCodi());
        MenuCuentaCorriente mCC = new MenuCuentaCorriente();
        boolean menuTipo = false;
        //if (comp.next()) {
        CallableStatement cS = Conexion.conectar().prepareCall(function);
        cS.registerOutParameter(1, java.sql.Types.INTEGER);
        cS.setString(2, muestraCC());
        cS.setInt(3, sucursal.getCodi());
        cS.setDouble(4, GestionaMenu.llegirDouble("Importe: "));
        for (; !menuTipo;) {
            String[] tipos = {"ABONO",
                "CARGO"};

            int menuImp = mCC.mostrarMenu(tipos);
            switch (menuImp) {
                case 0: {
                    cS.setString(5, "ABONO");
                    menuTipo = true;
                    break;
                }
                case 1: {
                    cS.setString(5, "CARGO");
                    menuTipo = true;
                    break;
                }

            }

        }
        cS.setString(6, GestionaMenu.llegirCadena("Concepto: "));

        //ResultSet rs = cS.executeQuery();
        cS.executeQuery();
        cS.close();

        return true;
    }

    /**
     * Muestra el saldo que contiene la cuenta corriente.
     *
     * @param sucursal La sucursal a la que pertenece la cuenta corriente.
     * @return El importe que contiene la cuenta corriente.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la
     * base de datos.
     * @throws CuentaCorrienteException En el caso que no haya ningúna cuenta
     * corriente.
     */
    public double mostrarSaldo(Sucursal sucursal) throws SQLException, CuentaCorrienteException {

        try (Statement st = Conexion.conectar().createStatement()) {

            String consultaSaldo = "SELECT IMPORTE_CC FROM CUENTA_CORRIENTE WHERE NUMERO_CC='" + muestraCC() + "' AND CODIGO_SCC=" + sucursal.getCodi();
            ResultSet rs = st.executeQuery(consultaSaldo);
            rs.next();
            if (rs.getInt(1) == 0) {
                throw new CuentaCorrienteException("ERROR: NO HAY CUENTA CORRIENTE");
            } else {
                return rs.getDouble(1);
            }
            //filas = st.executeUpdate(insertTitular);
        }

    }
}
