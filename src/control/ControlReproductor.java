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

import TelematicoTools.FormMouse.formRoot;
import TelematicoTools.Platillos.DiscoAux;
import TelematicoTools.Platillos.DiscoOne;
import Ventanas.Login;
import Ventanas.Perfil;
import Ventanas.Primaria;
import controlBaseDatos.LikeDAO;
import controlBaseDatos.MusicaDAO;
import controlBaseDatos.UsuarioDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.ClaseCancion;
import modelo.Usuario;


/**
 *
 * @author ArmandRC
 */
public class ControlReproductor implements MouseListener {
   
    
    private int idCancion;
    private Usuario user;
    private Primaria pri = new Primaria();
    private DiscoOne d1 = new DiscoOne();
    private DiscoAux dAux = new DiscoAux();
    private formRoot mueveMouse = new formRoot();
    private ArrayList<ClaseCancion> favoritas = new ArrayList<>();
    private ClaseCancion cCan;
    private MusicaDAO musicaBD;
    private int botonPlay = 0; // variable para controlar la reproducció
    private DefaultListModel Flist = null;
   
    
    public ControlReproductor(Primaria pri,
        String pass, String usu){
        UsuarioDAO usuarioConsulta=new UsuarioDAO();
        this.pri = pri;
        this.user = usuarioConsulta.getUsuarioDAO(usu,pass);
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
        this.pri.setSize (607, 667);
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
            musicaBD=new MusicaDAO();
            cCan=new ClaseCancion();
            cCan.setTitulo("60");
            getListaCanciones(musicaBD.buscaCancion(cCan));
        }else if (e.getSource() == this.pri.but70){
            musicaBD=new MusicaDAO();
            cCan=new ClaseCancion();
            cCan.setTitulo("70");
            getListaCanciones(musicaBD.buscaCancion(cCan));
        }else if (e.getSource() == this.pri.but80){
            musicaBD=new MusicaDAO();
            cCan=new ClaseCancion();
            cCan.setTitulo("80");
            getListaCanciones(musicaBD.buscaCancion(cCan));
        }else if (e.getSource() == this.pri.but90){
            musicaBD=new MusicaDAO();
            cCan=new ClaseCancion();
            cCan.setTitulo("90");
            getListaCanciones(musicaBD.buscaCancion(cCan));
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
            musicaBD=new MusicaDAO();
            cCan=new ClaseCancion();
            cCan.setTitulo("cabaret");
            getListaCanciones(musicaBD.buscaCancion(cCan));
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
           this.pri.removeAll();
           this.d1.stop();
           this.pri.dispose();
            Login login=new Login();
            login.setVisible(true);
        }else if (e.getSource() == this.pri.butPerfil){
            Perfil perfil=new Perfil();
            perfil.setVisible(true);
        }else if (e.getSource() == this.pri.butBusca){
            musicaBD=new MusicaDAO();
            cCan=new ClaseCancion();
            cCan.setTitulo(this.pri.textBusca.getText());
            getListaCanciones(musicaBD.buscaCancion(cCan));
        }
    }

    @Override
    public void mouseEntered (MouseEvent e) {
        
    }

    @Override
    public void mouseExited (MouseEvent e) {

    }
    
    private int prePlay (){
        int id = 0;
        
        for(ClaseCancion items: favoritas){
            if (items.getTitulo().equals(this.pri.jList1.getSelectedValue())){
                play(items);
                cCan=musicaBD.getCancionDAO(items);
                id=cCan.getId();
            }
        }
        return id;
    }

    
    private void play (ClaseCancion item){
        pri.nombreCan.setText (item.getTitulo());
        if (item.getTitulo().endsWith(".mp3")){
            d1.stop();
            d1.PlayMP3(item.getMusica());
        }else if (item.getTitulo().endsWith(".wav")){
            d1.stop();
            d1.PlayWAV(item.getMusica());
        }
    }
    
    private ArrayList<File> getRutaCanciones (File root){
        ArrayList<File> canciones =new ArrayList<>();
        File[] archivos = root.listFiles();
        for ( File patch : archivos){
            if ( (patch.isDirectory()) && (!patch.isHidden())){
                canciones.addAll (getRutaCanciones(root));
                System.out.println("- - - >" + patch.getName() + "<- - -");
            }else{
                if(patch.getName().endsWith(".mp3") ||
                        patch.getName().endsWith(".wav")){
                    canciones.add(patch);
                }
            }
        }
        return canciones;
    }
    
    
    //generar una lista para colocarlas en la lista del frame
    private void getListaCanciones(ArrayList<File> rootFiles){
        Flist = new DefaultListModel();
        pri.jList1.setModel(Flist);
        
        new Thread() {
            @Override
            public void run() {
                favoritas = new ArrayList<> ();
                for ( File items : rootFiles){
                    cCan = new ClaseCancion();
                    cCan.setTitulo(items.getName());
                    if(items.getName().endsWith(".mp3")){
                        cCan.setDuracion(dAux.duracionMP3(items.toString()));
                    }else if(items.getName().endsWith(".wav")){
                        cCan.setDuracion(dAux.duracionWav(items.toString()));
                    }
                    cCan.setMusica(dAux.getBytes(items.toString()));
                //    cCan.setId();
                    favoritas.add(cCan);
                    Flist.addElement(items.getName());
                    System.out.println(items.getName());
                }
            }
        }.start();
    }

}
