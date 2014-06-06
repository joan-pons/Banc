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
 * @author Andreu Oliver, Juanjo Macanás, Roberto Simón, Xavi Jimenez, Miquel Angel Cànaves
 */
public class CuentaCorriente {
    private final String IBAN = "";
    private final String ENTIDAD = "";
    private String oficina;
    private String dC;
    private final String CUENTA = "";
    private double importe;
    private ArrayList<Movimiento> movimientos = new ArrayList<>();
    private ArrayList<Movimiento> incidencias = new ArrayList<>();
    private HashMap<Cliente, String> titulares = new HashMap<Cliente, String>();

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
        return incidencia;
    }

    public void setIncidencia(ArrayList<Movimiento> incidencia) {
        this.incidencia = incidencia;
    }

    public HashMap<Cliente, String> getTitulares() {
        return titulares;
    }

    public void setTitulares(HashMap<Cliente, String> titulares) {
        this.titulares = titulares;
    }

    public CuentaCorriente(String oficina, String dC, double importe) {
        this.oficina = oficina;
        this.dC = dC;
        this.importe = importe;
    }

    public void agregarTitular(Cliente titular) throws CuentaCorrienteException {
        if (!titulares.containsValue(titular)) {
            if (!titulares.containsKey("Titular")) {
                titulares.put(titular, "Titular");
            } else {
                titulares.put(titular, "Segundo Titular");
            }
        } else {
            throw new CuentaCorrienteException("Error: Titular, " + titular.getDNI() + ", ya asociado a está cuenta.");
        }
    }

    public boolean modificarTitular(Cliente nuevoTitular, Cliente) {
        boolean resultado = false;

        return resultado;
    }

    public boolean eliminarTitular(Cliente cliente) throws CuentaCorrienteException {
        boolean resultado = false;
        if (!titulares.containsValue(cliente)) {
            if(titulares.get("Segundo").equals(cliente)){
                titulares.remove(cliente);
            }else if(titulares.get("Titular").equals(cliente) && titulares.containsKey("Segundo")){
                titulares.remove(cliente);
                Cliente nuevoTitular = titulares.get("Segundo");
                titulares.put(nuevoTitular,"Titular");
                titulares.remove("Segundo");
            }else if(titulares.containsValue(cliente) && titulares.containsKey("Titular") && !titulares.containsKey("Segundo")){
                throw new CuentaCorrienteException();
            }else{
                throw new CuentaCorrienteException();
            }
        }else{
            throw new CuentaCorrienteException();
        }
    }
}
