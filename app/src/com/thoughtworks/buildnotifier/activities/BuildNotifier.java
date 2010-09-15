package com.thoughtworks.buildnotifier.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import com.thoughtworks.buildnotifier.R;
import com.thoughtworks.buildnotifier.services.BuildMonitorService;

public class BuildNotifier extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        startService(new Intent(BuildNotifier.this, BuildMonitorService.class));

        View viewStatusButton = findViewById(R.id.view_status_button);
        viewStatusButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BuildNotifier.this, StatusActivity.class);
                startActivity(intent);
            }
        });

        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(BuildNotifier.this, About.class));
            }
        });

        View settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(BuildNotifier.this, ServerPreferences.class));
            }
        });

        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                stopService(new Intent(BuildNotifier.this, BuildMonitorService.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, ServerPreferences.class));
                return true;
        }
        return false;
    }
}
