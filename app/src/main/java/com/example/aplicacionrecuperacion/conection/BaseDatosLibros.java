package com.example.aplicacionrecuperacion.conection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class BaseDatosLibros extends SQLiteAssetHelper {
    public BaseDatosLibros(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
