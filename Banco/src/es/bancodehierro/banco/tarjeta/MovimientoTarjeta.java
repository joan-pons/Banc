package es.bancodehierro.banco.tarjeta;

import es.bancodehierro.banco.conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MovimientoTarjeta {

    int codigo;
    char tipo;
    String fecha;
    String concepto;
    double importe;
    int codigoTarjeta;

    public MovimientoTarjeta(char tipo, String fecha, String concepto, double importe, int codigoTarjeta) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.concepto = concepto;
        this.importe = importe;
        this.codigoTarjeta = codigoTarjeta;
        try {
            Connection cn = Conexion.conectar();
            Statement st = cn.createStatement();
            String query = "INSERT INTO movimientoTarjeta VALUES "
                    + "(tipo=" + tipo
                    + ",fecha=" + fecha
                    + ",concepto=" + concepto
                    + ",importe=" + importe
                    + ",codigoTarjeta=" + codigoTarjeta+")";
            st.executeUpdate(query);
            st.close();
            Conexion.desconectar();

        } catch (SQLException ex) {
            System.out.println("Error en la conexion");

        }
    }

}
