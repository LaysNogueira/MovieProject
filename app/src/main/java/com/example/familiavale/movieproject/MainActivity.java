package com.example.familiavale.movieproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFilmes = findViewById(R.id.btnFilme);
        Button btnTv = findViewById(R.id.btnTv);

        btnFilmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.familiavale.movieproject.MainActivity.this, com.example.familiavale.movieproject.GeneroActivity.class);
                startActivity(intent);
            }
        });

        btnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.familiavale.movieproject.MainActivity.this, com.example.familiavale.movieproject.TvActivity.class));
            }
        });
    }
}
