package com.thoughtworks.buildnotifier.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import com.thoughtworks.buildnotifier.R;
import com.thoughtworks.buildnotifier.view.adapters.PipelineAdapter;
import com.thoughtworks.buildnotifier.web.BuildClient;

public class PipelineActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pipeline_details);

        BuildClient client = new BuildClient(this);
        String name = getIntent().getExtras().getString("com.thoughtworks.go.pipeline.name");
        setListAdapter(new PipelineAdapter(this, client.getStatusOf(name)));
    }
}
