package com.truman.edu.hengyi.groupprojectquerymysql;

import static java.lang.Double.parseDouble;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * It retrieves data from the server, and then it stores the data into the
 * database
 */
public class MainActivity extends AppCompatActivity
{
  String url = "http://150.243.198.74:8080/phpquery" + ".php";
  // Declaring the variables that will be used in the program.
  Context mContext = null;
  File TeamMemberData = null;
  String dbPath = null;
  TeamMemberDAO TeamMemberDAO = null;

  ArrayList<TeamMember> TeamMemberRetrievedList = new ArrayList<>();

  /**
   * It retrieves data from the server, and then it stores the data into the
   * database
   * @param savedInstanceState This is a bundle object that is used to save the
   *                           state of the activity.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    // Setting the content view to the activity_main.xml file.
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // This is the code that is used to retrieve the data from the server.
    mContext = this.getApplicationContext();
    RequestQueue requestQueue = Volley.newRequestQueue(mContext);
    final TextView txt = findViewById(R.id.txt);
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
      // This is the code that is used to retrieve the data from the server.
      Request.Method.GET, url, null, new Response.Listener<JSONObject>()
    {
      @Override
      public void onResponse(JSONObject response)
      {
        // display response
        Log.d("Response: ", response.toString());

        // Creating a database file called Team_Member.sqlite and then it is
        //         installing the data into the database.
        dbPath = getCacheDir() + "/Team_Member.sqlite";
        installData(dbPath);
        TeamMemberDAO = new TeamMemberDAO(dbPath);

        // Retrieving the data from the server and then it is storing the data into
        // the database.
        try
        {
          JSONArray data = response.getJSONArray("data");
          for (int index = 0; index < data.length(); index++)
          {
            JSONObject JSONDataReceive = data.getJSONObject(index);
            // These are mapping to column names
            Integer Student_ID = JSONDataReceive.getInt("Student_ID");
            String First_Name = JSONDataReceive.getString("First_Name");
            String Last_Name = JSONDataReceive.getString("Last_Name");
            Double Lat = parseDouble(JSONDataReceive.getString("Lat"));
            Double Long = parseDouble(JSONDataReceive.getString("long_param"));
            TeamMemberDAO.insertOnePersonnel(Student_ID, First_Name, Last_Name,
              Lat, Long);
          }
        } catch (JSONException e)
        {
          e.printStackTrace();
        }
        TeamMemberRetrievedList = TeamMemberDAO.getAllPersonnel();

        // Appending the data to the text view.
        txt.append("\n\nQueryResult\n");
        for (TeamMember person : TeamMemberRetrievedList)
        {
          txt.append(
            person.getID() + " " + person.getFirstName() + " " + person.getLastName() + " " + person.getLat() + " " + person.getLong() + "\n");
        }

        createTable(dbPath);
        displayOurMember(dbPath, txt);

        Toast.makeText(getBaseContext(),
          "Retrieved data {Student_ID, FIRST_NAME, LAST_NAME, LAT, LONG} is as follows: ",
          Toast.LENGTH_LONG).show();
        for (TeamMember person : TeamMemberRetrievedList)
        {
          Toast.makeText(getBaseContext(),
            person.getID() + " " + person.getFirstName() + " " + person.getLastName() + " " + person.getLat() + " " + person.getLong(),
            Toast.LENGTH_SHORT).show();
        }
      }
    }, new Response.ErrorListener()
    {
      /**
       * It prints the error message to the console.
       * @param error The error that occurred
       */
      @Override
      public void onErrorResponse(VolleyError error)
      {
        error.printStackTrace();
      }
    });
    requestQueue.add(jsonObjectRequest);

  }

  /**
   * Read the SQLite DB file from the assets folder and write it to the hard
   * drive
   * @param dbPath The path to the database file.
   */
  public void installData(String dbPath)
  {
    // Creating a new file object.
    TeamMemberData = new File(dbPath);
    // Reading the SQLite DB file from the assets folder and writing it to the hard
    // drive.
    try
    {
      // Install SQLite DB
      // Step1: Read file into buffer
      InputStream isDb = getAssets().open("Team_Member.sqlite");
      int sizeDb = isDb.available();
      byte[] bufferDb = new byte[sizeDb];
      isDb.read(bufferDb);
      isDb.close();
      // Step2: Write into Android hard drive
      FileOutputStream fosDb = new FileOutputStream(TeamMemberData);
      fosDb.write(bufferDb);
      fosDb.close();
    } catch (FileNotFoundException e)
    {
      e.printStackTrace();
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * The function creates a table called OurMember in the database specified by
   * dbPath. The table has three columns: Student_ID, First_Name, and Last_Name.
   * The table is populated with the distinct values of the Student_ID,
   * First_Name, and Last_Name columns from the QueryResult table
   * @param dbPath The path to the database file.
   */
  public void createTable(String dbPath)
  {
    // Opening the database.
    SQLiteDatabase db;
    db = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    String queryString = "";

    // Creating a table called OurMember in the database. The table has three
    // columns: Student_ID, First_Name, and Last_Name.
    queryString = " CREATE TABLE OurMember " + "(Student_ID INTEGER (11)," + " First_Name String (30)," + " Last_Name STRING (30)" + ");";
    db.execSQL(queryString);

    // Inserting the distinct values of the Student_ID, First_Name, and Last_Name
    // columns from the QueryResult table into the OurMember table.
    queryString = "INSERT INTO OurMember " + "SELECT Distinct Student_ID, First_Name, Last_Name " + "FROM QueryResult;";
    db.execSQL(queryString);
  }

  /**
   * "This function displays the contents of the OurMember table in the
   * database." The first thing we do is open the database
   * @param dbPath The path to the database.
   * @param txt    The TextView object that will display the data.
   */
  public void displayOurMember(String dbPath, TextView txt)
  {
    // Opening the database.
    SQLiteDatabase db;
    db = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    String queryString = "";

    // Selecting all the data from the OurMember table and then it is
    // appending it to the text view.
    queryString = "SELECT * FROM OurMember";
    Cursor cursorGet = db.rawQuery(queryString, null);
    int num = cursorGet.getCount();
    txt.append("\n\nOurMember\n");

    // Checking to see if the cursor is null and if the cursor has a count greater
    // than 0.
    // If it does, then it moves to the first row and then it appends the data to
    // the text view.
    if (cursorGet != null && cursorGet.getCount() > 0)
    {
      if (cursorGet.moveToFirst())
      {
        while (!cursorGet.isAfterLast())
        {
          txt.append(cursorGet.getInt(0) + " " + cursorGet.getString(
            1) + " " + cursorGet.getString(2) + "\n");
          cursorGet.moveToNext();
        }
      }
    }
    cursorGet.close();
  }

  /**
   * > The `onDestroy()` function is called when the activity is about to be
   * destroyed
   */
  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    mContext = null;
  }
}
