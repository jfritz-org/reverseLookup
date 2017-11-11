package org.jfritz.reverseLookup.countries.italy;

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

public class TestItalyInfobel {

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
		expected.setFirstName("LUIGI");
		expected.setLastName("FERRARI");
		expected.setStreet("DEI DAINI 114");
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
		expected.setStreet("V. NOMENTANA 33");
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
		expected.setStreet("BATTISTA BARTESAGHI 123");
		expected.setHouseNumber(null);
		expected.setZipCode("22036");
		expected.setCity("ERBA");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+3931642176", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("SRL");
		expected.setLastName("INITALIA");
		expected.setStreet("DE' GOZZADINI BENO 270");
		expected.setHouseNumber(null);
		expected.setZipCode("20122");
		expected.setCity("MILANO");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+39226830102", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void test5() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null;
		
		Helper.testNumberOnSite(service, "+39817429549", SEARCH_SITE_NAME, expected);
	}
}
