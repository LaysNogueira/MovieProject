package com.example.familiavale.movieproject;

import android.app.Activity;

public class Filmes{
    public int id;
    public String nomeFilme, iconName,  lancamento, voto, detalhes;


    public String getIconName() {
        return iconName;
    }

    public Filmes(int id, String nomeFilme, String lancamento, String voto, String iconName, String detalhes) {
        this.id = id;
        this.nomeFilme = nomeFilme;
        this.lancamento = lancamento;
        this.voto = voto;
        this.iconName = iconName;
        this.detalhes = detalhes;

    }
}