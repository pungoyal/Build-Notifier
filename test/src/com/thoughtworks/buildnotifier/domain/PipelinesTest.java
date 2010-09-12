package com.thoughtworks.buildnotifier.domain;

import junit.framework.TestCase;

import org.junit.Test;

public class PipelinesTest extends TestCase {

	@Test
	public void testFind() {
		Pipelines pipelines = new Pipelines();
		Pipeline pipeline = pipelines.find("project");
		assertNull(pipeline);
	}
}
