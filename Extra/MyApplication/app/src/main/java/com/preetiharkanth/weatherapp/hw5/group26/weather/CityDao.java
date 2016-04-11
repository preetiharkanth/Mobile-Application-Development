package com.preetiharkanth.weatherapp.hw5.group26.weather;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajay Wisawe on 3/19/2016.
 */
public class CityDao {

    private SQLiteDatabase db;

    public CityDao (SQLiteDatabase db){
        this.db = db;
    }

    public long save(City city){
        ContentValues values = new ContentValues();
        values.put(CitiesTable.COLUMN_CITYNAME,city.getCityName());
        values.put(CitiesTable.COLUMN_STATE,city.getState());
        return  db.insert(CitiesTable.TABLENAME,null,values);
    }

    public boolean update(City city){
        ContentValues values = new ContentValues();
        values.put(CitiesTable.COLUMN_CITYNAME,city.getCityName());
        values.put(CitiesTable.COLUMN_STATE,city.getState());
        return db.update(CitiesTable.TABLENAME,values,CitiesTable.COLUMN_CITYNAME + "=?", new String[]{city.getCityName()+""}) > 0;
    }

    public  boolean delete(City city){
        return db.delete(CitiesTable.TABLENAME, CitiesTable.COLUMN_CITYNAME + "=?", new String[]{city.getCityName() + ""}) >0 ;
    }

    public City get(long cityKey) {
        City city = null;
        Cursor c = db.query(true, CitiesTable.TABLENAME, new String[]{CitiesTable.COLUMN_CITYKEY, CitiesTable.COLUMN_CITYNAME, CitiesTable.COLUMN_STATE},
                CitiesTable.COLUMN_CITYKEY + "=?", new String[]{cityKey + ""}, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            city = buildCityfromCursor(c);
            if (!c.isClosed())
                c.close();
        }
        return city;

    }

    public List<City> getAll() {
        List<City> cities = new ArrayList<City>();
        Cursor c = db.query(true, CitiesTable.TABLENAME, new String[]{CitiesTable.COLUMN_CITYKEY, CitiesTable.COLUMN_CITYNAME, CitiesTable.COLUMN_STATE},
                null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                City city = buildCityfromCursor(c);
                if (city != null)
                    cities.add(city);
            } while (c.moveToNext());
        }
        return cities;
    }

    public boolean deleteAll() {
        return db.delete(CitiesTable.TABLENAME, null, null) > 0;
    }


    private City buildCityfromCursor(Cursor c) {
        City city = null;
        if (c != null) {
            city = new City();
            city.setCityKey(c.getLong(0));
            city.setCityName(c.getString(1));
            city.setState(c.getString(2));
        }
        return city;
    }
}
