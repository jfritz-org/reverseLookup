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

public class LuxemburgBlockingTest {

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
		ReverseLookupRequest request = new ReverseLookupRequest("+35247921");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(5, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("International Luxembourg", result.getFirstName());
		Assert.assertEquals("ArcelorMittal", result.getLastName());
		Assert.assertEquals("24-26 BOULEVARD D'AVRANCHES", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("1160", result.getZipCode());
		Assert.assertEquals("LUXEMBOURG", result.getCity());
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+352225533");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(3, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Immobilière Luxembourgeoise Immosol Sàrl", result.getFirstName());
		Assert.assertEquals("Agence", result.getLastName());
		Assert.assertEquals("14 AVENUE DE LA LIBERTE", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("1930", result.getZipCode());
		Assert.assertEquals("LUXEMBOURG", result.getCity());
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+35226203026");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(3, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("LUXEMBOURG S.A R.L.", result.getFirstName());
		Assert.assertEquals("PEARLE", result.getLastName());
		Assert.assertEquals("18 RUE NOTRE-DAME", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("2240", result.getZipCode());
		Assert.assertEquals("LUXEMBOURG", result.getCity());
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+352220335");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(3, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Luxembourgeoise de Crémation SA", result.getFirstName());
		Assert.assertEquals("Société", result.getLastName());
		Assert.assertEquals("6 RUE ERMESINDE", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("6437", result.getZipCode());
		Assert.assertEquals("ECHTERNACH", result.getCity());
	}

	@Test
	public void test5() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+352788331");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Sàrl", result.getFirstName());
		Assert.assertEquals("Rischette", result.getLastName());
		Assert.assertEquals("4 ROUTE DE LUXEMBOURG", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("6130", result.getZipCode());
		Assert.assertEquals("JUNGLINSTER", result.getCity());
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
