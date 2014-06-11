package es.bancodehierro.banco.tarjeta;

import banc.Conexion;
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

    public Boolean eliminarTarjeta(int codigoTarjeta, Conexion conexio) {
        try {
            Connection cn = conexio.conectar();
            Statement st = cn.createStatement();
            String query = "DELETE FROM tarjeta WHERE codigoTarjeta = " + codigoTarjeta;
            st.executeUpdate(query);
            st.close();
            conexio.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en la conexion");
            return false;
        }
    }

    public Boolean verMovimientos(int codigoTarjeta) {
        return null;
    }
}
