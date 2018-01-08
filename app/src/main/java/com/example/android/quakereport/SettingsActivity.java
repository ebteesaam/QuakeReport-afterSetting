package com.example.android.quakereport;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ebtesam on 1/2/2018 AD.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


    }

    public static class EarthquakePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            bindPreferenceSummaryToValue(minMagnitude);

            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderBy);
        }

        private void bindPreferenceSummaryToValue(Preference minMagnitude) {
            minMagnitude.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(minMagnitude.getContext());
            String preferenceString = preferences.getString(minMagnitude.getKey(), "");
            onPreferenceChange(minMagnitude, preferenceString);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            // The code in this method takes care of updating the displayed preference summary after it has been changed
            String stringValue = o.toString();
            if (o instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefindix = listPreference.findIndexOfValue(stringValue);
                if (prefindix >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefindix]);

                }


            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }
    }
}
