package org.jfritz.reverseLookup.countries.netherlands;

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

public class TestNetherlandsNummerzoeker {

	private static final String SEARCH_SITE_NAME = "nummerzoeker.com";
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
		expected.setFirstName("International");
		expected.setLastName("Camping");
		expected.setStreet("Sint Bavodijk 2/D");
		expected.setHouseNumber(null);
		expected.setZipCode("4504AA");
		expected.setCity("Nieuwvliet");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+31117371233", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null;
//		expected.setFirstName("C");
//		expected.setLastName("Smid");
//		expected.setStreet("Westerdok 328");
//		expected.setHouseNumber(null);
//		expected.setZipCode("1013BH");
//		expected.setCity("Amsterdam");
//		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+31207711969", SEARCH_SITE_NAME, expected);
	}
}
