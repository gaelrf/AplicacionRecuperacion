package com.example.aplicacionrecuperacion.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Libro implements Serializable {

    private int id;

    private String titulo;

    private String autor;

    private int mio;

    private int prestado;

    private int estado;

    private String nombrePrestado;

    private String numeroPrestado;

    private int pagina;

    private int anhoRetorno;

    private int mesRetorno;

    private int diaRetorno;

    private int anhoLectura;

    private int mesLectura;

    private int diaLectura;

    private int valoracion;

    private String valoracionString;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getMio() {
        return mio;
    }

    public void setMio(int mio) {
        this.mio = mio;
    }

    public int getPrestado() {
        return prestado;
    }

    public void setPrestado(int prestado) {
        this.prestado = prestado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombrePrestado() {
        return nombrePrestado;
    }

    public void setNombrePrestado(String nombrePrestado) {
        this.nombrePrestado = nombrePrestado;
    }

    public String getNumeroPrestado() {
        return numeroPrestado;
    }

    public void setNumeroPrestado(String numeroPrestado) {
        this.numeroPrestado = numeroPrestado;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getAnhoRetorno() {
        return anhoRetorno;
    }

    public void setAnhoRetorno(int anhoRetorno) {
        this.anhoRetorno = anhoRetorno;
    }

    public int getMesRetorno() {
        return mesRetorno;
    }

    public void setMesRetorno(int mesRetorno) {
        this.mesRetorno = mesRetorno;
    }

    public int getDiaRetorno() {
        return diaRetorno;
    }

    public void setDiaRetorno(int diaRetorno) {
        this.diaRetorno = diaRetorno;
    }

    public int getAnhoLectura() {
        return anhoLectura;
    }

    public void setAnhoLectura(int anhoLectura) {
        this.anhoLectura = anhoLectura;
    }

    public int getMesLectura() {
        return mesLectura;
    }

    public void setMesLectura(int mesLectura) {
        this.mesLectura = mesLectura;
    }

    public int getDiaLectura() {
        return diaLectura;
    }

    public void setDiaLectura(int diaLectura) {
        this.diaLectura = diaLectura;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getValoracionString() {
        return valoracionString;
    }

    public void setValoracionString(String valoracionString) {
        this.valoracionString = valoracionString;
    }
}
