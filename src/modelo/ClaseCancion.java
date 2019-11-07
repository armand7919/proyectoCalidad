/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ArmandRC
 */
public class ClaseCancion {
   
    private String titulo;
    private String artista;
    private String album;
    private String duracion;
    private String genero;
    private int decada;
    private int like;
    private byte[] musica;
    private byte[] imagen;
    
    //Relación entre musica y claseGenero
  //  private ClaseGenero genero;

    public ClaseCancion() {
    }

    public ClaseCancion(String titulo, String artista,
            String album,String duracion, String genero,
            int decada, int like, byte[] imagen, byte[] musica) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        this.genero = genero;
        this.decada = decada;
        this.like = like;
        this.musica = musica;
        this.imagen = imagen;
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    @Override
    public String toString() {
        return titulo;
    }
    
    
}
