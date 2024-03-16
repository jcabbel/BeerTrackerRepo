package com.example.beertracker.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.beertracker.R;
import com.example.beertracker.models.Cerveza;

import java.util.ArrayList;

public class Listar_Productos_Adapter extends ArrayAdapter<Cerveza> {
    private Context context;
    private ArrayList<Cerveza> alCervezas;

    public Listar_Productos_Adapter (Context context, ArrayList<Cerveza> cervezas) {
        super(context, R.layout.row_listar_productos, cervezas);
        this.context = context;
        this.alCervezas = cervezas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(R.layout.row_listar_productos, null);
        }

        Cerveza cerveza = alCervezas.get(position);

        TextView tvNombre = rowView.findViewById(R.id.tvNombre);
        TextView tvPais = rowView.findViewById(R.id.tvPais);
        TextView tvTipo = rowView.findViewById(R.id.tvTipo);
        TextView tvMarca = rowView.findViewById(R.id.tvMarca);
        TextView tvPrecio = rowView.findViewById(R.id.tvPrecio);
        TextView tvGraduacion = rowView.findViewById(R.id.tvGraduacion);

        tvNombre.setText(cerveza.getNombre());
        tvPais.setText(cerveza.getPais());
        tvTipo.setText(cerveza.getTipo());
        tvMarca.setText(cerveza.getMarca());
        tvPrecio.setText(String.valueOf(cerveza.getPrecio()));
        tvGraduacion.setText(String.valueOf(cerveza.getGraduacion()));


        return rowView;
    }
}