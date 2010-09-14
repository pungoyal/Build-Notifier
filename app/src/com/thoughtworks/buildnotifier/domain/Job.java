package com.thoughtworks.buildnotifier.domain;

import java.util.Date;

public class Job extends Buildable {
    public Job(String name, BuildActivity activity, BuildStatus lastBuildStatus, String lastBuildLabel,
               Date lastBuildTime, String url) {
        super(name, activity, lastBuildStatus, lastBuildLabel, lastBuildTime, url);
    }
}
