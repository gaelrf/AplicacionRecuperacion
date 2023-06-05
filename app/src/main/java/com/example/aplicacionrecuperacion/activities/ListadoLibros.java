package com.example.aplicacionrecuperacion.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.aplicacionrecuperacion.R;
import com.example.aplicacionrecuperacion.adapters.AdaptadorLibro;
import com.example.aplicacionrecuperacion.conection.BaseDatosLibros;
import com.example.aplicacionrecuperacion.databinding.ActivityListadoLibrosBinding;
import com.example.aplicacionrecuperacion.model.Libro;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class ListadoLibros extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final int CODIGO_VISUALIZAR = 1;
    private static final int CODIGO_INSERTAR = 2;
    private static final int CODIGO_MODIFICAR = 3;

    List<Libro> libros;
    BaseDatosLibros datosLibros;
    //private final String[] COLUMNAS = {"titulo","autor","mio","prestado","estado","nombre_prestado",
    //        "numero_prestado","pagina","fecha_retorno","valoracion","comentario","fecha_lectura"};
    ActivityListadoLibrosBinding librosBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        librosBinding = ActivityListadoLibrosBinding.inflate(getLayoutInflater());
        View view = librosBinding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listarLibros();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
        String elemento=((Libro)librosBinding.lstLibros.getAdapter().getItem(info.position))
                .getTitulo();
        menu.setHeaderTitle(elemento);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);

    }

    public void listarLibros(){

        libros = new ArrayList<>();
        datosLibros = new BaseDatosLibros(this, "libros.db", null, 1);
        SQLiteDatabase database = datosLibros.getReadableDatabase();
        Cursor cursor = database.query("libro",null,null,null,null, null,null);
        if(cursor.moveToFirst()){
            librosBinding.txtSinLibros.setVisibility(View.GONE);
            librosBinding.btnSinLibros.setVisibility(View.GONE);
            do{
                Libro libro = new Libro();
                libro.setId(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setAutor(cursor.getString(2));
                libro.setMio(cursor.getInt(3));
                libro.setPrestado(cursor.getInt(4));
                libro.setEstado(cursor.getInt(5));
                libro.setNombrePrestado(cursor.getString(6));
                libro.setNumeroPrestado(cursor.getString(7));
                libro.setAnhoRetorno(cursor.getInt(8));
                libro.setMesRetorno(cursor.getInt(9));
                libro.setDiaRetorno(cursor.getInt(10));
                libro.setAnhoLectura(cursor.getInt(11));
                libro.setMesLectura(cursor.getInt(12));
                libro.setDiaLectura(cursor.getInt(13));
                libro.setPagina(cursor.getInt(14));
                libro.setValoracion(cursor.getInt(15));
                libro.setValoracionString(cursor.getString(16));


                libros.add(libro);


            }while (cursor.moveToNext());
            AdaptadorLibro adaptadorLibro=new AdaptadorLibro(this,R.layout.layout_lista_libros,libros);
            librosBinding.lstLibros.setAdapter(adaptadorLibro);

        }else {

            librosBinding.txtSinLibros.setVisibility(View.VISIBLE);
            librosBinding.btnSinLibros.setVisibility(View.VISIBLE);
            librosBinding.btnSinLibros.setOnClickListener(this);

        }

        database.close();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.opcInsertar:
                Intent intent = new Intent(this,DatosLibro.class);
                intent.putExtra("intencion",CODIGO_INSERTAR);
                startActivityForResult(intent,CODIGO_INSERTAR);

                break;
            case R.id.opcFinalizar:

                break;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int i = info.position;
        switch (item.getItemId()){
            case R.id.opcModificar:


            case R.id.opcBorrar:


        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this,DatosLibro.class);
        intent.putExtra("intencion",CODIGO_INSERTAR);
        startActivityForResult(intent,CODIGO_INSERTAR);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(this,DatosLibro.class);
        intent.putExtra("libro", libros.get(i));
        intent.putExtra("intencion",CODIGO_VISUALIZAR);
        startActivity(intent);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case CODIGO_MODIFICAR:


        }

    }

}