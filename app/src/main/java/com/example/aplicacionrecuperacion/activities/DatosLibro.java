package com.example.aplicacionrecuperacion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.aplicacionrecuperacion.databinding.ActivityDatosLibroBinding;
import com.example.aplicacionrecuperacion.model.Libro;

public class DatosLibro extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Intent recibido;
    ActivityDatosLibroBinding libroBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        libroBinding = ActivityDatosLibroBinding.inflate(getLayoutInflater());
        View view = libroBinding.getRoot();
        setContentView(view);
        recibido = getIntent();

        int intencion = recibido.getIntExtra("intencion",2);
        switch (intencion){

            case 1:

                break;
            case 2:

                break;
            case 3:

                break;


        }

    }

    public void rellenarFormulario(){

        Libro libro = (Libro) recibido.getSerializableExtra("libro");
        libroBinding.txtTitulo.setText(libro.getTitulo());
        libroBinding.txtAutor.setText(libro.getAutor());
        if (libro.getMio()==1) {
            libroBinding.chkMio.setChecked(true);
        }
        if (libro.getPrestado()==1) {
            libroBinding.chkPrestado.setChecked(true);
        }
        libroBinding.rgpEstado.check(libro.getEstado());
        libroBinding.txtEntidadPrestado.setText(libro.getNombrePrestado());
        libroBinding.txtTelefonoPrestado.setText(libro.getNumeroPrestado());
        libroBinding.dpkFechaPrestado.updateDate(libro.getAnhoRetorno(),libro.getMesRetorno(),libro.getDiaRetorno());
        libroBinding.dpkFechaLectura.updateDate(libro.getAnhoLectura(),libro.getMesLectura(),libro.getDiaLectura());
        libroBinding.spnValoracion.setSelection(libro.getValoracion());
        libroBinding.txtTitulo.setText(libro.getTitulo());
        libroBinding.txtTitulo.setText(libro.getTitulo());
        libroBinding.txtTitulo.setText(libro.getTitulo());
        libroBinding.txtTitulo.setText(libro.getTitulo());
        libroBinding.txtTitulo.setText(libro.getTitulo());
        libroBinding.txtTitulo.setText(libro.getTitulo());

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    
}