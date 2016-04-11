package com.example.ss899.homework3;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ss899 on 18-02-2016.
 */
public class Questions implements Parcelable {

    static HashMap<String, String> questiontext=new HashMap<>();
    static HashMap<String, ArrayList<String>> question_options=new HashMap<>();
    static HashMap<String, ArrayList<String>> question_opvalue=new HashMap<>();
    static HashMap<String, String> question_imgurl=new HashMap<>();
    public Questions(HashMap<String,String> questiontext,HashMap<String,ArrayList<String>> question_options,HashMap<String,ArrayList<String>> question_opvalue,HashMap<String,String> question_imgurl){
        this.questiontext=questiontext;
        this.question_options=question_options;
        this.question_opvalue=question_opvalue;
        this.question_imgurl=question_imgurl;
    }

    public HashMap<String, ArrayList<String>> getQuestion_options() {
        return question_options;
    }

    public void setQuestion_options(HashMap<String, ArrayList<String>> question_options) {
        this.question_options = question_options;
    }

    public HashMap<String, String> getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(HashMap<String, String> questiontext) {
        this.questiontext = questiontext;
    }

    public HashMap<String, ArrayList<String>> getQuestion_opvalue() {
        return question_opvalue;
    }

    public void setQuestion_opvalue(HashMap<String, ArrayList<String>> question_opvalue) {
        this.question_opvalue = question_opvalue;
    }

    public HashMap<String, String> getQuestion_imgurl() {
        return question_imgurl;
    }

    public void setQuestion_imgurl(HashMap<String, String> question_imgurl) {
        this.question_imgurl = question_imgurl;
    }



    protected Questions(Parcel in) {
        this.questiontext=in.readHashMap(ClassLoader.getSystemClassLoader());
        this.question_options=in.readHashMap(ClassLoader.getSystemClassLoader());
        this.question_opvalue=in.readHashMap(ClassLoader.getSystemClassLoader());
        this.question_imgurl=in.readHashMap(ClassLoader.getSystemClassLoader());

    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(questiontext);
        dest.writeMap(question_options);
        dest.writeMap(question_opvalue);
        dest.writeMap(question_imgurl);
    }
}
