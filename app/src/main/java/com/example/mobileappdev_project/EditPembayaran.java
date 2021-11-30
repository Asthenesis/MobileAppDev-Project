package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.ImageButton;
import android.os.Bundle;

public class EditPembayaran extends AppCompatActivity {

    private ImageButton sectionB,sectionC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pembayaran);

        ImageButton sectionA = (ImageButton) findViewById(R.id.SectionA);
        sectionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Pembayaran.class));
            }
        });

        ImageButton buttonback = (ImageButton) findViewById(R.id.Exit);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PosterScreen.class));
            }
        });
    }
}