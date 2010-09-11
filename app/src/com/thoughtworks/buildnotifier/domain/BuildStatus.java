package com.thoughtworks.buildnotifier.domain;

public enum BuildStatus {
	SUCCESS, FAILURE;

	public static BuildStatus parse(String status) {
		for (BuildStatus buildStatus : BuildStatus.values())
			if (buildStatus.matches(status))
				return buildStatus;
		throw new IllegalArgumentException("Invalid Status " + status);
	}

	private boolean matches(String s) {
		return s.equalsIgnoreCase(toString());
	}

}
