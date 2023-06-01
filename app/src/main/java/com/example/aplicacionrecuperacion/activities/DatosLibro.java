package com.example.aplicacionrecuperacion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.aplicacionrecuperacion.databinding.ActivityDatosLibroBinding;

public class DatosLibro extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDatosLibroBinding libroBinding = ActivityDatosLibroBinding.inflate(getLayoutInflater());
        View view = libroBinding.getRoot();
        setContentView(view);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    
}