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

import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author ArmandRC
 */
public class LikeDAO {
    Conexion c;
    
    
    public LikeDAO(){
        c=new Conexion();
    }
    
    public boolean darLike(int idUser, int idSong){
        if(c==null){
         c.conectar();
        }//******************************
        try {
            String sql="INSERT INTO LIKED_SONGS(ID_USUARIO, ID_CANCION) VALUES(?, ?);";
            PreparedStatement ps=c.conectar().prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.setInt(2, idSong);
            ps.execute();
            ps.close();
            ps=null;
            c.desconectar();
            System.out.println("Like dado");
            return true;

        } catch (SQLException ex) {
            System.out.println("No se logro insertar usuario");
            c.desconectar();
            return false;
        }
    }
    /*
        public static void main(String[] args){
 
        LikeDAO like=new LikeDAO();
        
        like.darLike(1, 1);
      
    }*/
}
