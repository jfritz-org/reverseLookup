package de.robotniko.reverseLookup.countries.luxembourg;

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

public class LuxemburgInfobel {

	private static final String SEARCH_SITE_NAME = "infobel.com";
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
		expected.setFirstName("International Luxembourg");
		expected.setLastName("ArcelorMittal");
		expected.setStreet("24-26 BOULEVARD D'AVRANCHES");
		expected.setHouseNumber(null);
		expected.setZipCode("1160");
		expected.setCity("LUXEMBOURG");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+35247921", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Immobilière Luxembourgeoise Immosol Sàrl");
		expected.setLastName("Agence");
		expected.setStreet("14 AVENUE DE LA LIBERTE");
		expected.setHouseNumber(null);
		expected.setZipCode("1930");
		expected.setCity("LUXEMBOURG");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+352225533", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Opticiens Sàrl");
		expected.setLastName("Pearle");
		expected.setStreet("18 RUE NOTRE-DAME");
		expected.setHouseNumber(null);
		expected.setZipCode("2240");
		expected.setCity("LUXEMBOURG");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+35226203026", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Luxembourgeoise de Crémation SA");
		expected.setLastName("Société");
		expected.setStreet("6 RUE ERMESINDE");
		expected.setHouseNumber(null);
		expected.setZipCode("6437");
		expected.setCity("ECHTERNACH");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+352220335", SEARCH_SITE_NAME, expected);
	}

	@Test
	public void test5() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		Person expected = new Person();
		expected.setFirstName("Sàrl");
		expected.setLastName("Rischette");
		expected.setStreet("4 ROUTE DE LUXEMBOURG");
		expected.setHouseNumber(null);
		expected.setZipCode("6130");
		expected.setCity("JUNGLINSTER");
		expected.setCompany(null);
		
		Helper.testNumberOnSite(service, "+352788331", SEARCH_SITE_NAME, expected);
	}
}
