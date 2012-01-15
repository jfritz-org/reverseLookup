package de.robotniko.reverseLookup.exceptions;

public class CountryNotSupportedException extends ReverseLookupException {

	private static final long serialVersionUID = 6005146880157182166L;

	public CountryNotSupportedException(final String country) {
		super("Country is not supported: " + country);
	}
}
