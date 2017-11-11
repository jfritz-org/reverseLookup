package org.jfritz.reverseLookup;

import java.util.List;

import org.jfritz.reverseLookup.api.ReverseLookupRequest;
import org.jfritz.reverseLookup.exceptions.ReverseLookupException;
import org.junit.Assert;

import org.jfritz.reverseLookup.api.ReverseLookupResponse;
import org.jfritz.reverseLookup.structs.Person;

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
	
	public static boolean assertExpected(ReverseLookupResponse result, Person expected) {
		boolean res = true;
		res = assertField(expected.getFirstName(), result.getFirstName()) && res;
		res = assertField(expected.getLastName(), result.getLastName()) && res;
		res = assertField(expected.getStreet(), result.getStreet()) && res;
		res = assertField(expected.getHouseNumber(), result.getHouseNumber()) && res;
		res = assertField(expected.getZipCode(), result.getZipCode()) && res;
		res = assertField(expected.getCity(), result.getCity()) && res;
		res = assertField(expected.getCompany(), result.getCompany()) && res;
		return res;
	}
	
	private static boolean assertField(String expected, String actual) {
		boolean res = true;
		
		if (expected == null) {
			res = actual == null;
		} else {
			res = (expected.equals(actual));
		}
		return res;
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
		} else if (expected == null && results.size() != 0) {
			System.err.println("Expected no result but got at least one result!");
			Assert.fail();
		} else {
			boolean atLeastOneMatches = false;
			
			for (int i=0; i<results.size(); i++) {
				ReverseLookupResponse result = results.get(i);
				Helper.outputResponseAndExpectedResults(result, expected);
				atLeastOneMatches = Helper.assertExpected(result, expected) || atLeastOneMatches;
			}
			
			Assert.assertTrue(atLeastOneMatches);
		}
	}
}
