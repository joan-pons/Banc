/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.cc;

import es.bancodehierro.banco.excepciones.CuentaCorrienteException;
import es.bancodehierro.banco.persona.Cliente;
import java.util.ArrayList;
import java.util.HashMap;

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
    
    public void agregarTitular(Cliente titular) throws CuentaCorrienteException {
        if (!titulares.containsValue(titular)) {
            if (!titulares.containsKey("Titular")) {
                titulares.put("Titular", titular);
            } else if (!titulares.containsKey("Segundo")) {
                titulares.put("Segundo", titular);
            } else {
                throw new CuentaCorrienteException("Error: Límite de titulares alcanzado.");
            }
        } else {
            throw new CuentaCorrienteException("Error: Titular, " + titular.getDNI() + ", ya asociado a está cuenta.");
        }
    }
    
    public void eliminarTitular(Cliente cliente) throws CuentaCorrienteException {
        if (!titulares.containsValue(cliente)) {
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
    
    public void intercambiarTitular() throws CuentaCorrienteException {
        
        Cliente auxiliar = titulares.get("Titular");
        
        eliminarTitular(titulares.get("Titular"));
        
        agregarTitular(auxiliar);
        
    }
}
