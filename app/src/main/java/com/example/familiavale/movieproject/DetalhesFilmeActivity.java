package com.example.familiavale.movieproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetalhesFilmeActivity extends AppCompatActivity {
    private List<DetalhesFilme> detalhesFilmes;
    public TextView detalhesTextView, nomeFilmeTextView, iconTextView;
    public ImageView filmeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        Bundle bundle = getIntent().getExtras();
        String nomeFilme = bundle.getString("nomeFilme");
        String detalhes = bundle.getString("detalhes");
        String icon = bundle.getString("icon");

        filmeImageView = findViewById(R.id.conditionImageView);
        Bitmap figura = FilmeArrayAdapter.getFiguras().get(icon);
        filmeImageView.setImageBitmap(figura);

        detalhesTextView = findViewById(R.id.detalhes);
        detalhesTextView.setText(detalhes);

        nomeFilmeTextView = findViewById(R.id.nomeFilme);
        nomeFilmeTextView.setText(nomeFilme);

    }
}