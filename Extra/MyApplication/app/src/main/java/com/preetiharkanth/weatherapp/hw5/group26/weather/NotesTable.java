package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Ajay Wisawe on 3/19/2016.
 */
public class NotesTable {

    static final String TABLENAME = "NOTES";
    static final String COLUMN_CITYKEY = "CITYKEY";
    static final String COLUMN_DATE = "DATE";
    static final String COLUMN_NOTE = "NOTE";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + " (");
        sb.append(COLUMN_CITYKEY + " integer primary key autoincrement, ");
        sb.append(COLUMN_DATE + " text, ");
        sb.append(COLUMN_NOTE + " text); ");

        String createString = sb.toString();
        Log.d("test3", createString);
        try {
            db.execSQL(createString);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    static public void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion){
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
            NotesTable.onCreate(db);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
