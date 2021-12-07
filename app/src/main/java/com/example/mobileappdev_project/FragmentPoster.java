package com.example.mobileappdev_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FragmentPoster extends Fragment {

    WebView webView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment_poster, container, false);

        webView2 = (WebView) v.findViewById(R.id.webView2);
        webView2.setWebViewClient(new WebViewClient());
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.loadUrl("https://www.instagram.com/p/B3UP2l2Fm3h/");

        return v;
    }
}