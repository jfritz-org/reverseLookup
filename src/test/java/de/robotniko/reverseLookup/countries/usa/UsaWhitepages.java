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
		expected.setFirstName("Howard");
		expected.setLastName("W Levison");
		expected.setStreet("602 Sun Valley Way");
		expected.setHouseNumber(null);
		expected.setZipCode("NJ 07932-3005");
		expected.setCity("Florham Park");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+19736350430", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Jennifer");
		expected.setLastName("L Smith");
		expected.setStreet("316 Streett Cir");
		expected.setHouseNumber(null);
		expected.setZipCode("MD 21050-3061");
		expected.setCity("Forest Hill");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+14104200629", SEARCH_SITE_NAME, expected);
	}

}
