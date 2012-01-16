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

public class USABlockingTest {

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
		ReverseLookupRequest request = new ReverseLookupRequest("+19736350430");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}


		Assert.assertEquals(4, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Adam S", result.getFirstName());
		Assert.assertEquals("Levison", result.getLastName());
		Assert.assertEquals("Sun Valley Way", result.getStreet());
		Assert.assertEquals("602", result.getHouseNumber());
		Assert.assertEquals("07932", result.getZipCode());
		Assert.assertEquals("Florham Park", result.getCity());
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+14104200629");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Jennifer L", result.getFirstName());
		Assert.assertEquals("Smith", result.getLastName());
		Assert.assertEquals("Streett Cir", result.getStreet());
		Assert.assertEquals("316", result.getHouseNumber());
		Assert.assertEquals("21050", result.getZipCode());
		Assert.assertEquals("Forest Hill", result.getCity());
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
