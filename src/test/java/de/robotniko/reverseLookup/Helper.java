package de.robotniko.reverseLookup;

import java.util.List;

import org.junit.Assert;

import de.robotniko.reverseLookup.api.ReverseLookupRequest;
import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;
import de.robotniko.reverseLookup.structs.Person;

public class Helper {

	public static void outputResponseAndExpectedResults(final ReverseLookupResponse response, Person expected) {
		if (expected == null) {
			System.err.println("Expected is NULL");
		} else {
			if (response != null) {
				System.out.println("Found by: " + response.getFoundBy());
		
				outputResponseExpected("Company: ", response.getCompany(), expected.getCompany());
				outputResponseExpected("Firstname: ", response.getFirstName(), expected.getFirstName());
				outputResponseExpected("Lastname: ", response.getLastName(), expected.getLastName());
				outputResponseExpected("Street: ", response.getStreet(), expected.getStreet());
				outputResponseExpected("House number: ", response.getHouseNumber(), expected.getHouseNumber());
				outputResponseExpected("Postal code: ", response.getZipCode(), expected.getZipCode());
				outputResponseExpected("City: ", response.getCity(), expected.getCity());
			} else {
				System.err.println("Response is NULL");
			}
		}
	}

	private static void outputResponseExpected(String caption, String response, String expected) {
		if (expected == null && response == null) {
			System.out.println(caption + " is NULL like expected");
		} else if (expected == null && response != null) {
			System.err.println(caption + " expected NULL but got " + response);
		} else if (response == null && expected != null) {
			System.err.println(caption + " expected " + expected + " but got NULL");
		} else {
			if (expected.equals(response)) {
				System.out.println(caption + response + " == " + expected);
			} else {
				System.err.println(caption + response + " != " + expected);
			}
		}
	}
	
	public static void assertExpected(ReverseLookupResponse result, Person expected) {
		assertField(expected.getFirstName(), result.getFirstName());
		assertField(expected.getLastName(), result.getLastName());
		assertField(expected.getStreet(), result.getStreet());
		assertField(expected.getHouseNumber(), result.getHouseNumber());
		assertField(expected.getZipCode(), result.getZipCode());
		assertField(expected.getCity(), result.getCity());
		assertField(expected.getCompany(), result.getCompany());
	}
	
	private static void assertField(String expected, String actual) {
		if (expected == null) {
			Assert.assertNull(actual);
		} else {
			Assert.assertEquals(expected, actual);
		}
	}
	
	public static void testNumberOnSite(final ReverseLookupService service, final String number, final String site, Person expected) throws ReverseLookupException {
		System.out.println("-------------------------------------------");
		ReverseLookupRequest request = new ReverseLookupRequest(number);

		List<ReverseLookupResponse> results = service.blockingLookupForSite(site, request);

		System.out.println("Testing number '" + number + "' on site '" + site + "', number of results: " + results.size());
		if (expected == null && results.size() == 0) {
			return;
		} else if (expected != null && results.size() == 0) {
			System.err.println("Expected one but got no results!");
			Assert.fail();
		} else {
			// only get first entry, do not check other entries! Maybe ugly, but sufficient for function tests
			ReverseLookupResponse result = results.get(0);
			Helper.outputResponseAndExpectedResults(result, expected);
			Helper.assertExpected(result, expected);
		}
	}
}
