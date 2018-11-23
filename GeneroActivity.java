package com.example.familiavale.movieproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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

public class GeneroActivity extends AppCompatActivity {
    public TextView nomeGenero;
    private List<Genero> generos;
    private GeneroArrayAdapter adapter;
    private ListView generoListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero);
        generos = new ArrayList<>();

        //generos
        nomeGenero = findViewById(R.id.nomeGenero);
        adapter = new GeneroArrayAdapter(this, generos);
        generoListView = findViewById(R.id.generoListView);
        generoListView.setAdapter(adapter);

        String endereco =
                getString(R.string.web_service_url,
                        getString(R.string.api_key),
                        getString(R.string.lang));
        new ObtemGeneros().execute(endereco);

        generoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Genero genero = (Genero) generoListView.getItemAtPosition(position);
                int idGenero = genero.id;
                Intent intent = new Intent(com.example.familiavale.movieproject.GeneroActivity.this, com.example.familiavale.movieproject.FilmesActivity.class);

                intent.putExtra("idGenero", idGenero);


                startActivity(intent);
            }
        });

    }

    private class ObtemGeneros extends
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
                generos.clear();
                JSONObject json = new JSONObject(jsonS);
                JSONArray list = json.getJSONArray("genres");
                for (int i = 0; i < list.length(); i++){
                    JSONObject genero = list.getJSONObject(i);
                    String nGenero = genero.getString("name");
                    int id = genero.getInt("id");
                    Genero g = new Genero(id, nGenero);
                    generos.add(g);
                }
                adapter.notifyDataSetChanged();

            }
            catch (JSONException e){
                e.printStackTrace();
            }


        }
    }

}

