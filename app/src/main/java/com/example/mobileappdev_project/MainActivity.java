package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ImageButton BtnHome = (ImageButton) findViewById(R.id.BtnHome);
       BtnHome.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            Home_Menu frag1 = new Home_Menu();
            frag1.setArguments(getIntent().getExtras());

               FragmentManager fm = getSupportFragmentManager();
               FragmentTransaction ft =fm.beginTransaction();

               ft.add(R.id.frame1,frag1);
               ft.replace(R.id.frame1,frag1);
               ft.commit();
           }
       });




    }
}