package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.cc.CuentaCorriente;
import es.bancodehierro.banco.conexion.Conexion;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

public class GestionTarjetas {

    public Boolean altaTarjeta(int codigoCliente, CuentaCorriente cuentaCorriente, String tipoTarjeta, Double limite) {
        if (limite == null) {
            //Debito d = new Debito(codigoCliente, cuentaCorriente, tipoTarjeta);
        } else {
            //Credito d = new Credito(codigoCliente, cuentaCorriente, tipoTarjeta, limite);
        }
        return null;
    }

    public Boolean eliminarTarjeta(int codigoTarjeta) {
        try {
            Connection cn = Conexion.conectar();
            Statement st = cn.createStatement();
            String query = "DELETE FROM tarjeta WHERE codigoTarjeta = " + codigoTarjeta;
            st.executeUpdate(query);
            st.close();
            Conexion.desconectar();
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
