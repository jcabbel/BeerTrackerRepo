package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beertracker.R;

public class Registrar_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        // Configurar el botón de volver
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para volver a la actividad principal
                Intent intent = new Intent(Registrar_Activity.this, Main_Menu_Activity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad después de iniciar la siguiente
            }
        });

        // Obtener referencias de los botones
        Button btnProducto = findViewById(R.id.btnProducto);
        Button btnExperiencia = findViewById(R.id.btnExperiencia);

        // Configurar listener para el botón Producto
        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de registro de producto
                abrirFormularioProducto();
            }
        });

        // Configurar listener para el botón Experiencia
        btnExperiencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de registro de experiencia
                abrirFormularioExperiencia();
            }
        });
    }

    // Método para abrir la actividad de registro de producto
    private void abrirFormularioProducto() {
        Intent intent = new Intent(this, Registro_Producto_Activity.class);
        startActivity(intent);
    }

    // Método para abrir la actividad de registro de experiencia
    private void abrirFormularioExperiencia() {
        Intent intent = new Intent(this, Registro_Experiencia_Activity.class);
        startActivity(intent);
    }
}
