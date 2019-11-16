/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlBD;


import java.util.ArrayList;

import modelo.Cancion;

/**
 *
 * @author ArmandRC
 */
public interface CancionBD {
    
    public ArrayList<Cancion> listaCanciones();
    public byte[] generaBytesMusica(Cancion cancion);
    public Cancion generaImagenAlbum(Cancion cancion);
    public boolean crear(Cancion cancion);
    public ArrayList<Cancion> buscaCancion(Cancion cancion);

    
}
