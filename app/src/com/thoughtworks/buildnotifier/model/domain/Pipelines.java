package com.thoughtworks.buildnotifier.model.domain;

import java.util.ArrayList;

public class Pipelines extends ArrayList<Pipeline> {
    private static final long serialVersionUID = 7224490213190557979L;

    public Pipeline findOrCreate(String name) {

        for (Pipeline pipeline : this) {
            if (pipeline.getName().equals(name))
                return pipeline;
        }
        Pipeline pipeline = new Pipeline(name);
        add(pipeline);
        return pipeline;
    }

    public void addStage(String pipelineName, Stage stage) {
        Pipeline pipeline = findOrCreate(pipelineName);
        pipeline.addStage(stage);
    }

    public void addJobToStage(String pipelineName, String stageName, Job job) {
        Pipeline pipeline = findOrCreate(pipelineName);
        pipeline.addJobToStage(stageName, job);
    }

    public Pipeline find(String name) {
        for (Pipeline pipeline : this) {
            if (pipeline.getName().equals(name))
                return pipeline;
        }
        return new Pipeline(name);
    }

    public boolean isFailing() {
        for (Pipeline pipeline : this) {
            if (pipeline.isFailing())
                return true;
        }
        return false;
    }
}
