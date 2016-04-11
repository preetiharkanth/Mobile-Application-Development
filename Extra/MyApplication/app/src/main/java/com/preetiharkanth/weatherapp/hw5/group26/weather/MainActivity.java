package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<City> adapter;
    List<City> cities = new ArrayList<City>();
    TextView defaultTextView;
    ListView listView;

    boolean isDefaultTextViewShowing = true;
    boolean isListViewShowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_add_city:
                Intent addCityActivityIntent = new Intent(MainActivity.this,AddCityActivity.class);
                startActivityForResult(addCityActivityIntent,Constants.ADDCITY_ACTIVITY);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_city,menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constants.ADDCITY_ACTIVITY:
                if(resultCode == Activity.RESULT_OK && data!=null){
                    final City city = (City) data.getSerializableExtra(Constants.CITY);
                    if(city!=null){
                        if(adapter == null && isDefaultTextViewShowing){
                            cities.add(city);
                        }
                    }

                    final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.mainLayout);
                    if(cities.size() >=1){
                        if(isDefaultTextViewShowing){
                            relativeLayout.removeView(defaultTextView);

                            listView = new ListView(MainActivity.this);
                            listView.setBackgroundColor(Color.LTGRAY);
                            adapter = new ArrayAdapter<City>(MainActivity.this, android.R.layout.simple_list_item_1);
                            listView.setAdapter(adapter);
                            adapter.setNotifyOnChange(true);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent hourlyDailyActivityintent = new Intent(MainActivity.this, HourlyDataActivity.class);
                                    hourlyDailyActivityintent.putExtra(Constants.CITY, cities.get(position));
                                    startActivity(hourlyDailyActivityintent);
                                }
                            });

                            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                    if(adapter != null){
                                        //dm.delete(cities.get(position).getCityKey())
                                        adapter.remove(cities.get(position));

                                        if(cities.size() < 1){
                                            if(isListViewShowing){
                                                relativeLayout.removeView(listView);
                                                adapter = null;

                                                relativeLayout.addView(defaultTextView);
                                                isDefaultTextViewShowing = true;
                                                isListViewShowing = false;
                                            }
                                        }

                                    }
                                    return false;
                                }
                            });

                            relativeLayout.addView(listView);
                            isDefaultTextViewShowing = false;
                            isListViewShowing = true;

                        }
                        else if(isListViewShowing){
                            if(adapter != null ){
                                //dm.save(city);
                                adapter.add(city);
                            }
                        }
                    }else {
                        if (isListViewShowing){
                            relativeLayout.removeView(listView);
                            relativeLayout.addView(defaultTextView);
                            isDefaultTextViewShowing = true;
                            isListViewShowing = false;
                        }
                    }
                    break;
        }
    }
}
}
