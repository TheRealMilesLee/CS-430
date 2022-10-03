package com.example.activityandfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentManager manager = getFragmentManager();
    MySettings mySettings = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {

            if (resultCode == Activity.RESULT_OK) {
                Bundle bdle = data.getExtras();
                String dataFromAboutMe = bdle.getString("HelloFromAboutMe");

                Toast.makeText(this, "This is a send back message from AboutMe Activity: ", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "AboutMe said: " + dataFromAboutMe, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            // FragmentManager manager = getFragmentManager();
            android.app.FragmentTransaction transaction = manager.beginTransaction();
            mySettings = new MySettings(); // New a PreferenceFragment

            transaction.replace(android.R.id.content, mySettings);
            transaction.addToBackStack("SettingsTag");
            transaction.commit(); // Send the transaction
            return true;
        }

        if (id == R.id.action_about_me) {

            // startActivity(new Intent(this, AboutMe.class));
            startActivityForResult(new Intent(this, AboutMe.class),0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}