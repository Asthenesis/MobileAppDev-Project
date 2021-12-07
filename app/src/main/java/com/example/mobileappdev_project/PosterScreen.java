package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PosterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_poster);
        getSupportActionBar().hide();

        ImageButton btn = (ImageButton)findViewById(R.id.btnProses);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PosterScreen.this, EditPembayaran.class));
            }
        });

       ImageButton ig = (ImageButton) findViewById(R.id.btnInstagram);
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WebViewIG.class));
            }
        });

        ImageButton btnBookmark = (ImageButton)findViewById(R.id.btnBookmark);

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PosterScreen.this, BookmarkReview.class));
            }
        });

        ImageButton btnback = (ImageButton) findViewById(R.id.btnBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PosterScreen.this, MainActivity.class));
            }
        });

    }
}