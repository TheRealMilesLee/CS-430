package com.example.activityjumps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Toast.makeText(NextActivity.this, "This is NextActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        Intent intent = getIntent();
        intent.putExtra("HelloFromNextActivity", "Hello From Next Activity");

        NextActivity.this.setResult(Activity.RESULT_OK, intent);
        NextActivity.this.finish();
        super.onBackPressed();
    }
}
