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
 *
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel
 * Angel Cànaves
 */
public class CuentaCorriente {

    private String iban;
    private final String ENTIDAD = "2100";
    private String oficina;
    private String dC;
    private String cuenta;
    private double importe;
    private ArrayList<Movimiento> movimientos = new ArrayList<>();
    private ArrayList<Movimiento> incidencias = new ArrayList<>();
    private HashMap<String, Cliente> titulares = new HashMap<>();

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getdC() {
        return dC;
    }

    public void setdC(String dC) {
        this.dC = dC;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public ArrayList<Movimiento> getIncidencia() {
        return incidencias;
    }

    public void setIncidencia(ArrayList<Movimiento> incidencia) {
        this.incidencias = incidencia;
    }

    public HashMap<String, Cliente> getTitulares() {
        return titulares;
    }

    public void setTitulares(HashMap<String, Cliente> titulares) {
        this.titulares = titulares;
    }

    public CuentaCorriente(String iban, String oficina, String dC, String cuenta, double importe) {
        this.iban = "ES" + iban;
        this.oficina = oficina;
        this.dC = dC;
        this.cuenta = cuenta;
        this.importe = importe;
    }

    public String muestraCC() {
        return (iban + ENTIDAD + oficina + dC + cuenta);
    }

    public void agregarTitular(Cliente titular, Sucursal sucursal) throws CuentaCorrienteException, SQLException {
        try (Statement st = Conexion.conectar().createStatement()) {
            String consultaTitular = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE='" + titular.getDni() + "'";
            if (st.executeQuery(consultaTitular) != null) {
                String insertTitular = null;
                int filas = '\0';
                String consultaCountTitular = "SELECT count(*) FROM CLIENTE_CUENTA_CORRIENTE WHERE NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
                ResultSet rs = st.executeQuery(consultaCountTitular);
                rs.next();
                if (rs.getInt(1) == 0) {
                    insertTitular = "INSERT INTO CLIENTE_CUENTA_CORRIENTE (DNI_CLIENTE_CC,NUMERO_CC,CODIGO_SUCURSAL,FECHA_CREACION,POSICIO) VALUES('" + titular.getDni() + "','" + muestraCC() + "','" + sucursal.getCodi() + "'," + "SYSTIMESTAMP" + ",1";
                    
                } else if (rs.getInt(1) == 1) {
                    insertTitular = "INSERT INTO CLIENTE_CUENTA_CORRIENTE (DNI_CLIENTE_CC,NUMERO_CC,CODIGO_SUCURSAL,FECHA_CREACION,POSICIO) VALUES('" + titular.getDni() + "','" + muestraCC() + "','" + sucursal.getCodi() + "'," + "SYSTIMESTAMP" + ",2";
                    
                } else {
                    System.err.println("Ya tiene dos titulares, no puedes añadir más");
                }
                filas = st.executeUpdate(insertTitular);
                System.out.println("Filas insertadas: " + filas);
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
    }

    public void eliminarTitular(Cliente cliente, Sucursal sucursal) throws CuentaCorrienteException, SQLException {
        try (Statement st = Conexion.conectar().createStatement()) {
            String consultaTitular = "SELECT * FROM CLIENTE WHERE DNI_CLIENTE='" + cliente.getDni() + "'";
            if (st.executeQuery(consultaTitular) != null) {
                String insertTitular = null;
                String borrarTitular = null;
                int filas = '\0';
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
    }

    public void intercambiarTitular(Sucursal sucursal) throws CuentaCorrienteException, SQLException {
        try (Statement st = Conexion.conectar().createStatement()) {
            
                String insertTitular = null;
                int filas = '\0';
                String consultaCountTitular = "SELECT count(*) FROM CLIENTE_CUENTA_CORRIENTE WHERE NUMERO_CC='" + muestraCC() + "' AND CODIGO_SUCURSAL=" + sucursal.getCodi();
                ResultSet rs = st.executeQuery(consultaCountTitular);
                rs.next();
                if (rs.getInt(1) == 0) {
                    System.err.println("ERROR");
                } else if (rs.getInt(1) == 1) {
                    System.err.println("ERROR: solo hay un titular");
                } /*else {
                     insertTitular = "UPDATE CLIENTE_CUENTA_CORRIENTE SET POSICIO= VALUES('" + titular.getDni() + "','" + muestraCC() + "','" + sucursal.getCodi() + "'," + "SYSTIMESTAMP" + ",1";
                    
                }*/
                filas = st.executeUpdate(insertTitular);
                System.out.println("Filas insertadas: " + filas);
            }
        
        
        
        Cliente auxiliar = titulares.get("Titular");

        eliminarTitular(titulares.get("Titular"),sucursal);

        try {
            agregarTitular(auxiliar, sucursal);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void cambiarTitular(Cliente viejo, Cliente nuevo, Sucursal sucursal) throws CuentaCorrienteException, SQLException {

        if (!titulares.containsKey("Segundo")) {
            try {
                agregarTitular(nuevo, sucursal);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            eliminarTitular(viejo,sucursal);
        } else {
            if (viejo.equals(titulares.get("Titular"))) {
                eliminarTitular(viejo,sucursal);
                try {
                    agregarTitular(nuevo, sucursal);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                intercambiarTitular(sucursal);
            } else {
                eliminarTitular(viejo,sucursal);
                try {
                    agregarTitular(nuevo, sucursal);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

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
}
