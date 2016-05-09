package com.example.jl.projectmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JL on 03/04/16.
 */
public class FavoritesDB extends SQLiteOpenHelper {

    private static final int DB_VERSION = 4;
    private static final String DB_NAME = "favoritesdb.db";
    private static final String EVENTS_TABLE_CREATE_SCRIPT = "CREATE TABLE event (_id DOUBLE PRIMARY KEY, title TEXT, description TEXT, date TEXT, place TEXT, image INT)";

    public FavoritesDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EVENTS_TABLE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS event");
        onCreate(db);
    }

    public void insertEvent(double id, String arg_title, String arg_des, String arg_dat, String arg_pla, int arg_img) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("_id", id);
            values.put("title", arg_title);
            values.put("description", arg_des);
            values.put("date", arg_dat);
            values.put("place", arg_pla);
            values.put("image", arg_img);
            db.insert("event", null, values);
        }
        db.close();
    }

    public void deleteEvent(double id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("event", "_id=" + id, null);
        db.close();
    }

    public List<Event> retrieveEvent() {
        SQLiteDatabase db = getReadableDatabase();

        List<Event> event_list = new ArrayList<Event>();

        String[] selected_columns = {"_id", "title", "description, date, place, image"};
        Cursor c = db.query("event", selected_columns, null, null, null, null, null, null);

        if (c.getCount() < 1) {
            return  event_list;
        }

        c.moveToFirst();
        do {
            Event anEvent = new Event(c.getDouble(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getInt(5));
            event_list.add(anEvent);
        } while (c.moveToNext());
        db.close();
        c.close();
        return event_list;
    }
}
