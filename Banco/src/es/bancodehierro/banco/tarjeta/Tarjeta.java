/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;
import static es.bancodehierro.banco.tarjeta.TipoTarjeta.CREDITO;
import static es.bancodehierro.banco.tarjeta.TipoTarjeta.DEBITO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public abstract class  Tarjeta {

    
    private int codigoTarjeta;
    private int codigoTitular;
    private int codigoCuentaCorriente;
    private  TipoTarjeta tipo;

    public Tarjeta(int codigoTarjeta, int codigoTitular, int codigoCuentaCorriente, TipoTarjeta tipo) {
        this.codigoTarjeta = codigoTarjeta;
        this.codigoTitular = codigoTitular;
        this.codigoCuentaCorriente = codigoCuentaCorriente;
        this.tipo = tipo;
    }
 
    
    /**
     * Este metodo es las sobrecarga de el constructo de Tarjeta, 
     * devuelve la tarjeta que conicide con el codigo de tarjeta que esta
     * guardada en la base de datos.
     * @param codigoTarjeta 
     */
   public Tarjeta(int codigoTarjeta ,ObjecteConexio conexio){
      Statement consulta = conexio.createStament();
       ResultSet resultat;
       String tipoT=null;
        try {
            resultat = consulta.executeQuery("select * from Tarjeta where codigoTarjeta ="+ codigoTarjeta);
              while(resultat.next()){
            int codTitular = resultat.getInt("CodigoTitular");
            int codCuentaCorriente = resultat.getInt("CodigoCuentaCorriente");
             tipoT = resultat.getString("TipoTarjeta");
             
        if(resultat!=null) resultat.close();
  
        if (tipoT == "CREDITO"){
            this.tipo = CREDITO;
        } else if (tipoT == "DEBITO"){
            this.tipo = DEBITO;
        }
       
        this.codigoTarjeta = codigoTarjeta;
        this.codigoTitular = codTitular;
        this.codigoCuentaCorriente = codCuentaCorriente;
        this.tipo = tipo;
        }
        } catch (SQLException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
   }

    public void setCodigoTarjeta(int codigoTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public void setCuenta(CuentaCorriente cuenta) {
        this.cuenta = cuenta;
    }

    public void setTipo(TipoTarjeta tipo) {
        this.tipo = tipo;
    }

    public int getCodigoTarjeta() {
        return codigoTarjeta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public CuentaCorriente getCuenta() {
        return cuenta;
    }

    public TipoTarjeta getTipo() {
        return tipo;
    }
}
