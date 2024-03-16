package com.example.beertracker.views;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beertracker.R;

public class Registro_Usuario_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar Toast
                Toast.makeText(Registro_Usuario_Activity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

                // Ir a la actividad de inicio de sesión
                Intent intent = new Intent(Registro_Usuario_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para volver a la actividad de inicio de sesión
                onBackPressed();
            }
        });
    }
}
