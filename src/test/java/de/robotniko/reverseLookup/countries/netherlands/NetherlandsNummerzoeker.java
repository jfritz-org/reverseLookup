package de.robotniko.reverseLookup.countries.netherlands;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.robotniko.MyTestHelper;
import de.robotniko.reverseLookup.Helper;
import de.robotniko.reverseLookup.ReverseLookupService;
import de.robotniko.reverseLookup.api.ReverseLookupRequest;
import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;
import de.robotniko.reverseLookup.structs.Person;

public class NetherlandsNummerzoeker {

	private static final String SEARCH_SITE_NAME = "nummerzoeker.com";
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
		expected.setFirstName("International");
		expected.setLastName("Camping");
		expected.setStreet("Sint Bavodijk 2/D");
		expected.setHouseNumber(null);
		expected.setZipCode("4504AA");
		expected.setCity("Nieuwvliet");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+31117371233", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("C");
		expected.setLastName("Smid");
		expected.setStreet("Westerdok 328");
		expected.setHouseNumber(null);
		expected.setZipCode("1013BH");
		expected.setCity("Amsterdam");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+31207711969", SEARCH_SITE_NAME, expected);
	}
}
