package de.robotniko.reverseLookup.exceptions;

public class PhoneNumberNotSupportedException extends ReverseLookupException {
	private static final long serialVersionUID = -8341562762193049740L;

	public PhoneNumberNotSupportedException(final String phoneNumber) {
		super("This phone number is not supported: " + phoneNumber);
	}
}
