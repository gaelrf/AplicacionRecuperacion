package com.example.aplicacionrecuperacion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.aplicacionrecuperacion.R;
import com.example.aplicacionrecuperacion.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        mainBinding.btnComenzar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(this, ListadoLibros.class);
        startActivity(i);

    }

}
