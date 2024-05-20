package com.example.beertracker.controllers;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class FirebaseLikes {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void darLike (String idUsuario, String idExperiencia) {
        DocumentReference experienciaRef = db.collection("experiences").document(idExperiencia);

        // Agregar un documento a la subcolección "likedBy" dentro del documento de la experiencia
        experienciaRef.collection("likedBy").document(idUsuario).set(new HashMap<>())
                .addOnSuccessListener(aVoid -> {
                    // Éxito al agregar el like, puedes realizar acciones adicionales aquí si es necesario
                    incrementarLikes(idExperiencia);
                })
                .addOnFailureListener(e -> {
                    // Error al agregar el like, manejar según sea necesario
                });
    }

    public void quitarLike(String idUsuario, String idExperiencia) {
        // Obtener referencia al documento de la publicación
        DocumentReference experienciaRef = db.collection("experiences").document(idExperiencia);
        // Eliminar el documento de "like" de la subcolección "likes"
        DocumentReference usuarioLikeRef = experienciaRef.collection("likedBy").document(idUsuario);
        usuarioLikeRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Éxito al quitar el like
                        // Actualizar el recuento de likes en el documento de la publicación
                        decrementarLikes(idExperiencia);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // Error al quitar el like
                        // Manejar el error según sea necesario
                    }
                });
    }

    private void incrementarLikes(String idExperiencia) {
        // Obtener referencia al documento de la publicación
        DocumentReference experienciaRef = db.collection("experiences").document(idExperiencia);

        // Incrementar el recuento de likes en 1 usando una transacción
        experienciaRef.update("likes", com.google.firebase.firestore.FieldValue.increment(1))
                .addOnSuccessListener(aVoid -> {
                    // Éxito al incrementar el recuento de likes
                })
                .addOnFailureListener(e -> {
                    // Error al incrementar el recuento de likes
                    // Manejar el error según sea necesario
                });
    }

        private void decrementarLikes(String idExperiencia) {
            // Obtener referencia al documento de la publicación
            DocumentReference experienciaRef = db.collection("experiences").document(idExperiencia);

            // Decrementar el recuento de likes en 1 usando una transacción
            experienciaRef.update("likes", com.google.firebase.firestore.FieldValue.increment(-1))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Éxito al decrementar el recuento de likes
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            // Error al decrementar el recuento de likes
                            // Manejar el error según sea necesario
                        }
                    });
    }
}

