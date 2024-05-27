package com.example.beertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;
import com.jakewharton.threetenabp.AndroidThreeTen;

import com.example.beertracker.views.Bienvenida_Activity;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidThreeTen.init(this);

        Intent intent = new Intent(MainActivity.this, Bienvenida_Activity.class);
        startActivity(intent);
        finish();
    }
}