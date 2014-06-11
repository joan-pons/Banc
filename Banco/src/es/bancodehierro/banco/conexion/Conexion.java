/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.bancodehierro.banco.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author antonio
 */
public class Conexion {

    private static Connection conexio = null;
    private static final String url ="";
    public static Connection conectar() {
        try {
            if (conexio == null) {
                conexio = DriverManager.getConnection(url);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
        return conexio;
    }
}
