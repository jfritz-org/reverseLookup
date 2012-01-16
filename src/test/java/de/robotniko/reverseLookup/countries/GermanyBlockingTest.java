package de.robotniko.reverseLookup.countries;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.robotniko.MyTestHelper;
import de.robotniko.reverseLookup.ReverseLookupService;
import de.robotniko.reverseLookup.api.ReverseLookupRequest;
import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;

public class GermanyBlockingTest {

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
	public void testBlockingLookupStaatstheater() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+495311234567");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Am Theater", result.getFirstName());
		Assert.assertEquals("STAATSTHEATER", result.getLastName());
		Assert.assertEquals("Am Theater", result.getStreet());
		Assert.assertEquals("", result.getHouseNumber());
		Assert.assertEquals("38100", result.getZipCode());
		Assert.assertEquals("Braunschweig", result.getCity());
	}

	@Test
	public void testBlockingLookupZehner() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+49277242239");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Annette", result.getFirstName());
		Assert.assertEquals("Zehner", result.getLastName());
		Assert.assertEquals("An der Alten Kirche", result.getStreet());
		Assert.assertEquals("3", result.getHouseNumber());
		Assert.assertEquals("35745", result.getZipCode());
		Assert.assertEquals("Herborn", result.getCity());
	}

	@Test
	public void testBlockingLookupUllrich() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+498990199190");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Rainer", result.getFirstName());
		Assert.assertEquals("Ullrich", result.getLastName());
		Assert.assertEquals("Zugspitzstr.", result.getStreet());
		Assert.assertEquals("20", result.getHouseNumber());
		Assert.assertEquals("85609", result.getZipCode());
		Assert.assertEquals("Aschheim", result.getCity());
	}

	@Test
	public void testBlockingLookupThen() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+498104889820");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}
		System.out.println("Number of results: " + results.size());

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("O. Dr.med.", result.getFirstName());
		Assert.assertEquals("Then", result.getLastName());
		Assert.assertEquals("Bahnhofplatz", result.getStreet());
		Assert.assertEquals("7", result.getHouseNumber());
		Assert.assertEquals("82054", result.getZipCode());
		Assert.assertEquals("Sauerlach", result.getCity());
	}

	@Test
	public void testBlockingLookupApollo() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+499115402808");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}
		System.out.println("Number of results: " + results.size());

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("", result.getFirstName());
		Assert.assertEquals("Apollo-Optik", result.getLastName());
		Assert.assertEquals("Äußere Bayreuther Str.", result.getStreet());
		Assert.assertEquals("80", result.getHouseNumber());
		Assert.assertEquals("90491", result.getZipCode());
		Assert.assertEquals("Nürnberg", result.getCity());
	}

	@Test
	public void testBlockingLookupCityCar() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4920648286171");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}
		System.out.println("Number of results: " + results.size());

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Car", result.getFirstName());
		Assert.assertEquals("City", result.getLastName());
		Assert.assertEquals("Kleiststr.", result.getStreet());
		Assert.assertEquals("48", result.getHouseNumber());
		Assert.assertEquals("46539", result.getZipCode());
		Assert.assertEquals("Dinslaken", result.getCity());
	}

	@Test
	public void testBlockingLookupMichael() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+496084950130");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}
		System.out.println("Number of results: " + results.size());

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Michael u. Martina", result.getFirstName());
		Assert.assertEquals("Schmidt", result.getLastName());
		Assert.assertEquals("Treisberger Weg", result.getStreet());
		Assert.assertEquals("12", result.getHouseNumber());
		Assert.assertEquals("61389", result.getZipCode());
		Assert.assertEquals("Schmitten", result.getCity());
	}

	@Test
	public void testBlockingLookupKlinikum() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+496221567200");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}
		System.out.println("Number of results: " + results.size());

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Heidelberg", result.getFirstName());
		Assert.assertEquals("Universitätsklinikum", result.getLastName());
		Assert.assertEquals("Im Neuenheimer Feld", result.getStreet());
		Assert.assertEquals("325", result.getHouseNumber());
		Assert.assertEquals("69120", result.getZipCode());
		Assert.assertEquals("Heidelberg", result.getCity());
	}

	@Test
	public void testBlockingLookupLago() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+497215704230");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}
		System.out.println("Number of results: " + results.size());

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("LAGO", result.getFirstName());
		Assert.assertEquals("Bowling-Center", result.getLastName());
		Assert.assertEquals("Gablonzer Str.", result.getStreet());
		Assert.assertEquals("13", result.getHouseNumber());
		Assert.assertEquals("76185", result.getZipCode());
		Assert.assertEquals("Karlsruhe", result.getCity());
	}

	@Test
	public void testBlockingLookupDirk() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4989963853");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}
		System.out.println("Number of results: " + results.size());

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Birgit u. Dirk", result.getFirstName());
		Assert.assertEquals("Lütkefent", result.getLastName());
		Assert.assertEquals("Moarstr.", result.getStreet());
		Assert.assertEquals("6", result.getHouseNumber());
		Assert.assertEquals("85737", result.getZipCode());
		Assert.assertEquals("Ismaning", result.getCity());
	}

	@Test
	public void testBlockingLookupInfraplan() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+498962021830");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}
		System.out.println("Number of results: " + results.size());

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Alfred Steuerberater", result.getFirstName());
		Assert.assertEquals("Pritzl", result.getLastName());
		Assert.assertEquals("Südliche Münchner Str.", result.getStreet());
		Assert.assertEquals("55", result.getHouseNumber());
		Assert.assertEquals("82031", result.getZipCode());
		Assert.assertEquals("Grünwald", result.getCity());
	}

	private void outputResponse(final ReverseLookupResponse response) {
		System.out.println("-------------------------------------------");
		System.out.println("Found by: " + response.getFoundBy());
		System.out.println("Company: " + response.getCompany());
		System.out.println("Firstname: " + response.getFirstName());
		System.out.println("Lastname: " + response.getLastName());
		System.out.println("Street: " + response.getStreet());
		System.out.println("House number: " + response.getHouseNumber());
		System.out.println("Postal code: " + response.getZipCode());
		System.out.println("City: " + response.getCity());
	}
}
