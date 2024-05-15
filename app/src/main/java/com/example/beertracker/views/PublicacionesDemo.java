package com.example.beertracker.views;

import com.example.beertracker.models.Publicacion;
import com.example.beertracker.R;

import java.util.ArrayList;
import java.util.List;

public class PublicacionesDemo {

    public static List<Publicacion> getDemoPublicaciones() {
        List<Publicacion> publicaciones = new ArrayList<>();

        publicaciones.add(new Publicacion("Josep Mumbardó", "Increíble cerveza al fresco", R.mipmap.ic_launcher, R.mipmap.ic_launcher, 13, "Ver todos los comentarios"));
        publicaciones.add(new Publicacion("Pol Martín", "En la mejor compañía", R.mipmap.ic_launcher, R.mipmap.ic_launcher, 18, "Ver todos los comentarios"));
        publicaciones.add(new Publicacion("Julio Cabellos", "Cervecita de viernes", R.mipmap.ic_launcher, R.mipmap.ic_launcher, 87, "Ver todos los comentarios"));
        publicaciones.add(new Publicacion("Eric Escuder", "Padel como excusa", R.mipmap.ic_launcher, R.mipmap.ic_launcher, 65, "Ver todos los comentarios"));
        publicaciones.add(new Publicacion("Aleix Mola", "Comienza el finde", R.mipmap.ic_launcher, R.mipmap.ic_launcher, 70, "Ver todos los comentarios"));

        return publicaciones;
    }
}
