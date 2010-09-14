package com.thoughtworks.buildnotifier.model.domain;

import java.util.ArrayList;

public class Pipeline {
    private ArrayList<Stage> stages;
    private final String name;

    public Pipeline(String name) {
        this.name = name;
        this.stages = new ArrayList<Stage>();
    }

    public void addStage(Stage stage) {
        stages.add(stage);
    }

    public String getName() {
        return name;
    }

    public void addJobToStage(String stageName, Job job) {
        for (Stage stage : stages) {
            if (stage.getName().equals(stageName)) {
                stage.addJob(job);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pipeline pipeline = (Pipeline) o;

        if (!name.equals(pipeline.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public int stagesCount() {
        return stages.size();
    }

    public Stage stageAt(int position) {
        return stages.get(position);
    }
}
