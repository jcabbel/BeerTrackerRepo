package com.example.beertracker.models;

import com.google.firebase.Timestamp;

public class Publicacion {
    private String usuario;
    private String experiencia;
    private String descripcion;
    private String imagenPerfil;
    private String imagenPublicacion;
    private long likes;
    private String comentarios;
    private Timestamp timestamp;

    public Publicacion() {

    }

    public Publicacion(String usuario, String experiencia, String descripcion, String imagenPerfil, String imagenPublicacion, long likes, String comentarios, Timestamp timestamp) {
        this.usuario = usuario;
        this.experiencia = experiencia;
        this.descripcion = descripcion;
        this.imagenPerfil = imagenPerfil;
        this.imagenPublicacion = imagenPublicacion;
        this.likes = likes;
        this.comentarios = comentarios;
        this.timestamp = timestamp;
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

    public long getLikes() {
        return likes;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public void setImagenPublicacion(String imagenPublicacion) {
        this.imagenPublicacion = imagenPublicacion;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    public String getExperiencia(){return experiencia;}
}
