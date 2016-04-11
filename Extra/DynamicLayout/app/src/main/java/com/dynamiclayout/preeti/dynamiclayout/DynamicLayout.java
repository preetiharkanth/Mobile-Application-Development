package com.dynamiclayout.preeti.dynamiclayout;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DynamicLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dynamic_layout);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        setContentView(relativeLayout);

        TextView textView = new TextView(this);
        textView.setText(R.string.helloWorld);
        textView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setId(100);
        relativeLayout.addView(textView);

        
        /* <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/clickButton"
        android:id="@+id/clickButton"
        android:layout_below="@+id/hello_world"
        android:layout_centerHorizontal="true" /> */

        Button btn = new Button(this);
        btn.setText(R.string.clickButton);
        RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT );
        buttonLayoutParams.addRule(RelativeLayout.BELOW,textView.getId());
        btn.setLayoutParams(buttonLayoutParams);
        relativeLayout.addView(btn);
    }
}
