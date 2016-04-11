package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Ajay Wisawe on 3/19/2016.
 */
public class CitiesTable{

    static final String TABLENAME = "CITIES";
    static final String COLUMN_CITYKEY = "CITYKEY";
    static final String COLUMN_CITYNAME = "CITYNAME";
    static final String COLUMN_STATE = "STATE";

        static public void onCreate(SQLiteDatabase db){
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE " + TABLENAME + " (");
            sb.append(COLUMN_CITYKEY + " integer primary key autoincrement, ");
            sb.append( COLUMN_CITYNAME + " text, ");
            sb.append(COLUMN_STATE + " text); ");

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
            CitiesTable.onCreate(db);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
