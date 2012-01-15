package de.robotniko.reverseLookup.api;

public class ReverseLookupRequest {

	private String internationalPhoneNumber;

	/**
	 * Do a request for given phone number in international format
	 * p.e. +497211330
	 * @param intPhoneNumber
	 */
	public ReverseLookupRequest(final String intPhoneNumber) {
		this.internationalPhoneNumber = intPhoneNumber;
	}

	public String getInternationalPhoneNumber() {
		return this.internationalPhoneNumber;
	}
}
