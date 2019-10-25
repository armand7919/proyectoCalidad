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
public class claseCancion {
    
    private String codigo;
    private String nombre;
    private String duracion;
    private byte[] musica;
    
    //Relación entre musica y claseGenero
    private claseGenero genero;

    public claseCancion() {
    }

    public claseCancion(String codigo, String nombre,
            String duracion, byte[] musica, claseGenero genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.duracion = duracion;
        this.musica = musica;
        this.genero = genero;
    }

    public claseGenero getGenero() {
        return genero;
    }

    public void setGenero(claseGenero genero) {
        this.genero = genero;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
