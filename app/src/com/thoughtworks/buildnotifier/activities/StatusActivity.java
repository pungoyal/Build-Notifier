package com.thoughtworks.buildnotifier.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import com.thoughtworks.buildnotifier.R;
import com.thoughtworks.buildnotifier.view.adapters.StatusAdapter;
import com.thoughtworks.buildnotifier.web.BuildClient;

public class StatusActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.status);

        BuildClient client = new BuildClient(this);
        setListAdapter(new StatusAdapter(this, client.getStatus()));
    }
}
