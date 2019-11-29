/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Ventanas.Perfil;
import Ventanas.Reproductor;
import controlBD.UsuarioBDImp;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author Dani
 */

public class ControlPerfil implements MouseListener{

    private Perfil per= new Perfil();
    private Usuario user= new Usuario();

    public ControlPerfil( Usuario user) {
        this.user=user;
        inicia();
    }
    
    
    public void inicia (){
        this.per.lbNombre.setText(this.user.getNombre()+" "+this.user.getApellido());
        this.per.lbCorreo.setText(this.user.getCorreo());
        this.per.lbTelefono.setText(String.valueOf(this.user.getTelefono()));
       
        this.per.setVisible(true);
        
           
    }
    
 
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
   
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
}
