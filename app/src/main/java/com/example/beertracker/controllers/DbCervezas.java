package com.example.beertracker.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.beertracker.controllers.DbHelper;
import com.example.beertracker.models.Cerveza;

import java.util.ArrayList;

public class DbCervezas extends DbHelper {

    Context context;

    public DbCervezas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCerveza(String nombre, String pais, String tipo, String marca, double precio, double graduacion) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            nombre = capitalizeFirstLetter(nombre);
            pais = capitalizeFirstLetter(pais);
            tipo = capitalizeFirstLetter(tipo);
            marca = capitalizeFirstLetter(marca);

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("pais", pais);
            values.put("tipo", tipo);
            values.put("marca", marca);
            values.put("precio", precio);
            values.put("graduacion", graduacion);
            id = db.insert(TABLE_CERVEZAS, null, values);

        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Cerveza> mostrarCervezas(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cerveza> listaCervezas = new ArrayList<>();
        Cerveza cerveza = null;
        Cursor cursorCervezas = null;

        cursorCervezas = db.rawQuery("SELECT * FROM " + TABLE_CERVEZAS, null);

        if (cursorCervezas.moveToFirst()){
            do{
                cerveza = new Cerveza();
                cerveza.setId(cursorCervezas.getString(0));
                cerveza.setNombre(cursorCervezas.getString(1));
                cerveza.setMarca(cursorCervezas.getString(2));

                listaCervezas.add(cerveza);
            } while (cursorCervezas.moveToNext());
        }
        cursorCervezas.close();

        return listaCervezas;
    }
    public ArrayList<String> obtenerAtributoCervezas(String atributo) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<String> listaAtributoCervezas = new ArrayList<>();
        Cursor cursorCervezas = null;

        cursorCervezas = db.rawQuery("SELECT " + atributo + " FROM " + TABLE_CERVEZAS, null);

        if (cursorCervezas.moveToFirst()) {
            do {
                String valorAtributo = cursorCervezas.getString(0);
                listaAtributoCervezas.add(valorAtributo);
            } while (cursorCervezas.moveToNext());
        }
        cursorCervezas.close();
        db.close(); // Cierra la base de datos después de usarla

        return listaAtributoCervezas;
    }


    public ArrayList<Cerveza> getCervezasPorPais(String pais) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cerveza> listaCervezas = new ArrayList<>();
        Cursor cursorCervezas = null;

        cursorCervezas = db.rawQuery("SELECT * FROM " + TABLE_CERVEZAS + " WHERE pais=?", new String[]{pais});

        if (cursorCervezas.moveToFirst()) {
            do {
                Cerveza cerveza = new Cerveza();
                cerveza.setId(cursorCervezas.getString(0));
                cerveza.setNombre(cursorCervezas.getString(1));
                cerveza.setMarca(cursorCervezas.getString(2));

                listaCervezas.add(cerveza);
            } while (cursorCervezas.moveToNext());
        }
        cursorCervezas.close();

        return listaCervezas;
    }

    public ArrayList<Cerveza> getCervezasPorTipo(String tipo) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cerveza> listaCervezas = new ArrayList<>();
        Cursor cursorCervezas = null;

        cursorCervezas = db.rawQuery("SELECT * FROM " + TABLE_CERVEZAS + " WHERE tipo=?", new String[]{tipo});

        if (cursorCervezas.moveToFirst()) {
            do {
                Cerveza cerveza = new Cerveza();
                cerveza.setId(cursorCervezas.getString(0));
                cerveza.setNombre(cursorCervezas.getString(1));
                cerveza.setMarca(cursorCervezas.getString(2));

                listaCervezas.add(cerveza);
            } while (cursorCervezas.moveToNext());
        }
        cursorCervezas.close();

        return listaCervezas;
    }

    public ArrayList<Cerveza> getCervezasPorMarca(String marca) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cerveza> listaCervezas = new ArrayList<>();
        Cursor cursorCervezas = null;

        cursorCervezas = db.rawQuery("SELECT * FROM " + TABLE_CERVEZAS + " WHERE marca=?", new String[]{marca});

        if (cursorCervezas.moveToFirst()) {
            do {
                Cerveza cerveza = new Cerveza();
                cerveza.setId(cursorCervezas.getString(0));
                cerveza.setNombre(cursorCervezas.getString(1));
                cerveza.setMarca(cursorCervezas.getString(2));

                listaCervezas.add(cerveza);
            } while (cursorCervezas.moveToNext());
        }
        cursorCervezas.close();

        return listaCervezas;
    }

    public Cerveza verCerveza(int id) {

        if (existeCerveza(id)) {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cerveza cerveza = null;
            Cursor cursorCervezas = null;

            cursorCervezas = db.rawQuery("SELECT * FROM " + TABLE_CERVEZAS + " WHERE id = " + id + " LIMIT 1", null);

            if (cursorCervezas.moveToFirst()) {
                cerveza = new Cerveza();
                cerveza.setId(cursorCervezas.getString(0));
                cerveza.setNombre(cursorCervezas.getString(1));
                cerveza.setMarca(cursorCervezas.getString(2));
            }

            cursorCervezas.close();

            return cerveza;
        } else {
            Toast.makeText(context, "No existe ninguna cerveza por el identificador facilitado", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void editarCerveza(int id, String nombre, String pais, String tipo, String marca, double precio, double graduacion) {

        if(existeCerveza(id)) {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            try {
                db.execSQL("UPDATE " + TABLE_CERVEZAS + " SET nombre = '" + nombre + "', pais = '" + pais + "', tipo = '" + tipo +
                        "', marca = '" + marca + "', precio = '" + precio + "', graduacion = '" + graduacion + "' WHERE id='" + id + "' ");
            } catch (Exception ex) {
                ex.toString();
            } finally {
                db.close();
            } } else {
            Toast.makeText(context, "No existe ninguna cerveza por el identificador facilitado", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarCerveza(int id) {

        if(existeCerveza(id)){
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            try {
                db.execSQL("DELETE FROM " + TABLE_CERVEZAS + " WHERE id = '" + id + "'");
            } catch (Exception ex) {
                ex.toString();
            } finally {
                db.close();
            } } else {
            Toast.makeText(context, "No existe ninguna cerveza por el identificador facilitado", Toast.LENGTH_LONG).show();
        }
    }

    public boolean existeCerveza(int id) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cerveza> listaCervezas = new ArrayList<>();
        Cerveza cerveza = null;
        Cursor cursorCervezas = null;

        cursorCervezas = db.rawQuery("SELECT * FROM " + TABLE_CERVEZAS, null);

        if (cursorCervezas.moveToFirst()){
            do{
                cerveza = new Cerveza();
                cerveza.setId(cursorCervezas.getString(0));
                cerveza.setNombre(cursorCervezas.getString(1));
                cerveza.setMarca(cursorCervezas.getString(2));

                listaCervezas.add(cerveza);
            } while (cursorCervezas.moveToNext());
        }
        cursorCervezas.close();

        for (Cerveza c : listaCervezas) {
            if (c.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    public String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }


}

