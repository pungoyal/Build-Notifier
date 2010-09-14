package com.thoughtworks.buildnotifier.domain;

import com.thoughtworks.buildnotifier.mothers.JobMother;
import com.thoughtworks.buildnotifier.mothers.StageMother;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class PipelineTest {
    @Test
    public void testEquals() {
        Pipeline pipeline = new Pipeline("name");
        assertTrue(pipeline.equals(new Pipeline("name")));
        assertTrue(new Pipeline("name").equals(pipeline));

        assertFalse(pipeline.equals(new Pipeline("other-name")));
        assertFalse(pipeline.equals(new Pipeline("")));

        assertFalse(pipeline.equals(null));
        assertFalse(pipeline.equals("name"));
    }

    @Test
    public void addJobToStage() {
        Pipeline pipeline = new Pipeline("pipe");
        pipeline.addStage(StageMother.create("stage"));
        pipeline.addJobToStage("stage", JobMother.create());

        Stage stage = pipeline.stageAt(1);
    }
}
