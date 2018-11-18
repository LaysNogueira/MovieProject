package com.example.familiavale.movieproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
import java.util.List;

public class DetalhesFilmeActivity extends AppCompatActivity {
    DetalhesFilme detalhesFilme;
    private TextView nFilme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        Bundle bundle = getIntent().getExtras();
        int idFilme = bundle.getInt(    "idFilme");
        String idF = Integer.toString(idFilme);

        nFilme = findViewById(R.id.nomeFilme);

        String endereco = getString(R.string.web_service_url_detalhes, getString(R.string.api_key), getString(R.string.lang), idF);
        new DetalhesFilmeActivity.ObtemDetalhes().execute(endereco);

    }

    private class ObtemDetalhes extends
            AsyncTask<String, Void, String> {

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
            try{
                JSONObject belongs = new JSONObject(jsonS);
                    String nfilme = belongs.getString("name");
                    String overview = belongs.getString("overview");
                    String poster = belongs.getString("poster_path");
                JSONObject genres = belongs.getJSONObject("genres");
                    String genre = genres.getString("name");
                    DetalhesFilme df = new DetalhesFilme(genre, nfilme, overview, poster);
                    nFilme.setText(df.nfilme);
            }
            catch (JSONException e){
                e.printStackTrace();
            }


        }
    }
}
