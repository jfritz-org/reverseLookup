package org.jfritz.reverseLookup;

import java.io.File;
import java.io.InputStream;
import java.net.Proxy;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jfritz.reverseLookup.api.IReverseLookupFinishedListener;
import org.jfritz.reverseLookup.api.IReverseLookupService;
import org.jfritz.reverseLookup.api.ReverseLookupRequest;
import org.jfritz.reverseLookup.api.ReverseLookupResponse;
import org.jfritz.reverseLookup.exceptions.ReverseLookupException;
import org.jfritz.reverseLookup.structs.ReverseLookupCountry;
import org.jfritz.reverseLookup.xml.ReverseLookupXMLHandler;

public class ReverseLookupService implements IReverseLookupService {

	private ReverseLookupManagement mgmt = new ReverseLookupManagement();
	private Proxy proxy;
	private ReverseLookupAsyncThread asyncThread;

	public ReverseLookupService() {
		asyncThread = new ReverseLookupAsyncThread(false);
		asyncThread.start();
	}

	public void loadConfigFile(final String path) throws ReverseLookupException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(new File(path), new ReverseLookupXMLHandler(mgmt));
		} catch (Throwable t) {
			throw new ReverseLookupException(t);
		}
	}

	public void loadConfig(final InputStream is) throws ReverseLookupException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(is, new ReverseLookupXMLHandler(mgmt));
		} catch (Throwable t) {
			throw new ReverseLookupException(t);
		}
	}

	public void asynchronousLookup(List<ReverseLookupRequest> requestList,
			IReverseLookupFinishedListener l) throws ReverseLookupException {
		asyncThread.addRequest(requestList, l);
	}

	public void asynchronousLookup(ReverseLookupRequest request,
			IReverseLookupFinishedListener l) throws ReverseLookupException {
		asyncThread.addRequest(request, l);
	}

	public void stopAsyncLookup() {
		asyncThread.suspendLookup();
	}

	public void terminateAsyncLookup() {
		asyncThread.terminate();
	}

	
	public List<ReverseLookupResponse> blockingLookup(ReverseLookupRequest request) throws ReverseLookupException {
		final String intNumber = request.getInternationalPhoneNumber();
		final ReverseLookupCountry country = mgmt.getCountryByPhoneNumber(intNumber);
		final int countryCodeLength = country.getCode().length();
		final String numberWithoutCountryCode = intNumber.substring(countryCodeLength);

		ReverseLookupCountryProcessing countryProcessing = new ReverseLookupCountryProcessing(country, this.proxy);
		return countryProcessing.process(numberWithoutCountryCode);
	}

	public List<ReverseLookupResponse> blockingLookupForSite(final String siteName, ReverseLookupRequest request) throws ReverseLookupException {
		final String intNumber = request.getInternationalPhoneNumber();
		final ReverseLookupCountry country = mgmt.getCountryByPhoneNumber(intNumber);
		final int countryCodeLength = country.getCode().length();
		final String numberWithoutCountryCode = intNumber.substring(countryCodeLength);

		ReverseLookupCountryProcessing countryProcessing = new ReverseLookupCountryProcessing(country, this.proxy);
		return countryProcessing.processUsingSite(siteName, numberWithoutCountryCode);
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}
}
