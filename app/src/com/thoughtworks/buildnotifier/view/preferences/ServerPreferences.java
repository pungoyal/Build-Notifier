package com.thoughtworks.buildnotifier.view.preferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.thoughtworks.buildnotifier.R;
import com.thoughtworks.buildnotifier.model.domain.Constants;
import com.thoughtworks.buildnotifier.view.listeners.PreferenceChangeListener;

public class ServerPreferences extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        PreferenceChangeListener preferenceChangeListener = new PreferenceChangeListener(this);
        findPreference(Constants.SERVER_KEY).setOnPreferenceChangeListener(preferenceChangeListener);
    }

}
