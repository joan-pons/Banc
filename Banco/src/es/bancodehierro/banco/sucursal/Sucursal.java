/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.sucursal;

/**
 * Clase sucursal para generar objetos de tipo sucursal con toda la informacion
 * referente a una sucursal
 *
 * @author Guillem Arrom
 */
public class Sucursal {

    private String poblacio;
    private String direccio;
    private int codi;
    private int codiPostal;
    private Sucursal central;
    private String telefono;

    /**
     * Constructor basico de sucursal con todos sus parametros
     * 
     * @param poblacio String de la poblacion donde se ubica
     * @param direccio String de la direccion de donde se ubica
     * @param codi Codigo especifico y unico de sucursal
     * @param codiPostal Codigo postal de donde se ubica
     * @param central Objeto Sucursal con toda la informacion sobre su central (null=no tiene central)
     * @param telefono String telefono con su telefono.
     */
    public Sucursal(String poblacio, String direccio, int codi, int codiPostal, Sucursal central, String telefono) {
        this.poblacio = poblacio;
        this.direccio = direccio;
        this.codi = codi;
        this.codiPostal = codiPostal;
        this.central = central;
        this.telefono = telefono;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public int getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(int codiPostal) {
        this.codiPostal = codiPostal;
    }

    public Sucursal getCentral() {
        return central;
    }

    public void setCentral(Sucursal central) {
        this.central = central;
    }

}
