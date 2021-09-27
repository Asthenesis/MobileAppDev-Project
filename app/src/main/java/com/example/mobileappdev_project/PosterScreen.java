package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PosterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_poster);
        getSupportActionBar().hide();
    }
}