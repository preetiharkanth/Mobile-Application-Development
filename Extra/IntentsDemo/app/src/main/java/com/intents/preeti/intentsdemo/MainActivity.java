package com.intents.preeti.intentsdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final static String NAME_KEY = "NAME";
    final static String AGE_KEY = "AGE";
    final static String USER_KEY = "USER";
    final static String PERSON_KEY = "PERSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.unccButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://wwww.uncc.edu"));

                //  Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // intent.putExtra(NAME_KEY,"Preeti Harkanth");
                //intent.putExtra(AGE_KEY,(double) 26.0);

                //User user = new User("Sunkashi", 24.0);
                //Person person = new Person("Ankita", "Mumbai,MH",26);

                // intent.putExtra(PERSON_KEY,person);
                //intent.putExtra(USER_KEY,user);
                //Intent intent = new Intent("com.intents.preeti.intentsdemo.intent.action.VIEW");
                //intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
            }
        });

        findViewById(R.id.callButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8035049227"));
                startActivity(intent);
            }
        });
    }
}
