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

public class AustriaBlockingTest {

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
	public void testTrivadis() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4313323531");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Delphi GmbH", result.getFirstName());
		Assert.assertEquals("Trivadis", result.getLastName());
		Assert.assertEquals("Handelskai 94-96", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("1200", result.getZipCode());
		Assert.assertEquals("Wien", result.getCity());
	}

	@Test
	public void testKarin() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4353365227");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Böglerhof GmbH", result.getFirstName());
		Assert.assertEquals("Romantikhotel", result.getLastName());
		Assert.assertEquals("Alpbach  166", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("6236", result.getZipCode());
		Assert.assertEquals("Alpbach (T)", result.getCity());
	}

	@Test
	public void testPrammerRudolf() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+43732641574");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Rudolf / Edeltraud", result.getFirstName());
		Assert.assertEquals("Prammer", result.getLastName());
		Assert.assertEquals("Götzelsdorf  17", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("4221", result.getZipCode());
		Assert.assertEquals("Steyregg (OÖ)", result.getCity());
	}

	@Test
	public void testPrammerJohann() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4372374145");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Johann", result.getFirstName());
		Assert.assertEquals("Prammer", result.getLastName());
		Assert.assertEquals("Stelzhamerstr 7", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("4225", result.getZipCode());
		Assert.assertEquals("Luftenberg an der Donau", result.getCity());
	}

	@Test
	public void testPrammerWalter() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4372372698");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Walter", result.getFirstName());
		Assert.assertEquals("Prammer", result.getLastName());
		Assert.assertEquals("Abwinden  Opalweg 8", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("4225", result.getZipCode());
		Assert.assertEquals("Luftenberg an der Donau (OÖ)", result.getCity());
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
