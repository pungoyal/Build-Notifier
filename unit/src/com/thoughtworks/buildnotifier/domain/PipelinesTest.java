package com.thoughtworks.buildnotifier.domain;

import com.thoughtworks.buildnotifier.model.domain.Pipeline;
import com.thoughtworks.buildnotifier.model.domain.Pipelines;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class PipelinesTest {

    @Test
    public void testFind() {
        Pipelines pipelines = new Pipelines();
        Pipeline pipeline = pipelines.find("project");
        assertNotNull(pipeline);
    }
}
