/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Ventanas.Registro;
import controlBaseDatos.UsuarioDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Usuario;

/**
 *
 * @author ArmandRC
 */
public class ControlRegistro implements MouseListener {

    int verificador;
    Usuario usuarioRegistro;
    Registro registro=new Registro();
    

    public ControlRegistro() {
    }
    
    public ControlRegistro(Registro registro) {
        this.registro = registro;
        iniciar();//se puede sesabilitar para hacerlo en el main, sólo es para prueba
    } 
    
    public void iniciar(){
        this.registro.butAceptar.addMouseListener (this);
        this.registro.butCancelar.addMouseListener (this);
        //se inicializa la ventana
        this.registro.setSize (605, 700);
        this.registro.setVisible (true);
        System.out.println("fase 1 inicia");
        
    }
    
    public void registrar(Registro registro){
        Usuario user=new Usuario();
              
        UsuarioDAO usuarioDAO=new UsuarioDAO(); //variable de clase usuarioBaseDatos que aun no se ha hecho
        
        user.setNombre(registro.texNombre.getText()); //ejemplo de como meter los datos del usuario
        user.setApellido(registro.textApellido.getText());
        user.setSexo("Mujer");
        user.setCorreo("Muejer@hot.com");
        user.setUsuario("lopez78");
        user.setContrasena("123");
        user.setTelefono(58591623);
           
        usuarioDAO.crear(user);
        
        System.out.println("fase 4 registra ok");
         //   usuarioRegistro.setNombre(registro.texNombre.getText());
            //se pasan todos los parametros del registro
         //   usuarioBaseDatos=usuarioRegistro;//una vez lleno el usuario que regresaras lo pasas a el usuarioBaseDatos
        
    }
    
    public int verificaCampos(){
        int verificador=1; //ahí que cambiar por cero, el uno sólo es para la prueba
        //usa la variable registro para que hacer las verificaciones y una vez que este todo correcto asegna 1 a la variable verificador
        
        System.out.println("fase 2 verifica ");
        
        return verificador;
    }
    
    
 
    public void mouseClicked(MouseEvent e) {
       
    }


    public void mousePressed(MouseEvent e) {
    
    }


    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == this.registro.butAceptar){
             verificador = verificaCampos(); // llamas ala fucion para verificar los campos 
             if(verificador == 1){
                System.out.println("fase 3 mouse");
                registrar(this.registro);
                
             }else{
                 
                 // una ventana auxiliar de registro fallido
             }
        }else if (e.getSource() == this.registro.butCancelar){
            registro.dispose();
        }
       
    }

  
    public void mouseEntered(MouseEvent e) {
       
    }


    public void mouseExited(MouseEvent e) {
        
    }
    
    
}
