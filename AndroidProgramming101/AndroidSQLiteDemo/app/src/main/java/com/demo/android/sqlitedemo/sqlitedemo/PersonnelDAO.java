package com.demo.android.sqlitedemo.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class PersonnelDAO {
    public static final String TAG = "PersonnelDAO";
    public static final String TABLE_NAME = "Personnel";

    public static final String NAME = "NAME";
    public static final String INFO = "JOB";

    // Name of DB
    public static final String DATABASE_NAME = "android_connection_demo.db";

    // DB object
    private SQLiteDatabase db;

    public PersonnelDAO (String dbPath) {
        db = SQLiteDatabase.openOrCreateDatabase(dbPath, null);

        if (db != null) {
            Log.e(TAG, "Got the db instance!");
        } else {
            Log.e(TAG, "db instance is null!");
        }
    }

    public ArrayList<Personnel> getAllPersonnel () {
        ArrayList<Personnel> result = new ArrayList<>();
        String queryString = "";

        queryString = "select * from " + TABLE_NAME;
        Log.e(TAG, "The queryString = " + queryString);
        Cursor cursor = db.rawQuery(queryString, null);

        int num = cursor.getCount();
        Log.e(TAG, "row count = " + num);

        if (cursor!=null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Personnel person = new Personnel(null, null);
                    person.setName(cursor.getString(1));
                    person.setJob(cursor.getString(2));

                    result.add(person);
                    cursor.moveToNext();
                }
            }
        }
        cursor.close();
        return result;
    }
}
