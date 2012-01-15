package de.robotniko.reverseLookup;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import de.robotniko.reverseLookup.structs.ReverseLookupSite;

public class ReverseLookupSiteConnection {
	private static final Logger LOG = Logger.getLogger(ReverseLookupSiteConnection.class);

	private static final int CONNECTION_TIMEOUT = 5000;
	private static final int READ_TIMEOUT = 10000;
	private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.0; de; rv:1.9.1) Gecko/20090624 Firefox/3.5 (.NET CLR 3.5.30729)";

	private Proxy proxy = Proxy.NO_PROXY;
	private String charSet = "ISO-8859-1";

	private URLConnection urlConnection;
	private ReverseLookupSite lookupSite;

	public void setProxy(final Proxy p) {
		proxy = p;
	}

	public URLConnection connect(final ReverseLookupSite lookupSite, final String number) throws IOException {
		LOG.debug("ReverseLookup using " + lookupSite.getName());
		this.lookupSite = lookupSite;
		String refactoredNumber = refactorNumber(number);
		String urlString = replacePlaceHoldersInURL(
				lookupSite.getUrl().toString(),
				lookupSite.getPrefix(),
				lookupSite.getAreaCodeLength(),
				refactoredNumber);

		establishConnection(urlString);
		return this.urlConnection;
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
		urlConnection.addRequestProperty("User-Agent", USER_AGENT);

		parseHeaders();
	}

	private void parseHeaders() {
		LOG.debug("Parsing headers");
		charSet = "ISO-8859-1";

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
						charSet = charsetSplit[1].trim();
						LOG.debug("Found defined charSet: " + charSet);
					}
				}
			}
		}
	}

	public String getCharSet() {
		return this.charSet;
	}
}