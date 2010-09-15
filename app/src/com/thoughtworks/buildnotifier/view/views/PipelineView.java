package com.thoughtworks.buildnotifier.view.views;

import android.content.Context;
import android.graphics.Color;
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
        stageButton.setTextColor(getTextColor(stage.getLastBuildStatus(), stage.getActivity()));
        stageButton.setBackgroundResource(getResource(stage.getLastBuildStatus(), stage.getActivity()));

        addView(stageButton, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

    }

    private int getTextColor(BuildStatus lastStatus, BuildActivity activity) {
        if (activity == BuildActivity.BUILDING)
            return lastStatus == BuildStatus.SUCCESS ? Color.YELLOW : Color.rgb(255, 128, 64);
        return lastStatus == BuildStatus.SUCCESS ? Color.GREEN : Color.RED;
    }

    private int getResource(BuildStatus lastStatus, BuildActivity activity) {
        if (activity == BuildActivity.BUILDING)
            return lastStatus == BuildStatus.SUCCESS ? R.drawable.building : R.drawable.warning;
        return lastStatus == BuildStatus.SUCCESS ? R.drawable.success : R.drawable.failure;
    }

}
