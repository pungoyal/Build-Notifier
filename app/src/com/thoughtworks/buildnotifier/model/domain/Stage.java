package com.thoughtworks.buildnotifier.model.domain;

import java.util.ArrayList;
import java.util.Date;

public class Stage extends Buildable {
    public Stage(String name, BuildActivity activity, BuildStatus lastBuildStatus, String lastBuildLabel,
                 Date lastBuildTime, String url) {
        super(name, activity, lastBuildStatus, lastBuildLabel, lastBuildTime, url);
        this.jobs = new ArrayList<Job>();
    }

    private ArrayList<Job> jobs;

    public void addJob(Job job) {
        jobs.add(job);
    }

    public Job jobAt(int i) {
        return jobs.get(i);
    }

    public boolean isFailing() {
        return lastBuildStatus.equals(BuildStatus.FAILURE);
    }
}
