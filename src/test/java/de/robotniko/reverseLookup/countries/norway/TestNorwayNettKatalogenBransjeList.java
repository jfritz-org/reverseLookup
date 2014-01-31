package de.robotniko.reverseLookup.countries.norway;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.robotniko.MyTestHelper;
import de.robotniko.reverseLookup.Helper;
import de.robotniko.reverseLookup.ReverseLookupService;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;
import de.robotniko.reverseLookup.structs.Person;

public class TestNorwayNettKatalogenBransjeList {

	private static final String SEARCH_SITE_NAME = "nettkatalogen.no/bransjelist";
	private static ReverseLookupService service = new ReverseLookupService();

	@BeforeClass
	public static void setup() throws ReverseLookupException {
		MyTestHelper.initLogging();
		service.loadConfig(MyTestHelper.class.getResourceAsStream("/reverselookup.xml"));
	}

	@After
	public void after() throws InterruptedException {
		int sleeptime = 3;
		System.out.println("\nSleeping for " + sleeptime + " seconds");
		Thread.sleep(sleeptime * 1000);
	}

	@Test
	public void testAksnes() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Trudvang Eiendom AS");
		expected.setStreet("Sandvenvegen 28");
		expected.setZipCode("5600");
		expected.setCity("Norheimsund (Kvam)");
		
		Helper.testNumberOnSite(service, "+4756553530", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testGriep() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Ulrike");
		expected.setLastName("Barbara Margarete Griep");
		expected.setStreet("Loholtbakken 7");
		expected.setZipCode("7049");
		expected.setCity("TRONDHEIM");
		
		Helper.testNumberOnSite(service, "+4773945687", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testHelsebanken() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Lena Laupsa");
		expected.setStreet("Hardangerfjordvegen 650 Helsebanken");
		expected.setZipCode("5610");
		expected.setCity("ØYSTESE (Kvam)");
		
		Helper.testNumberOnSite(service, "+4756553850", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testSkage() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("JOSTEIN SKAGE");
		expected.setStreet("Skagevegen 148");
		expected.setZipCode("5258");
		expected.setCity("Blomsterdalen (Bergen)");
		
		Helper.testNumberOnSite(service, "+4755226351", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKraftverk() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Kvam Kraftverk AS");
		expected.setZipCode("5600");
		expected.setStreet("Kaldestad 40");
		expected.setCity("Norheimsund (Kvam)");
		
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
		expected.setCompany("AS Tingbakken");
		expected.setStreet("Sandvenvegen 39 A");
		expected.setZipCode("5600");
		expected.setCity("Norheimsund (Kvam)");

		// THIS IS STILL BUGGY
//		expected.setCompany("Scopus AS");
//		expected.setStreet("5600 Norheimsund (Kvam)");
//		expected.setZipCode("5600");
//		expected.setCity("Norheimsund (Kvam)");
		
		Helper.testNumberOnSite(service, "+4756551733", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testPPT() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Bergen kommune");
		expected.setStreet("Bergen Rådhus");
		expected.setZipCode("5016");
		expected.setCity("Bergen");
		
		Helper.testNumberOnSite(service, "+4755562250", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testAdventure() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Skodei");
		expected.setStreet("Markveien 6");
		expected.setZipCode("9510");
		expected.setCity("Alta");
		
		Helper.testNumberOnSite(service, "+4778445050", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKvitbrygga() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null; // no entry
		
		Helper.testNumberOnSite(service, "+4775750080", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testSamferdselsdepartementet() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Kulturdepartementet");
		expected.setStreet("Akersgata 59");
		expected.setZipCode("0180");
		expected.setCity("Oslo");
		
		Helper.testNumberOnSite(service, "+4722249090", SEARCH_SITE_NAME, expected);
	}
}
