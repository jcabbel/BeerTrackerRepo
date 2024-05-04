package com.example.beertracker.models;

public class Experiencia {
    private int id;
    private Usuario usuario;
    private Cerveza cerveza;
    private String sabor;
    private String lugar;
    private int valoracion;
    private String observaciones;

    public Experiencia(){

    }

    public Experiencia (int id){
        this.id = id;
    }

    public Experiencia (int id, Usuario usuario, Cerveza cerveza, String sabor, String lugar, int valoracion, String observaciones){
        this.id = id;
        this.usuario = usuario;
        this.cerveza = cerveza;
        this.sabor = sabor;
        this.lugar = lugar;
        this.valoracion = valoracion;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cerveza getCerveza() {
        return cerveza;
    }

    public void setCerveza(Cerveza cerveza) {
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
}
