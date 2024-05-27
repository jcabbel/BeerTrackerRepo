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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro_Producto_Activity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etMarca;
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
        etMarca = findViewById(R.id.editTextMarca);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String etNombreString = etNombre.getText().toString();
                String etMarcaString = etMarca.getText().toString();

                if (etNombreString.isEmpty() || etMarcaString.isEmpty()) {
                    Toast.makeText(Registro_Producto_Activity.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                                    } else {
                    agregarCerveza(etNombreString, etMarcaString);
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

    public void agregarCerveza(String nombre, String marca) {
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", nombre);
        data.put("marca", marca);
        data.put("timestamp", FieldValue.serverTimestamp());

        db.collection("beers")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Registro_Producto_Activity.this, "Cerveza añadida", Toast.LENGTH_SHORT).show();
                        String docId = documentReference.getId();
                        Log.d(TAG, "Documento agregado con el ID: " + docId);

                        documentReference.update("id", docId)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "ID agregado al documento");
                                        Intent intent = new Intent(Registro_Producto_Activity.this, Registrar_Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error al agregar ID al documento", e);
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registro_Producto_Activity.this, "Error al agregar la cerveza", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Error al agregar documento", e);
                    }
                });
    }
}
