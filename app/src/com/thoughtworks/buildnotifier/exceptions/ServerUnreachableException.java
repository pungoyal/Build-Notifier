package com.thoughtworks.buildnotifier.exceptions;

import java.io.IOException;

public class ServerUnreachableException extends Exception {
	private static final long serialVersionUID = -1210273251548913969L;

	public ServerUnreachableException(IOException e, String server) {
		super(e);
	}
}
