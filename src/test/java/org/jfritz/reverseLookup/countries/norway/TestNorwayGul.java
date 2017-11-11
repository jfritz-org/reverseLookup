package org.jfritz.reverseLookup.countries.norway;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfritz.MyTestHelper;
import org.jfritz.reverseLookup.Helper;
import org.jfritz.reverseLookup.ReverseLookupService;
import org.jfritz.reverseLookup.countries.TestConstants;
import org.jfritz.reverseLookup.exceptions.ReverseLookupException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import org.jfritz.reverseLookup.structs.Person;

public class TestNorwayGul {

	private static final String SEARCH_SITE_NAME = "gul.no";
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
	public void testAksnes() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Trudvang");
		expected.setLastName("Eiendom AS");
		expected.setStreet("Sandvenvegen 28");
		expected.setZipCode("5600");
		expected.setCity("Norheimsund");
		
		Helper.testNumberOnSite(service, "+4756553530", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testGriep() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null; // no entry
		
		Helper.testNumberOnSite(service, "+4773945687", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testHelsebanken() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Ragnar");
		expected.setLastName("Hansen");
		expected.setStreet("Hardangerfjordvegen 650");
		expected.setZipCode("5610");
		expected.setCity("Øystese");
		
		Helper.testNumberOnSite(service, "+4756553850", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testSkage() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Jostein");
		expected.setLastName("Skage");
		expected.setStreet("Skagevegen 148");
		expected.setZipCode("5258");
		expected.setCity("Blomsterdalen");
		
		Helper.testNumberOnSite(service, "+4755226351", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKraftverk() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Kvam");
		expected.setLastName("Kraftverk AS");
		expected.setStreet("Kaldestad 40");
		expected.setZipCode("5600");
		expected.setCity("Norheimsund");
		
		Helper.testNumberOnSite(service, "+4756553300", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testWangensteen() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null; // no entry
		
		Helper.testNumberOnSite(service, "+4798043923", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testFargehandel() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("AS");
		expected.setLastName("Tingbakken");
		expected.setStreet("Sandvenvegen 39A");
		expected.setZipCode("5600");
		expected.setCity("Norheimsund");
		
		Helper.testNumberOnSite(service, "+4756551733", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testPPT() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("");
		expected.setLastName("");
		expected.setStreet("Kalfarveien 20");
		expected.setZipCode("5018");
		expected.setCity("Bergen");
		
		Helper.testNumberOnSite(service, "+4755562250", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testAdventure() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Skodei");
		expected.setLastName("Elin Bjørnæs");
		expected.setStreet("Markveien 6");
		expected.setZipCode("9510");
		expected.setCity("Alta");
		
		Helper.testNumberOnSite(service, "+4778445050", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKvitbrygga() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Kvitbrygga");
		expected.setLastName("AS");
		expected.setStreet("Furøyveien 260");
		expected.setZipCode("8178");
		expected.setCity("Halsa");
		
		Helper.testNumberOnSite(service, "+4775750080", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testSamferdselsdepartementet() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Klima-Og");
		expected.setLastName("Miljødepartementet");
		expected.setStreet("Kongens gate 20");
		expected.setZipCode("0153");
		expected.setCity("Oslo");
		
		Helper.testNumberOnSite(service, "+4722249090", SEARCH_SITE_NAME, expected);
	}
}
