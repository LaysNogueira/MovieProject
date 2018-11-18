package com.example.familiavale.movieproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmeArrayAdapter extends ArrayAdapter <Filmes>{

    private Map <String, Bitmap> figuras =
            new HashMap<>();

    public FilmeArrayAdapter(@NonNull Context context, List<Filmes> filmes) {
        super(context, -1, filmes);
    }

    private class ViewHolder {
        public ImageView filmeImageView;
        public TextView nomeFilme;
        public TextView popularity;
        public TextView voto;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Context context = getContext();
        Filmes caraDaVez = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater =
                    LayoutInflater.from(context);
            convertView = inflater.
                    inflate(R.layout.list_filmes, parent, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.nomeFilme= convertView.findViewById(R.id.nomeFilme);
            viewHolder.popularity = convertView.findViewById(R.id.popularidadeTextView);
            viewHolder.voto = convertView.findViewById(R.id.votoTextView);
            viewHolder.filmeImageView = convertView.findViewById(R.id.conditionImageView);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        if (figuras.containsKey(caraDaVez.iconName)){
            Bitmap figura = figuras.get(caraDaVez.iconName);
            viewHolder.filmeImageView.setImageBitmap(figura);
        }
        else{
            new BaixaImagem(viewHolder.filmeImageView, caraDaVez.iconName).
                    execute(context.getString(R.string.img_download_url, caraDaVez.iconName));

        }

        viewHolder.nomeFilme.setText(context.getString(R.string.filme, caraDaVez.nomeFilme));
        viewHolder.popularity.setText(context.getString(R.string.popularity, caraDaVez.popularidade));
        viewHolder.voto.setText(context.getString(R.string.popularity, caraDaVez.popularidade));
        viewHolder.voto.setText(context.getString(R.string.voto, caraDaVez.voto));
        return convertView;
    }
    private class BaixaImagem extends AsyncTask <String, Void, Bitmap>{
        private ImageView filmeImageView;
        private String iconName;
        BaixaImagem (ImageView filmeImageView, String iconName){
            this.filmeImageView = filmeImageView;
            this.iconName = iconName;
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                Bitmap figura = BitmapFactory.decodeStream(inputStream);
                return figura;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap figura) {
            filmeImageView.setImageBitmap (figura);
            figuras.put(iconName,  figura);
        }
    }
}
