package com.example.beertracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.beertracker.R;

public class Perfil_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar); // Obtener el toolbar

        // Configurar el botón de volver al menú principal
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para volver al menú principal
                Intent intent = new Intent(Perfil_Activity.this, Main_Menu_Activity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad después de iniciar la siguiente
            }
        });
    }
}
