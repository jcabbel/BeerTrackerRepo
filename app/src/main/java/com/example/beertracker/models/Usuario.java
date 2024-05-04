package com.example.beertracker.models;

import org.threeten.bp.LocalDate;

public class Usuario {
    private String email;
    private String nombre;
    private String apellidos;
    private int genero;
    private LocalDate fecha_nacimiento;
    private String fotoUri;

    public Usuario () {

    }
    public Usuario(String email) {
        this.email = email;
    }

    public Usuario (String email, String nombre, String apellidos, int genero, LocalDate fecha_nacimiento){
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public String getFotoUri() {
        return fotoUri;
    }

    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", genero=" + genero +
                ", fecha_nacimiento=" + fecha_nacimiento +
                '}';
    }
}
