package com.thoughtworks.buildnotifier.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.thoughtworks.buildnotifier.model.domain.Pipelines;
import com.thoughtworks.buildnotifier.view.views.StatusView;

public class StatusAdapter extends BaseAdapter {

    private final Context context;
    private Pipelines pipelines;

    public StatusAdapter(Activity activity, Pipelines pipelines) {
        this.context = activity;
        this.pipelines = pipelines;
    }

    public int getCount() {
        return pipelines.size();
    }

    public Object getItem(int position) {
        return pipelines.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return new StatusView(context, pipelines.get(position));
    }
}
