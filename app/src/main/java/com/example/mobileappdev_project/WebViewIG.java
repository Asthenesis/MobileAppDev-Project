package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebViewIG extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_ig);

        WebView webig = (WebView) findViewById(R.id.webinstagram);
        webig.loadUrl("https://www.instagram.com/wordfangs/");
    }
}