/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

/**
 *
 * @author ArmandRC
 */
public class UsuarioBDImp implements UsuarioBD{
 AdminBD c;
 public UsuarioBDImp(){
     c=new AdminBD();
 }
 @Override
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
 @Override
 public List<Usuario> listaUsuarios(){
      Usuario usuario= null;
      List<Usuario> lista= new ArrayList<Usuario>();
      if(c==null){
         c.conectar();
      }
      try {//se justifica la siguiente cadena seguida porque en ocaciones no pasa bienel comando sql
         String sql="SELECT ID_USUARIO,NOMBRE_USUARIO,APELLIDO_USUARIO,SEXO,CORREO_E,USER_NAME,PASSWORD,TELEFONO FROM usuario;";
         PreparedStatement ps=c.conectar().prepareStatement(sql);
         ResultSet rs=ps.executeQuery();

         while(rs.next()){
             usuario=new Usuario();
             usuario.setId(rs.getInt("ID_USUARIO"));
             usuario.setNombre(rs.getString("NOMBRE_USUARIO"));
             usuario.setApellido(rs.getString("APELLIDO_USUARIO"));
             usuario.setSexo(rs.getString("SEXO"));
             usuario.setCorreo(rs.getString("CORREO_E"));
             usuario.setUsuario(rs.getString("USER_NAME"));
             usuario.setContrasena(rs.getString("PASSWORD"));
             usuario.setTelefono(rs.getInt("TELEFONO"));
             lista.add(usuario);
        }
         
         rs.close();
         rs=null;
         ps.close();
         ps=null;
         c.desconectar();
         System.out.println("Consulta exitosa");
         return lista;
         
      } catch (SQLException ex) {
         System.out.println("Consulta fallida");
         c.desconectar();
         return lista;
      }
 }
 @Override 
 public int cambiaParametro(String user, String psw, String newUser){
      int verificador=0;
      Usuario usuario=null;
      List<Usuario> lista= new ArrayList<Usuario>();
      UsuarioBDImp userDAO=new UsuarioBDImp();
      lista=userDAO.listaUsuarios();
      
      for (int i=0; i<lista.size(); i++){
          usuario=lista.get(i);
          if(usuario.getNombre().equals(user) &&
                  usuario.getContrasena().equals(psw)){
                   try {
                        String sql="UPDATE usuario SET USER_NAME = ? WHERE ID_USUARIO =?;";
                        PreparedStatement ps=c.conectar().prepareStatement(sql);
                        ps.setString(1, newUser);
                        ps.setInt(2, usuario.getId());
                        ps.executeUpdate();
                        ps.close();
                        ps=null;
                        c.desconectar();
                        System.out.println("Usuario actualizado");
                        verificador=1;

                    } catch (SQLException ex) {
                        System.out.println("No se logro actualizar usuario");
                        c.desconectar();
                        verificador=0;
                    }
              
          }
      }

         return verificador;   
  }
 @Override
 public Usuario consultaLogin(Usuario usuario){
        Usuario usuariob= null;
        List<Usuario> lista= new ArrayList<Usuario>();
        UsuarioBDImp userDAO=new UsuarioBDImp();
        lista=userDAO.listaUsuarios();
        
        for (int i=0; i<lista.size(); i++){
            usuariob=lista.get(i);
            if(usuario.getUsuario().equals(usuario.getUsuario()) &&
                    usuario.getContrasena().equals(usuario.getContrasena())){
                return usuariob;
            }
        }

         return usuariob;   
  }
 @Override 
 public Usuario getUsuarioDAO(String user, String psw ){
        Usuario usuario=new Usuario();
        Usuario usuarioReturn=new Usuario();
        List<Usuario> lista= new ArrayList<>();
        UsuarioBDImp userDAO=new UsuarioBDImp();
        lista=userDAO.listaUsuarios();
        
        for (int i=0; i<lista.size(); i++){
            usuario=lista.get(i);
            if(usuario.getUsuario().equals(user) &&
                    usuario.getContrasena().equals(psw)){
                usuarioReturn=lista.get(i);
            }
        }
        return usuarioReturn;   
  }  
}
