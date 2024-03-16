package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beertracker.R;

public class Registro_Experiencia_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_experiencia);

        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnCancelarRegistro = findViewById(R.id.btnCancelar);
        Button btnCargarFoto = findViewById(R.id.btnCargarFoto);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un mensaje Toast al guardar
                Toast.makeText(Registro_Experiencia_Activity.this, "Función Guardar Experiencia disponible en futuras versiones", Toast.LENGTH_SHORT).show();
                // Volver a registrar.xml
                volverARegistro();
            }
        });

        btnCancelarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a registrar.xml sin guardar la experiencia
                volverARegistro();
            }
        });

        btnCargarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un mensaje Toast indicando que se requiere actualización
                Toast.makeText(Registro_Experiencia_Activity.this, "Función Cargar Foto disponible en futuras versiones", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void volverARegistro() {
        Intent intent = new Intent(Registro_Experiencia_Activity.this, Registrar_Activity.class);
        startActivity(intent);
        finish(); // Finalizar esta actividad para evitar volver atrás con el botón de retroceso
    }
}
