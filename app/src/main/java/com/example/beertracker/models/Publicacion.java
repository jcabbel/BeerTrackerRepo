package com.example.beertracker.models;

public class Publicacion {
    private String usuario;
    private String descripcion;
    private String imagenPerfil;
    private String imagenPublicacion;
    private int likes;
    private String comentarios;

    public Publicacion (){

    }

    public Publicacion(String usuario, String descripcion, String imagenPerfil, String imagenPublicacion, int likes, String comentarios) {
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.imagenPerfil = imagenPerfil;
        this.imagenPublicacion = imagenPublicacion;
        this.likes = likes;
        this.comentarios = comentarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public String getImagenPublicacion() {
        return imagenPublicacion;
    }

    public int getLikes() {
        return likes;
    }

    public String getComentarios() {
        return comentarios;
    }
}
