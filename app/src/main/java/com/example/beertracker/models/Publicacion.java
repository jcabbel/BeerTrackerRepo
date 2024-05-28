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
    private String cerveza;  // Nuevo campo añadido a la feed
    private String sabor;  // Nuevo campo añadido a la feed
    private String lugar;  // Nuevo campo añadido a la feed
    private int valoracion;  // Nuevo campo añadido a la feed
    private String observaciones;  // Nuevo campo añadido a la feed
    private Timestamp timestamp;

    public Publicacion() {

    }

    public Publicacion(String usuario, String experiencia, String descripcion, String imagenPerfil, String imagenPublicacion, long likes, String comentarios, String cerveza, String sabor, String lugar, int valoracion, String observaciones, Timestamp timestamp) {
        this.usuario = usuario;
        this.experiencia = experiencia;
        this.descripcion = descripcion;
        this.imagenPerfil = imagenPerfil;
        this.imagenPublicacion = imagenPublicacion;
        this.likes = likes;
        this.comentarios = comentarios;
        this.cerveza = cerveza;
        this.sabor = sabor;
        this.lugar = lugar;
        this.valoracion = valoracion;
        this.observaciones = observaciones;
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

    //getters y setters de los nuevos campos añadidos a la feed ------------------------------------NUEVOS Get+Set PARAMETROS NUEVOS incluídos en PUBLICACIONFEED

    public String getCerveza() {
        return cerveza;
    }
    public void setCerveza(String cerveza) {
        this.cerveza = cerveza;
    }
    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    public String getExperiencia(){return experiencia;}
}
