package de.robotniko.reverseLookup.countries.austria;

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

public class AustriaHeroldFirma {

	private static final String SEARCH_SITE_NAME = "herold.at (Firma)";
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
	public void testTrivadis() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setStreet("Handelskai 94-96/Millennium Tower");
		expected.setHouseNumber(null);
		expected.setZipCode("1200");
		expected.setCity("Wien");
		expected.setCompany("Trivadis Delphi GmbH");
		
		Helper.testNumberOnSite(service, "+4313323531", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKarin() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setStreet("Nr. 166");
		expected.setHouseNumber(null);
		expected.setZipCode("6236");
		expected.setCity("Alpbach");
		expected.setCompany("Romantikhotel Böglerhof GmbH");
		
		Helper.testNumberOnSite(service, "+4353365227", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testPrammerRudolf() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null; // not a company
		
		Helper.testNumberOnSite(service, "+43732641574", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testPrammerJohann() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null; // not a company
		
		Helper.testNumberOnSite(service, "+4372374145", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testPrammerWalter() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null; // not a company
		
		Helper.testNumberOnSite(service, "+4372372698", SEARCH_SITE_NAME, expected);
	}
}
