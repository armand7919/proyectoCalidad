/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlBaseDatos;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ClaseCancion;

/**
 *
 * @author ArmandRC
 */
public class MusicaDAO {
    Conexion c;
    
    public MusicaDAO(){
    c=new Conexion();
    } 
    
public ClaseCancion lCacniones(){
      //  String direccion=null;
      ClaseCancion cancion= null;
      List<ClaseCancion> lista= new ArrayList<ClaseCancion>();
      if(c==null){
       //  c.conectar();
      }
      try {//se justifica la siguiente cadena seguida porque en ocaciones no pasa bienel comando sql
         String sql="SELECT TITULO,ARTISTA,ALBUM,DECADA,AUDIO,IMAGE,GENERO FROM cancion;";
         PreparedStatement ps=c.conectar().prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         

         while(rs.next()){
             cancion=new ClaseCancion();
             cancion.setTitulo(rs.getString("TITULO"));
             cancion.setMusica(rs.getBytes("AUDIO"));////modificacion a la base de datos...
          //   new File (direccion);
             lista.add(cancion);
        }
         
         rs.close();
         rs=null;
         ps.close();
         ps=null;
         c.desconectar();
         System.out.println("Consulta exitosa");
         return cancion;
         
      } catch (SQLException ex) {
         System.out.println("Consulta fallida");
         c.desconectar();
         return cancion;
      }
 }
    public List<ClaseCancion> listaCacniones(){
        String direccion=null;
      ClaseCancion cancion= null;
      List<ClaseCancion> lista= new ArrayList<ClaseCancion>();
      if(c==null){
       //  c.conectar();
      }
      try {//se justifica la siguiente cadena seguida porque en ocaciones no pasa bienel comando sql
         String sql="SELECT TITULO,ARTISTA,ALBUM,DECADA,AUDIO,IMAGE,GENERO FROM cancion;";
         PreparedStatement ps=c.conectar().prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         

         while(rs.next()){
             cancion=new ClaseCancion();
             cancion.setTitulo(rs.getString("TITULO"));
             cancion.setAlbum(rs.getString("AUDIO"));
          //   new File (direccion);
             lista.add(cancion);
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
    
     public boolean crear(ClaseCancion cancion){
     if(c==null){
        // c.conectar();
     }//******************************
     try {
         String sql="INSERT INTO CANCION(TITULO, ARTISTA,ALBUM, DECADA, LIKES, AUDIO, IMAGE, GENERO)VALUES(?,?,?,?,?,?,?,?);";
         PreparedStatement ps=c.conectar().prepareStatement(sql);
         ps.setString(1, cancion.getTitulo());
         ps.setString(2, cancion.getArtista());
         ps.setString(3, cancion.getAlbum());
         ps.setInt(4, cancion.getDecada());
         ps.setInt(5, cancion.getLike());
         ps.setBytes(6, cancion.getMusica());
         ps.setBytes(7, cancion.getImagen());
         ps.setString(3, cancion.getGenero());
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
    
    public static void main(String[] args){
       ClaseCancion cancion=null;
        MusicaDAO musica=new MusicaDAO();
          cancion=musica.lCacniones();
          System.out.println(cancion.getAlbum());
        //  System.out.println(cancion.getAlbum());
      
    }
 
}
