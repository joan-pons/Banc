/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.cc;

import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.enumeraciones.EnumMovimiento;
import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.persona.Cliente;
import es.bancodehierro.banco.sucursal.Sucursal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase cuenta corriente en la que se trabajará con el importe y los movimientos que genera.
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
     * La lista de incidencias que se generan en la cuenta corriente.
     */
    private ArrayList<Movimiento> incidencias = new ArrayList<>();
    /**
     * La lista de titulares que tendrá la cuenta corriente (como máximo 2).
     */
    private HashMap<String, Cliente> titulares = new HashMap<>();

    /**
     * Devuelve una cadena con el valor de la oficina.
     * @return Un String con la oficina a la que pertenece la cuenta corriente.
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * Para modificar la oficina de la cuenta correinte.
     * @param oficina La nueva oficina.
     */
    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    /**
     * Devuelve una cadena con el valor de dc.
     * @return Un string con el dc de la cuenta corriente.
     */
    public String getdC() {
        return dC;
    }

    /**
     * Para modificar el dc de cuanta corriente.
     * @param dC El nuevo dc.
     */
    public void setdC(String dC) {
        this.dC = dC;
    }

    /**
     * Devuelve el valor del importe de la cuenta corriente.
     * @return Un Double con el importe que tiene la cuenta corriente.
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Para modificar el valor de importe.
     * @param importe Un double con el nuevo valor de importe.
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Devuelve la lista de movimientos de la cuenta corriente.
     * @return El ArrayList de movimientos.
     */
    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * Para modificar la lista de movimientos de cuenta corriente.
     * @param movimientos El ArrayList con los movimientos de cuenta corriente.
     */
    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    /**
     * Devuelve la lista con las incidencias de cuenta corriente.
     * @return El ArrayList de incidencias en la cuenta corriente.
     */
    public ArrayList<Movimiento> getIncidencia() {
        return incidencias;
    }

    /**
     * Para modificar la lista de incidencias de cuenta corriente.
     * @param incidencia El ArrayList con las incidencias de cuenta corriente.
     */
    public void setIncidencia(ArrayList<Movimiento> incidencia) {
        this.incidencias = incidencia;
    }

    /**
     * Devuelve la lista de titulares de la cuenta corriente.
     * @return El HashMap con los titulares de cuenta correinte.
     */
    public HashMap<String, Cliente> getTitulares() {
        return titulares;
    }

    /**
     * Para modificar el HashMap de los titulares de cuenta corriente.
     * @param titulares Un HashMap con los nuevos titulares.
     */
    public void setTitulares(HashMap<String, Cliente> titulares) {
        this.titulares = titulares;
    }

    /**
     * Contructor de una cuenta corriente.
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
     * @throws CuentaCorrienteException En caso que ya existean dos clientes en
     * la cuenta corriente o ya exista el cliente nuevo en la cuenta correinte.
     * @throws SQLException En el caso que haya fallado alguna sentencia a la base de datos.
     */
    public int agregarTitular(Cliente titular, Sucursal sucursal) throws CuentaCorrienteException, SQLException {
        int filas = '\0';
        try (Statement st = Conexion.conectar().createStatement()) {
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
     * @throws CuentaCorrienteException
     * @throws SQLException  En el caso que haya fallado alguna sentencia a la base de datos.
     */
    public int eliminarTitular(Cliente cliente, Sucursal sucursal) throws CuentaCorrienteException, SQLException {
        int filas = '\0';
        try (Statement st = Conexion.conectar().createStatement()) {
            String consultaTitular = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE='" + cliente.getDni() + "'";
            if (st.executeQuery(consultaTitular) != null) {
                String insertTitular = null;
                String borrarTitular = null;
                String consultaCountTitular = "SELECT count(*) FROM CLIENTE_CUENTA_CORRIENTE WHERE NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
                ResultSet rs = st.executeQuery(consultaCountTitular);
                rs.next();
                if (rs.getInt(1) == 1) {
                    throw new CuentaCorrienteException("No tienes mas titulares, antes da de baja la cuenta");
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
        
        return filas;
    }

    /**
     * Intercambio de titular a segundo titular y viceversa.
     * 
     * @param sucursal La sucursal a la que pertenece la cuenta corriente.
     * @return El número de filas afectadas.
     * @throws CuentaCorrienteException En caso que solo haya un cliente asociado en la cuenta corriente.
     * @throws SQLException  En el caso que haya fallado alguna sentencia a la base de datos.
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
            } /*else {
             insertTitular = "UPDATE CLIENTE_CUENTA_CORRIENTE SET POSICIO= 3 WHERE;
                    
             }*/
            filas = st.executeUpdate(insertTitular);
        }

        Cliente auxiliar = titulares.get("Titular");
        eliminarTitular(titulares.get("Titular"), sucursal);
        agregarTitular(auxiliar, sucursal);

        return filas;
    }

    /**
     * Cambio de titulares de la cuenta corriente.
     * 
     * @param viejo El cliente que se desea desvincular.
     * @param nuevo El cliente que se desea vincular a la cuenta corriente.
     * @param sucursal La sucursal a la que pertenece la cuenta corriente.
     * @throws CuentaCorrienteException 
     * @throws SQLException En el caso que haya fallado alguna sentencia a la base de datos.
     */
    public void cambiarTitular(Cliente viejo, Cliente nuevo, Sucursal sucursal) throws CuentaCorrienteException, SQLException {
        if (!titulares.containsKey("Segundo")) {
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
        }
    }

    /**
     * Devuelve los movimientos de la cuenta corriente.
     * 
     * @param incidencia true en el caso que quieran las incidencias, false si quieren los movimientos.
     * @return Un ArrayList con los movimientos filtrados.
     * @throws CuentaCorrienteException En el caso que no haya movimientos.
     */
    public ArrayList<Movimiento> mostrarMovimiento(Boolean incidencia) throws CuentaCorrienteException {
        if (incidencia == true) {
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
        }
    }

    /**
     * Muestra todos los movimientos o las incidencias de la cuenta corriente filtradas por el tipo de movimiento.
     * 
     * @param incidencia true en el caso que quieran las incidencias, false si quieren los movimientos.
     * @param tipo El tipo de movimiento que desean.
     * @return Un ArrayList con los movimientos filtrados.
     * @throws CuentaCorrienteException En el caso que no haya movimientos.
     */
    public ArrayList<Movimiento> mostrarMovimiento(Boolean incidencia, EnumMovimiento tipo) throws CuentaCorrienteException {
        ArrayList<Movimiento> movimientoFiltrado = new ArrayList<>();
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

    }

    @Override
    public String toString() {
        return "CuentaCorriente{" + "cuenta=" + muestraCC() + ", importe=" + importe + '}';
    }

}
