package org.jfritz.reverseLookup.api;

import java.io.InputStream;
import java.net.Proxy;
import java.util.List;

import org.jfritz.reverseLookup.exceptions.ReverseLookupException;

public interface IReverseLookupService {

	public abstract void loadConfig(final InputStream inputStream) throws ReverseLookupException;
	public abstract void loadConfigFile(final String path) throws ReverseLookupException;
	public abstract void setProxy(final Proxy proxy);

	public abstract List<ReverseLookupResponse> blockingLookup(final ReverseLookupRequest request) throws ReverseLookupException;
	public abstract void asynchronousLookup(final List<ReverseLookupRequest> request, final IReverseLookupFinishedListener l) throws ReverseLookupException;
	public abstract void asynchronousLookup(final ReverseLookupRequest request, final IReverseLookupFinishedListener l) throws ReverseLookupException;

	public abstract void stopAsyncLookup();
	public abstract void terminateAsyncLookup();
}
