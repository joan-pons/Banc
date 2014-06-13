package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionTarjetas {

    public Boolean altaTarjeta(String codigoCliente, String cuentaCorriente, int sucursal, Double limite,String tipo) {
       if (tipo.toUpperCase() == "DEBITO"){
            Debito d = new Debito(codigoCliente, cuentaCorriente, sucursal);
        } else {
            Credito c = new Credito(codigoCliente, cuentaCorriente, sucursal, limite);
        }
        return null;
    }

    public Boolean eliminarTarjeta(int codigoTarjeta) {
        try {
            Statement st = Conexion.conectar().createStatement();
            String query = "DELETE FROM tarjeta WHERE codigoTarjeta = " + codigoTarjeta;
            st.executeUpdate(query);
            st.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en la conexion");
            return false;
        }
    }

    public void verMovimientos(String codigoTarjeta) {

    }
}
