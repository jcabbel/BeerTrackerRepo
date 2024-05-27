package com.example.beertracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Bienvenida_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Iniciar la actividad de bienvenida al iniciar la aplicación
        Intent intent = new Intent(Bienvenida_Activity.this, Login_Activity.class);
        startActivity(intent);
        finish(); // Terminar la actividad actual para que el usuario no pueda regresar aquí
    }
}