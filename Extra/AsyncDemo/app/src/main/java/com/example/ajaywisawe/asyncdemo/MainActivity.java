package com.example.ajaywisawe.asyncdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DoWork().execute();
      //
    }


    // three parameters 1. type of input parmaters u ll pass to async, 2 what parameter u ll use to notify the progress
    //3 result. This method runs in child thread
    class DoWork extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            for (int i=0;i<100;i++){
                for (int j=0;j<1000000;j++){

                }
                //can take multiple parameters
                publishProgress(i+1);
            }
            return null;
        }
        // these methods run in main thread
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            progressDialog.dismiss();
        }

        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Computing Progress");
            progressDialog.show();
        }

        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
    }
}
