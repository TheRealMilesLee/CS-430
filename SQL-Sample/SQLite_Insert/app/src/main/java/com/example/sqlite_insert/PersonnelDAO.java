/**
 * It creates an empty ArrayList of Personnel objects, then it creates a query
 * string, then it executes the query string, then it loops through the results and
 * adds each result to the ArrayList, then it returns the ArrayList
 */
package com.example.sqlite_insert;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class PersonnelDAO
{
  public static final String TAG = "PersonnelDAO";
  public static final String TABLE_NAME = "Personnel";
  public static final String NAME = "NAME";
  public static final String INFO = "JOB";
  // Name of DB
  public static final String DATABASE_NAME = "android_connection_demo.db";
  // DB object
  private SQLiteDatabase db;
  // Creating a new instance of the database.
  public PersonnelDAO(String dbPath)
  {
    db = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    if (db != null)
    {
      Log.e(TAG, "Got the db instance!");
    }
    else
    {
      Log.e(TAG, "db instance is null!");
    }
  }

  /**
   * > The function inserts a new row into the table
   */
  public void insertOnePersonnel()
  {
    String queryString = "";
    queryString = "insert into " + TABLE_NAME + " (ID, NAME, JOB) VALUES ('3', 'Lee', 'student')";
    db.execSQL(queryString);
  }

  /**
   * It creates an empty ArrayList of Personnel objects, then it creates a query
   * string, then it executes the query string, then it loops through the results
   * and adds each result to the ArrayList, then it returns the ArrayList
   * @return An ArrayList of Personnel objects.
   */
  public ArrayList<Personnel> getAllPersonnel()
  {
    ArrayList<Personnel> result = new ArrayList<>();
    String queryString = "";
    queryString = "select * from " + TABLE_NAME;
    Cursor cursorGet = db.rawQuery(queryString, null);
    int num = cursorGet.getCount();
    if (cursorGet.getCount() > 0)
    {
      if (cursorGet.moveToFirst())
      {
        while (!cursorGet.isAfterLast())
        {
          Personnel person = new Personnel(0, null, null);
          person.setID(cursorGet.getInt(0));
          person.setName(cursorGet.getString(1));
          person.setJob(cursorGet.getString(2));
          result.add(person);
          cursorGet.moveToNext();
        }
      }
    }
    cursorGet.close();
    return result;
  }
}
