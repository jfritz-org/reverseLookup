package org.jfritz.reverseLookup;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import org.jfritz.reverseLookup.structs.ReverseLookupSite;

public class ReverseLookupSiteConnection {
	private static final Logger LOG = Logger.getLogger(ReverseLookupSiteConnection.class);

	private static final int CONNECTION_TIMEOUT = 5000;
	private static final int READ_TIMEOUT = 10000;

	private Proxy proxy = Proxy.NO_PROXY;
	private String charset = "ISO-8859-1";

	private URLConnection urlConnection;
	private ReverseLookupSite lookupSite;
	private String requestUrl;
	
	public void setProxy(final Proxy p) {
		proxy = p;
	}

	public URLConnection connect(final ReverseLookupSite lookupSite, final String number) throws IOException {
		LOG.debug("ReverseLookup using " + lookupSite.getName());
		this.lookupSite = lookupSite;
		String refactoredNumber = refactorNumber(number);
		requestUrl = replacePlaceHoldersInURL(
				lookupSite.getUrl().toString(),
				lookupSite.getPrefix(),
				lookupSite.getAreaCodeLength(),
				refactoredNumber);

		establishConnection(requestUrl);
		return this.urlConnection;
	}
	
	public String getRequestUrl() {
		return requestUrl;
	}

	private String refactorNumber(final String number) {
		String result = number;
		String prefix = lookupSite.getPrefix();

		//needed to make sure international calls are formatted correctly
		if(!number.startsWith(prefix))
		{
			result = prefix + number;
		}
		return result;
	}

	private String replacePlaceHoldersInURL(final String urlstr, final String prefix, final int ac_length, final String nummer) {
		String result = urlstr;
		if(result.contains("$AREACODE")
				&& (nummer.length() > (prefix.length()+ac_length))) {
			result = result.replaceAll("\\$AREACODE", nummer.substring(prefix.length(), ac_length+prefix.length()));
			result = result.replaceAll("\\$NUMBER", nummer.substring(prefix.length()+ac_length));
		}else if(result.contains("$PFXAREACODE")
				&& (nummer.length() > (prefix.length()+ac_length))){
			result = result.replaceAll("\\$PFXAREACODE", nummer.substring(0, prefix.length()+ac_length));
			result = result.replaceAll("\\$NUMBER", nummer.substring(prefix.length()+ ac_length));
		}else {
			result = result.replaceAll("\\$NUMBER", nummer);
		}
		return result;
	}

	private void establishConnection(final String urlString) throws IOException {
		LOG.debug("Establishing connection to " + urlString);
		URL url = new URL(urlString);
		urlConnection = url.openConnection(proxy);
		urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
		urlConnection.setReadTimeout(READ_TIMEOUT);
		urlConnection.addRequestProperty("User-Agent", lookupSite.getUserAgent());

		parseHeaders();
	}

	private void parseHeaders() {
		LOG.debug("Parsing headers");
		charset = lookupSite.getCharset();

		Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
		Set<String> keys = headerFields.keySet();
		for (String key: keys) {
			LOG.debug("Header[" + key + "]: " + headerFields.get(key).toString());
			readCharSet(headerFields, key);
		}
	}

	private void readCharSet(Map<String, List<String>> headerFields,
			String key) {
		if ("content-type".equalsIgnoreCase(key)) {
			List<String> headerValues = headerFields.get(key);
			for (String headerValue: headerValues) {
				String[] split = headerValue.split(";", 2);
				for (String v: split) {
					if (v.trim().toLowerCase().startsWith("charset=")) {
						String[] charsetSplit = v.split("="); //$NON-NLS-1$
						charset = charsetSplit[1].trim();
						LOG.debug("Found defined charSet: " + charset);
					}
				}
			}
		}
	}

	public String getCharSet() {
		return this.charset;
	}
}