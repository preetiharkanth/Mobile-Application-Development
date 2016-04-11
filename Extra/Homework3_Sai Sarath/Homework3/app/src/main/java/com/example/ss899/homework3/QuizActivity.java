package com.example.ss899.homework3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

public class QuizActivity extends AppCompatActivity {
    int index=0;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
//        Questions qs=new Questions(Questions.questiontext,Questions.question_options,Questions.question_opvalue,Questions.question_imgurl);
        ArrayList<Questions> questionlist=new ArrayList<>();
        questionlist=getIntent().getParcelableArrayListExtra("parcel");

        Questions q=questionlist.get(0);
        Log.d("quiz", questionlist.get(0).getQuestion_imgurl().get("0") + "");
        LinearLayout lradio=new LinearLayout(this);
        RadioGroup rg= (RadioGroup) findViewById(R.id.radiogroup);
//        RadioGroup.LayoutParams rprms;
        TextView textViewqid= (TextView) findViewById(R.id.textViewqid);
        textViewqid.setText("Q0");
        new GetImage().execute(q.getQuestion_imgurl().get("0"));


        for(int i=0;i<q.getQuestion_options().get("0").size();i++){
            RadioButton rdbtn = new RadioButton(this);
            Log.d("inside for",i+" "+q.getQuestion_options().get("0").get(i)+"");
            rdbtn.setText(q.getQuestion_options().get("0").get(i)+"");
            rdbtn.setId(i);
            rg.addView(rdbtn);

        }

    }
    private class GetImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView= (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap image=null;
                    try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
                        image= BitmapFactory.decodeStream(con.getInputStream());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return image;
        }
    }
}
