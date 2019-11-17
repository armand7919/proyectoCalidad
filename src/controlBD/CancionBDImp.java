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
package controlBD;


import TelematicoTools.Platillos.DiscoAux;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import modelo.Cancion;
import modelo.Usuario;

/**
 *
 * @author ArmandRC
 */
public class CancionBDImp implements CancionBD{
    AdminBD c;
    String phatinicial="C:\\Users\\ArmandRC\\Desktop";
    
    public CancionBDImp(){
    c=new AdminBD();
    } 
    @Override
    public ArrayList<Cancion> listaCanciones(){ 
        Cancion cancion= null;
        ArrayList<Cancion> lista= new ArrayList<Cancion>();
        if(c==null){
            c.conectar();
        }
        try {
            String sql="SELECT * FROM cancion;";
            PreparedStatement ps=c.conectar().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
         
            while(rs.next()){
                cancion=new Cancion();
                cancion.setSimagen(phatinicial+rs.getString("IMAGE"));
                cancion.setSmusica(phatinicial+rs.getString("AUDIO"));
                cancion.setTitulo(rs.getString("TITULO"));
                cancion.setArtista(rs.getString("ARTISTA"));
                cancion.setAlbum(rs.getString("ALBUM"));
                cancion.setDecada(rs.getInt("DECADA"));
                cancion.setGenero(rs.getString("GENERO"));
                cancion.setId(rs.getInt("ID_CANCION"));
                
                
                lista.add(cancion);
            }
            rs.close();
            rs=null;
            ps.close();
            ps=null;
            c.desconectar();
       //     System.out.println("Consulta exitosa");
            return lista;
        } catch (SQLException ex) {
       //     System.out.println("Consulta fallida");
            c.desconectar();
            return lista;
        }
    }
    @Override
    public byte[] generaBytesMusica(Cancion cancion){
        c.conectar();
        byte[] bytesCancion = null;
        DiscoAux traeMusicaBD=new DiscoAux();
        File convertidor;
        convertidor=new File(cancion.getSmusica());
        bytesCancion=traeMusicaBD.getBytes(convertidor.toString());
        c.desconectar();
        return bytesCancion;
    }
    @Override
    public Cancion generaImagenAlbum(Cancion cancion){
        c.conectar();
        ImageIcon imagenAlbum = new ImageIcon(cancion.getSimagen());
        cancion.setImagen(imagenAlbum);
        c.desconectar();
        return cancion;
    }
    @Override
    public boolean crear(Cancion cancion){
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
            //    System.out.println("Musica ingresada ingresado");
            return true;
        } catch (SQLException ex) {
            //    System.out.println("No se logro insertar musica");
            c.desconectar();
            return false;
        }
    }
    @Override
    public ArrayList<Cancion> buscaCancion(Cancion cancion){
        Cancion cancionr=new Cancion();
        ArrayList<Cancion> lista;
        ArrayList<Cancion> listaR= new ArrayList<>();
        lista=listaCanciones();
        for (int i=0; i<lista.size(); i++){
            cancionr=lista.get(i);
            if (( 1 == revisaCadena(cancionr.getSmusica(),
                    cancion.getTitulo())) || (1 == revisaCadena(cancionr.getArtista(),
                            cancion.getTitulo()))){
                listaR.add(cancionr);
                //    System.out.println(cancionr.getSimagen());
            }
        }
        lista=null;
        return listaR;  
    }
    
    public ArrayList<Cancion> misCanciones(Usuario usuario){
        ArrayList<Cancion> lista= new ArrayList<>();
        ArrayList<Cancion> listaR= new ArrayList<>();
        ArrayList<Cancion> listaA= new ArrayList<>();
        Cancion cancion=new Cancion();
        Cancion canciona=new Cancion();
        listaA=selecionUsuario(usuario);
        lista=listaCanciones();
        for (int i=0; i<listaA.size(); i++){
            canciona=listaA.get(i);
            for (int j=0; j<lista.size(); j++){
                cancion=lista.get(j);
                if (canciona.getId() == cancion.getId() ){
                    listaR.add(cancion);
                }
            }
        }
        return listaR;
    }
    
    public ArrayList<Cancion> listaUsuarioDB(){
        ArrayList<Cancion> lista= new ArrayList<>();
        Cancion cancion= null;
        if(c==null){
            c.conectar();
        }
        try {
            String sql="SELECT ID_CANCION,ID_USUARIO FROM liked_songs;;";
            PreparedStatement ps=c.conectar().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
         
            while(rs.next()){
                cancion=new Cancion();
                cancion.setId(rs.getInt("ID_CANCION"));
                cancion.setLike(rs.getInt("ID_USUARIO"));
                lista.add(cancion);
            }
            rs.close();
            rs=null;
            ps.close();
            ps=null;
            c.desconectar();
       //     System.out.println("Consulta exitosa");
            return lista;
        } catch (SQLException ex) {
       //     System.out.println("Consulta fallida");
            c.desconectar();
            return lista;
        }
    }
    
    public ArrayList<Cancion> selecionUsuario(Usuario usuario){
        ArrayList<Cancion> lista= new ArrayList<>();
        ArrayList<Cancion> listaR= new ArrayList<>();
        Cancion canciona=new Cancion();
        lista=listaUsuarioDB();
        
        for (int i=0; i<lista.size(); i++){
            canciona=lista.get(i);
            if ( usuario.getId() == canciona.getLike() ){
                listaR.add(canciona);
             //   System.out.println(canciona.getId());
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
            testigo=1;
        } 
        return testigo;
    }     
     //Fin de clase*************************************************************
}
