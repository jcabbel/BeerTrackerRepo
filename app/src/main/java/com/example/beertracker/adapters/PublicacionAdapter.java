package com.example.beertracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.beertracker.R;
import com.example.beertracker.models.Publicacion;

import java.util.List;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder> {

    private List<Publicacion> publicaciones;
    private Context context;

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
}
