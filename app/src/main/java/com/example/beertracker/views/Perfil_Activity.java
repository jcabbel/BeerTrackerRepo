package com.example.beertracker.views;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.beertracker.R;
import com.example.beertracker.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.regex.Pattern;

public class Perfil_Activity extends AppCompatActivity {

    private ImageView imageView;
    String fotoUri;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cardview);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imageView);

        FirebaseUser usuarioFirebase = FirebaseAuth.getInstance().getCurrentUser();
        String usuarioId = usuarioFirebase.getEmail();

        cargarImagenPerfil(usuarioId);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil_Activity.this, Main_Menu_Activity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad después de iniciar la siguiente
            }
        });
    }
    private void cargarImagenPerfil(String usuarioId) {
        db.collection("users").document(usuarioId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String fotoUri = document.getString("fotoUri");
                                Glide.with(Perfil_Activity.this)
                                        .load(fotoUri)
                                        .placeholder(R.drawable.beer_bw)
                                        .into(imageView);
                            }
                        }
                    }
                });
    }
}
