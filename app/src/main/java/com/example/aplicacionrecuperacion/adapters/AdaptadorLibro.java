package com.example.aplicacionrecuperacion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aplicacionrecuperacion.R;
import com.example.aplicacionrecuperacion.model.Libro;

import java.util.List;

public class AdaptadorLibro extends ArrayAdapter {

    private List<Libro> libros;
    private Context context;


    public AdaptadorLibro(@NonNull Context context, int resource, @NonNull List<Libro> libros) {

        super(context, resource, libros);
        this.context=context;
        this.libros=libros;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila=convertView;
        ViewHolder viewHolder;
        if (fila == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            fila = layoutInflater.inflate(R.layout.layout_lista_libros, null);
            viewHolder=new ViewHolder();
            viewHolder.txtTituloLista=fila.findViewById(R.id.txtTituloLista);
            viewHolder.txtAutorLista=fila.findViewById(R.id.txtAutorLista);
            viewHolder.imgPrestado=fila.findViewById(R.id.imgPrestado);
            viewHolder.imgPropio=fila.findViewById(R.id.imgPropio);
            fila.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)fila.getTag();
        }

        viewHolder.txtTituloLista.setText(libros.get(position).getTitulo());
        viewHolder.txtAutorLista.setText(libros.get(position).getAutor());
        if (libros.get(position).getMio()==1){

            viewHolder.imgPropio.setVisibility(View.VISIBLE);

        } else {

            viewHolder.imgPropio.setVisibility(View.INVISIBLE);

        }
        if (libros.get(position).getPrestado()==1){

            viewHolder.imgPrestado.setVisibility(View.VISIBLE);

        } else {

            viewHolder.imgPrestado.setVisibility(View.INVISIBLE);

        }

        return  fila;

    }

}
