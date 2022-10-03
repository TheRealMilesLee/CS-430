package com.example.activityandfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AboutMe extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        Toast.makeText(this, "This is About Me", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        Intent intentBackToMain = new Intent();
        intentBackToMain.putExtra("HelloFromAboutMe", "Hello From About Me"); // This is a {key, value} pair
        setResult(RESULT_OK, intentBackToMain);
        this.finish();
        super.onBackPressed();
    }
}