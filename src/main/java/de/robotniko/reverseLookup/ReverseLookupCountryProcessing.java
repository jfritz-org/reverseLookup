package de.robotniko.reverseLookup;

import java.io.IOException;
import java.net.Proxy;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.structs.ReverseLookupCountry;
import de.robotniko.reverseLookup.structs.ReverseLookupSite;

public class ReverseLookupCountryProcessing {

	private ReverseLookupCountry country;

	private String charSet;
	private Proxy proxy;

	public ReverseLookupCountryProcessing(final ReverseLookupCountry country, final Proxy proxy) {
		this.country = country;
		this.proxy = proxy;
	}

	public List<ReverseLookupResponse> process(final String number) throws IOException {
		List<ReverseLookupResponse> result = new ArrayList<ReverseLookupResponse>();
		for (int i=0; i<country.getNumWebsites(); i++) {

			// TODO use numParallelThread for parsing multiple sites at once
			ReverseLookupSite lookupSite = country.getLookupSite(i);

			ReverseLookupSiteConnection siteConnection = new ReverseLookupSiteConnection();
			if (proxy != null) {
				siteConnection.setProxy(proxy);
			}
			try {
				URLConnection urlConnection = siteConnection.connect(lookupSite, number);
				System.out.println(urlConnection.getURL().toString());
				this.charSet = siteConnection.getCharSet();

				ReverseLookupSiteReader reader = new ReverseLookupSiteReader(urlConnection, charSet);
				List<String> lines = reader.readSite();

				ReverseLookupSiteParsing parser = new ReverseLookupSiteParsing(lookupSite, lines);
				parser.parse();
				result.addAll(parser.getResponseList());
			} catch (Exception e) {
				// do nothing here, maybe logging
			}
		}
		Collections.sort(result);
		result = removeMeaninglessEntries(result);
		return result;
	}

	private List<ReverseLookupResponse> removeMeaninglessEntries(final List<ReverseLookupResponse> input) {
		List<ReverseLookupResponse> output = new ArrayList<ReverseLookupResponse>();
		for (ReverseLookupResponse r: input) {
			if (ReverseLookupResponse.calculateFilledProperties(r) > 3) {
				output.add(r);
			}
		}
		return output;
	}
}
