package com.preetiharkanth.weatherapp.hw5.group26.weather;

/**
 * Created by Ajay Wisawe on 3/19/2016.
 */
public class Note {
    long cityKey;
    String date;
    String note;

    public Note(long cityKey, String date, String note) {
        this.cityKey = cityKey;
        this.date = date;
        this.note = note;
    }

    public Note(){

    }

    public long getCityKey() {
        return cityKey;
    }

    public void setCityKey(long cityKey) {
        this.cityKey = cityKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
