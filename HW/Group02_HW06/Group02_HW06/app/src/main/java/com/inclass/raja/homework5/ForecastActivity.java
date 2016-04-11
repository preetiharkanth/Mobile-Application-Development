package com.inclass.raja.homework5;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ForecastActivity extends AppCompatActivity implements GetForecast.DisplayForecast {

    ListView listView;
    static ProgressDialog progressDialog;
    String cityName, stateName;
    String url;
    TextView textViewForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        textViewForecast = (TextView) findViewById(R.id.textView_ForecastActivity);
        cityName = getIntent().getExtras().getString("city");
        stateName = getIntent().getExtras().getString("state");
        textViewForecast.setText("Current Location: " + cityName + "," + stateName);
        url = "http://api.wunderground.com/api/37af142f823f9ab7/forecast10day/q/" + stateName + "/" + cityName + ".xml";
        new GetForecast(this).execute(url);
        listView = (ListView) findViewById(R.id.listView_forecast);


    }


    @Override
    public void forecastWeather(ArrayList<Forecast> forecastList) {

        ForecastAdapter forecastAdapter = new ForecastAdapter(ForecastActivity.this, R.layout.forecast_adapter, forecastList);
        listView.setAdapter(forecastAdapter);
        forecastAdapter.setNotifyOnChange(true);

    }
}
