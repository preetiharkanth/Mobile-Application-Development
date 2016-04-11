package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCityActivity extends AppCompatActivity implements ValidateCityStateAsyncTask.IValidateCityStateAsyncTask {

    boolean isValidated = false;
    ProgressDialog progressDialog;

    EditText stateNameEditText;
    EditText cityNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

         cityNameEditText = (EditText) findViewById(R.id.cityName);
         stateNameEditText = (EditText) findViewById(R.id.stateName);

        findViewById(R.id.saveCityButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityNameEditText.getText().toString();
                String stateName = stateNameEditText.getText().toString();

                String url = Constants.LOCATION_API_ENDPOINT + "place=" + cityName + "," + stateName + "&apiKey=d622bd3a3b5c8b7b";
                new ValidateCityStateAsyncTask(AddCityActivity.this).execute(url);

            }
        });


    }

    @Override
    public void setValidated(boolean validated) {
        this.isValidated = validated;

        if(isValidated){
            City city = new City(cityNameEditText.getText().toString(),stateNameEditText.getText().toString());
            Intent resultIntent = getIntent();
            resultIntent.putExtra(Constants.CITY,city);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();


        }
    }

    @Override
    public void showProcessing() {
        progressDialog = new ProgressDialog(AddCityActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Validating input..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

    }

    @Override
    public void finishProcessing() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }

    }
}
