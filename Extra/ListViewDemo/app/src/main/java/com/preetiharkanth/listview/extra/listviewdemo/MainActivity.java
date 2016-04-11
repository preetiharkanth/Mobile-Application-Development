package com.preetiharkanth.listview.extra.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //String[] colors = {"Red","Blue","Green","White","Black","Orange","Yellow"};
    ArrayList<Color> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors = new ArrayList<Color>();
        colors.add(new Color("Blue","#000000"));
        colors.add(new Color("Green","#0000FF"));
        colors.add(new Color("Red","#654321"));
        colors.add(new Color("White","#006600"));
        colors.add(new Color("Black","#FF6600"));
        colors.add(new Color("Orange","#FF0000"));
        colors.add(new Color("Grey","#BBBBBB"));
        colors.add(new Color("Pink","#FFC0CB"));


        ListView listView = (ListView) findViewById(R.id.listView);
        /*ArrayAdapter<Color> adapter = new ArrayAdapter<Color>(this,android.R.layout.simple_list_item_1,colors);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);*/
        /*adapter.add(new Color("Purple"));
        adapter.remove(colors.get(0));
        adapter.insert(new Color("Brown"),0);
        *///adapter.notifyDataSetChanged();

        ColorAdaptor colorAdaptor = new ColorAdaptor(this,R.layout.row_item_layout,colors);
        listView.setAdapter(colorAdaptor);
        colorAdaptor.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", "position is " + position + ", Value is " + colors.get(position).toString());
            }
        });

    }
}
