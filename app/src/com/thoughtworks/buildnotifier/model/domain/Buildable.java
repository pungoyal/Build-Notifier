package com.thoughtworks.buildnotifier.model.domain;

import java.util.Date;

public abstract class Buildable {

    private final String name;
    private final BuildActivity activity;
    protected final BuildStatus lastBuildStatus;
    private final String lastBuildLabel;
    private final Date lastBuildTime;
    private final String url;

    protected Buildable(String name, BuildActivity activity, BuildStatus lastBuildStatus, String lastBuildLabel,
                        Date lastBuildTime, String url) {
        this.name = name;
        this.activity = activity;
        this.lastBuildStatus = lastBuildStatus;
        this.lastBuildLabel = lastBuildLabel;
        this.lastBuildTime = lastBuildTime;
        this.url = url;
    }

    @Override
    public String toString() {
        return getName() + ":" + getActivity() + ":" + getLastBuildStatus() + ":" + getLastBuildLabel() + ":"
                + getLastBuildTime() + ":" + getUrl();
    }

    public String getName() {
        return name;
    }

    public BuildStatus getLastBuildStatus() {
        return lastBuildStatus;
    }

    public String getLastBuildLabel() {
        return lastBuildLabel;
    }

    public Date getLastBuildTime() {
        return lastBuildTime;
    }

    public String getUrl() {
        return url;
    }

    public BuildActivity getActivity() {
        return activity;
    }
}
