/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.sucursal;

/**
 *
 * @author guillem
 */
public class Sucursal {

    private String poblacio;
    private String direccio;
    private int codi;
    private int codiPostal;
    private Sucursal central;

    public Sucursal(String poblacio, String direccio, int codi, int codiPostal, Sucursal central) {
        this.poblacio = poblacio;
        this.direccio = direccio;
        this.codi = codi;
        this.codiPostal = codiPostal;
        this.central = central;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
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
