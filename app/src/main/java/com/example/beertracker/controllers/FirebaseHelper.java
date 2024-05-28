package com.example.beertracker.controllers;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.beertracker.models.Publicacion;
import com.example.beertracker.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FirebaseHelper {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public interface publicacionesCallback {
        void onCallback(List<Publicacion> publicaciones);
        void onFailure(Exception e);
    }

    public interface usuariosCallback {
        void onCallback(Usuario usuario);
        void onFailure(Exception e);
    }

    public static void getPublicaciones(publicacionesCallback callback) {

        List<Publicacion> publicaciones = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("experiences")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()){
                            Log.d("DemoPublicaciones", "Acceso a la coleccón EXPERIENCES");

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String mail = document.getString("usuario");
                                DocumentReference userRef = db.collection("users").document(mail);
                                Log.d("DemoPublicaciones", "Usuario seleccionado");

                                userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                                        if (documentSnapshot.exists()) {
                                            String nombre = documentSnapshot.getString("nombre") + " " + documentSnapshot.getString("apellidos");
                                            String experiencia = document.getId();
                                            String descripcion = document.getString("observaciones");
                                            String imagenPerfil = documentSnapshot.getString("fotoUri");
                                            String imagenPublicacion = document.getString("fotoUrl");
                                            Timestamp timestamp = document.getTimestamp("timestamp");
                                            long likes = document.getLong("likes");
                                            String comentarios = "Comentarios";
//ERIC: Estas son las anotaciones sobre el error que da publicaciones.add, más abajo comentado para evitar el error.
        //He comentado publicaciones.add porque me da un error en los campos
            // || cerveza, sabor, lugar, valoracion, observaciones,||
        //Yo creo que el problema viene en que en algún lado, no se si en la db de firebase o dónde,
                //se está perdiendo la el get o set de los valores parametros que dan error.
        // He modificado el layout para que las publicaciones contengan ya todos los valores
        // He modificado el java de publicacion, publicacionAdapter
                                            //publicaciones.add(new Publicacion(nombre, experiencia, descripcion, imagenPerfil, imagenPublicacion, likes, comentarios, cerveza, sabor, lugar, valoracion, observaciones, timestamp));
                                            Log.d("DemoPublicaciones", "Número de publicaciones: " + publicaciones.size());

                                            if (publicaciones.size() == task.getResult().size()) {
                                                Collections.sort(publicaciones, new Comparator<Publicacion>() {
                                                    @Override
                                                    public int compare(Publicacion p1, Publicacion p2) {
                                                        return p2.getTimestamp().compareTo(p1.getTimestamp());
                                                    }
                                                });
                                                callback.onCallback(publicaciones);
                                            }
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        callback.onFailure(e);
                                    }
                                });
                            }
                        }
                    }
                    });
        }

    public static void getUsuarioDB(String email, final usuariosCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("users").document(email);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String nombre = documentSnapshot.getString("nombre");
                    String apellidos = documentSnapshot.getString("apellidos");
                    String fotoUri = documentSnapshot.getString("fotoUri");
                    Long genero = documentSnapshot.getLong("genero");

                    Map<String, Object> fechaNacimientoMap = (Map<String, Object>) documentSnapshot.get("fecha_nacimiento");
                    LocalDate fechaNacimiento = null;
                    if (fechaNacimientoMap != null) {
                        int year = ((Long) fechaNacimientoMap.get("year")).intValue();
                        int month = ((Long) fechaNacimientoMap.get("monthValue")).intValue();
                        int day = ((Long) fechaNacimientoMap.get("dayOfMonth")).intValue();
                        fechaNacimiento = LocalDate.of(year, month, day);
                    }

                    Usuario usuario = new Usuario(email, nombre, apellidos, genero != null ? genero.intValue() : 0, fechaNacimiento, fotoUri);
                    callback.onCallback(usuario);

                } else {
                    callback.onCallback(null);  // Usuario no encontrado
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e);
            }
        });
    }

}
