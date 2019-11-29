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

package control;

import java.awt.Image;
import javax.swing.Icon;
import TelematicoTools.FormMouse.formRoot;
import TelematicoTools.Platillos.DiscoAux;
import TelematicoTools.Platillos.DiscoOne;
import Ventanas.Login;
import Ventanas.Reproductor;
import controlBD.LikeDAO;
import controlBD.CancionBDImp;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Cancion;
import modelo.Usuario;

/**
 *
 * @author ArmandRC
 */
public class ControlReproductor implements MouseListener {
    private int idCancion;
    private Usuario user;
    private Reproductor pri = new Reproductor();
    private DiscoOne d1 = new DiscoOne();
    private DiscoAux dAux = new DiscoAux();
    private formRoot mueveMouse = new formRoot();
    private ArrayList<Cancion> favoritas = new ArrayList<>();
    private Cancion cCan;
    private CancionBDImp musicaBD;
    private int botonPlay = 0; // variable para controlar la reproducció
    private DefaultListModel Flist = null;
    
    
    public ControlReproductor(Usuario usuario){
        this.user = usuario;
        inicia();
    }
    
    private void inicia (){
        //Eventos de los botones
        this.pri.butPausa.addMouseListener (this);
        this.pri.butPlay.addMouseListener (this);
        this.pri.butPreferencias.addMouseListener (this);
        this.pri.butStop.addMouseListener (this);
        this.pri.butLike.addMouseListener(this);
        this.pri.butCerrar.addMouseListener(this);
        this.pri.butPerfil.addMouseListener(this);
        this.pri.but60.addMouseListener(this);
        this.pri.but70.addMouseListener(this);
        this.pri.but80.addMouseListener(this);
        this.pri.but90.addMouseListener(this);
        this.pri.butBusca.addMouseListener(this);
        //Evento para los temas de la lista favoritas
        this.pri.jList1.addMouseListener (this);
        this.pri.laUsuario.setText(this.user.getUsuario());
        //se inicializa la ventana
        this.pri.setSize (607, 687);
        this.pri.setVisible (true);
        mueveMouse.ControlProgress (this.pri.jProgressBar1,
                this.pri.jSlider1, d1);
        d1.getTimeRun (this.pri.jProgressBar1);
        
        
    }

    @Override
    public void mouseClicked (MouseEvent e) {
     
    }

    @Override
    public void mousePressed (MouseEvent e) {
        
    }

    @Override
    public void mouseReleased (MouseEvent e) {        
        if (e.getSource() == this.pri.but60){
            obtenerCancion("60s");
        }else if (e.getSource() == this.pri.but70){
            obtenerCancion("70s");
        }else if (e.getSource() == this.pri.but80){
            obtenerCancion("80s");
        }else if (e.getSource() == this.pri.but90){
            obtenerCancion("90s");
        }else if (e.getSource() == this.pri.butPausa){
            botonPlay = 1;
            d1.pause();
        }else if (e.getSource() == this.pri.butPlay){
              switch (botonPlay){
                case 0:
                    idCancion = prePlay();
                break;
                case 1:
                    d1.continuar();
                break;      
            }
        }else  if (e.getSource() == this.pri.butPreferencias){
            musicaBD=new CancionBDImp();
            listass(musicaBD.misCanciones(user)); //mis cacniones 
            this.pri.jList1.setModel(Flist);
        }else  if (e.getSource() == this.pri.butStop){
            d1.stop();
        }else if (e.getSource() == this.pri.butLike){
            if(idCancion == 0){
                JOptionPane.showMessageDialog(this.pri, "Intenta nuevamente");
            }else{
                LikeDAO like=new LikeDAO();
                like.darLike(this.user.getId(), idCancion );
                JOptionPane.showMessageDialog(this.pri, "Diste ¡¡¡Like!!!");
            }
        }else if (e.getSource() == this.pri.jList1){
            botonPlay=0;
            if (e.getClickCount() == 2){
               idCancion=prePlay();
            }   
        }else if (e.getSource() == this.pri.butCerrar){
            cerrarAplicacion();
        }else if (e.getSource() == this.pri.butPerfil){
            ControlPerfil perfil = new ControlPerfil(this.user);
        }else if (e.getSource() == this.pri.butBusca){
            obtenerCancion(this.pri.textBusca.getText());
        }
    }

    @Override
    public void mouseEntered (MouseEvent e) {
        
    }

    @Override
    public void mouseExited (MouseEvent e) {

    }
    
    private void cerrarAplicacion(){
            this.pri.removeAll();
            this.d1.stop();
            this.pri.dispose();
            Login login=new Login();
            login.setVisible(true);
    }
    
    private void obtenerCancion(String busqueda){
            musicaBD=new CancionBDImp();
            cCan=new Cancion();
            cCan.setTitulo(busqueda);
            listass(musicaBD.buscaCancion(cCan));
            this.pri.jList1.setModel(Flist);
    }
    
    private int prePlay (){
        int id = 0;
        for(Cancion cancion: favoritas){
            if (cancion.getTitulo().equals(this.pri.jList1.getSelectedValue())){
                id=cancion.getId();
                play(cancion);
            }
        }
        return id;
    }
    
    
    private void play (Cancion cancion){
        CancionBDImp aux=new CancionBDImp();
        imagenAlbum(aux.generaImagenAlbum(cancion));
        this.pri.nombreCan.setText (cancion.getTitulo());
        d1.stop();
        d1.PlayMP3(aux.generaBytesMusica(cancion));
    }
    
    private void imagenAlbum(Cancion cancion){
        Icon icono = new ImageIcon (cancion.getImagen().getImage().getScaledInstance(pri.jLabel3.getWidth(),
        pri.jLabel3.getHeight(), Image.SCALE_DEFAULT));
        this.pri.jLabel3.setText(null);
        this.pri.jLabel3.setIcon(icono);    
    }
    
    private DefaultListModel listass(ArrayList<Cancion> lista){
        this.Flist = new DefaultListModel();
        this.favoritas=new ArrayList<Cancion>(lista);
        
        for (int i=0; i<lista.size(); i++){
            this.Flist.addElement(lista.get(i).getTitulo());
        }
        return Flist;
    }
}
