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

public class ItalyBlockingTest {

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
		ReverseLookupRequest request = new ReverseLookupRequest("+3928260860");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Luigi", result.getFirstName());
		Assert.assertEquals("Ferrari", result.getLastName());
		Assert.assertEquals("Via Costantino Baroni 114", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("20142", result.getZipCode());
		Assert.assertEquals("Milano (MI)", result.getCity());
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+39655262755");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Studio", result.getCompany());
		Assert.assertEquals("Dr. Mario", result.getFirstName());
		Assert.assertEquals("Rossi", result.getLastName());
		Assert.assertEquals("Largo Gaetano la Loggia 33", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("00149", result.getZipCode());
		Assert.assertEquals("Roma (RM)", result.getCity());
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+3931642176");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("FOTO OTTICA", result.getFirstName());
		Assert.assertEquals("FANTINATO", result.getLastName());
		Assert.assertEquals("Corso Venticinque Aprile 123", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("22036", result.getZipCode());
		Assert.assertEquals("Erba (CO)", result.getCity());
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+39226830102");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Prenotazioni Hotel", result.getCompany());
		Assert.assertEquals("S.R.L.", result.getFirstName());
		Assert.assertEquals("Initalia", result.getLastName());
		Assert.assertEquals("Viale Monza 270", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("20128", result.getZipCode());
		Assert.assertEquals("Milano (MI)", result.getCity());
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
