package es.bancodehierro.banco.conexion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author antonio
 */
public class Conexion {

    private static Connection conexion = null;
    private static final String url = "jdbc:oracle:thin:banco/oracle@//192.168.7.24:1521/orcl";
    
    public static Connection conectar() {
        try {
            if (conexion == null) {
                conexion = DriverManager.getConnection(url);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
        return conexion;
    }
    
    public static void desconectar(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage() + ". \n ErrorCode:" + ex.getErrorCode() + ", SQLState:" + ex.getSQLState());
        }
    }
}