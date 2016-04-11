package com.preetiharkanth.firebase.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Button mButtonFoggy = (Button) findViewById(R.id.foggyButton);
        Button mButtonSunny = (Button) findViewById(R.id.sunnyButton);
        TextView condition = (TextView) findViewById(R.id.conditionTextView);
    }

    public boolean onCreateOptionsMenu(Menu menu){

        return true;
    }


}
