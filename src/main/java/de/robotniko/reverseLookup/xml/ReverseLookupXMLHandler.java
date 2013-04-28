package de.robotniko.reverseLookup.xml;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.robotniko.reverseLookup.ReverseLookupManagement;
import de.robotniko.reverseLookup.structs.ReverseLookupCountry;
import de.robotniko.reverseLookup.structs.ReverseLookupEntry;
import de.robotniko.reverseLookup.structs.ReverseLookupSite;

public class ReverseLookupXMLHandler extends DefaultHandler {
	private final static Logger LOG = Logger.getLogger(ReverseLookupXMLHandler.class);

	private ReverseLookupCountry country;
	private ReverseLookupSite website;
	private ReverseLookupEntry entry;

	private boolean parsingName = false;
	private boolean parsingFirstName = false;
	private boolean parsingLastName = false;
	private boolean parsingStreet = false;
	private boolean parsingHouseNumber = false;
	private boolean parsingCity = false;
	private boolean parsingZipCode = false;
	private boolean parsingCompany = false;

	private String readCharacters = "";
	private ReverseLookupManagement mgmt;

	public ReverseLookupXMLHandler(final ReverseLookupManagement mgmt) {
		this.mgmt = mgmt;
	}

	public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException {
		if ("country".equalsIgnoreCase(tagName)) {
			country = new ReverseLookupCountry();
			if (attributes.getValue("code") != null) {
				country.setCode(attributes.getValue("code"));
			}
		} else if ("website".equalsIgnoreCase(tagName)) {
			website = new ReverseLookupSite();
			if (attributes.getValue("name") != null) {
				website.setName(attributes.getValue("name"));
			}
			if (attributes.getValue("charset") != null) {
				website.setCharset(attributes.getValue("charset"));
			}
			if (attributes.getValue("url") != null) {
				website.setUrl(attributes.getValue("url"));
			}
			if (attributes.getValue("prefix") != null) {
				website.setPrefix(attributes.getValue("prefix"));
			}
			if (attributes.getValue("areacode") != null) {
				website.setAreaCodeLength(attributes.getValue("areacode").length());
			}
			if (attributes.getValue("numLines") != null) {
				try {
					Integer numLines = Integer.parseInt(attributes.getValue("numLines"));
					website.setNumLines(numLines.intValue());
				} catch (NumberFormatException nfe) {
					LOG.warn("Could not parse numLines. Not a number: " + attributes.getValue("numLines"));
				}
			}
		} else if ("entry".equalsIgnoreCase(tagName)) {
			entry = new ReverseLookupEntry();
			if (attributes.getValue("firstOccurance") != null) {
				entry.setFirstOccurance(attributes.getValue("firstOccurance"));
			}
			if (attributes.getValue("swapFirstAndLastName") != null) {
				entry.setSwapFirstAndLastName(
						Boolean.parseBoolean(attributes.getValue("swapFirstAndLastName")));
			}
		} else if ("name".equalsIgnoreCase(tagName)) {
			parsingName = true;
		} else if ("firstname".equalsIgnoreCase(tagName)) {
			parsingFirstName = true;
		} else if ("lastname".equalsIgnoreCase(tagName)) {
			parsingLastName = true;
		} else if ("street".equalsIgnoreCase(tagName)) {
			parsingStreet = true;
		} else if ("houseNumber".equalsIgnoreCase(tagName)) {
			parsingHouseNumber = true;
		} else if ("city".equalsIgnoreCase(tagName)) {
			parsingCity = true;
		} else if ("zipcode".equalsIgnoreCase(tagName)) {
			parsingZipCode = true;
		} else if ("company".equalsIgnoreCase(tagName)) {
			parsingCompany = true;
		}

		readCharacters = "";
	}

	public void endElement(String uri, String localName, String tagName) throws SAXException {
		if ("country".equalsIgnoreCase(tagName) && mgmt != null) {
			mgmt.addReverseLookupCountry(country);
			country = null;
		} else if ("website".equalsIgnoreCase(tagName) && country != null) {
			country.addWebsite(website);
			website = null;
		} else if ("entry".equalsIgnoreCase(tagName) && website != null) {
			website.addEntry(entry);
			entry = null;
		} else if (parsingName && entry != null) {
			entry.setNamePattern(readCharacters);
			parsingName = false;
		} else if (parsingFirstName && entry != null) {
			entry.setFirstNamePattern(readCharacters);
			parsingFirstName = false;
		} else if (parsingLastName && entry != null) {
			entry.setLastNamePattern(readCharacters);
			parsingLastName = false;
		} else if (parsingStreet && entry != null) {
			entry.setStreetPattern(readCharacters);
			parsingStreet = false;
		} else if (parsingHouseNumber && entry != null) {
			entry.setHouseNumberPattern(readCharacters);
			parsingHouseNumber = false;
		} else if (parsingCity && entry != null) {
			entry.setCityPattern(readCharacters);
			parsingCity = false;
		} else if (parsingZipCode && entry != null) {
			entry.setZipPattern(readCharacters);
			parsingZipCode = false;
		} else if (parsingCompany && entry != null) {
			entry.setCompanyPattern(readCharacters);
			parsingCompany = false;
		}
	}

	public void characters(char[] characters, int start, int length) throws SAXException {
		readCharacters += new String(characters, start, length);
	}
}
