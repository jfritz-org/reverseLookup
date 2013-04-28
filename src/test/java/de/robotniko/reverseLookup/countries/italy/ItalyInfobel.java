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

public class ItalyInfobel {

	private static final String SEARCH_SITE_NAME = "infobel.com";
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
		Person expected = new Person();
		expected.setFirstName("LUIGI");
		expected.setLastName("FERRARI");
		expected.setStreet("V. COSTANTINO BARONI 114");
		expected.setHouseNumber(null);
		expected.setZipCode("20142");
		expected.setCity("MILANO");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+3928260860", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("MARIO");
		expected.setLastName("ROSSI");
		expected.setStreet("LG. GAETANO LA LOGGIA 33");
		expected.setHouseNumber(null);
		expected.setZipCode("00149");
		expected.setCity("ROMA");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+39655262755", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("FOTO OTTICA (S.N.C.)");
		expected.setLastName("FANTINATO");
		expected.setStreet("C. VENTICINQUE APRILE 123");
		expected.setHouseNumber(null);
		expected.setZipCode("22036");
		expected.setCity("ERBA");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+3931642176", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("S.R.L. (PRENOTAZIONI HOTEL)");
		expected.setLastName("INITALIA");
		expected.setStreet("VL. MONZA 270");
		expected.setHouseNumber(null);
		expected.setZipCode("20128");
		expected.setCity("MILANO");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+39226830102", SEARCH_SITE_NAME, expected);
	}
}
