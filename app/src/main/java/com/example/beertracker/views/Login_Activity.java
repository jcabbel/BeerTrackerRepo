package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beertracker.R;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    TextView textViewRegister;
    TextView textViewForgotPassword;
    EditText editTextEmail;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewRegister = findViewById(R.id.textViewRegister);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de registro de usuario
                Intent intent = new Intent(Login_Activity.this, Registro_Usuario_Activity.class);
                startActivity(intent);
            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de recuperación de contraseña
                Intent intent = new Intent(Login_Activity.this, Recuperar_Contrasena_Activity.class);
                startActivity(intent);
            }
        });

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                validarUsuario(email, password);
            }
        });
    }

    public void validarUsuario(String email, String password){

        if(!email.isEmpty() && !password.isEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(Login_Activity.this, Main_Menu_Activity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login_Activity.this, "Error al validar usuario", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(Login_Activity.this, "Los campos no pueden estar en blanco", Toast.LENGTH_SHORT).show();
        }
    }
}
