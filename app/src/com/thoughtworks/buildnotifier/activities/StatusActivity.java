package com.thoughtworks.buildnotifier.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import com.thoughtworks.buildnotifier.R;
import com.thoughtworks.buildnotifier.adapters.StatusAdapter;
import com.thoughtworks.buildnotifier.domain.Pipelines;
import com.thoughtworks.buildnotifier.web.WebClient;

public class StatusActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.status);

        WebClient client = new WebClient(this);
        Pipelines pipelines;
        try {
            pipelines = client.getStatus();
        } catch (Exception e) {
            pipelines = new Pipelines();
            e.printStackTrace();
        }

        setListAdapter(new StatusAdapter(this, pipelines));
    }
}
