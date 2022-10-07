/**
 * "Read the SQLite database file from the assets folder and write it to the hard
 * drive."
 * The first line of the function creates a File object that points to the database
 * file. The next line is a try block that contains the code that reads the
 * database file from the assets folder and writes it to the hard drive
 */
package com.example.sqlite_insert;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.io.*;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity
{
  File personnelData = null;
  String dbPath = null;
  PersonnelDAO mPersonnelDAO = null;
  ArrayList<Personnel> PersonnelRetrievedList = new ArrayList<>();
  /**
   * We create a database file in the cache directory of the application, and then
   * we install the database file with the data we want to use
   * @param savedInstanceState A Bundle object containing the activity's previously
   * saved state. If the activity has never existed before, the value of the Bundle
   * object is null.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    // Setting up the database file and installing the data.
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    dbPath = getCacheDir() + "/android_connection_demo.sqlite";
    installData(dbPath);

    // After the installation of DB, we can use that!
    mPersonnelDAO = new PersonnelDAO(dbPath);

    // Inserting a new row into the database and then retrieving all the rows from
    // the database.
    mPersonnelDAO.insertOnePersonnel();
    PersonnelRetrievedList = mPersonnelDAO.getAllPersonnel();

    // Printing out the data that was retrieved from the database.
    Toast.makeText(this, "Retrieved data {ID, NAME, JOB} is as follows: ",
      Toast.LENGTH_LONG).show();
    for (Personnel person : PersonnelRetrievedList)
    {
      Toast.makeText(this,
        person.getID() + " " + person.getName() + " " + person.getJob(),
        Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * "Read the SQLite database file from the assets folder and write it to the hard
   * drive."
   * The first line of the function creates a File object that points to the
   * database file. The next line is a try block that contains the code that reads
   * the database file from the assets folder and writes it to the hard drive
   * @param dbPath The path to the database file.
   */
  public void installData(String dbPath)
  {
    personnelData = new File(dbPath);
    try
    {
      // Install SQLite DB
      // Step1: Read file into buffer
      InputStream isDb = getAssets().open("android_connection_demo.sqlite");
      int sizeDb = isDb.available();
      byte[] bufferDb = new byte[sizeDb];
      isDb.read(bufferDb);
      isDb.close();
      // Step2: Write into Android hard drive
      FileOutputStream fosDb = new FileOutputStream(personnelData);
      fosDb.write(bufferDb);
      fosDb.close();
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}