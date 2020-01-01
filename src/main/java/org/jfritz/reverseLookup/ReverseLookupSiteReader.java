package org.jfritz.reverseLookup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ReverseLookupSiteReader {
	private final static Logger LOG = Logger.getLogger(ReverseLookupSiteReader.class);
	private static final int MAX_LINES = 10000;

	private URLConnection urlConnection;
	private List<String> lines;
	private String charSet;

	public ReverseLookupSiteReader(final URLConnection urlConnection, final String charSet) {
		this.urlConnection = urlConnection;
		this.charSet = charSet;
	}

	public List<String> readSite() throws UnsupportedEncodingException, IOException {
		LOG.debug("Reading site");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), charSet));

		lines = new ArrayList<String>(MAX_LINES);
		String currentLine = "";
		int numReadLines = 0;
		while (null != (currentLine = br.readLine())) {
			if (containsUnicodeCharacters(currentLine)) {
				currentLine = convertUnicodeCharacters(currentLine);
			}
			lines.add(currentLine);
			numReadLines++;
			if (numReadLines > MAX_LINES) {
				LOG.debug("Exceeded maximum number of lines to be read. Quit reading further response.");
				break;
			}
		}
		LOG.debug("Read num lines: " + numReadLines);
		br.close();
		return this.lines;
	}

	private boolean containsUnicodeCharacters(final String line) {
		return java.util.regex.Pattern.compile("\\\\u[0-9a-f]{4}").matcher(line).find();
	}

	private String convertUnicodeCharacters(final String input) {
		if (input.indexOf("\\u")==-1) {
			return input;
		}

		String escaped = input;
		String processed="";

		int position=escaped.indexOf("\\u");
		while (position!=-1) {
			if (position!=0) {
				processed += escaped.substring(0, position);
			}
			String token=escaped.substring(position+2,position+6);
			escaped=escaped.substring(position+6);
			processed+=(char)Integer.parseInt(token,16);
			position=escaped.indexOf("\\u");
		}
		processed+=escaped;

		return processed;
	}
}
