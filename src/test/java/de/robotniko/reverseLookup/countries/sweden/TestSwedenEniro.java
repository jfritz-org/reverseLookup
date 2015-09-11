package de.robotniko.reverseLookup.countries.sweden;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.robotniko.MyTestHelper;
import de.robotniko.reverseLookup.Helper;
import de.robotniko.reverseLookup.ReverseLookupService;
import de.robotniko.reverseLookup.countries.TestConstants;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;
import de.robotniko.reverseLookup.structs.Person;

public class TestSwedenEniro {

	private static final String SEARCH_SITE_NAME = "privatpersoner.eniro.se";
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
	public void testLystad() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("George");
		expected.setLastName("Niklas");
		expected.setStreet("Karlsgatan 22A");
		expected.setZipCode("45231");
		expected.setCity("STRÖMSTAD");
		
		Helper.testNumberOnSite(service, "+4652610580", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void testEdberg() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Nilsson");
		expected.setLastName("Johan");
		
		Helper.testNumberOnSite(service, "+46702619401", SEARCH_SITE_NAME, expected);
	}
}
