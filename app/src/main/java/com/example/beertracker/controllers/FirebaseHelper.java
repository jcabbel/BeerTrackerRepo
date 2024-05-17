package com.example.beertracker.controllers;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.beertracker.models.Publicacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {

    public interface FirestoreCallback {
        void onCallback(List<Publicacion> publicaciones);
        void onFailure(Exception e);
    }

    public static void getPublicaciones(FirestoreCallback callback) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Publicacion> publicaciones = new ArrayList<>();

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
                                            String descripcion = document.getString("observaciones");
                                            String imagenPerfil = documentSnapshot.getString("fotoUri");
                                            String imagenPublicacion = document.getString("fotoUrl");
                                            int likes = 1;
                                            String comentarios = "Comentarios";

                                            publicaciones.add(new Publicacion(nombre, descripcion, imagenPerfil, imagenPublicacion, likes, comentarios));
                                            Log.d("DemoPublicaciones", "Número de publicaciones: " + publicaciones.size());

                                            if (publicaciones.size() == task.getResult().size()) {
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
}
