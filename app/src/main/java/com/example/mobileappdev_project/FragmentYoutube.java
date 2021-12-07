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

public class FragmentYoutube extends Fragment {

    WebView webView3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment_youtube, container, false);

        webView3 = (WebView) v.findViewById(R.id.webView3);
        webView3.setWebViewClient(new WebViewClient());
        webView3.getSettings().setJavaScriptEnabled(true);
        webView3.loadUrl("https://www.youtube.com/channel/UC_WMZDQv29eJBMpv9PwWazw");

        return v;
    }
}