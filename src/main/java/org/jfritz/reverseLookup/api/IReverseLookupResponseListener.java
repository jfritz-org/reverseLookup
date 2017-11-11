package org.jfritz.reverseLookup.api;

import java.util.List;

public interface IReverseLookupResponseListener {

	public void lookupResponse(final List<ReverseLookupResponse> response, final int percent);

}
