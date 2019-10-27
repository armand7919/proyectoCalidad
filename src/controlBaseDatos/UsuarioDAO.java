/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlBaseDatos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author ArmandRC
 */
public class UsuarioDAO {
 Conexion c;
 
 public UsuarioDAO(){
     c=new Conexion();
 }
    
 public boolean crear(Usuario user){
     if(c==null){
         c.conectar();
     }//******************************
     try {
         String sql="INSERT INTO USUARIO(NOMBRE_USUARIO,APELLIDO_USUARIO, SEXO, CORREO_E, USER_NAME, PASSWORD, TELEFONO)VALUES(?,?,?,?,?,?,?);";
         PreparedStatement ps=c.conectar().prepareStatement(sql);
         ps.setString(1, user.getNombre());
         ps.setString(2, user.getApellido());
         ps.setString(3, user.getSexo());
         ps.setString(4, user.getCorreo());
         ps.setString(5, user.getUsuario());
         ps.setString(6, user.getContrasena());
         ps.setInt(7, user.getTelefono());
         ps.execute();
         ps.close();
         ps=null;
         c.desconectar();
         System.out.println("Usuario ingresado");
         return true;
         
     } catch (SQLException ex) {
         System.out.println("No se logro insertar usuario");
         c.desconectar();
         return false;
     }
 }
 /* prueba de inser
    public static void main(String[] args){
        Usuario usuario=new Usuario();
        UsuarioDAO userDAO=new UsuarioDAO();
        
        usuario.setNombre("Alma");
        usuario.setApellido("Robles");
        usuario.setSexo("Mujer");
        usuario.setCorreo("Muejer@hot.com");
        usuario.setUsuario("lopez78");
        usuario.setContrasena("123");
        usuario.setTelefono(58591623);
        
        userDAO.crear(usuario);
      
    }
 */
}
