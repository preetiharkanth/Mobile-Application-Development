package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

/**
 * Created by Ajay Wisawe on 3/19/2016.
 */
public class DatabaseManger {

    private Context mContext;
    private SQLiteDatabase db;
    private DatabaseOpenHelper dbOpenHelper;
    private CityDao cityDao;
    private NoteDao noteDao;
    private String dbName;

    public DatabaseManger(Context mContext,String dbName){
       Log.d("DatabaseManger", "databasedatamanager constructor");
        this.mContext = mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext,this.dbName);
        db = dbOpenHelper.getWritableDatabase();
        cityDao = new CityDao(db);
        noteDao = new NoteDao(db);
        Log.d("DatabseManger", "data manager constructor end");
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public long saveCity(City city){
        return this.cityDao.save(city);
    }

    public long saveNote(Note note){
        return this.noteDao.save(note);
    }

    public boolean updateCity(City city){
        return this.cityDao.update(city);
    }

    public boolean updateNote(Note note){
        return this.noteDao.update(note);
    }

    public boolean deleteCity(City city){
        return this.cityDao.delete(city);
    }

    public boolean deleteNote(Note note){
        return this.noteDao.delete(note);
    }

    public City getCity(long cityKey){
        return this.cityDao.get(cityKey);
    }

    public Note getNote(long cityKey){
        return this.noteDao.get(cityKey);
    }

    public List<City> getAllCities(){
        return this.cityDao.getAll();
    }

    public List<Note> getAllNotes(){
        return this.noteDao.getAll();
    }

}
