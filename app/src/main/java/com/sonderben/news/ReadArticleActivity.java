package com.sonderben.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class ReadArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);

        webView = findViewById(R.id.webview);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("URL_IMG");
            webView.loadUrl(value);
        }
    }
    private WebView webView;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearFormData();
    }
}