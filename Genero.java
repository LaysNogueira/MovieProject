package com.example.familiavale.movieproject;

import android.app.Activity;

public class Genero{
    public int id;
    public String nomeGenero;

    public Genero(){

    }
    public Genero(int id, String nomeGenero) {
        this.id = id; this.nomeGenero = nomeGenero;
    }

    public Genero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }
}

