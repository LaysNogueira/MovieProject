package com.example.familiavale.movieproject;

import android.widget.ImageView;

public class DetalhesFilme {
    public String ngenero;
    public String nfilme;
    public String detalhe;
    public String dicon;

    public DetalhesFilme(String ngenero, String nfilme, String detalhe, String dicon){
        this.ngenero = ngenero;
        this.nfilme = nfilme;
        this.detalhe = detalhe;
        this.dicon = dicon;
    }


    public String getNgenero() {
        return ngenero;
    }

    public void setNgenero(String ngenero) {
        this.ngenero = ngenero;
    }

    public String getNfilme() {
        return nfilme;
    }

    public void setNfilme(String nfilme) {
        this.nfilme = nfilme;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getDicon() {
        return dicon;
    }

    public void setDicon(String dicon) {
        this.dicon = dicon;
    }
}