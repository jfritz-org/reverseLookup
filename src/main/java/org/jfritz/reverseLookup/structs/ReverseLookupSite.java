package org.jfritz.reverseLookup.structs;

import java.util.ArrayList;
import java.util.List;

public class ReverseLookupSite {

	private String name;
	private String url;
	private String prefix;
	private String charset = "ISO-8859-1";
	private int areaCodeLength;
	private int numLines;

	private String userAgent = "ReverseLookupClient";

	private List<ReverseLookupEntry> entries = new ArrayList<ReverseLookupEntry>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getAreaCodeLength() {
		return this.areaCodeLength;
	}

	public void setAreaCodeLength(int acLength) {
		this.areaCodeLength = acLength;
	}

	public int getNumLines() {
		return this.numLines;
	}

	public void setNumLines(int numLines) {
		this.numLines = numLines;
	}

	public void addEntry(final ReverseLookupEntry entry) {
		if (!this.entries.contains(entry)) {
			this.entries.add(entry);
		}
	}
	
	public void setCharset(final String charset) {
		this.charset = charset;
	}
	
	public String getCharset() {
		return charset;
	}

	public int getNumEntries() {
		return this.entries.size();
	}

	public ReverseLookupEntry getEntry(final int index) {
		if (index >= 0 && index < this.entries.size()) {
			return this.entries.get(index);
		}
		return null;
	}
	
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public String getUserAgent() {
		return userAgent;
	}
}
