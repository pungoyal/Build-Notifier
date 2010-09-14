package com.thoughtworks.buildnotifier.mothers;

import com.thoughtworks.buildnotifier.model.domain.BuildActivity;
import com.thoughtworks.buildnotifier.model.domain.BuildStatus;
import com.thoughtworks.buildnotifier.model.domain.Job;

import java.util.Date;

public class JobMother {
    public static Job create() {
        return new Job("job", BuildActivity.BUILDING, BuildStatus.FAILURE, "10", new Date(), "");
    }
}