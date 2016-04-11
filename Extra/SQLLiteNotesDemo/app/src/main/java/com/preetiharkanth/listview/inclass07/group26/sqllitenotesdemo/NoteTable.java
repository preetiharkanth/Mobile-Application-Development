package com.preetiharkanth.listview.inclass07.group26.sqllitenotesdemo;

import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Ajay Wisawe on 3/13/2016.
 */
public class NoteTable {

    static  final String TABLENAME = "notes";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_SUBJECT = "subject";
    static final String COLUMN_TEXT = "text";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+ TABLENAME + "(");
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_SUBJECT + " text not null, ");
        sb.append(COLUMN_TEXT + " text not null );");

    try{
        db.execSQL(sb.toString());
    }catch (android.database.SQLException ex){
        ex.printStackTrace();
    }


    }

    static  public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        NoteTable.onCreate(db);
    }
}
