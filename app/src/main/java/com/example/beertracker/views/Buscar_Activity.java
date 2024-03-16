package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.beertracker.R;

public class Buscar_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        Button btnBuscarProducto = findViewById(R.id.btnBuscarProducto);
        Button btnBuscarExperiencia = findViewById(R.id.btnBuscarExperiencia);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar); // Obtener el toolbar

        btnBuscarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de búsqueda de productos
                abrirBuscarProducto();
            }
        });

        btnBuscarExperiencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de búsqueda de experiencias
                abrirBuscarExperiencia();
            }
        });

        // Configurar el botón de volver al menú principal
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para volver al menú principal
                Intent intent = new Intent(Buscar_Activity.this, Main_Menu_Activity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad después de iniciar la siguiente
            }
        });
    }

    // Método para abrir la actividad de búsqueda de productos
    private void abrirBuscarProducto() {
        Intent intent = new Intent(this, Buscar_Producto_Activity.class);
        startActivity(intent);
    }

    // Método para abrir la actividad de búsqueda de experiencias
    private void abrirBuscarExperiencia() {
        Intent intent = new Intent(this, Buscar_Experiencia_Activity.class);
        startActivity(intent);
    }
}
