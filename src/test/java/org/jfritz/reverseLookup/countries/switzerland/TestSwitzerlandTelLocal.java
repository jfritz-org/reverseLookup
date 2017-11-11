package org.jfritz.reverseLookup.countries.switzerland;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfritz.reverseLookup.ReverseLookupService;
import org.jfritz.reverseLookup.countries.TestConstants;
import org.jfritz.reverseLookup.exceptions.ReverseLookupException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import org.jfritz.MyTestHelper;
import org.jfritz.reverseLookup.Helper;
import org.jfritz.reverseLookup.structs.Person;

public class TestSwitzerlandTelLocal {

	private static final String SEARCH_SITE_NAME = "tel.local.ch";
	private static ReverseLookupService service = new ReverseLookupService();

	@BeforeClass
	public static void setup() throws ReverseLookupException {
		MyTestHelper.initLogging();
		service.loadConfig(MyTestHelper.class.getResourceAsStream("/reverselookup.xml"));
	}

	@After
	public void after() throws InterruptedException {
		int sleeptime = TestConstants.SLEEP_TIME;
		System.out.println("\nSleeping for " + sleeptime + " ms");
		Thread.sleep(sleeptime);
	}

	@Test
	public void test1() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Kurt und Nadja (-Siegenthaler)");
		expected.setLastName("Krebs");
		expected.setStreet("Wichelackerstrasse 31");
		expected.setHouseNumber(null);
		expected.setZipCode("3144");
		expected.setCity("Gasel");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+41318493427", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("GmbH");
		expected.setLastName("Cytracon");
		expected.setStreet("Rütistrasse 20a");
		expected.setHouseNumber(null);
		expected.setZipCode("8134");
		expected.setCity("Adliswil");
		expected.setCompany("Internet- / IT-Services");
		
		Helper.testNumberOnSite(service, "+41447712727", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Achim und Cornelia");
		expected.setLastName("Geiser");
		expected.setStreet("Webereistrasse 39");
		expected.setHouseNumber(null);
		expected.setZipCode("5703");
		expected.setCity("Seon");
		
		Helper.testNumberOnSite(service, "+41627750431", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Dialog AG");
		expected.setLastName("Trendcommerce");
		expected.setStreet("Heiligkreuzstrasse 2");
		expected.setHouseNumber(null);
		expected.setZipCode("9008");
		expected.setCity("St. Gallen");
		
		Helper.testNumberOnSite(service, "+41715550800", SEARCH_SITE_NAME, expected);
	}
	
}
