package com.thoughtworks.buildnotifier.domain;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PipelinesTest {

	@Test
	public void testFind() {
		Pipelines pipelines = new Pipelines();
		Pipeline pipeline = pipelines.find("project");
		assertNull(pipeline);
	}
}
