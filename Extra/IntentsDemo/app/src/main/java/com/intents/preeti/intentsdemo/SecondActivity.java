package com.intents.preeti.intentsdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


       if(getIntent().getExtras() != null)
       {
           String name = getIntent().getExtras().getString(MainActivity.NAME_KEY);
           Double age = getIntent().getExtras().getDouble(MainActivity.AGE_KEY, 25);
           User u = (User)getIntent().getExtras().getSerializable(MainActivity.USER_KEY);
           Person p = (Person)getIntent().getExtras().getParcelable(MainActivity.PERSON_KEY);
           //Toast.makeText(this,name+","+age,Toast.LENGTH_LONG).show();
           //Toast.makeText(this,u.toString(),Toast.LENGTH_LONG).show();
           Toast.makeText(this,p.toString(),Toast.LENGTH_LONG).show();

       } else {
           Log.d("Error","Null Intent");
       }
        /*findViewById(R.id.goBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

}
