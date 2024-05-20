package com.example.beertracker.adapters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.beertracker.R;
import com.example.beertracker.controllers.FirebaseLikes;
import com.example.beertracker.models.Publicacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder> {

    private List<Publicacion> publicaciones;
    private Context context;
    FirebaseLikes fbLikes = new FirebaseLikes();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public PublicacionAdapter(List<Publicacion> publicaciones, Context context) {
        this.publicaciones = publicaciones;
        this.context = context;
    }

    @Override
    public PublicacionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_muro_publicacion, parent, false);
        return new PublicacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublicacionViewHolder holder, int position) {
        Publicacion publicacion = publicaciones.get(position);
        holder.usuario.setText(publicacion.getUsuario());
        holder.descripcion.setText(publicacion.getDescripcion());
        String experienciaId = publicacion.getExperiencia();

        FirebaseUser usuarioFirebase = FirebaseAuth.getInstance().getCurrentUser();
        String usuarioId = usuarioFirebase.getEmail();

        listenToLikesChanges(publicacion.getExperiencia(), holder.textoMegusta);
        checkUserLike(experienciaId, usuarioId, holder.megusta);

        holder.megusta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DocumentReference publicacionRef = db.collection("experiences").document(experienciaId)
                        .collection("likedBy").document(usuarioId);
                publicacionRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                fbLikes.quitarLike(usuarioId, experienciaId);
                                holder.megusta.setImageResource(R.drawable.nomegusta);
                            } else {
                                fbLikes.darLike(usuarioId, experienciaId);
                                holder.megusta.setImageResource(R.drawable.megusta);
                            }
                        } else {
                            Log.e(TAG, "Error al verificar el like: ", task.getException());
                        }
                    }
                });

            }
        });

        Glide.with(holder.itemView.getContext())
                .load(publicacion.getImagenPerfil())
                .apply(new RequestOptions().placeholder(R.drawable.beer_bw))
                .into(holder.imagenPerfil);

        Glide.with(holder.itemView.getContext())
                .load(publicacion.getImagenPublicacion())
                .apply(new RequestOptions().placeholder(R.drawable.beer_bw))
                .into(holder.imagenPublicacion);

        holder.textoMegusta.setText(publicacion.getLikes() + " Me gusta");
        holder.verComentarios.setText(publicacion.getComentarios());
    }

    private void checkUserLike(String experienciaId, String usuarioId, ImageView megusta) {
        DocumentReference publicacionRef = db.collection("experiences").document(experienciaId)
                .collection("likedBy").document(usuarioId);
        publicacionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    megusta.setImageResource(R.drawable.megusta);
                } else {
                    megusta.setImageResource(R.drawable.nomegusta);
                }
            } else {
                Log.e(TAG, "Error al verificar el like: ", task.getException());
            }
        });
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    public class PublicacionViewHolder extends RecyclerView.ViewHolder {

        ImageView imagenPerfil, imagenPublicacion, megusta, comentarios;
        TextView usuario, descripcion, textoMegusta, verComentarios;

        public PublicacionViewHolder(View itemView) {
            super(itemView);
            imagenPerfil = itemView.findViewById(R.id.imagen_perfil);
            imagenPublicacion = itemView.findViewById(R.id.publicacion);
            megusta = itemView.findViewById(R.id.megusta);
            comentarios = itemView.findViewById(R.id.comentarios);
            usuario = itemView.findViewById(R.id.usuario);
            descripcion = itemView.findViewById(R.id.descripcion);
            textoMegusta = itemView.findViewById(R.id.textoMegusta);
            verComentarios = itemView.findViewById(R.id.ver_comentarios);
        }
    }

    private void listenToLikesChanges(String experienciaId, TextView textView) {
        DocumentReference experienciaRef = db.collection("experiences").document(experienciaId);
        experienciaRef.addSnapshotListener((documentSnapshot, e) -> {
            if (e != null) {
                Log.w(TAG, "Listen failed.", e);
                return;
            }

            if (documentSnapshot != null && documentSnapshot.exists()) {
                long likes = documentSnapshot.getLong("likes");
                textView.setText(likes + " Me gusta");
            } else {
                Log.d(TAG, "Current data: null");
            }
        });
    }
}
