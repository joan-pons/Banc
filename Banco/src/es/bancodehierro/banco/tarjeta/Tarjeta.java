/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.bancodehierro.banco.tarjeta;

import banc.Conexion;
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
   public Tarjeta(int codigoTarjeta ,Conexion conexion){
     
       ResultSet resultat;
       String tipoT=null;
       int codTitular = 0;
       int codCuentaCorriente = 0;
        try {
             Statement consulta = conexion.conectar().createStatement();
            resultat = consulta.executeQuery("select * from Tarjeta where codigoTarjeta ="+ codigoTarjeta);
              while(resultat.next()){
                codTitular = resultat.getInt("CodigoTitular");
                codCuentaCorriente = resultat.getInt("CodigoCuentaCorriente");
                tipoT = resultat.getString("TipoTarjeta");
              }
        if(resultat!=null) resultat.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void setCodigoTarjeta(int codigoTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
    }

    public void setTipo(TipoTarjeta tipo) {
        this.tipo = tipo;
    }

    public int getCodigoTarjeta() {
        return codigoTarjeta;
    }

    public int getCodigoTitular() {
        return codigoTitular;
    }

    public int getCodigoCuentaCorriente() {
        return codigoCuentaCorriente;
    }

    public void setCodigoTitular(int codigoTitular) {
        this.codigoTitular = codigoTitular;
    }

    public void setCodigoCuentaCorriente(int codigoCuentaCorriente) {
        this.codigoCuentaCorriente = codigoCuentaCorriente;
    }



    public TipoTarjeta getTipo() {
        return tipo;
    }
}
