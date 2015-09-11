package de.robotniko.reverseLookup.countries.norway;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.robotniko.MyTestHelper;
import de.robotniko.reverseLookup.Helper;
import de.robotniko.reverseLookup.ReverseLookupService;
import de.robotniko.reverseLookup.countries.TestConstants;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;
import de.robotniko.reverseLookup.structs.Person;

public class TestNorwayGulesider {

	private static final String SEARCH_SITE_NAME = "gulesider.no";
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
		expected.setCompany("Nils Aksnes & Co A/S");
		expected.setStreet("Sandvenvegen 28");
		expected.setZipCode("5600");
		expected.setCity("Norheimsund");
		
		Helper.testNumberOnSite(service, "+4756553530", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testGriep() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Griep");
		expected.setLastName("Ulrike");
		expected.setStreet("Loholtbakken 7");
		expected.setZipCode("7049");
		expected.setCity("Trondheim");
		
		Helper.testNumberOnSite(service, "+4773945687", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testHelsebanken() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("ANS Helsebanken");
		expected.setStreet("Hardangerfjordvegen 650");
		expected.setZipCode("5610");
		expected.setCity("Øystese");
		
		Helper.testNumberOnSite(service, "+4756553850", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testSkage() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Skage");
		expected.setLastName("Jostein");
		expected.setStreet("Skagevegen 148");
		expected.setZipCode("5258");
		expected.setCity("Blomsterdalen");
		
		Helper.testNumberOnSite(service, "+4755226351", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKraftverk() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Kvam Kraftverk AS");
		expected.setZipCode("5600");
		expected.setCity("Norheimsund");
		
		Helper.testNumberOnSite(service, "+4756553300", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testWangensteen() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Ove Wangensteen");
		expected.setLastName("Lars");
		expected.setZipCode("2612");
		expected.setCity("Sjusjøen");
		
		Helper.testNumberOnSite(service, "+4798043923", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testFargehandel() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Norheimsund Fargehandel");
		expected.setStreet("Sandvenvegen 39A");
		expected.setZipCode("5600");
		expected.setCity("Norheimsund");
		
		Helper.testNumberOnSite(service, "+4756551733", SEARCH_SITE_NAME, expected);
	}

	@Ignore
	public void testPPT() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("PPT Fana Bydel");
		expected.setStreet("Wollert Konows plass 3");
		expected.setZipCode("5224");
		expected.setCity("Nesttun");
		
		Helper.testNumberOnSite(service, "+4755562250", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testAdventure() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("North Adventure AS");
		expected.setStreet("Bjørn Wirkolas vei 11");
		expected.setZipCode("9510");
		expected.setCity("Alta");
		
		Helper.testNumberOnSite(service, "+4778445050", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKvitbrygga() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Kvitbrygga AS");
		expected.setStreet("Furøyveien 260");
		expected.setZipCode("8178");
		expected.setCity("Halsa");
		
		Helper.testNumberOnSite(service, "+4775750080", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testSamferdselsdepartementet() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setCompany("Samferdselsdepartementet");
		expected.setStreet("Akersgata 59");
		expected.setZipCode("0180");
		expected.setCity("Oslo");
		
		Helper.testNumberOnSite(service, "+4722249090", SEARCH_SITE_NAME, expected);
	}
}
