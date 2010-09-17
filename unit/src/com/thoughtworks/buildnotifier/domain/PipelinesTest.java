package com.thoughtworks.buildnotifier.domain;

import com.thoughtworks.buildnotifier.model.domain.*;
import org.junit.Test;

import java.util.Date;

import static com.thoughtworks.buildnotifier.model.domain.BuildActivity.*;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class PipelinesTest {

    @Test
    public void testFindOrCreate() {
        Pipelines pipelines = new Pipelines();
        Pipeline pipeline = pipelines.findOrCreate("project");
        assertNotNull(pipeline);
    }

    @Test
    public void testFindOrCreateExistingPipeline() {
        Pipelines pipelines = new Pipelines();
        Pipeline pipeline1 = new Pipeline("Project");
        Stage stage = new Stage("hello", BuildActivity.SLEEPING, BuildStatus.SUCCESS, "1234", new Date(), "i-am-a-url");
        pipeline1.addStage(stage);
        pipelines.addStage(pipeline1.getName(), stage);
        
        assertEquals(pipeline1, pipelines.findOrCreate("Project"));
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


    @Test
    public void testAddStage() {
        Pipelines pipelines = new Pipelines();
        Pipeline pipeline1 = new Pipeline("Convoy");
        Pipeline pipeline2 = new Pipeline("Regression");
        Stage stage1 = new Stage("RSpec", SLEEPING, BuildStatus.SUCCESS, "1234", new Date(), "http://localhost:4567/cctray.xml");
        Stage stage2 = new Stage("RSpec", BUILDING, BuildStatus.SUCCESS, "1234", new Date(), "http://localhost:4567/cctray.xml");

        pipeline1.addStage(stage1);
        pipeline2.addStage(stage2);

        pipelines.addStage(pipeline1.getName(), stage1);


        pipelines.addStage(pipeline1.getName(), stage1);

        assertEquals(pipelines.contains(pipeline1), true);
        pipelines.add(pipeline2);
        assertEquals(pipelines.contains(pipeline2), true);
        assertEquals(2, pipelines.size());
        assertEquals(1, pipeline1.stagesCount());
        assertEquals(1, pipeline2.stagesCount());
    }

    @Test
    public void testAddJobToStage() {
        Pipelines pipelines = new Pipelines();
        Pipeline pipeline1 = new Pipeline("Convoy");
        Pipeline pipeline2 = new Pipeline("Regression");
        Stage stage1 = new Stage("RSpec", SLEEPING, BuildStatus.SUCCESS, "1234", new Date(), "http://localhost:4567/cctray.xml");
        Stage stage2 = new Stage("RSpec", BUILDING, BuildStatus.SUCCESS, "1234", new Date(), "http://localhost:4567/cctray.xml");

        Job job1 = new Job("job1", BuildActivity.SLEEPING, BuildStatus.SUCCESS, "123", new Date(), "i-am-a-url");
        Job job2 = new Job("job1", BuildActivity.SLEEPING, BuildStatus.SUCCESS, "123", new Date(), "i-am-a-url");

        pipeline1.addStage(stage1);
        pipeline2.addStage(stage2);
        pipelines.addStage(pipeline1.getName(), stage1);
        pipelines.addStage(pipeline2.getName(), stage2);

        pipelines.addJobToStage(pipeline1.getName(), stage1.getName(), job1);
        pipelines.addJobToStage(pipeline2.getName(), stage2.getName(), job2);

        assertEquals(job1, pipeline1.stageAt(0).jobAt(0));
        assertEquals(job2, pipeline2.stageAt(0).jobAt(0));
        assertEquals(pipelines.size(), 2);
    }
}
