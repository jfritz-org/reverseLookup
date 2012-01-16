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

public class SchweizBlockingTest {

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
		ReverseLookupRequest request = new ReverseLookupRequest("+41318493427");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}


		Assert.assertEquals(3, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Kurt und Nadja (-Siegenthaler)", result.getFirstName());
		Assert.assertEquals("Krebs", result.getLastName());
		Assert.assertEquals("Wichelackerstrasse 31", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("3144", result.getZipCode());
		Assert.assertEquals("Gasel", result.getCity());
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+41447712727");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Cytracon GmbH", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Rütistrasse 20a", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("8134", result.getZipCode());
		Assert.assertEquals("Adliswil", result.getCity());
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+41627750431");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Achim und Cornelia", result.getFirstName());
		Assert.assertEquals("Geiser", result.getLastName());
		Assert.assertEquals("Webereistrasse 39", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("5703", result.getZipCode());
		Assert.assertEquals("Seon", result.getCity());
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+41715550800");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("CallWorld Telemarketing AG", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Heiligkreuzstrasse 2", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("9008", result.getZipCode());
		Assert.assertEquals("St. Gallen", result.getCity());
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
