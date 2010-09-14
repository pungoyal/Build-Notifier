package com.thoughtworks.buildnotifier.activities;

import android.app.ListActivity;
import android.os.Bundle;

import android.view.Window;
import com.thoughtworks.buildnotifier.R;
import com.thoughtworks.buildnotifier.adapters.PipelineAdapter;
import com.thoughtworks.buildnotifier.domain.Pipeline;
import com.thoughtworks.buildnotifier.web.WebClient;

public class PipelineActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pipeline_details);

		WebClient client = new WebClient(this);
		String name = getIntent().getExtras().getString("com.thoughtworks.go.pipeline.name");
		Pipeline pipeline;
		try {
			pipeline = client.getStatusOf(name);
		} catch (Exception e) {
			pipeline = new Pipeline(name);
			e.printStackTrace();
		}
		setListAdapter(new PipelineAdapter(this, pipeline));
	}
}
