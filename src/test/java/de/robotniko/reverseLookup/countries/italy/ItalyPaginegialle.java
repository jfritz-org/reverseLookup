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
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;
import de.robotniko.reverseLookup.structs.Person;

public class ItalyPaginegialle {

	private static final String SEARCH_SITE_NAME = "paginegialle.it";
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
		expected.setCompany("FOTOOTTICA FANTINATO");
		
		Helper.testNumberOnSite(service, "+3931642176", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setStreet("Via Carnia");
		expected.setHouseNumber("33");
		expected.setZipCode("46013");
		expected.setCity("Milano (MI)");
		expected.setCompany("HOTEL MARGOT");
		
		Helper.testNumberOnSite(service, "+39226830102", SEARCH_SITE_NAME, expected);
	}
}
