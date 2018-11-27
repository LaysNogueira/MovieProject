package com.example.familiavale.movieproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class FilmesActivity extends AppCompatActivity {
    // filmes
    public TextView nomeFilme, lancamento, voto;
    private List<Filmes> filmes;
    private FilmeArrayAdapter adapterFilme;
    private ListView filmeListView;

    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes);
        filmes = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();

        nomeFilme = findViewById(R.id.nomeFilme);
        lancamento = findViewById(R.id.lancamento);
        voto = findViewById(R.id.votoTextView);
        adapterFilme = new FilmeArrayAdapter(this, filmes);
        filmeListView= findViewById(R.id.filmeListView);
        filmeListView.setAdapter(adapterFilme);


        int idGenero = bundle.getInt(    "idGenero");
        String idG = Integer.toString(idGenero);

        String endereco = getString(R.string.web_service_url_filmes, getString(R.string.api_key), getString(R.string.lang), idG);
        new ObtemFilmes().execute(endereco);



        filmeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filmes filme = (Filmes) filmeListView.getItemAtPosition(position);
                String detalhes = filme.detalhes;
                String icon = filme.iconName;
                String nomeFilme = filme.nomeFilme;
                int idFilme = filme.id;
                Intent intent = new Intent(com.example.familiavale.movieproject.FilmesActivity.this, com.example.familiavale.movieproject.DetalhesFilmeActivity.class);

                intent.putExtra("nomeFilme", nomeFilme);
                intent.putExtra("detalhes", detalhes);
                intent.putExtra("icon", icon);
                intent.putExtra("idFilme", idFilme);

                startActivity(intent);
            }
        });

    }

    private class ObtemFilmes extends
            AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            dialog = new ProgressDialog(FilmesActivity.this);
            dialog.setCancelable(true);
            dialog.setMessage("Buscando. Por favor, aguarde...");
            dialog.show();
        }
        @Override
        protected String doInBackground(String... enderecos) {
            try{
                URL url = new URL(enderecos[0]);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(inputStream));
                String linha = null;
                String json = "";
                while ((linha = reader.readLine()) != null){
                    json += linha;
                }
                return json;
            }
            catch (MalformedURLException e){
                e.printStackTrace();

            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String jsonS) {
            dialog.cancel();
            super.onPostExecute(jsonS);
            try{
                filmes.clear();
                JSONObject json = new JSONObject(jsonS);
                JSONArray list = json.getJSONArray("results");
                for (int i = 0; i < list.length(); i++){
                    JSONObject filme = list.getJSONObject(i);
                    String nFilme= filme.getString("title");
                    String lancamento =filme.getString("release_date");
                    String voto = filme.getString("vote_average");
                    String icon = filme.getString("poster_path");
                    String detalhes = filme.getString("overview");
                    int id = filme.getInt("id");
                    Filmes f = new Filmes(id, nFilme, lancamento, voto, icon, detalhes);
                    filmes.add(f);
                }
                adapterFilme.notifyDataSetChanged();

            }
            catch (JSONException e){
                e.printStackTrace();
            }


        }
    }
}