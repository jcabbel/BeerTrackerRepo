package com.example.beertracker.models;

public class NombreMarca {
    private String nombre;
    private String marca;
    private String id;

    public NombreMarca(String nombre, String marca, String id) {
        this.nombre = nombre;
        this.marca = marca;
        this.id = id;
    }

    public String getNombreMarca() {
        return marca + " - " + nombre;
    }

    public String getId() {
        return id;
    }
}
