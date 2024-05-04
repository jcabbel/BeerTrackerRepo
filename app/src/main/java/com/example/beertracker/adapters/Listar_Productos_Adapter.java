package com.example.beertracker.adapters;

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
        TextView tvMarca = rowView.findViewById(R.id.tvMarca);

        tvNombre.setText(cerveza.getNombre());
        tvMarca.setText(cerveza.getMarca());

        return rowView;
    }
}