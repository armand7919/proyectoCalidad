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

import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author ArmandRC
 */
public class LikeDAO {
    AdminBD c;
    
    
    public LikeDAO(){
        c=new AdminBD();
    }
    
    public boolean darLike(int idUser, int idSong){
        if(c==null){
         c.conectar();
        }//******************************
        try {
            String sql="CALL ADD_LIKED_SONG(?,?);;";
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
