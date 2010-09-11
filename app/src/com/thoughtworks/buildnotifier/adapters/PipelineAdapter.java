package com.thoughtworks.buildnotifier.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.thoughtworks.buildnotifier.domain.Pipeline;
import com.thoughtworks.buildnotifier.views.PipelineView;

public class PipelineAdapter extends BaseAdapter {

	private final Context context;
	private final Pipeline pipeline;

	public PipelineAdapter(Context context, Pipeline pipeline) {
		this.context = context;
		this.pipeline = pipeline;
	}

	public int getCount() {
		return pipeline.stagesCount();
	}

	public Object getItem(int position) {
		return pipeline.stageAt(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return new PipelineView(context, pipeline.stageAt(position));
	}
}
