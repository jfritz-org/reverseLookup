package de.robotniko.reverseLookup.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.robotniko.reverseLookup.ReverseLookupManagement;
import de.robotniko.reverseLookup.exceptions.CountryNotSupportedException;
import de.robotniko.reverseLookup.structs.ReverseLookupCountry;
import de.robotniko.reverseLookup.structs.ReverseLookupEntry;
import de.robotniko.reverseLookup.structs.ReverseLookupSite;
import de.robotniko.reverseLookup.xml.ReverseLookupXMLHandler;

public class ReverseLookupXMLHandlerTest {

	private ReverseLookupManagement mgmt;

	@Before
	public void init() {
		mgmt = new ReverseLookupManagement();
	}

	@Test
	public void test() throws CountryNotSupportedException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			InputStream is = ReverseLookupXMLHandler.class.getResourceAsStream("/testXMLHandler");
			parser.parse(is, new ReverseLookupXMLHandler(mgmt));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ReverseLookupCountry country = mgmt.getCountry("+49");
		Assert.assertEquals("+49", country.getCode());
		Assert.assertEquals(5, country.getNumWebsites());

		ReverseLookupSite site0 = country.getLookupSite(0);
		Assert.assertEquals("www.dasoertliche.de", site0.getName());
		Assert.assertEquals("http://dasoertliche.de/Controller?form_name=search_inv&ph=$NUMBER", site0.getUrl());
		Assert.assertEquals("0", site0.getPrefix());
		Assert.assertEquals(3, site0.getNumEntries());

		ReverseLookupEntry entry0 = site0.getEntry(0);
		Assert.assertEquals("zipcode", entry0.getFirstOccurance());
		Assert.assertNull(entry0.getFirstNamePattern());
		Assert.assertNull(entry0.getLastNamePattern());
		Assert.assertEquals("\\sna: \"([^\"]*)\",", entry0.getNamePattern());
		Assert.assertEquals("\\sst: \"([^\"]*)\",", entry0.getStreetPattern());
		Assert.assertEquals("\\sci: \"([^\"]*)\",", entry0.getCityPattern());
		Assert.assertEquals("\\spc: \"([^\"]*)\",", entry0.getZipPattern());

		ReverseLookupEntry entry2 = site0.getEntry(2);
		Assert.assertNull(entry2.getFirstOccurance());
		Assert.assertNull(entry2.getFirstNamePattern());
		Assert.assertNull(entry2.getLastNamePattern());
		Assert.assertEquals("class=\"entry\"\\s*(?:onmouseover=\"\")?\\s*>([^<]*)</a>", entry2.getNamePattern());
		Assert.assertEquals("^\\s*([^,>]+),&nbsp;\\d{5}&nbsp;[^<]*</div>", entry2.getStreetPattern());
		Assert.assertEquals("^[^,]*,&nbsp;\\d{5}&nbsp;([^<]*)</div>", entry2.getCityPattern());
		Assert.assertEquals("^[^,]*,&nbsp;(\\d{5})&nbsp;[^<]*</div>", entry2.getZipPattern());
	}
}