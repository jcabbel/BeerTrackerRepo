package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beertracker.R;
import com.example.beertracker.adapters.PublicacionAdapter;
import com.example.beertracker.controllers.FirebaseHelper;
import com.example.beertracker.models.Publicacion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Mis_Publicaciones_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PublicacionAdapter publicacionAdapter;
    private List<Publicacion> publicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_publicaciones);

        publicaciones = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        publicacionAdapter = new PublicacionAdapter(publicaciones, Mis_Publicaciones_Activity.this);
        recyclerView.setAdapter(publicacionAdapter);

        FirebaseUser usuarioFirebase = FirebaseAuth.getInstance().getCurrentUser();
        String usuarioId = usuarioFirebase.getEmail();

        FirebaseHelper.getPublicacionesByUser(usuarioId, new FirebaseHelper.publicacionesCallback() {
            @Override
            public void onCallback(List<Publicacion> publicacionesRecibidas) {
                // Guardar la información recibida y actualizar el adaptador
                publicaciones.clear();
                publicaciones.addAll(publicacionesRecibidas);
                publicacionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(Mis_Publicaciones_Activity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnAdd = findViewById(R.id.btnAñadir);
        Button btnSearch = findViewById(R.id.btnBuscar);
        Button btnWall = findViewById(R.id.btnMuro);
        Button btnProfile = findViewById(R.id.btnPerfil);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando se presiona el botón "Añadir", iniciar la actividad de registro
                Intent intent = new Intent(Mis_Publicaciones_Activity.this, Registrar_Activity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recargar la actividad actual
                recreate();
            }
        });

        btnWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad del menú principal
                Intent intent = new Intent(Mis_Publicaciones_Activity.this, Main_Menu_Activity.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad de perfil
                Intent intent = new Intent(Mis_Publicaciones_Activity.this, Perfil_Activity.class);
                startActivity(intent);
            }
        });

    }
}