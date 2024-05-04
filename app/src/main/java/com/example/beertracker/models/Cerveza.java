package com.example.beertracker.models;

public class Cerveza {
    private String id;
    private String nombre;
    private String marca;

    public Cerveza(){
    }

    public Cerveza(String id) {
        this.id = id;
    }

    public Cerveza(String id, String nombre, String marca) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}
