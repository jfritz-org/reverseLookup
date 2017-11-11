package org.jfritz.reverseLookup.countries.germany;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfritz.reverseLookup.Helper;
import org.jfritz.reverseLookup.ReverseLookupService;
import org.jfritz.reverseLookup.countries.TestConstants;
import org.jfritz.reverseLookup.exceptions.ReverseLookupException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import org.jfritz.MyTestHelper;
import org.jfritz.reverseLookup.structs.Person;

public class TestGermanyGoYellow {

	private static final String SEARCH_SITE_NAME = "www.goyellow.de";
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
	public void testJoo() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("J.");
		expected.setLastName("Joo");
		expected.setZipCode("");
		expected.setCity("Karlsruhe");
		
		Helper.testNumberOnSite(service, "+49721502943", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testKramm() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("P.");
		expected.setLastName("Kramm");
		expected.setStreet("Johannes-Gropper-Weg 10A");
		expected.setZipCode("59494");
		expected.setCity("Soest");
		
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
		Person expected = null;
		
		Helper.testNumberOnSite(service, "+495311234567", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testThen() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Dr. med. Olaf Facharzt für Orthopädie und Sportmedizin");
		expected.setLastName("Then");
		expected.setStreet("Bahnhofplatz 7");
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
		expected.setCity("Nürnberg");
		
		Helper.testNumberOnSite(service, "+499115402808", SEARCH_SITE_NAME, expected);
	}
	
	@Ignore
	public void testSchmidt() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Michael");
		expected.setLastName("Schmidt");
		expected.setStreet("Treisberger Weg 12");
		expected.setZipCode("61389");
		expected.setCity("Brombach Gemeinde Schmitten");
		
		Helper.testNumberOnSite(service, "+496084950130", SEARCH_SITE_NAME, expected);
	}	
	
	@Test
	public void testLago() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Karlsruhe");
		expected.setLastName("Bowling-Center");
		expected.setStreet("Gablonzer Str. 13");
		expected.setZipCode("76185");
		expected.setCity("Karlsruhe");
		
		Helper.testNumberOnSite(service, "+497215704230", SEARCH_SITE_NAME, expected);
	}
}