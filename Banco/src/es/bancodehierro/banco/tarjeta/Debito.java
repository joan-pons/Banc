/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.conexion.Conexion;
import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bernadi
 */
public class Debito extends Tarjeta {

    private Double saldo;



    public Debito(int codigoTarjeta, int codigoTitular, int codigoCuentaCorriente, String tipo) {
        super(codigoTarjeta, codigoTitular, codigoCuentaCorriente, tipo);
        
    }
/**
 * Este constructor llama al constructos de Tarjeta que Carja la tarjeta de la base de datos
 * Tambien nos selecciona cual es saldo de la cuenta a la que esta asociada la Tarjeta de debito
 * @param codigoTarjeta 
 */
    public Debito(int codigoTarjeta) {
        super(codigoTarjeta);
        
           ResultSet resultat;
       String tipoT=null;
       double tarjetaSaldo = 0;
       int codCuentaCorriente = 0;
        try {
            Connection conexion = Conexion.conectar();
             Statement consulta = conexion.createStatement();
            resultat = consulta.executeQuery("select importe from TARJETA T join CUENTA_CORRIENTE CC on T.NUMERO_CC = CC.NUMERO_CC  where CODIGO_TARJETA_DEBITO ="+ codigoTarjeta);
              while(resultat.next()){
                tarjetaSaldo = resultat.getInt("IMPORTE_CC");
              }
        if(resultat!=null) resultat.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.saldo = tarjetaSaldo;
    }

    
    
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean pagar(double importe, String concepto) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateTime = date.toString();
        MovimientoTarjeta m = new MovimientoTarjeta('P', dateTime, concepto, importe, this.codigoTarjeta);
        return true;
    }

    public Boolean ingresar(double importe, String concepto) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateTime = date.toString();
        MovimientoTarjeta m = new MovimientoTarjeta('I', dateTime, concepto, importe, this.codigoTarjeta);
        return true;
    }
}
