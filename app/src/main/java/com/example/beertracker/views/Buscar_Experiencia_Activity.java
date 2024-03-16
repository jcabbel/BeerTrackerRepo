package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beertracker.R;

public class Buscar_Experiencia_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_experiencia);

        Button btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementar aquí la lógica de búsqueda de experiencias
                Toast.makeText(Buscar_Experiencia_Activity.this, "Función de búsqueda de experiencias disponible en futuras versiones", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón para cancelar búsqueda
        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad de búsqueda
                onBackPressed();
            }
        });
    }
}
