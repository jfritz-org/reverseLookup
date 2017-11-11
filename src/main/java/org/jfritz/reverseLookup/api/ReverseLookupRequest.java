package org.jfritz.reverseLookup.api;

public class ReverseLookupRequest implements Comparable<ReverseLookupRequest> {

	private String internationalPhoneNumber;
	private int priority;
	private IReverseLookupResponseListener listener;

	/**
	 * Do a request for given phone number in international format
	 * p.e. +497211330
	 * @param intPhoneNumber
	 */
	public ReverseLookupRequest(final String intPhoneNumber) {
		this(intPhoneNumber, 10, null);
	}

	/**
	 * Do a request for given phone number in international format
	 * p.e. +497211330
	 * @param intPhoneNumber
	 * @param listener interface for receiving progress and results
	 */
	public ReverseLookupRequest(final String intPhoneNumber, final IReverseLookupResponseListener listener) {
		this(intPhoneNumber, 10, listener);
	}

	/**
	 * Do a request for given phone number in international format
	 * p.e. +497211330
	 * @param intPhoneNumber
	 * @param priority lesser means higher priority
	 */
	public ReverseLookupRequest(final String intPhoneNumber, final int priority) {
		this(intPhoneNumber, priority, null);
	}

	/**
	 * Do a request for given phone number in international format
	 * p.e. +497211330
	 * @param intPhoneNumber
	 * @param priority lesser means higher priority
	 * @param listener interface for receiving progress and results
 	 */
	public ReverseLookupRequest(final String intPhoneNumber, final int priority, final IReverseLookupResponseListener listener) {
		this.internationalPhoneNumber = intPhoneNumber;
		this.priority = priority;
		this.listener = listener;
	}

	public String getInternationalPhoneNumber() {
		return this.internationalPhoneNumber;
	}

	public int getPriority() {
		return priority;
	}

	public int compareTo(ReverseLookupRequest o) {
		int prioComp = Integer.valueOf(this.priority).compareTo(o.getPriority());
		if (prioComp == 0) {
			return internationalPhoneNumber.compareTo(o.getInternationalPhoneNumber());
		} else {
			return prioComp;
		}
	}

	public IReverseLookupResponseListener getListener() {
		return listener;
	}
}
