package de.robotniko.reverseLookup.countries.belgium;

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

public class BelgiumBlockingTest {

	private static final String SEARCH_SITE_NAME = "www.infobel.com";
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
		expected.setFirstName("Com. d' Ixelles");
		expected.setLastName("Adm.");
		expected.setStreet("Chaussée d'Ixelles 168");
		expected.setHouseNumber(null);
		expected.setZipCode("1050");
		expected.setCity("Ixelles");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+3225156111", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("D'INFORMATIQUE POUR LA REGION BRUXELLOISE - CIRB");
		expected.setLastName("CENTRE");
		expected.setStreet("Avenue des Arts 20/10");
		expected.setHouseNumber(null);
		expected.setZipCode("1000");
		expected.setCity("Bruxelles");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+3222824770", SEARCH_SITE_NAME, expected);
	}
}
