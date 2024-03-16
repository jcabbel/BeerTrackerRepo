package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beertracker.R;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textViewRegister = findViewById(R.id.textViewRegister);
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de registro de usuario
                Intent intent = new Intent(Login_Activity.this, Registro_Usuario_Activity.class);
                startActivity(intent);
            }
        });

        TextView textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de recuperación de contraseña
                Intent intent = new Intent(Login_Activity.this, Recuperar_Contrasena_Activity.class);
                startActivity(intent);
            }
        });

        // Simplemente mostramos un toast y avanzamos a la actividad del menú principal al hacer clic en Iniciar Sesión
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un toast indicando inicio de sesión exitoso
                Toast.makeText(Login_Activity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                // Crear un Intent para abrir la actividad del menú principal
                Intent intent = new Intent(Login_Activity.this, Main_Menu_Activity.class);

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });
    }
}
