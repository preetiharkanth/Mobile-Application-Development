package com.preetiharkanth.inclass06.group26.inclass06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsActivity extends AppCompatActivity {

    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if(getIntent().getExtras() != null){
            url = getIntent().getExtras().getString("URL");
           // new GetNewsAsyncTask().execute(url);
        }

        // if ()




    }
}
