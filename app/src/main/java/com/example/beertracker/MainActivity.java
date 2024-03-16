package com.example.beertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.beertracker.controllers.DbCervezas;
import com.example.beertracker.controllers.DbHelper;
import com.example.beertracker.views.Bienvenida_Activity;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DbCervezas db = new DbCervezas(this);

        Intent intent = new Intent(MainActivity.this, Bienvenida_Activity.class);

        crearDB();

        db.insertarCerveza("Cinco estrellas", "España", "Lager","Mahou", 0.85, 5.5  );
        db.insertarCerveza("Clásica", "España", "Lager","Mahou", 0.50, 4.8);
        db.insertarCerveza("1925", "España", "Lager","Alhambra", 1.32, 6.4);

        startActivity(intent);
        finish();
    }
    public void crearDB(){
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db == null){
            Toast.makeText(MainActivity.this, "Error al crear la base de datos", Toast.LENGTH_LONG).show();
        }
    }
}