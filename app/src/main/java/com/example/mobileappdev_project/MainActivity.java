package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        Home_Menu frag1 = new Home_Menu();
        frag1.setArguments(getIntent().getExtras());

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();

        ft.add(R.id.frame1,frag1);
        ft.replace(R.id.frame1,frag1);
        ft.commit();


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
        ImageButton BtnSearch = (ImageButton) findViewById(R.id.BtnSearch);
        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchMenu frag1 = new SearchMenu();
                frag1.setArguments(getIntent().getExtras());

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                ft.add(R.id.frame1, frag1);
                ft.replace(R.id.frame1, frag1);
                ft.commit();


            }
        });

        ImageButton BtnHistory = (ImageButton) findViewById(R.id.BtnHistory);
        BtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryMenu frag1 = new HistoryMenu();
                frag1.setArguments(getIntent().getExtras());

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                ft.add(R.id.frame1, frag1);
                ft.replace(R.id.frame1, frag1);
                ft.commit();

            }
        });


        ImageButton btnprofile = (ImageButton)findViewById(R.id.BtnProfile);
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });

    }
}