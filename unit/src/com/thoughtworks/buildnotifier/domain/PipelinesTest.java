package com.thoughtworks.buildnotifier.domain;

import com.thoughtworks.buildnotifier.model.domain.*;
import org.junit.Test;

import java.util.Date;

import static com.thoughtworks.buildnotifier.model.domain.BuildActivity.*;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class PipelinesTest {

    @Test
    public void testFind() {
        Pipelines pipelines = new Pipelines();
        Pipeline pipeline = pipelines.find("project");
        assertNotNull(pipeline);
    }

    @Test
    public void testIsFailingWhenOnePipelineISFailing() {
        Pipelines pipelines = new Pipelines();
        Pipeline pipeline1 = new Pipeline("Convoy");
        Pipeline pipeline2 = new Pipeline("Regression");
        Stage stage1 = new Stage("RSpec", SLEEPING, BuildStatus.FAILURE, "1234", new Date(), "http://localhost:4567/cctray.xml");
        Stage stage2 = new Stage("RSpec", BUILDING, BuildStatus.SUCCESS, "1234", new Date(), "http://localhost:4567/cctray.xml");

        pipeline1.addStage(stage1);
        pipeline2.addStage(stage2);
        pipelines.add(pipeline1);
        pipelines.add(pipeline2);

        assertEquals(pipelines.isFailing(), true);
    }

    @Test
    public void testIsFailingWhenNoPipelineISFailing() {
        Pipelines pipelines = new Pipelines();
        Pipeline pipeline1 = new Pipeline("Convoy");
        Pipeline pipeline2 = new Pipeline("Regression");
        Stage stage1 = new Stage("RSpec", SLEEPING, BuildStatus.SUCCESS, "1234", new Date(), "http://localhost:4567/cctray.xml");
        Stage stage2 = new Stage("RSpec", BUILDING, BuildStatus.SUCCESS, "1234", new Date(), "http://localhost:4567/cctray.xml");

        pipeline1.addStage(stage1);
        pipeline2.addStage(stage2);
        pipelines.add(pipeline1);
        pipelines.add(pipeline2);

        assertEquals(pipelines.isFailing(), false);
    }
}
