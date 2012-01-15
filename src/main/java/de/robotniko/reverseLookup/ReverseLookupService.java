package de.robotniko.reverseLookup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import de.robotniko.reverseLookup.api.IReverseLookupResponseListener;
import de.robotniko.reverseLookup.api.IReverseLookupService;
import de.robotniko.reverseLookup.api.ReverseLookupRequest;
import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;
import de.robotniko.reverseLookup.structs.ReverseLookupCountry;
import de.robotniko.reverseLookup.xml.ReverseLookupXMLHandler;

public class ReverseLookupService implements IReverseLookupService {

	private ReverseLookupManagement mgmt = new ReverseLookupManagement();
	private Proxy proxy;

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

	public void asynchronousLookup(ReverseLookupRequest request,
			IReverseLookupResponseListener l) throws ReverseLookupException {
		// TODO Auto-generated method stub
	}

	public List<ReverseLookupResponse> blockingLookup(
			ReverseLookupRequest request) throws ReverseLookupException {
		final String intNumber = request.getInternationalPhoneNumber();
		final ReverseLookupCountry country = mgmt.getCountryByPhoneNumber(intNumber);
		final int countryCodeLength = country.getCode().length();
		final String numberWithoutCountryCode = intNumber.substring(countryCodeLength);

		List<ReverseLookupResponse> result = null;
		ReverseLookupCountryProcessing countryProcessing = new ReverseLookupCountryProcessing(country, this.proxy);
		try {
			result = countryProcessing.process(numberWithoutCountryCode);
		} catch (IOException ioe) {
			throw new ReverseLookupException(ioe);
		}
		return result;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}
}