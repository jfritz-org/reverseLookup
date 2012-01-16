package de.robotniko.reverseLookup.countries;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import de.robotniko.MyTestHelper;
import de.robotniko.reverseLookup.api.IReverseLookupFinishedListener;
import de.robotniko.reverseLookup.api.IReverseLookupResponseListener;
import de.robotniko.reverseLookup.api.IReverseLookupService;
import de.robotniko.reverseLookup.api.ReverseLookupFacade;
import de.robotniko.reverseLookup.api.ReverseLookupRequest;
import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;

public class GermanyAsyncTest {

	private static IReverseLookupService service = ReverseLookupFacade.getReverseLookupService();

	@BeforeClass
	public static void setup() throws ReverseLookupException {
		MyTestHelper.initLogging();
		service.loadConfig(MyTestHelper.class.getResourceAsStream("/reverselookup.xml"));
	}

	@After
	public void after() throws InterruptedException {
		int sleeptime = 2;
		System.out.println("\nSleeping for " + sleeptime + " seconds");
		Thread.sleep(sleeptime * 1000);
	}

	@Test
	public void testAsync() throws InterruptedException, ReverseLookupException {
		final List<ReverseLookupRequest> requestList = new ArrayList<ReverseLookupRequest>();

		ResponseAssertListener l1 = new ResponseAssertListener(1, null, "Am Theater", "STAATSTHEATER", "Am Theater", "", "38100", "Braunschweig");
		ReverseLookupRequest request1 = new ReverseLookupRequest("+495311234567", l1);
		requestList.add(request1);

		ResponseAssertListener l2 = new ResponseAssertListener(1, null, "Annette", "Zehner", "An der Alten Kirche", "3", "35745", "Herborn");
		ReverseLookupRequest request2 = new ReverseLookupRequest("+49277242239", l2);
		requestList.add(request2);

		ResponseAssertListener l3 = new ResponseAssertListener(1, null, "Rainer", "Ullrich", "Zugspitzstr.", "20", "85609", "Aschheim");
		ReverseLookupRequest request3 = new ReverseLookupRequest("+498990199190", l3);
		requestList.add(request3);

		final GermanyAsyncTest asyncTest = this;
		IReverseLookupFinishedListener finishedListener = new IReverseLookupFinishedListener() {

			public void finished() {
				synchronized(asyncTest) {
					asyncTest.notify();
				}
			}
		};

		service.asynchronousLookup(requestList, finishedListener);

		Thread.sleep(3000);

		ResponseAssertListener l4 = new ResponseAssertListener(2, null, "O. Dr.med.", "Then", "Bahnhofplatz", "7", "82054", "Sauerlach");
		ReverseLookupRequest request4 = new ReverseLookupRequest("+498104889820", l4);
		requestList.add(request4);

		service.asynchronousLookup(request4, finishedListener);

		synchronized(this) {
			wait();
		}
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

	private class ResponseAssertListener implements IReverseLookupResponseListener {

		private int numResponses;
		private String company;
		private String firstName;
		private String lastName;
		private String street;
		private String houseNumber;
		private String zipCode;
		private String city;

		public ResponseAssertListener(final int numResponses, final String company,
				final String firstName, final String lastName, final String street,
				final String houseNumber, final String zipCode, final String city) {
			this.numResponses = numResponses;
			this.company = company;
			this.firstName = firstName;
			this.lastName = lastName;
			this.street = street;
			this.houseNumber = houseNumber;
			this.zipCode = zipCode;
			this.city = city;
		}

		public void lookupResponse(List<ReverseLookupResponse> response,
				int percent) {
			outputResponse(response.get(0));

			Assert.assertEquals(numResponses, response.size());
			Assert.assertEquals(this.company, response.get(0).getCompany());
			Assert.assertEquals(this.firstName, response.get(0).getFirstName());
			Assert.assertEquals(this.lastName, response.get(0).getLastName());
			Assert.assertEquals(this.street, response.get(0).getStreet());
			Assert.assertEquals(this.houseNumber, response.get(0).getHouseNumber());
			Assert.assertEquals(this.zipCode, response.get(0).getZipCode());
			Assert.assertEquals(this.city, response.get(0).getCity());
			System.out.println(percent + "% finished");
		}
	}
}
