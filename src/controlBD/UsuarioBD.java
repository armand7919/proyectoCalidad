/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlBD;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author ArmandRC
 */
public interface UsuarioBD {
    
        
 public boolean crear(Usuario user);
 public List<Usuario> listaUsuarios();
 public int cambiaParametro(String user, String psw, String newUser);
 public Usuario consultaLogin(Usuario usuario);
 public Usuario getUsuarioDAO(String user, String psw );
    
    
    
}
