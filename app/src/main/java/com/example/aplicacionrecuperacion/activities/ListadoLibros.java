package com.example.aplicacionrecuperacion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.aplicacionrecuperacion.R;
import com.example.aplicacionrecuperacion.databinding.ActivityListadoLibrosBinding;

public class ListadoLibros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityListadoLibrosBinding librosBinding = ActivityListadoLibrosBinding.inflate(getLayoutInflater());
        View view = librosBinding.getRoot();
        setContentView(view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

}