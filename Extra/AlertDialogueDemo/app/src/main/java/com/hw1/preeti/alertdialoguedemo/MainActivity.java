package com.hw1.preeti.alertdialoguedemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CharSequence[] items = {"Red" , "Blue", "Green", "Yellow"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.setCancelable(false);

       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick a color")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Log.d("demo", items[which]+ "is " + isChecked);
                    }
                })*/
               // .setMessage("Are you sure")
               /* .setItems(items, new DialogInterface.OnClickListener() {
                            //   .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("demo", "Selected" + items[which]);
                            }
                        }
                )*/
                /*.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("demo", "Selected" + items[which]);

                    }
                })*/
              /*  .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Demo", "Clicked Ok");
                            }
                        }
                )
                .setCancelable(false);*/
               /* .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Demo", "Clicked Cancel");
                    }
                });*/

       // final AlertDialog alert = builder.create();

        findViewById(R.id.buttonAlert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                //progress.dismiss();

            }
        });

    }
}
