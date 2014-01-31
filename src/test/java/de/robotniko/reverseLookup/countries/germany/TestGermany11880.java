package de.robotniko.reverseLookup.countries.germany;

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

public class TestGermany11880 {

	private static final String SEARCH_SITE_NAME = "www.11880.com";
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
	public void testJoo() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("J.");
		expected.setLastName("Joo");
		
		Helper.testNumberOnSite(service, "+49721502943", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKramm() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("P. Mand A.");
		expected.setLastName("Kramm");
		expected.setStreet("Johannes-Gropper-Weg 10 A");
		expected.setZipCode("59494");
		expected.setCity("Soest Westf");
		
		Helper.testNumberOnSite(service, "+49292113115", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testSportvereinigung() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Besigheim Geschäftsstelle");
		expected.setLastName("Sportvereinigung");
		expected.setStreet("Jahnstr. 9");
		expected.setZipCode("74354");
		expected.setCity("Besigheim");
		
		Helper.testNumberOnSite(service, "+497143830091", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testStaatstheater() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null; // nicht bekannt
		
		Helper.testNumberOnSite(service, "+495311234567", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testThen() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Olaf Dr. med. Facharzt für Orthopädie");
		expected.setLastName("Then");
		expected.setStreet("Bahnhofstr. 7");
		expected.setZipCode("82054");
		expected.setCity("Sauerlach");
		
		Helper.testNumberOnSite(service, "+498104889820", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void testApollo() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("");
		expected.setLastName("Apollo-Optik");
		expected.setStreet("Äußere Bayreuther Str. 80");
		expected.setZipCode("90491");
		expected.setCity("Nürnberg Mittelfr");
		
		Helper.testNumberOnSite(service, "+499115402808", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void testSchmidt() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Michael Martina");
		expected.setLastName("Schmidt");
		expected.setStreet("Treisberger Weg 12");
		expected.setZipCode("61389");
		expected.setCity("Schmitten Taunus");
		
		Helper.testNumberOnSite(service, "+496084950130", SEARCH_SITE_NAME, expected);
	}	
	
	@Test
	public void testKlinikum() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null; // nicht bekannt
		
		Helper.testNumberOnSite(service, "+496221560", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void testKlinikumDurchwahl() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = null; // nicht bekannt
		
		Helper.testNumberOnSite(service, "+496221567200", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void testLago() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Bowling-Center Karlsruhe Müller und Veith OHG");
		expected.setLastName("LAGO");
		expected.setStreet("Gablonzer Str. 13");
		expected.setZipCode("76185");
		expected.setCity("Karlsruhe Baden");
		
		Helper.testNumberOnSite(service, "+497215704230", SEARCH_SITE_NAME, expected);
	}
	
	@Test
	public void testPritzl() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Alfred Steuerberater");
		expected.setLastName("Pritzl");
		expected.setStreet("Südliche Münchner Str. 55");
		expected.setZipCode("82031");
		expected.setCity("Grünwald Kr München");
		
		Helper.testNumberOnSite(service, "+49896202180", SEARCH_SITE_NAME, expected);
	}
}