package com.example.beertracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.beertracker.R;

public class Main_Menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btnAdd = findViewById(R.id.btnAñadir);
        Button btnSearch = findViewById(R.id.btnBuscar); // Agregamos la referencia al botón de Buscar
        Button btnWall = findViewById(R.id.btnMuro); // Agregamos la referencia al botón de Muro
        Button btnProfile = findViewById(R.id.btnPerfil); // Agregamos la referencia al botón de Perfil

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se presiona el botón "Añadir", iniciar la actividad de registro
                Intent intent = new Intent(Main_Menu_Activity.this, Registrar_Activity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se presiona el botón "Buscar", iniciar la actividad de búsqueda
                Intent intent = new Intent(Main_Menu_Activity.this, Buscar_Activity.class);
                startActivity(intent);
            }
        });

        btnWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un Toast indicando que la función está disponible en próximas versiones
                Toast.makeText(Main_Menu_Activity.this, "Función disponible en próximas versiones", Toast.LENGTH_SHORT).show();
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad de perfil
                Intent intent = new Intent(Main_Menu_Activity.this, Perfil_Activity.class);
                startActivity(intent);
            }
        });

    }
}
