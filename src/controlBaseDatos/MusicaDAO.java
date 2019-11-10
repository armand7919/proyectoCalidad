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


import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    public ClaseCancion lCanciones(){
        ClaseCancion cancion= null;
        List<ClaseCancion> lista= new ArrayList<ClaseCancion>();
        
        if(c==null){
            c.conectar();
        }
        try {//se justifica la siguiente cadena seguida porque en ocaciones no pasa bienel comando sql
            String sql="SELECT TITULO,ARTISTA,ALBUM,DECADA,AUDIO,IMAGE,GENERO FROM cancion;";
            PreparedStatement ps=c.conectar().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                cancion=new ClaseCancion();
                cancion.setTitulo(rs.getString("AUDIO"));
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
            c.conectar();
        }
        try {//se justifica la siguiente cadena seguida porque en ocaciones no pasa bienel comando sql
            String sql="SELECT ID_CANCION,TITULO,ARTISTA,ALBUM,DECADA,AUDIO,IMAGE,GENERO FROM cancion;";
            PreparedStatement ps=c.conectar().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
         
            while(rs.next()){
                cancion=new ClaseCancion();
                cancion.setTitulo(rs.getString("TITULO"));
                cancion.setArtista(rs.getString("ARTISTA"));
                cancion.setAlbum(rs.getString("ALBUM"));
                cancion.setDecada(rs.getInt("DECADA"));
                cancion.setSmusica(rs.getString("AUDIO"));
                cancion.setSimagen(rs.getString("IMAGE"));
                cancion.setGenero(rs.getString("GENERO"));
                cancion.setId(rs.getInt("ID_CANCION"));
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
             c.conectar();
         }
         try {
             String sql="INSERT INTO CANCION(TITULO, ARTISTA,ALBUM, DECADA, LIKES, AUDIO, IMAGE, GENERO)VALUES(?,?,?,?,?,?,?,?);";
             PreparedStatement ps=c.conectar().prepareStatement(sql);
             ps.setString(1, cancion.getTitulo());
             ps.setString(2, cancion.getArtista());
             ps.setString(3, cancion.getAlbum());
             ps.setInt(4, cancion.getDecada());
             ps.setInt(5, cancion.getLike());
             ps.setString(6,cancion.getSmusica());
             ps.setString(7,cancion.getSimagen());
             ps.setString(8, cancion.getGenero());
             ps.executeUpdate();
             ps.close();
             ps=null;
             c.desconectar();
             System.out.println("Musica ingresada ingresado");
             return true;
         } catch (SQLException ex) {
             System.out.println("No se logro insertar musica");
             c.desconectar();
             return false;
         }
     }

     public ArrayList<File> buscaCancion(ClaseCancion cancion){
      
         ClaseCancion cancionr;
         List<ClaseCancion> lista;
         ArrayList<File> listaR= new ArrayList<>();
         MusicaDAO musicaDAO=new MusicaDAO();
         lista=musicaDAO.listaCacniones();
      
         for (int i=0; i<lista.size(); i++){
             cancionr=lista.get(i);
             if ( 1 == revisaCadena(cancionr.getSmusica(),
                  cancion.getTitulo())){
                 listaR.add(new File(cancionr.getSmusica()));
                 System.out.println(cancionr.getTitulo());
             }
         }
         return listaR;   
  }
  
     public int revisaCadena(String baseDatos, String busqueda){
         int testigo=0;

        //escapar y agregar limites de palabra completa - case-insensitive
        Pattern regex = Pattern.compile("\\b" +
                Pattern.quote(busqueda) + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher match = regex.matcher(baseDatos);

        //la palabra está en el texto??
        if (match.find()) {  //si se quiere encontrar todas las ocurrencias: cambiar el if por while
            System.out.println("Encontrado: '" + match.group() 
                             + "' dentro de '" + busqueda 
                             + "' en la posición " + match.start());
            testigo=1;
        } 
        return testigo;
      }
     
     public ClaseCancion getCancionDAO(ClaseCancion cancion){
      
         ClaseCancion cancionAux = new ClaseCancion();
         ClaseCancion cancionR = new ClaseCancion();
         List<ClaseCancion> lista;
         MusicaDAO musicaDAO=new MusicaDAO();
         lista=musicaDAO.listaCacniones();
      
         for (int i=0; i<lista.size(); i++){
             cancionAux=lista.get(i);
             if ( 1 == revisaCadena(cancionAux.getSmusica(),
                  cancion.getTitulo())){
                 cancionR = lista.get(i);
             }
         }
         return cancionR;   
  }
      ///test del codigo *************************************** 
    public static void main(String[] args){
     /* Pruebasss*****************************1
        DiscoOne disc0=new DiscoOne();
        DiscoOne disc1=new DiscoOne();
        MusicaDAO musicaDAO=new MusicaDAO();
        ClaseCancion cancion=new ClaseCancion();
        ClaseCancion cancionr=new ClaseCancion();
        cancion.setTitulo("cabaret");
        
        
        List<ClaseCancion> lista;
        lista=musicaDAO.buscaCancion(cancion);
        for (int i=0; i<lista.size(); i++){
          cancionr=lista.get(i);
          System.out.println(cancionr.getSmusica());
        }
        
        File aux=new File(cancionr.getSmusica());
        cancion.setMusica(disc0.getBytes(aux.toString()));
        disc1.PlayMP3(cancion.getMusica());**************1
        ////***************Prueba de agregar Cacnion****************///
     /*  ClaseCancion cancion=new ClaseCancion();
       ClaseCancion cancion2=new ClaseCancion();
       ClaseCancion cancion3=new ClaseCancion();
       DiscoOne disc0=new DiscoOne();
       DiscoAux disc1=new DiscoAux();
       File fileCancion=new File("C:\\Users\\ArmandRC\\Desktop\\rockola\\jazz 90\\Sexiest Songbook\\Jazz and U2\\WITH OR WITHOUT YOU.mp3");
       
       
     //  System.out.println(cancion.getAlbum());
       // verificar que llegue la musica en el metodo crear
       File fileImagen = new File("C:\\Users\\ArmandRC\\Desktop\\rockola\\jazz 90\\Sexiest Songbook\\Jazz and U2\\Jazz and U2.png");
       
       
       /* MusicaDAO musica=new MusicaDAO();
       
       
       cancion=musica.lCacniones();
        System.out.println(cancion.getAlbum());*/ //  System.out.println(cancion.getAlbum());
      //   disc0.PlayMP3(cancion.getMusica());
     //      disc1.saveLocal(musicaBytes, "");
        
      //  cancion.setMusica(fileCancion);
     /*   cancion.setSmusica(fileCancion.toString());
        cancion.setSimagen(fileImagen.toString());
        cancion.setAlbum("Jazz and U2");
        cancion.setArtista("Sexiest Songbook");
        cancion.setDecada(90);
        cancion.setLike(0);
        cancion.setTitulo("WITH OR WITHOUT YOU");
        cancion.setGenero("Jazz");
        System.out.println(cancion.getSmusica());
        musicaDAO.crear(cancion);
      /*  cancion2=musicaDAO.lCanciones();
        String asd=cancion2.getTitulo();
        File in = new File(asd);
        cancion3.setMusica(disc0.getBytes(in.toString()));
        disc0.PlayMP3(cancion3.getMusica());*/
      
    }
 
}
