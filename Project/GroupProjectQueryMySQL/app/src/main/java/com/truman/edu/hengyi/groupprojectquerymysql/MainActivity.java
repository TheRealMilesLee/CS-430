package com.truman.edu.hengyi.groupprojectquerymysql;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity
{
    String url = "http://150.243.198.74:8080/phpquery" + ".php";

    Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this.getApplicationContext();
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        final TextView txt = (TextView) findViewById(R.id.txt);

        /* Prepare the Request
         *  Please refer to this tutorial for further info.
         *  https://www.itsalif.info/content/android-volley-tutorial-http-get-post-put
         *  Or Google's document
         *  https://developer.android.com/training/volley/simple
         */

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
        Request.Method.GET, url, null,
        new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                // display response
                Log.d("Response: ", response.toString());
                try
                {
                    JSONArray data = response.getJSONArray("data");
                    for (int index = 0; index < data.length(); index++)
                    {
                        JSONObject JSONDataReceive = data.getJSONObject(index);
                        // These are mapping to column names
                        String Student_ID = JSONDataReceive.getString(
                                "Student_ID");
                        String First_Name = JSONDataReceive.getString(
                                "First_Name");
                        String Last_Name = JSONDataReceive.getString(
                                "Last_Name");
                        String Lat = JSONDataReceive.getString(
                                "Lat");
                        String Long = JSONDataReceive.getString(
                                "long_param");
                        txt.append(
                                Student_ID + " " + First_Name + " " + Last_Name + " " + Lat + " " + Long + " \n");
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                // Log.d("Error.Response", error.getMessage());
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mContext = null;
    }
}
