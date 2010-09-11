package com.thoughtworks.buildnotifier.exceptions;

public class XMLParseException extends Exception {
	private static final long serialVersionUID = -5332508604923415351L;

	public XMLParseException(Exception e, String input) {
		super(e);
	}
}
