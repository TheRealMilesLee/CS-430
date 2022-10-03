package com.example.activityandfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Map;

public class MySettings extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private static final String TAG = "MySettings";

    private static final String USERNAME_PREFERENCE  = "user_name_pref";

    Context mContext = null;

    ArrayList<CharSequence> occupationListEntries = null;
    ArrayList<CharSequence> occupationListEntryValues = null;

    ArrayList<CharSequence> ageListEntries = null;
    ArrayList<CharSequence> ageListEntryValues = null;

    Preference connectionPref = null;

    @Override
    public boolean onPreferenceClick(Preference preference) {

        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        connectionPref = findPreference(key);

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        
        Map<String, ?> allEntries = this.getPreferenceManager().getSharedPreferences().getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            // Log.e("TAG", entry.getKey() + ": " + entry.getValue().toString());
            String key = entry.getKey();
            connectionPref = findPreference(key);

            if (entry.getKey().equals("user_name_pref".toString())) {
                connectionPref.setSummary(entry.getValue().toString());
            }

        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity().getApplicationContext();

        try {
            addPreferencesFromResource(R.xml.settings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(android.R.color.white));

        return view;
    }

}
