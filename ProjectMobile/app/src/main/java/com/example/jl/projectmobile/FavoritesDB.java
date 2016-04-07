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

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "favoritesdb.db";
    private static final String EVENTS_TABLE_CREATE_SCRIPT = "CREATE TABLE event (_id INT PRIMARY KEY, title TEXT, image INT, description TEXT)";

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

    public void insertEvent(int id, String arg_title, int arg_img, String arg_des) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("_id", id);
            values.put("title", arg_title);
            values.put("image", arg_img);
            values.put("description", arg_des);
            db.insert("event", null, values);
        }
        db.close();
    }

    public void deleteEvent(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("event", "_id=" + id, null);
        db.close();
    }

    public List<Event> retrieveEvent() {
        SQLiteDatabase db = getReadableDatabase();

        List<Event> event_list = new ArrayList<Event>();

        String[] selected_columns = {"_id", "title", "image", "description"};
        Cursor c = db.query("event", selected_columns, null, null, null, null, null, null);

        if (c.getCount() < 1) {
            return  event_list;
        }

        c.moveToFirst();
        do {
            Event anEvent = new Event(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3));
            event_list.add(anEvent);
        } while (c.moveToNext());
        db.close();
        c.close();
        return event_list;
    }
}
