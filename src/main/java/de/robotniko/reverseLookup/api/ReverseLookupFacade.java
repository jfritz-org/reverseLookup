package de.robotniko.reverseLookup.api;

import de.robotniko.reverseLookup.ReverseLookupService;

public class ReverseLookupFacade {

	private static ReverseLookupService lookupService = new ReverseLookupService();

	public static IReverseLookupService getReverseLookupService() {
		return lookupService;
	}
}
