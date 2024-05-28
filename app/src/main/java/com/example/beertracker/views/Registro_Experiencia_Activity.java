    package com.example.beertracker.views;

    import static android.content.ContentValues.TAG;

    import android.content.Intent;
    import android.net.Uri;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import com.example.beertracker.R;
    import com.example.beertracker.models.Cerveza;
    import com.example.beertracker.models.NombreMarca;
    import com.example.beertracker.models.Usuario;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.firestore.CollectionReference;
    import com.google.firebase.firestore.DocumentReference;
    import com.google.firebase.firestore.FieldValue;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.firestore.QueryDocumentSnapshot;
    import com.google.firebase.firestore.QuerySnapshot;
    import com.google.firebase.storage.FirebaseStorage;
    import com.google.firebase.storage.StorageReference;
    import com.google.firebase.storage.UploadTask;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.UUID;
    import java.util.regex.Pattern;

    public class Registro_Experiencia_Activity extends AppCompatActivity {

        Spinner spinnerCervezas;
        EditText etSabor;
        EditText etLugar;
        EditText etValoracion;
        EditText etObservaciones;
        String cervezaSeleccionada;
        String idSeleccionado;
        Button btnGuardar;
        Button btnCancelarRegistro;
        Button btnCargarFoto;
        private static final int PICK_IMAGE_REQUEST = 1;
        Uri imageUri;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro_experiencia);

            btnGuardar = findViewById(R.id.btnGuardar);
            btnCancelarRegistro = findViewById(R.id.btnCancelar);
            btnCargarFoto = findViewById(R.id.btnCargarFoto);

            spinnerCervezas = findViewById(R.id.spinnerCerveza);

            etSabor = findViewById(R.id.editTextSabor);
            etLugar = findViewById(R.id.editTextLugar);
            etValoracion = findViewById(R.id.editTextValoracion);
            etObservaciones = findViewById(R.id.editTextObservaciones);

            rellenarSpinnerCervezas();

            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cervezaSeleccionada == null || cervezaSeleccionada.isEmpty()) {
                        Toast.makeText(Registro_Experiencia_Activity.this, "Elija una cerveza", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    FirebaseUser usuarioFirebase = FirebaseAuth.getInstance().getCurrentUser();
                    Usuario usuario = new Usuario(usuarioFirebase.getEmail());
                    Cerveza cerveza = new Cerveza(idSeleccionado);

                    String sabor = etSabor.getText().toString();
                    String lugar = etLugar.getText().toString();
                    int valoracion = Integer.parseInt(etValoracion.getText().toString());
                    String observaciones = etObservaciones.getText().toString();

                    agregarExperiencia(usuario, cerveza, sabor, lugar, valoracion, observaciones, imageUri);
                    Toast.makeText(Registro_Experiencia_Activity.this, "Experiencia añadida", Toast.LENGTH_SHORT).show();

                    // Volver a registrar.xml
                    volverARegistro();
                }
            });

            btnCancelarRegistro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    volverARegistro();
                }
            });

            btnCargarFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    seleccionarFoto();
                }
            });
        }

        private void volverARegistro() {
            Intent intent = new Intent(Registro_Experiencia_Activity.this, Registrar_Activity.class);
            startActivity(intent);
            finish(); // Finalizar esta actividad para evitar volver atrás con el botón de retroceso
        }
//ERIC: He añadido los campos nuevos que ha de agregar la experiencia en la db para mostrar más tarde en la feed || cerveza, sabor, lugar, valoracion y observaciones
        private void agregarExperiencia(Usuario usuario, Cerveza cerveza, String sabor, String lugar, int valoracion, String observaciones, Uri fotoUri) {
//ERIC: Veo que cerveza se pasa sube a firebase como cerveza ID, esto no lo he tenido en cuenta al programar... Me acabo de dar cuenta (23:10h después de hablar por la noche))
            String usuarioMail = usuario.getEmail();
            String cervezaId = cerveza.getId();
            String valoracionString = Integer.toString(valoracion);

            // Verifica si hay una imagen seleccionada
            if (fotoUri != null) {
                // Crea una referencia al almacenamiento de Firebase
                StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images/experiences_images/" + UUID.randomUUID().toString());

                // Sube la imagen al almacenamiento de Firebase
                storageRef.putFile(fotoUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Obtiene la URL de descarga de la imagen
                                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        // URL de descarga de la imagen
                                        String fotoUrl = uri.toString();

                                        // Crea los datos para la experiencia
                                        Map<String, Object> data = new HashMap<>();
                                        data.put("usuario", usuarioMail);
                                        data.put("cerveza", cervezaId);
                                        data.put("sabor", sabor);
                                        data.put("lugar", lugar);
                                        data.put("valoracion", valoracionString);
                                        data.put("observaciones", observaciones);
                                        data.put("timestamp", FieldValue.serverTimestamp());
                                        data.put("fotoUrl", fotoUrl);
                                        data.put("likes", 0);

                                        // Agrega los datos a Firestore
                                        db.collection("experiences")
                                                .add(data)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Log.d(TAG, "Experiencia agregada con el ID: " + documentReference.getId());
                                                        Intent intent = new Intent(Registro_Experiencia_Activity.this, Registrar_Activity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error añadiendo la experiencia", e);
                                                    }
                                                });
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Maneja el fallo de subida de la imagen
                                Log.e(TAG, "Error al subir la imagen", e);
                            }
                        });
            } else {
                // Si no hay imagen seleccionada, guarda los datos sin la URL de la imagen
                Map<String, Object> data = new HashMap<>();
                data.put("usuario", usuarioMail);
                data.put("cerveza", cervezaId);
                data.put("sabor", sabor);
                data.put("lugar", lugar);
                data.put("valoracion", valoracionString);
                data.put("observaciones", observaciones);
                data.put("timestamp", FieldValue.serverTimestamp());
                data.put("likes", 0);


                // Agrega los datos a Firestore
                db.collection("experiences")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "Experiencia agregada con el ID: " + documentReference.getId());
                                Intent intent = new Intent(Registro_Experiencia_Activity.this, Registrar_Activity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error añadiendo la experiencia", e);
                            }
                        });
            }
        }


        public void rellenarSpinnerCervezas() {
            ArrayList<NombreMarca> datos = new ArrayList<>();
            ArrayList<String> ids = new ArrayList<>();
            db.collection("beers")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String nombre = document.getString("nombre");
                                    String marca = document.getString("marca");
                                    String id = document.getString("id");
                                    NombreMarca nombreMarca = new NombreMarca(nombre, marca, id);
                                    datos.add(nombreMarca);
                                    ids.add(id);
                                }

                                // Ordenar la lista de objetos NombreMarca por nombre y marca
                                Collections.sort(datos, new Comparator<NombreMarca>() {
                                    @Override
                                    public int compare(NombreMarca nm1, NombreMarca nm2) {
                                        return nm1.getNombreMarca().compareTo(nm2.getNombreMarca());
                                    }
                                });

                                // Crear una lista de nombres para el adaptador del Spinner
                                ArrayList<String> nombres = new ArrayList<>();
                                for (NombreMarca nm : datos) {
                                    nombres.add(nm.getNombreMarca());
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(Registro_Experiencia_Activity.this,
                                        android.R.layout.simple_spinner_item, nombres);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerCervezas.setAdapter(adapter);

                                // Asignar el listener después de que el spinner tenga datos
                                spinnerCervezas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                        cervezaSeleccionada = parentView.getItemAtPosition(position).toString();
                                        idSeleccionado = datos.get(position).getId(); // Obtener la ID correspondiente
                                        btnGuardar.setEnabled(true);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parentView) {
                                        cervezaSeleccionada = "";
                                        btnGuardar.setEnabled(false);
                                        Toast.makeText(Registro_Experiencia_Activity.this, "Elija una cerveza", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Log.d(TAG, "Error obteniendo resultados: ", task.getException());
                            }
                        }
                    });
        }


            private void seleccionarFoto() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imageUri = data.getData();
                Toast.makeText(Registro_Experiencia_Activity.this, "Imagen seleccionada", Toast.LENGTH_SHORT).show();
            }
        }
    }

