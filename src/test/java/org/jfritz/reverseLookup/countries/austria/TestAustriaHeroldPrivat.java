package org.jfritz.reverseLookup.countries.austria;

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

public class TestAustriaHeroldPrivat {

	private static final String SEARCH_SITE_NAME = "herold.at (Privat)";
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
	public void testTrivadis() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Austria GmbH");
		expected.setLastName("Trivadis");
		expected.setStreet("Handelskai 94-96");
		expected.setHouseNumber(null);
		expected.setZipCode("1200");
		expected.setCity("Wien");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+4313323531", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKarin() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("GmbH");
		expected.setLastName("Böglerhof");
		expected.setStreet("Alpbach 166");
		expected.setHouseNumber(null);
		expected.setZipCode("6236");
		expected.setCity("Alpbach");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+4353365227", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testPrammerRudolf() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Enrico");
		expected.setLastName("Prammer");
		expected.setStreet("Götzelsdorf 17");
		expected.setHouseNumber(null);
		expected.setZipCode("4221");
		expected.setCity("Steyregg");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+43732641574", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testPrammerJohann() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Johann");
		expected.setLastName("Prammer");
		expected.setStreet("Stelzhamerstr 7");
		expected.setHouseNumber(null);
		expected.setZipCode("4225");
		expected.setCity("Luftenberg an der Donau");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+4372374145", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testPrammerWalter() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Walter");
		expected.setLastName("Prammer");
		expected.setStreet("Abwinden Opalweg 8");
		expected.setHouseNumber(null);
		expected.setZipCode("4225");
		expected.setCity("Luftenberg an der Donau (OÖ)");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+4372372698", SEARCH_SITE_NAME, expected);
	}
}
