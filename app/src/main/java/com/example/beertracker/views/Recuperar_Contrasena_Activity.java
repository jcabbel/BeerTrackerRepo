package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.beertracker.R;

public class Recuperar_Contrasena_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        Button btnRecuperar = findViewById(R.id.btnRecuperar);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simulación de restablecimiento de contraseña
                // Aquí podrías agregar la lógica para restablecer la contraseña en tu sistema
                // y mostrar un Toast indicando que la contraseña ha sido restablecida
                Toast.makeText(Recuperar_Contrasena_Activity.this, "Contraseña restablecida", Toast.LENGTH_SHORT).show();

                // Regresar a la actividad de inicio de sesión
                Intent intent = new Intent(Recuperar_Contrasena_Activity.this, Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Regresar a la actividad de inicio de sesión
                Intent intent = new Intent(Recuperar_Contrasena_Activity.this, Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
