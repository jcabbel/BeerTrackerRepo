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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.beertracker.R;
import com.example.beertracker.controllers.FirebaseHelper;
import com.example.beertracker.models.Publicacion;
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

import java.util.List;
import java.util.regex.Pattern;

public class Perfil_Activity extends AppCompatActivity implements FirebaseHelper.usuariosCallback {

    private ImageView imageView;
    private TextView textViewNombre;
    private TextView textViewEmail;
    private TextView textViewNumeroPublicaciones;
    private TextView textViewNumeroLikes;

    String fotoUri;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cardview);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imageView);
        textViewNombre = findViewById(R.id.textViewNombre);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewNumeroPublicaciones = findViewById(R.id.textViewNumeroPublicaciones);
        textViewNumeroLikes = findViewById(R.id.textViewNumeroLikes);

        FirebaseUser usuarioFirebase = FirebaseAuth.getInstance().getCurrentUser();
            String usuarioId = usuarioFirebase.getEmail();
            FirebaseHelper firebaseHelper = new FirebaseHelper();
            FirebaseHelper.getUsuarioDB(usuarioId, this);

        FirebaseHelper.getPublicacionesByUser(usuarioId, new FirebaseHelper.publicacionesCallback() {
            @Override
            public void onCallback(List<Publicacion> publicaciones) {
                actualizarTextViews(publicaciones);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "No se puede obtener las publicaciones de este usuario: " + usuarioId);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil_Activity.this, Main_Menu_Activity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad después de iniciar la siguiente
            }
        });
    }

    private void actualizarTextViews(List<Publicacion> publicaciones) {
        int numeroPublicaciones = publicaciones.size();
        int numeroDeLikes = 0;

        for (Publicacion publicacion : publicaciones) {
            numeroDeLikes += publicacion.getLikes();
        }

        textViewNumeroPublicaciones.setText("Creadas: " + numeroPublicaciones);
        textViewNumeroLikes.setText("Likes recibidos: " + numeroDeLikes);
    }

    @Override
    public void onCallback(Usuario usuario) { if (usuario != null) {
        textViewNombre.setText(usuario.getNombre() + " " + usuario.getApellidos());
        textViewEmail.setText(usuario.getEmail());

        Glide.with(Perfil_Activity.this)
                .load(usuario.getFotoUri())
                .placeholder(R.drawable.beer_bw)
                .into(imageView);
    } else {
        Log.d(TAG, "Usuario no encontrado");
    }

    }

    @Override
    public void onFailure(Exception e) {
        Log.e(TAG, "Error al obtener el usuario", e);
    }
}
