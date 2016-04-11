package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajay Wisawe on 3/19/2016.
 */
public class NoteDao {

    private SQLiteDatabase db;

    public NoteDao(SQLiteDatabase db){
        this.db = db;
    }

    public long save(Note note){
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_DATE,note.getDate());
        values.put(NotesTable.COLUMN_NOTE,note.getNote());
        return  db.insert(NotesTable.TABLENAME,null,values);
    }

    public boolean update(Note note){
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_DATE,note.getDate());
        values.put(NotesTable.COLUMN_NOTE, note.getNote());
        return db.update(CitiesTable.TABLENAME,values,NotesTable.COLUMN_CITYKEY + "=?", new String[]{note.getCityKey() +""}) > 0;
    }

    public  boolean delete(Note note){
        return db.delete(NotesTable.TABLENAME, NotesTable.COLUMN_CITYKEY + "=?", new String[]{note.getCityKey() + ""}) >0 ;
    }

    public Note get(long cityKey) {
        Note note = null;
        Cursor c = db.query(true, NotesTable.TABLENAME, new String[]{NotesTable.COLUMN_CITYKEY, NotesTable.COLUMN_DATE, NotesTable.COLUMN_NOTE},
                NotesTable.COLUMN_CITYKEY + "=?", new String[]{cityKey + ""}, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            note = buildNotefromCursor(c);
            if (!c.isClosed())
                c.close();
        }
        return note;

    }

    public List<Note> getAll() {
        List<Note> notes = new ArrayList<Note>();
        Cursor c = db.query(true, CitiesTable.TABLENAME, new String[]{NotesTable.COLUMN_CITYKEY, NotesTable.COLUMN_DATE, NotesTable.COLUMN_NOTE},
                null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                Note note = buildNotefromCursor(c);
                if (note != null)
                    notes.add(note);
            } while (c.moveToNext());
        }
        return notes;
    }


    public boolean deleteAll() {
        return db.delete(NotesTable.TABLENAME, null, null) > 0;
    }

    private Note buildNotefromCursor(Cursor c) {
        Note note = null;
        if (c != null) {
            note = new Note();
            note.setCityKey(c.getLong(0));
            note.setDate(c.getString(1));
            note.setNote(c.getString(2));
        }
        return note;
    }

}
