package com.example.aplicacionrecuperacion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.example.aplicacionrecuperacion.R;
import com.example.aplicacionrecuperacion.conection.BaseDatosLibros;
import com.example.aplicacionrecuperacion.databinding.ActivityDatosLibroBinding;
import com.example.aplicacionrecuperacion.model.Libro;

public class DatosLibro extends AppCompatActivity implements AdapterView.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    Intent recibido;
    ActivityDatosLibroBinding libroBinding;
    BaseDatosLibros datosLibros;
    Libro libro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        libroBinding = ActivityDatosLibroBinding.inflate(getLayoutInflater());
        View view = libroBinding.getRoot();
        setContentView(view);
        recibido = getIntent();

        int intencion = recibido.getIntExtra("intencion",2);
        libro = (Libro) recibido.getSerializableExtra("libro");

        switch (intencion){

            case 1:
                rellenarFormulario(libro);
                break;
            case 2:

                break;
            case 3:
                rellenarFormulario(libro);
                break;
        }

        vivibilidad(intencion);

        setListeners();


    }

    private void setListeners() {

        libroBinding.btnGuardar.setOnClickListener(this);
        libroBinding.btnBuscar.setOnClickListener(this);
        libroBinding.btnLlamar.setOnClickListener(this);
        libroBinding.btnModificar.setOnClickListener(this);
        libroBinding.btnVolver.setOnClickListener(this);
        libroBinding.chkPrestado.setOnCheckedChangeListener(this);
        libroBinding.rgpEstado.setOnCheckedChangeListener(this);

    }

    public void rellenarFormulario(Libro libro){

        libroBinding.etxTitulo.setText(libro.getTitulo());
        libroBinding.etxAutor.setText(libro.getAutor());
        if (libro.getMio()==1) {
            libroBinding.chkMio.setChecked(true);
        }
        if (libro.getPrestado()==1) {
            libroBinding.chkPrestado.setChecked(true);
        }
        libroBinding.rgpEstado.check(libro.getEstado());
        libroBinding.etxEntidadPrestado.setText(libro.getNombrePrestado());
        libroBinding.etxTelefonoPrestado.setText(libro.getNumeroPrestado());
        libroBinding.dpkFechaPrestado.updateDate(libro.getAnhoRetorno(),libro.getMesRetorno(),libro.getDiaRetorno());
        libroBinding.dpkFechaLectura.updateDate(libro.getAnhoLectura(),libro.getMesLectura(),libro.getDiaLectura());
        String pagina = String.valueOf(libro.getPagina());
        libroBinding.etxPagina.setText(pagina);
        libroBinding.spnValoracion.setSelection(libro.getValoracion());
        libroBinding.etxComentario.setText(libro.getValoracionString());

    }

    public void vivibilidad(int intencion){

        switch (intencion){
            case 1:

                if (libroBinding.chkPrestado.isChecked()) {
                    libroBinding.btnLlamar.setVisibility(View.VISIBLE);
                }else {
                    if (libroBinding.chkMio.isChecked()){
                        libroBinding.btnBuscar.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case 2:
                libroBinding.btnGuardar.setVisibility(View.VISIBLE);
                break;

            case 3:
                libroBinding.btnModificar.setVisibility(View.VISIBLE);
                break;
        }

        if (libroBinding.chkPrestado.isChecked()){

            libroBinding.etxEntidadPrestado.setVisibility(View.VISIBLE);
            libroBinding.etxTelefonoPrestado.setVisibility(View.VISIBLE);

        }

        switch (libroBinding.rgpEstado.getCheckedRadioButtonId()){

            case R.id.rbtPendiente:

                break;

            case R.id.rbtLeyendo:

                libroBinding.dpkFechaLectura.setVisibility(View.GONE);
                libroBinding.etxPagina.setVisibility(View.VISIBLE);
                break;

            case R.id.rbtLeido:

                libroBinding.dpkFechaLectura.setVisibility(View.GONE);
                libroBinding.spnValoracion.setVisibility(View.VISIBLE);
                libroBinding.etxComentario.setVisibility(View.VISIBLE);
                break;

        }

    }
    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()){

            case R.id.btnVolver:
                intent = new Intent();
                intent.putExtra("realizado", false);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnGuardar:
                long guardar = guardar();
                intent =new Intent();
                intent.putExtra("realizado", true);
                intent.putExtra("guardar", guardar);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnModificar:
                int modificar = modificar();
                intent =new Intent();
                intent.putExtra("realizado", true);

                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnLlamar:



        }

    }

    public long guardar(){

        datosLibros = new BaseDatosLibros(this, "libros.db", null, 1);
        SQLiteDatabase datos = datosLibros.getWritableDatabase();
        ContentValues nuevoRegistro = leerFormulario();
        return datos.insert("libro",null,nuevoRegistro);

    }

    public int modificar(){

        datosLibros = new BaseDatosLibros(this, "libros.db", null, 1);
        SQLiteDatabase datos = datosLibros.getWritableDatabase();
        ContentValues registroModificado = leerFormulario();
        return datos.update("libro",registroModificado,"id = "+ libro.getId(),null);

    }

    public ContentValues leerFormulario(){

        ContentValues registro = new ContentValues();
        registro.put("titulo",libroBinding.etxTitulo.getEditableText().toString());
        registro.put("autor",libroBinding.etxAutor.getEditableText().toString());
        registro.put("mio",libroBinding.chkMio.isChecked());
        registro.put("prestado",libroBinding.chkPrestado.isChecked());
        registro.put("estado",libroBinding.rgpEstado.getCheckedRadioButtonId());
        registro.put("nombre_prestado",libroBinding.etxEntidadPrestado.getEditableText().toString());
        registro.put("numero_prestado",libroBinding.etxTelefonoPrestado.getEditableText().toString());
        registro.put("anho_retorno", libroBinding.dpkFechaPrestado.getYear());
        registro.put("mes_retorno", libroBinding.dpkFechaPrestado.getMonth());
        registro.put("dia_retorno", libroBinding.dpkFechaPrestado.getDayOfMonth());
        registro.put("anho_lectura", libroBinding.dpkFechaLectura.getYear());
        registro.put("mes_lectura", libroBinding.dpkFechaLectura.getMonth());
        registro.put("dia_lectura", libroBinding.dpkFechaLectura.getDayOfMonth());
        registro.put("pagina",libroBinding.etxPagina.getEditableText().toString());
        registro.put("valoracion",libroBinding.spnValoracion.getSelectedItemPosition());
        registro.put("comentario",libroBinding.etxComentario.getText().toString());
        return registro;

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        switch (i){

            case R.id.rbtPendiente:
                libroBinding.dpkFechaLectura.setVisibility(View.VISIBLE);
                libroBinding.etxPagina.setVisibility(View.GONE);
                libroBinding.etxPagina.setText(null);
                libroBinding.spnValoracion.setVisibility(View.GONE);
                libroBinding.spnValoracion.setSelection(0);
                libroBinding.etxComentario.setVisibility(View.GONE);
                libroBinding.etxComentario.setText(null);
                break;
            case R.id.rbtLeyendo:
                libroBinding.dpkFechaLectura.setVisibility(View.GONE);
                libroBinding.dpkFechaLectura.updateDate(1,1,1);
                libroBinding.etxPagina.setVisibility(View.VISIBLE);
                libroBinding.spnValoracion.setVisibility(View.GONE);
                libroBinding.spnValoracion.setSelection(0);
                libroBinding.etxComentario.setVisibility(View.GONE);
                libroBinding.etxComentario.setText(null);
                break;
            case R.id.rbtLeido:
                libroBinding.dpkFechaLectura.setVisibility(View.GONE);
                libroBinding.dpkFechaLectura.updateDate(1,1,1);
                libroBinding.etxPagina.setVisibility(View.GONE);
                libroBinding.etxPagina.setText(null);
                libroBinding.spnValoracion.setVisibility(View.VISIBLE);
                libroBinding.etxComentario.setVisibility(View.VISIBLE);
                break;
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (b){

            libroBinding.etxEntidadPrestado.setVisibility(View.VISIBLE);
            libroBinding.etxTelefonoPrestado.setVisibility(View.VISIBLE);

        }else {

            libroBinding.etxEntidadPrestado.setVisibility(View.GONE);
            libroBinding.etxEntidadPrestado.setText(null);
            libroBinding.etxTelefonoPrestado.setVisibility(View.GONE);
            libroBinding.etxTelefonoPrestado.setText(null);

        }

    }


}