package com.thoughtworks.buildnotifier.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.thoughtworks.buildnotifier.R;

import com.thoughtworks.buildnotifier.activities.PipelineActivity;
import com.thoughtworks.buildnotifier.domain.Pipeline;

public class StatusView extends LinearLayout implements OnClickListener {
	private final Context context;
	private final Pipeline pipeline;

	public StatusView(Context context, Pipeline pipeline) {
		super(context);
		this.context = context;
		this.pipeline = pipeline;

		Button pipelineButton = new Button(context);
		pipelineButton.setText(pipeline.getName());
		pipelineButton.setTextColor(Color.BLACK);
		pipelineButton.setOnClickListener(this);
        pipelineButton.setBackgroundResource(R.drawable.button);

		addView(pipelineButton, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}

	public void onClick(View v) {
		Intent intent = new Intent(context, PipelineActivity.class);
		intent.putExtra("com.thoughtworks.go.pipeline.name", pipeline.getName());
		context.startActivity(intent);
	}
}
