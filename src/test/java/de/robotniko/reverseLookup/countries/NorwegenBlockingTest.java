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

public class NorwegenBlockingTest {

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
		ReverseLookupRequest request = new ReverseLookupRequest("+4756553530");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(3, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Rørleggermestrenes Servicekontor AL", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Eidsvågbakken 1", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("5105", result.getZipCode());
		Assert.assertEquals("Eidsvåg i Åsane", result.getCity());
	}

	@Test
	public void test2() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4773945687");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Ulrike", result.getFirstName());
		Assert.assertEquals("Griep", result.getLastName());
		Assert.assertEquals("Loholtbakken 7", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("7049", result.getZipCode());
		Assert.assertEquals("Trondheim", result.getCity());
	}

	@Test
	public void test3() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4756553850");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(7, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Kvam herad", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Grovagjelet 16", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("5600", result.getZipCode());
		Assert.assertEquals("Norheimsund", result.getCity());
	}

	@Test
	public void test4() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4755226351");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Jostein Skage", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Skagevegen 148", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("5258", result.getZipCode());
		Assert.assertEquals("Blomsterdalen", result.getCity());
	}

	@Test
	public void test5() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4756553300");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Kvam Kraftverk AS", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertNull(result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("5600", result.getZipCode());
		Assert.assertEquals("Norheimsund", result.getCity());
	}

	@Test
	public void test6() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4798043923");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertNull(result.getCompany());
		Assert.assertEquals("Ove Wangensteen", result.getFirstName());
		Assert.assertEquals("Lars", result.getLastName());
		Assert.assertNull(result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("2612", result.getZipCode());
		Assert.assertEquals("Sjusjøen", result.getCity());
	}

	@Test
	public void test7() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4756551733");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Norheimsund Fargehandel", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Sandvenvegen 39 A", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("5600", result.getZipCode());
		Assert.assertEquals("Norheimsund", result.getCity());
	}

	@Test
	public void test8() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4755562250");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Bergen kommune", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Rådhusgaten 10", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("5014", result.getZipCode());
		Assert.assertEquals("Bergen", result.getCity());
	}

	@Test
	public void test9() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4778445050");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(2, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("VIA Travel - VIA Tours Alta", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Markedsgata 21 -25", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("9510", result.getZipCode());
		Assert.assertEquals("Alta", result.getCity());
	}

	@Test
	public void test10() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4775750080");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(1, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Kvitbrygga AS", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Furøyveien 260", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("8178", result.getZipCode());
		Assert.assertEquals("Halsa", result.getCity());
	}

	@Test
	public void test11() throws ReverseLookupException, ParserConfigurationException, SAXException, IOException {
		ReverseLookupRequest request = new ReverseLookupRequest("+4722249090");

		List<ReverseLookupResponse> results = service.blockingLookup(request);

		System.out.println("Number of results: " + results.size());
		for (int i=0; i<results.size(); i++) {
			outputResponse(results.get(i));
		}

		Assert.assertEquals(20, results.size());
		ReverseLookupResponse result = results.get(0);
		Assert.assertEquals("Statsministerens Kontor", result.getCompany());
		Assert.assertNull(result.getFirstName());
		Assert.assertNull(result.getLastName());
		Assert.assertEquals("Glacisgata 1", result.getStreet());
		Assert.assertNull(result.getHouseNumber());
		Assert.assertEquals("0151", result.getZipCode());
		Assert.assertEquals("Oslo", result.getCity());
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
