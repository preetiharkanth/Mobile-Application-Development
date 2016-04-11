package com.preetiharkanth.extra.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetPersonsAsync().execute("http://liisp.uncc.edu/~mshehab/api/json/persons-v1.json");
        //new GetPersonsAsync().execute("http://liisp.uncc.edu/~mshehab/api/xml/persons.xml");
    }
}
