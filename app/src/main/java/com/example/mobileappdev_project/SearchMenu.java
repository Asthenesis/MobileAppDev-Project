package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SearchMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);
        this.setTitle("");
        getSupportActionBar().hide();
    }
}