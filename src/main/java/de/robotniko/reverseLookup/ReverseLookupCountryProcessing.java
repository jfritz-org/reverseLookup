package de.robotniko.reverseLookup;

import java.io.IOException;
import java.net.Proxy;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.structs.ReverseLookupCountry;
import de.robotniko.reverseLookup.structs.ReverseLookupSite;

public class ReverseLookupCountryProcessing {
	private static final Logger LOG = Logger.getLogger(ReverseLookupCountryProcessing.class);

	private ReverseLookupCountry country;

	private String charSet;
	private Proxy proxy;

	public ReverseLookupCountryProcessing(final ReverseLookupCountry country, final Proxy proxy) {
		this.country = country;
		this.proxy = proxy;
	}

	public List<ReverseLookupResponse> processUsingSite(final String siteName, final String number) {
		List<ReverseLookupResponse> result = new ArrayList<ReverseLookupResponse>();

		ReverseLookupSite lookupSite = country.getLookupSiteByName(siteName);
		if (lookupSite != null) {
			List<ReverseLookupResponse> res = processNumberOnSite(lookupSite, number);
			if (res != null) {
				result.addAll(res);
			}
		}
		
		Collections.sort(result);
		result = removeMeaninglessEntries(result);
		return result;
	}
	
	public List<ReverseLookupResponse> process(final String number) {
		List<ReverseLookupResponse> result = new ArrayList<ReverseLookupResponse>();
		for (int i=0; i<country.getNumWebsites(); i++) {

			// TODO use numParallelThread for parsing multiple sites at once
			ReverseLookupSite lookupSite = country.getLookupSite(i);

			List<ReverseLookupResponse> res = processNumberOnSite(lookupSite, number);
			if (res != null) {
				result.addAll(res);
			}
		}
		
		Collections.sort(result);
		result = removeMeaninglessEntries(result);
		return result;
	}

	private List<ReverseLookupResponse> processNumberOnSite(ReverseLookupSite lookupSite, final String number) {
		ReverseLookupSiteConnection siteConnection = new ReverseLookupSiteConnection();
		if (proxy != null) {
			siteConnection.setProxy(proxy);
		}
		try {
			URLConnection urlConnection = siteConnection.connect(lookupSite, number);
			System.out.println("Requesting: " + siteConnection.getRequestUrl() + ", connection to: " + urlConnection.getURL().toString());
			this.charSet = siteConnection.getCharSet();

			ReverseLookupSiteReader reader = new ReverseLookupSiteReader(urlConnection, charSet);
			List<String> lines = reader.readSite();

			ReverseLookupSiteParsing parser = new ReverseLookupSiteParsing(lookupSite, lines);
			parser.parse();
			return parser.getResponseList();
		} catch (Exception e) {
			// do nothing here, maybe logging
				LOG.error("Error", e);
		}
		return null;
	}

	private List<ReverseLookupResponse> removeMeaninglessEntries(final List<ReverseLookupResponse> input) {
		List<ReverseLookupResponse> output = new ArrayList<ReverseLookupResponse>();
		for (ReverseLookupResponse r: input) {
			if (ReverseLookupResponse.calculateFilledProperties(r) > 0) {
				output.add(r);
			}
		}
		return output;
	}
}
