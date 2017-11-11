package org.jfritz.reverseLookup.countries.italy;

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

public class TestItalyPaginegialle {

	private static final String SEARCH_SITE_NAME = "paginegialle.it";
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
		Person expected = null; // not a company
		
		Helper.testNumberOnSite(service, "+3928260860", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setStreet("Largo La Loggia Gaetano");
		expected.setHouseNumber("33");
		expected.setZipCode("00149");
		expected.setCity("Roma (RM)");
		expected.setCompany("ROSSI DR. MARIO");
		
		Helper.testNumberOnSite(service, "+39655262755", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setStreet("C. XXV Aprile");
		expected.setHouseNumber("123");
		expected.setZipCode("22036");
		expected.setCity("Erba (CO)");
		expected.setCompany("FANTINATO FOTOTTICA");
		
		Helper.testNumberOnSite(service, "+3931642176", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setStreet("Viale Monza");
		expected.setHouseNumber("270");
		expected.setZipCode("20128");
		expected.setCity("Milano (MI)");
		expected.setCompany("INITALIA SRL");
		
		Helper.testNumberOnSite(service, "+39226830102", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void test5() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		
		expected.setStreet("Via Caracciolo");
		expected.setHouseNumber(null);
		expected.setZipCode("80016");
		expected.setCity("Marano Di Napoli (NA)");
		expected.setCompany("ISTITUTO PARITARIO GARDEN HOUSE");
		
		Helper.testNumberOnSite(service, "+39817429549", SEARCH_SITE_NAME, expected);
	}
}
