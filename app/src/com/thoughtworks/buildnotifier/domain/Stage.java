package com.thoughtworks.buildnotifier.domain;

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
}
