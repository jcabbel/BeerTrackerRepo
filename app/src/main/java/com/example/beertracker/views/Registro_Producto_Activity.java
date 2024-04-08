package com.example.beertracker.views;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beertracker.R;
import com.example.beertracker.controllers.DbCervezas;
import com.example.beertracker.models.Cerveza;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro_Producto_Activity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etPais;
    private EditText etTipo;
    private EditText etMarca;
    private EditText etPrecio;
    private EditText etGraduacion;
    private Button btnGuardar;
    private Button btnCancelar;
    private Button btnCargarFoto;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_producto);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCargarFoto = findViewById(R.id.btnCargarFoto);

        etNombre = findViewById(R.id.editTextNombre);
        etPais = findViewById(R.id.editTextPais);
        etTipo = findViewById(R.id.editTextTipo);
        etMarca = findViewById(R.id.editTextMarca);
        etPrecio = findViewById(R.id.editTextPrecio);
        etGraduacion = findViewById(R.id.editTextGraduacion);

        DbCervezas db = new DbCervezas(this);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String etNombreString = etNombre.getText().toString();
                String etPaisString = etPais.getText().toString();
                String etTipoString = etTipo.getText().toString();
                String etMarcaString = etMarca.getText().toString();
                double etPrecioDouble = Double.parseDouble(String.valueOf(etPrecio.getText()));
                double etGraduacionDouble = Double.parseDouble(String.valueOf(etGraduacion.getText()));

                if (etNombreString.isEmpty() || etPaisString.isEmpty() || etTipoString.isEmpty() || etMarcaString.isEmpty()) {
                    Toast.makeText(Registro_Producto_Activity.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    agregarCerveza(etNombreString, etPaisString, etTipoString, etMarcaString, etPrecioDouble, etGraduacionDouble);
                }


            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad de registro
                Intent intent = new Intent(Registro_Producto_Activity.this, Registrar_Activity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad después de iniciar la siguiente
            }
        });

        btnCargarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un mensaje Toast indicando que se requiere actualización
                Toast.makeText(Registro_Producto_Activity.this, "Función disponible en futuras versiones", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void agregarCerveza(String nombre, String pais, String tipo, String marca, double precio, double graduacion){
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", nombre);
        data.put("pais", pais);
        data.put("tipo", tipo);
        data.put("marca", marca);
        data.put("precio", precio);
        data.put("graduacion", graduacion);
        data.put("timestamp", FieldValue.serverTimestamp());

        db.collection("beers")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSo agregado con el ID: " + documentReference.getId());
                        Intent intent = new Intent(Registro_Producto_Activity.this, Registrar_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error añadiendo el documento", e);
                    }
                });

    }

}
