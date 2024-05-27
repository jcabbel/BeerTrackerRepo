package com.example.beertracker.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.beertracker.R;
import com.example.beertracker.adapters.PublicacionAdapter;
import com.example.beertracker.controllers.FirebaseHelper;
import com.example.beertracker.models.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class Main_Menu_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PublicacionAdapter publicacionAdapter;
    private List<Publicacion> publicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        publicaciones = new ArrayList<>();

        // Configurar el RecyclerView para la feed de publicaciones
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        publicacionAdapter = new PublicacionAdapter(publicaciones, Main_Menu_Activity.this);
        recyclerView.setAdapter(publicacionAdapter);


        Log.d("DemoPublicaciones", "Llamando a getDemoPublicaciones()");

        // Obtener lista de publicaciones
        FirebaseHelper.getPublicaciones(new FirebaseHelper.publicacionesCallback() {
            @Override
            public void onCallback(List<Publicacion> publicacionesRecibidas) {
                Log.d("DemoPublicaciones", "onCallback() llamado");

                // Guardar la información recibida y actualizar el adaptador
                publicaciones.clear();
                publicaciones.addAll(publicacionesRecibidas);
                publicacionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(Main_Menu_Activity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });

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
                Intent intent = new Intent(Main_Menu_Activity.this, Mis_Publicaciones_Activity.class);
                startActivity(intent);
            }
        });

        btnWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
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
