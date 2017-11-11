package org.jfritz.reverseLookup.countries.usa;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfritz.reverseLookup.countries.TestConstants;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import org.jfritz.MyTestHelper;
import org.jfritz.reverseLookup.Helper;
import org.jfritz.reverseLookup.ReverseLookupService;
import org.jfritz.reverseLookup.exceptions.ReverseLookupException;
import org.jfritz.reverseLookup.structs.Person;

public class TestUsaWhitepages {
	private static final String SEARCH_SITE_NAME = "whitepages.com";
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
		expected.setFirstName("Jamie");
		expected.setLastName("Levison");
		expected.setStreet("602 Sun Valley Way");
		expected.setHouseNumber(null);
		expected.setZipCode("NJ 07932-3005");
		expected.setCity("Florham Park");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+19736350430", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Jennifer");
		expected.setLastName("L Smith");
		expected.setStreet("316 Streett Cir");
		expected.setHouseNumber(null);
		expected.setZipCode("MD 21050-3061");
		expected.setCity("Forest Hill");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+14104200629", SEARCH_SITE_NAME, expected);
	}

}
