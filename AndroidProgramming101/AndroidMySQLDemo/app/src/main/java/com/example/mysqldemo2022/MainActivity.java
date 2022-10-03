package com.example.mysqldemo2022;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

// Please see https://github.com/google/volley
import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String url = "http://150.243.146.168:8080/AndroidMySQLDemo/LoadPersonnel.php";
    Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this.getApplicationContext();

        /*
        * This RequestQueue needs to be initialized in onCreate. Otherwise, you will get this:
        * java.lang.NullPointerException: Attempt to invoke virtual method 'java.io.File android.content.Context.getCacheDir()' on a null object reference
        */
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        final TextView txt = (TextView) findViewById(R.id.txt);

        /* Prepare the Request
        *  Please refer to this tutorial for further info.
        *  https://www.itsalif.info/content/android-volley-tutorial-http-get-post-put
        *  Or Google's document
        *  https://developer.android.com/training/volley/simple
        */

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response: ", response.toString());
                        try {
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jasondata = data.getJSONObject(i);

                                // These are mapping to column names
                                String name = jasondata.getString("NAME");
                                String job = jasondata.getString("JOB");
                                txt.append(name + " " + job + " " + " \n");
                                // txt is the TextView

                            }
                            txt.append("--- End of Data ---\n"); // put the data to TextView

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log.d("Error.Response", error.getMessage());
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContext = null;
    }
}