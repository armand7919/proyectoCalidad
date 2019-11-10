/*
 * @(#)Rockolav2.java 1.82 09/10/19
 *
 * Armando Rivera Cervantes
 * Copyright (c) Inc.
 * Iztapalapa CDMX México
 * Todos los derechos reservados.
 *
 * Más información y descripción del Copyright.
 *
 */
package controlBaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author ArmandRC
 */
public class Conexion {
    
    Connection cx;
    String bD= "proyector";
    String url = "jdbc:mysql://localhost:3306/"+bD;
    String user = "root";
    String pass = "Pascal1979";
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cx=(Connection)DriverManager.getConnection(url,user,pass);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Conexion fallida");
        }
        return cx;
    }
    public void desconectar(){
        try {
            cx.close();
            System.out.println("Se desconecto");
        } catch (SQLException ex) {
            System.out.println("No logro desconectarse");
        }
    }
    
    /*Prueba de conexion exitosa
    public static void main(String[] args){
        Conexion c=new Conexion();
        c.conectar();
        c.desconectar();
    }*/
}
