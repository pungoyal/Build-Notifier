package com.thoughtworks.buildnotifier.mothers;

import com.thoughtworks.buildnotifier.model.domain.BuildActivity;
import com.thoughtworks.buildnotifier.model.domain.BuildStatus;
import com.thoughtworks.buildnotifier.model.domain.Stage;

import java.util.Date;

public class StageMother {

    public static Stage create(String name) {
        return new Stage(name, BuildActivity.BUILDING, BuildStatus.FAILURE, "100", new Date(), "");
    }
}