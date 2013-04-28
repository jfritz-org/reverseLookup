package de.robotniko.reverseLookup.countries.usa;

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

public class UsaWhitepages {
	private static final String SEARCH_SITE_NAME = "whitepages.com";
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
		expected.setFirstName("Adam S");
		expected.setLastName("Levison");
		expected.setStreet("Sun Valley Way");
		expected.setHouseNumber("602");
		expected.setZipCode("07932");
		expected.setCity("Florham Park");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+19736350430", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Mark D");
		expected.setLastName("Smith");
		expected.setStreet("Streett Cir");
		expected.setHouseNumber("316");
		expected.setZipCode("21050");
		expected.setCity("Forest Hill");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+14104200629", SEARCH_SITE_NAME, expected);
	}

}
