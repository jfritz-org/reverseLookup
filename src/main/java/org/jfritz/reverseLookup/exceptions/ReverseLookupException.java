package org.jfritz.reverseLookup.exceptions;

public class ReverseLookupException extends Exception {

	private static final long serialVersionUID = 8133964877363068976L;

	public ReverseLookupException(final Throwable t) {
		super(t);
	}

	public ReverseLookupException(final String message) {
		super(message);
	}
}
