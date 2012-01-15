package de.robotniko.reverseLookup;

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
}
