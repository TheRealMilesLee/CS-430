package com.demo.android.sqlitedemo.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    File personnelData = null;
    String dbPath = null;
    PersonnelDAO mPersonnelDAO = null;
    ArrayList<Personnel> PersonnelRetrievedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbPath = getCacheDir() + "/android_connection_demo.sqlite";
        installData(dbPath);

        // After the installation of DB, we can use that!
        mPersonnelDAO = new PersonnelDAO(dbPath);
        PersonnelRetrievedList = mPersonnelDAO.getAllPersonnel();

        Toast.makeText(this, "Retrieved data {NAME, JOB} is as follows: ", Toast.LENGTH_LONG).show();
        for (Personnel person : PersonnelRetrievedList) {
            Toast.makeText(this, person.getName() + " " + person.getJob(), Toast.LENGTH_SHORT).show();
        }
    }

    public void installData(String dbPath) {
        personnelData = new File(dbPath);

        try {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
