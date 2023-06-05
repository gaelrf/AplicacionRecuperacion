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

public class DatosLibro extends AppCompatActivity implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    Intent recibido;
    ActivityDatosLibroBinding libroBinding;
    BaseDatosLibros datosLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        libroBinding = ActivityDatosLibroBinding.inflate(getLayoutInflater());
        View view = libroBinding.getRoot();
        setContentView(view);
        recibido = getIntent();

        int intencion = recibido.getIntExtra("intencion",2);
        Libro libro = (Libro) recibido.getSerializableExtra("libro");

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
        libroBinding.etxPagina.setText(libro.getPagina());
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent;
        switch (view.getId()){

            case R.id.btnVolver:
                intent = new Intent();
                intent.putExtra("realizado", 0);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnGuardar:
                guardar();
                intent =new Intent();
                intent.putExtra("realizado", 1);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnModificar:
                modificar();
                intent =new Intent();
                intent.putExtra("realizado", 1);
                setResult(RESULT_OK, intent);
                finish();


        }

    }

    public void guardar(){

        datosLibros = new BaseDatosLibros(this, "libros.db", null, 1);
        SQLiteDatabase datos = datosLibros.getWritableDatabase();
        ContentValues nuevoRegistro = leerFormulario();
        datos.insert("libro",null,nuevoRegistro);

    }

    public void modificar(){

        datosLibros = new BaseDatosLibros(this, "libros.db", null, 1);
        SQLiteDatabase datos = datosLibros.getWritableDatabase();
        ContentValues registroModificado = leerFormulario();
        Libro libro = (Libro) recibido.getSerializableExtra("libro");
        datos.update("libro",registroModificado,"id = "+ libro.getId(),null);

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
        registro.put("caloracion",libroBinding.spnValoracion.getSelectedItemPosition());
        return registro;

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        switch (i){

            case R.id.rbtPendiente:
                libroBinding.dpkFechaLectura.setVisibility(View.VISIBLE);
                libroBinding.etxPagina.setVisibility(View.GONE);
                libroBinding.etxPagina.setText(null);


        }

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {



    }
}