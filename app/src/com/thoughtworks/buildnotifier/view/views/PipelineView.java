package com.thoughtworks.buildnotifier.view.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.LinearLayout;
import com.thoughtworks.buildnotifier.R;
import com.thoughtworks.buildnotifier.model.domain.BuildActivity;
import com.thoughtworks.buildnotifier.model.domain.BuildStatus;
import com.thoughtworks.buildnotifier.model.domain.Stage;

public class PipelineView extends LinearLayout {

    public PipelineView(Context context, Stage stage) {
        super(context);

        Button stageButton = new Button(context);
        stageButton.setText(stage.getName());
        stageButton.setTextColor(Color.BLACK);
        stageButton.setTypeface(null, Typeface.BOLD);
        stageButton.setBackgroundResource(getResource(stage.getLastBuildStatus(), stage.getActivity()));

        addView(stageButton, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

    }

    private int getResource(BuildStatus lastStatus, BuildActivity activity) {
        if (activity == BuildActivity.BUILDING)
            return lastStatus == BuildStatus.SUCCESS ? R.drawable.building : R.drawable.warning;
        return lastStatus == BuildStatus.SUCCESS ? R.drawable.success : R.drawable.failure;
    }

}
