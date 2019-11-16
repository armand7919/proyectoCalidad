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
package modelo;

import javax.swing.ImageIcon;

/**
 *
 * @author ArmandRC
 */
public class Cancion {
   
    private String titulo;
    private String artista;
    private String album;
    private String duracion;
    private String genero;
    private String smusica;
    private String simagen;
    private int id;
    private int decada;
    private int like;
    private byte[] musica;
    private ImageIcon imagen;
    

    public Cancion() {
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public byte[] getMusica() {
        return musica;
    }

    public void setMusica(byte[] musica) {
        this.musica = musica;
    }
    
    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDecada() {
        return decada;
    }
    
    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setDecada(int decada) {
        this.decada = decada;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
    
    public String getSmusica() {
        return smusica;
    }

    public void setSmusica(String smusica) {
        this.smusica = smusica;
    }

    public String getSimagen() {
        return simagen;
    }

    public void setSimagen(String simagen) {
        this.simagen = simagen;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return titulo;
    }
    
    
}
