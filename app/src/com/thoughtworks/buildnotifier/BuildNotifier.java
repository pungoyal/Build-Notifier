package com.thoughtworks.buildnotifier;

import com.thoughtworks.buildnotifier.preferences.ServerPreferences;
import com.thoughtworks.buildnotifier.views.StatusActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class BuildNotifier extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

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

		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
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
