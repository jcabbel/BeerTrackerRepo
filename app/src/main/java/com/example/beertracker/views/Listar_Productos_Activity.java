package com.example.beertracker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.beertracker.R;
import com.example.beertracker.controllers.DbCervezas;
import com.example.beertracker.controllers.Listar_Productos_Adapter;
import com.example.beertracker.models.Cerveza;

import java.util.ArrayList;

public class Listar_Productos_Activity extends AppCompatActivity {
    ArrayList alCervezas = new ArrayList<Cerveza>();
    ListView lvCervezas;
    String campo;
    String valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos);

        DbCervezas db = new DbCervezas(this);

        campo = getIntent().getStringExtra("Campo");
        valor = getIntent().getStringExtra("Valor");

        switch (campo) {
            case "Marca": {
                alCervezas = db.getCervezasPorMarca(valor);

                break;
            } case "Pais": {
                alCervezas = db.getCervezasPorPais(valor);

                break;
            } case "Tipo": {
                alCervezas = db.getCervezasPorTipo(valor);

            }
            default: {
                break;
            }
        }
        Listar_Productos_Adapter adaptadorListarProductos = new Listar_Productos_Adapter(Listar_Productos_Activity.this, alCervezas);
        lvCervezas = findViewById(R.id.lvListarProductos);
        lvCervezas.setAdapter(adaptadorListarProductos);
    }
}