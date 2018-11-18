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

public class GeneroArrayAdapter extends ArrayAdapter <Genero>{

    Map<String, Bitmap> bitmaps = new HashMap<>();

    public GeneroArrayAdapter(@NonNull Context context, List<Genero> forecast) {
        super(context, -1, forecast);
    }

    private class ViewHolder {
        public TextView nomeGenero;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Context context = getContext();
        Genero caraDaVez = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater =
                    LayoutInflater.from(context);
            convertView = inflater.
                    inflate(R.layout.list_genero, parent, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.nomeGenero = convertView.findViewById(R.id.nomeGenero);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.nomeGenero.setText(context.getString(R.string.genero, caraDaVez.nomeGenero));
        return convertView;
    }
}
