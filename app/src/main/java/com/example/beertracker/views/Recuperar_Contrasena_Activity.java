package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.beertracker.R;

public class Recuperar_Contrasena_Activity extends AppCompatActivity {

    Button btnRecuperar;
    EditText editTextEmail;
    String email;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        btnRecuperar = findViewById(R.id.btnRecuperar);
        editTextEmail = findViewById(R.id.editTextEmail);

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString();
                recuperarPassword();
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

    public void recuperarPassword(){
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Recuperar_Contrasena_Activity.this, "Verifica tu correo electrónico", Toast.LENGTH_SHORT).show();
                        } else {
                            Exception e = task.getException();
                            if (e != null) {
                                String errorCode = e.getMessage();
                            }
                        }
                    }
                });
    }
}
