package com.example.beertracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beertracker.R;
import com.example.beertracker.controllers.DbCervezas;

import java.util.ArrayList;

public class Buscar_Producto_Activity extends AppCompatActivity {
    private Spinner spinnerCampo;
    private Spinner spinnerValor;
    private String seleccionCampo;
    private String seleccionValor;

    String[] listaCampos = {"Marca","Pais","Tipo"};
    ArrayList<String> listaValores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);

        DbCervezas db = new DbCervezas(this);

        spinnerCampo = findViewById(R.id.spinnerCampo);
        spinnerValor = findViewById(R.id.spinnerValor);

        ArrayAdapter<String> adapterCampos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCampos);
        spinnerCampo.setAdapter(adapterCampos);

        spinnerCampo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                seleccionCampo = parentView.getItemAtPosition(position).toString();
                listaValores = db.obtenerAtributoCervezas(seleccionCampo);
                listaValores = removeDuplicates(listaValores);
                ArrayAdapter<String> adapterValores = new ArrayAdapter<>(Buscar_Producto_Activity.this, android.R.layout.simple_spinner_item, listaValores);
                spinnerValor.setAdapter(adapterValores);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                seleccionValor = "";
            }
        });

        spinnerValor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                seleccionValor = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                seleccionValor = "";
            }
        });


        Button btnBuscar = findViewById(R.id.btnBuscar);
        Button btnCancelarBusqueda = findViewById(R.id.btnCancelar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seleccionValor.isEmpty()) {
                    Toast.makeText(Buscar_Producto_Activity.this, "Seleccione un valor antes de buscar", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Buscar_Producto_Activity.this, Listar_Productos_Activity.class);
                    intent.putExtra("Valor", seleccionValor);
                    intent.putExtra("Campo", seleccionCampo);
                    startActivity(intent);
                    Toast.makeText(Buscar_Producto_Activity.this, "Búsqueda realizada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelarBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private ArrayList<String> removeDuplicates(ArrayList<String> list) {
        ArrayList<String> uniqueList = new ArrayList<>();
        for (String value : list) {
            if (!uniqueList.contains(value)) {
                uniqueList.add(value);
            }
        }
        return uniqueList;
    }
}