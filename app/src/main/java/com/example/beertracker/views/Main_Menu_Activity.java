package com.example.beertracker.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.beertracker.R;
import com.example.beertracker.adapters.PublicacionAdapter;
import com.example.beertracker.models.Publicacion;

import java.util.List;

public class Main_Menu_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PublicacionAdapter publicacionAdapter;
    private List<Publicacion> publicacionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Configurar el RecyclerView para la feed de publicaciones
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener lista de publicaciones demo
        publicacionList = PublicacionesDemo.getDemoPublicaciones();

        // Configurar el adaptador del RecyclerView
        publicacionAdapter = new PublicacionAdapter(publicacionList, this);
        recyclerView.setAdapter(publicacionAdapter);

        // Configuración de botones
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
                // Actualizar el contenido del RecyclerView con las publicaciones demo
                publicacionAdapter.notifyDataSetChanged();
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
