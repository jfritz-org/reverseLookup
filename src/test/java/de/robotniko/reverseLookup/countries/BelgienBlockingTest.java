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

public class BelgienBlockingTest {

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
		ReverseLookupRequest request = new ReverseLookupRequest("+3225156111");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(5, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("communale de et à Ixelles (Ixelles)", result.getFirstName());
		Assert.assertEquals("Admin.", result.getLastName());
		Assert.assertEquals("Chaussée d'Ixelles 168/a", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("1050", result.getZipCode());
		Assert.assertEquals("Ixelles", result.getCity());
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+3222824770");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(5, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("d'Informatique pour la Région Bruxelloise (CIRB)", result.getFirstName());
		Assert.assertEquals("Centre", result.getLastName());
		Assert.assertEquals("Avenue des Arts 21", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("1000", result.getZipCode());
		Assert.assertEquals("Bruxelles", result.getCity());
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+3232052011");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Havenbedrijf Antwerpen", result.getFirstName());
		Assert.assertEquals("Gemeentelijk", result.getLastName());
		Assert.assertEquals("Entrepotkaai 1", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("2000", result.getZipCode());
		Assert.assertEquals("Anvers", result.getCity());
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+3261275331");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("du Procureur du Roi de Neufchâteau (Parquet du Procureur du Roi près le tribunal de première instance de Neufchâteau)", result.getFirstName());
		Assert.assertEquals("Parquet", result.getLastName());
		Assert.assertEquals("Place Charles-Bergh 1", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("6840", result.getZipCode());
		Assert.assertEquals("Neufchâteau", result.getCity());
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
