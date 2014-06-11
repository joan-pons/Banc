package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.persona.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionTarjetas {

    public Boolean altaTarjeta(int codigoCliente, CuentaCorriente cuentaCorriente, TipoTarjeta tipoTarjeta, Double limite) {
        if (limite == null) {
            //Debito d = new Debito(codigoCliente, cuentaCorriente, tipoTarjeta);
        } else {
            // Credito d = new Credito(codigoCliente, cuentaCorriente, tipoTarjeta, limite);
        }
        return null;
    }

    public Boolean eliminarTarjeta(int codigoTarjeta) {
         try {
            Statement st = conexio.createStatement();
            String query = "DELETE FROM tarjeta WHERE codigoTarjeta = "+codigoTarjeta;
            st.executeUpdate(query);
            st.close();
            return true;
        } catch (SQLException ex) {
             System.out.println("Error al eliminar tarjeta. Confirma el codigo de la tarjeta.");
             return false;
        }
    }

    public Boolean pagar() {

    }

    public Boolean ingresarDebito(double importe, String concepto, int codigoTarjeta) {
        try{
            if(importe<=0){
                throw new 
            }
        } catch () {
            
        }
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateTime = date.toString();
        MovimientoTarjeta m = new MovimientoTarjeta('I', dateTime, concepto, importe, codigoTarjeta);
        return true;
    }

    public Boolean verMovimientos(int codigoTarjeta) {
        return null;
    }
}
