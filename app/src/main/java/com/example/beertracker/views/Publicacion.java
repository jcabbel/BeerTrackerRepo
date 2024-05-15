package com.example.beertracker.models;

public class Publicacion {
    private String usuario;
    private String descripcion;
    private int imagenPerfil;
    private int imagenPublicacion;
    private int likes;
    private String comentarios;

    public Publicacion(String usuario, String descripcion, int imagenPerfil, int imagenPublicacion, int likes, String comentarios) {
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

    public int getImagenPerfil() {
        return imagenPerfil;
    }

    public int getImagenPublicacion() {
        return imagenPublicacion;
    }

    public int getLikes() {
        return likes;
    }

    public String getComentarios() {
        return comentarios;
    }
}
