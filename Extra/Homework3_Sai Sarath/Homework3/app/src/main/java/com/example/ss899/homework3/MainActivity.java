package com.example.ss899.homework3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    Button bstart;
    Button bexit;
    final static String baseURL="http://dev.theappsdr.com/apis/spring_2016/hw3/index.php?qid=";
    String qid=null;
    String qtext=null;
    ArrayList<String> qoptions;
    ArrayList<String> qopvalue;
//    String qopvalue=null;
    String imgurl=null;
    Questions qs=null;
    ArrayList<Questions> questionlist=new ArrayList<>();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bstart= (Button) findViewById(R.id.buttonstart);
        bstart.setClickable(false);
        bexit= (Button) findViewById(R.id.buttonexit);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading Questions");
        progressDialog.setCancelable(false);
        new GetQuestions().execute(baseURL+"0");
        new GetQuestions().execute(baseURL+"1");
        new GetQuestions().execute(baseURL+"2");
        new GetQuestions().execute(baseURL+"3");
        new GetQuestions().execute(baseURL+"4");
        new GetQuestions().execute(baseURL+"5");
        new GetQuestions().execute(baseURL+"6");

        bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,QuizActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("parcel",questionlist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private class GetQuestions extends AsyncTask<String, Void , Questions>{

        @Override
        protected void onPostExecute(Questions ques) {

            questionlist.add(ques);
            bstart.setClickable(true);
            Log.d("questionlist",questionlist.size()+"");
            if(questionlist.size()==7){
                progressDialog.dismiss();
            }

        }

        @Override
        protected void onPreExecute() {
//            progressDialog=new ProgressDialog(MainActivity.this);

//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected Questions doInBackground(String... params) {
            BufferedReader reader=null;
            try {
                URL url=new URL(params[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                StringBuilder sb=new StringBuilder();
                String line="";
                reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                while((line=reader.readLine())!=null){
                    sb.append(line);

                }
                String content=sb.toString();
                Log.d("content", content);

                String[] questions=content.split(";");
                Log.d("demo", questions[7] + "");
                qid=questions[0];
                qtext=questions[1];
                qoptions=new ArrayList<>();
                qopvalue=new ArrayList<>();
//                for(int i=2;i<questions.length;i++){
                    switch(questions.length){
                        case 8:
                            qoptions.add(questions[2]);
                            qoptions.add(questions[4]);
                            qoptions.add(questions[6]);
                            qopvalue.add(questions[3]);
                            qopvalue.add(questions[5]);
                            qopvalue.add(questions[7]);
                            break;
                        case 9:
                            qoptions.add(questions[2]);
                            qoptions.add(questions[4]);
                            qoptions.add(questions[6]);
                            qopvalue.add(questions[3]);
                            qopvalue.add(questions[5]);
                            qopvalue.add(questions[7]);
                            imgurl=questions[8];
                            break;
                        case 10:
                            qoptions.add(questions[2]);
                            qoptions.add(questions[4]);
                            qoptions.add(questions[6]);
                            qopvalue.add(questions[3]);
                            qopvalue.add(questions[5]);
                            qopvalue.add(questions[7]);
                            qoptions.add(questions[8]);
                            qopvalue.add(questions[9]);
                            break;
                        case 11:
                            qoptions.add(questions[2]);
                            qoptions.add(questions[4]);
                            qoptions.add(questions[6]);
                            qopvalue.add(questions[3]);
                            qopvalue.add(questions[5]);
                            qopvalue.add(questions[7]);
                            qoptions.add(questions[8]);
                            qopvalue.add(questions[9]);
                            imgurl=questions[10];
                            break;
                        case 12:
                            qoptions.add(questions[2]);
                            qoptions.add(questions[4]);
                            qoptions.add(questions[6]);
                            qopvalue.add(questions[3]);
                            qopvalue.add(questions[5]);
                            qopvalue.add(questions[7]);
                            qoptions.add(questions[8]);
                            qopvalue.add(questions[9]);
                            qoptions.add(questions[10]);
                            qopvalue.add(questions[11]);
                            break;
                    }

                qs=new Questions(Questions.questiontext,Questions.question_options,Questions.question_opvalue,Questions.question_imgurl);
                qs.getQuestiontext().put(qid,qtext);
                qs.getQuestion_options().put(qid,qoptions);
                qs.getQuestion_opvalue().put(qid,qopvalue);
                qs.getQuestion_imgurl().put(qid, imgurl);

//                Log.d("qs",Questions.question_imgurl.get("1")+"");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return qs;
        }
    }
}
