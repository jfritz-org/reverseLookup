package de.robotniko.reverseLookup;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.structs.ReverseLookupCountry;
import de.robotniko.reverseLookup.structs.ReverseLookupEntry;
import de.robotniko.reverseLookup.structs.ReverseLookupSite;

public class ReverseLookupCountryProcessingTest {

	private ReverseLookupCountryProcessing cProcessing;

	@Before
	public void init() {
		ReverseLookupSite lookupSite = new ReverseLookupSite();
		lookupSite.setName("www.dasoertliche.de");
		lookupSite.setUrl("http://www.dasoertliche.de/Controller?form_name=search_inv&amp;ph=$NUMBER");
		lookupSite.setPrefix("0");
		lookupSite.setNumLines(3);
//        <street>&lt;div class=&quot;strasse&quot;&gt;\s*([^,]*),[^\d]*\d*\s*&lt;span class=""&gt;[^&lt;]*&lt;/span&gt;</street>
//        <city>&lt;div class=&quot;strasse&quot;&gt;\s*[^,]*,[^\d]*\d*\s*&lt;span class=""&gt;([^&lt;]*)&lt;/span&gt;</city>
//        <zipcode>&lt;div class=&quot;strasse&quot;&gt;\s*[^,]*,[^\d]*(\d*)\s*&lt;span class=""&gt;[^&lt;]*&lt;/span&gt;</zipcode>

		ReverseLookupEntry entry = new ReverseLookupEntry();
    	entry.setNamePattern("class=\"preview iname\"[^>]*><span class=\"\">([^<]*)</span>");
    	entry.setStreetPattern("<div class=\"strasse\">\\s*([^,]*),[^\\d]*\\d*\\s*<span class=\"\">[^<]*</span>");
    	entry.setCityPattern("<div class=\"strasse\">\\s*[^,]*,[^\\d]*\\d*\\s*<span class=\"\">([^<]*)</span>");
    	entry.setZipPattern("<div class=\"strasse\">\\s*[^,]*,[^\\d]*(\\d*)\\s*<span class=\"\">[^<]*</span>");

		lookupSite.addEntry(entry);

		ReverseLookupCountry country = new ReverseLookupCountry();
		country.addWebsite(lookupSite);

		cProcessing = new ReverseLookupCountryProcessing(country, null);
	}

	@Test
	public void testNoLookupSites() throws IOException {
		ReverseLookupCountry country = new ReverseLookupCountry();
		cProcessing = new ReverseLookupCountryProcessing(country, null);

		Assert.assertEquals(0, cProcessing.process("").size());
	}

	@Ignore
	public void testReal() throws IOException {
		List<ReverseLookupResponse> result = cProcessing.process("07211330");
		Assert.assertEquals(4, result.size());

		ReverseLookupResponse result1 = result.get(0);
		Assert.assertNull(result1.getCompany());
		Assert.assertEquals("Galerie", result1.getFirstName());
		Assert.assertEquals("Städtische", result1.getLastName());
		Assert.assertEquals("Lorenzstr.", result1.getStreet());
		Assert.assertEquals("27", result1.getHouseNumber());
		Assert.assertEquals("76135", result1.getZipCode());
		Assert.assertEquals("Karlsruhe", result1.getCity());
		Assert.assertEquals("www.dasoertliche.de", result1.getFoundBy());

		ReverseLookupResponse result2 = result.get(1);
		Assert.assertNull(result2.getCompany());
		Assert.assertEquals("", result2.getFirstName());
		Assert.assertEquals("Schulen", result2.getLastName());
		Assert.assertEquals("Karl-Friedrich-Str.", result2.getStreet());
		Assert.assertEquals("10", result2.getHouseNumber());
		Assert.assertEquals("76133", result2.getZipCode());
		Assert.assertEquals("Karlsruhe", result2.getCity());
		Assert.assertEquals("www.dasoertliche.de", result2.getFoundBy());

		ReverseLookupResponse result3 = result.get(2);
		Assert.assertNull(result3.getCompany());
		Assert.assertEquals("", result3.getFirstName());
		Assert.assertEquals("Stadtverwaltung", result3.getLastName());
		Assert.assertEquals("Karl-Friedrich-Str.", result3.getStreet());
		Assert.assertEquals("10", result3.getHouseNumber());
		Assert.assertEquals("76133", result3.getZipCode());
		Assert.assertEquals("Karlsruhe", result3.getCity());
		Assert.assertEquals("www.dasoertliche.de", result3.getFoundBy());

		ReverseLookupResponse result4 = result.get(3);
		Assert.assertNull(result4.getCompany());
		Assert.assertEquals("", result4.getFirstName());
		Assert.assertEquals("Stadtverwaltung", result4.getLastName());
		Assert.assertEquals("Karl-Friedrich-Str.", result4.getStreet());
		Assert.assertEquals("10", result4.getHouseNumber());
		Assert.assertEquals("76133", result4.getZipCode());
		Assert.assertEquals("Karlsruhe", result4.getCity());
		Assert.assertEquals("www.dasoertliche.de", result4.getFoundBy());
	}
}
