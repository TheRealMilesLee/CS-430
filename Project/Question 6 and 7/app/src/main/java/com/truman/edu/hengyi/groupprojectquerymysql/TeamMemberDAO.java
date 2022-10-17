/**
 * It's a class that contains methods to insert and query data from the
 * database
 */
package com.truman.edu.hengyi.groupprojectquerymysql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class TeamMemberDAO
{
  // Defining the name of the table and the name of the columns.
  public static final String TAG = "TeamMemberDAO";
  public static final String TABLE_NAME = "QueryResult";
  public static final String NAME = "NAME";
  public static final String INFO = "JOB";

  // Name of DB
  public static final String DATABASE_NAME = "Team_Member.sqlite";

  // DB object
  private final SQLiteDatabase db;

  // Creating a new instance of the database.
  public TeamMemberDAO(String dbPath)
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
   * This function inserts a new row into the database
   * @param Student_ID The student's ID number
   * @param First_Name The first name of the person
   * @param Last_Name  The last name of the person
   * @param Lat        Latitude
   * @param Long       The longitude of the location
   */
  public void insertOnePersonnel(Integer Student_ID, String First_Name,
                                 String Last_Name, Double Lat, Double Long)
  {
    String queryString = "";
    queryString = "INSERT INTO " + TABLE_NAME + " (Student_ID,First_Name,Last_Name,Lat,Long)" + " VALUES " + "(" + Student_ID + ", '" + First_Name + "', '" + Last_Name + "', " + Lat + ", " + Long + ");";

    Log.e(TAG, "The queryString = " + queryString);
    db.execSQL(queryString);
  }

  /**
   * It creates an empty ArrayList of TeamMember objects, then creates a query
   * string, then executes the query, then loops through the results, then
   * creates a TeamMember object for each row, then adds the TeamMember object
   * to the ArrayList, then returns the ArrayList
   * @return An ArrayList of TeamMember objects.
   */
  public ArrayList<TeamMember> getAllPersonnel()
  {
    // It creates an empty ArrayList of TeamMember objects, then creates a query
    //     string, then executes the query, then loops through the results, then
    // creates
    //     a TeamMember object for each row, then adds the TeamMember object to the
    //     ArrayList, then returns the ArrayList
    ArrayList<TeamMember> result = new ArrayList<>();
    String queryString = "";

    // Selecting all the data from the table.
    queryString = "SELECT * FROM " + TABLE_NAME;
    Log.e(TAG, "The queryString = " + queryString);
    Cursor cursorGet = db.rawQuery(queryString, null);

    int num = cursorGet.getCount();
    Log.e(TAG, "row count = " + num);

    // Checking if the cursor is not null and if the cursor has more than 0 rows.
    // If it does, it will move to the first row and then loop through the rows
    // until it reaches the last row. It will then create a new TeamMember object
    // and set the values of the object to the values of the row. It will then add
    // the object to the ArrayList and move to the next row.
    if (cursorGet != null && cursorGet.getCount() > 0)
    {
      if (cursorGet.moveToFirst())
      {
        while (!cursorGet.isAfterLast())
        {
          // Creating a new TeamMember object and setting the values of the object
          // to the values of the row.
          TeamMember person = new TeamMember(0, null, null, 0, 0);
          person.setID(cursorGet.getInt(0));
          person.setFirstName(cursorGet.getString(1));
          person.setLastName(cursorGet.getString(2));
          person.setLat(cursorGet.getDouble(3));
          person.setLong(cursorGet.getDouble(4));
          // Adding the person object to the ArrayList and then moving to the next
          // row.
          result.add(person);
          cursorGet.moveToNext();
        }
      }
    }
    cursorGet.close();
    return result;
  }
}
