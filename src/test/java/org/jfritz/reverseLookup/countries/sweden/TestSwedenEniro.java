package org.jfritz.reverseLookup.countries.sweden;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfritz.reverseLookup.ReverseLookupService;
import org.jfritz.reverseLookup.countries.TestConstants;
import org.jfritz.reverseLookup.exceptions.ReverseLookupException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import org.jfritz.MyTestHelper;
import org.jfritz.reverseLookup.Helper;
import org.jfritz.reverseLookup.structs.Person;

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
		Person expected = null;

		Helper.testNumberOnSite(service, "+46702619401", SEARCH_SITE_NAME, expected);
	}
}
