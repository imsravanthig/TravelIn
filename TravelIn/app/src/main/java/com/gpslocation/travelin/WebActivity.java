package com.gpslocation.travelin;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.gpslocation.travelin.Helper.Constants;

public class WebActivity extends Activity {

    WebView web;
    String categoryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        categoryType = getIntent().getStringExtra("SearchType");

        web = (WebView) findViewById(R.id.webview);

        if (categoryType.equals("Events")) {
            web.loadUrl(Constants.EVENTS_URL);
        } else if (categoryType.equals("Visiting Places")) {
            web.loadUrl(Constants.VISITING_PLACES_URL);
        }else if (categoryType.equals("Best food")) {
            web.loadUrl(Constants.BEST_FOOD_URL);
        }
    }
}
