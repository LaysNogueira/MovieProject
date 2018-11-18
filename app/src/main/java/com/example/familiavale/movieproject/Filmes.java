package com.example.familiavale.movieproject;

import android.app.Activity;

public class Filmes{
    public int id;
    public String nomeFilme, iconName,  popularidade, voto;

    public Filmes(){

    }

    public Filmes(int id, String nomeFilme, String popularidade, String voto, String iconName) {
        this.id = id;
        this.nomeFilme = nomeFilme;
        this.popularidade = popularidade;
        this.voto = voto;
        this.iconName = iconName;
    }
}
