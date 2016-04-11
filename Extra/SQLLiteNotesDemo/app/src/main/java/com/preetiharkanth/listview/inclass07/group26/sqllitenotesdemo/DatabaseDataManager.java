package com.preetiharkanth.listview.inclass07.group26.sqllitenotesdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Ajay Wisawe on 3/14/2016.
 */
public class DatabaseDataManager {

    private Context mContext;
    private DataBAseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private NoteDAO noteDAO;

    public  DatabaseDataManager(Context mContext){
        this.mContext = mContext;
        dbOpenHelper = new DataBAseOpenHelper(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        noteDAO = new NoteDAO(db);
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public  NoteDAO getNoteDAO(){
        return this.noteDAO;
    }

    public  long saveNote(Note note){
        return  this.noteDAO.save(note);
    }

    public boolean update(Note note){
        return this.noteDAO.update(note);
    }

    public boolean deleteNote(Note note){
        return this.noteDAO.delete(note);
    }

    public  Note getNote(long id){
        return this.noteDAO.get(id);
    }

    public List<Note> getAllNote(){
        return  this.noteDAO.getAll();
    }
}
