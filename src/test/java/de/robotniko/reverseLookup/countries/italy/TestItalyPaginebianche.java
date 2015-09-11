package de.robotniko.reverseLookup.countries.italy;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.robotniko.MyTestHelper;
import de.robotniko.reverseLookup.Helper;
import de.robotniko.reverseLookup.ReverseLookupService;
import de.robotniko.reverseLookup.countries.TestConstants;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;
import de.robotniko.reverseLookup.structs.Person;

public class TestItalyPaginebianche {

	private static final String SEARCH_SITE_NAME = "paginebianche.it";
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
		expected.setFirstName("LUIGI");
		expected.setLastName("FERRARI");
		expected.setStreet("Via Baroni Costantino 114");
		expected.setHouseNumber(null);
		expected.setZipCode("20142");
		expected.setCity("Milano (MI)");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+3928260860", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("FOTOTTICA");
		expected.setLastName("FANTINATO");
		expected.setStreet("Corso XXV Aprile 123");
		expected.setHouseNumber(null);
		expected.setZipCode("22036");
		expected.setCity("Erba (CO)");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+3931642176", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("SRL");
		expected.setLastName("INITALIA");
		expected.setStreet("Viale Monza 270");
		expected.setHouseNumber(null);
		expected.setZipCode("20128");
		expected.setCity("Milano (MI)");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+39226830102", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void test5() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		
		expected.setFirstName("PARITARIO GARDEN HOUSE");
		expected.setLastName("ISTITUTO");
		expected.setStreet("Via Caracciolo");
		expected.setHouseNumber(null);
		expected.setZipCode("80016");
		expected.setCity("Marano Di Napoli (NA)");
		expected.setCompany(null);

		Helper.testNumberOnSite(service, "+39817429549", SEARCH_SITE_NAME, expected);
	}
}
