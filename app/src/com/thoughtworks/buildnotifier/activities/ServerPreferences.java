package com.thoughtworks.buildnotifier.activities;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import com.thoughtworks.buildnotifier.R;
import com.thoughtworks.buildnotifier.model.domain.Constants;
import com.thoughtworks.buildnotifier.preferences.BuildNotifierPreferenceManager;

public class ServerPreferences extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        findPreference(Constants.SERVER_KEY).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                return BuildNotifierPreferenceManager.setServer(ServerPreferences.this, preference, (String) o);
            }
        });
    }
}
