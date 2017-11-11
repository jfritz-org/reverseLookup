package org.jfritz.reverseLookup.api;

import org.jfritz.reverseLookup.ReverseLookupService;

public class ReverseLookupFacade {

	private static ReverseLookupService lookupService = new ReverseLookupService();

	public static IReverseLookupService getReverseLookupService() {
		return lookupService;
	}
}
