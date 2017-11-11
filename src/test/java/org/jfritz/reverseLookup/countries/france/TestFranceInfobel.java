package org.jfritz.reverseLookup.countries.france;

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

public class TestFranceInfobel {

	private static final String SEARCH_SITE_NAME = "infobel.com";
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
		expected.setFirstName("");
		expected.setLastName("MAIRIE");
		expected.setStreet("Rue Etang");
		expected.setHouseNumber(null);
		expected.setZipCode("57230");
		expected.setCity("Philippsbourg");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+33387065155", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("MATERNE");
		expected.setLastName("MEHR");
		expected.setStreet("2 Rue Fort Louis");
		expected.setHouseNumber(null);
		expected.setZipCode("67480");
		expected.setCity("ROESCHWOOG");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+33388862622", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Frédéric");
		expected.setLastName("Heldt");
		expected.setStreet("2 Rue Eglise");
		expected.setHouseNumber(null);
		expected.setZipCode("67480");
		expected.setCity("Auenheim");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+33388863772", SEARCH_SITE_NAME, expected);
	}
}
