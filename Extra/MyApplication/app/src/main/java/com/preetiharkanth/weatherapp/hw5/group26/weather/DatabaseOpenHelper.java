package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ajay Wisawe on 3/19/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper{
    final static int DB_VERSION = 1;
    String DB_NAME = "weatherdb";

    public DatabaseOpenHelper(Context context,String name ) {
        super(context, name, null, DB_VERSION);
        this.DB_NAME = name;

    }

    @Override
       public void onCreate(SQLiteDatabase db) {
        //if (DB_NAME.equals("notes")) {
            NotesTable.onCreate(db);
        //} else if (DB_NAME.equals("cities")) {
            CitiesTable.onCreate(db);
        //}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (DB_NAME.equals("notes")) {
            NotesTable.onUpdate(db,oldVersion,newVersion);
        } else if (DB_NAME.equals("cities")) {
            CitiesTable.onUpdate(db,oldVersion,newVersion);
        }
    }
}
