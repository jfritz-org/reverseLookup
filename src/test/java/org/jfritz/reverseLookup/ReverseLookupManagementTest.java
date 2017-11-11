package org.jfritz.reverseLookup;

import junit.framework.Assert;

import org.jfritz.reverseLookup.exceptions.CountryNotSupportedException;
import org.junit.Before;
import org.junit.Test;

import org.jfritz.reverseLookup.exceptions.PhoneNumberNotSupportedException;
import org.jfritz.reverseLookup.structs.ReverseLookupCountry;

public class ReverseLookupManagementTest {

	private ReverseLookupManagement rlm = new ReverseLookupManagement();

	@Before
	public void init() {
		rlm.addReverseLookupCountry(createCountry("+1"));
		rlm.addReverseLookupCountry(createCountry("+49"));
		rlm.addReverseLookupCountry(createCountry("+123"));
		rlm.addReverseLookupCountry(createCountry("+23456"));
	}

	@Test
	public void test1() throws CountryNotSupportedException {
		ReverseLookupCountry country = rlm.getCountry("+1");
		Assert.assertEquals("+1", country.getCode());
		Assert.assertEquals(0, country.getNumWebsites());
	}

	@Test
	public void test49() throws CountryNotSupportedException {
		ReverseLookupCountry country = rlm.getCountry("+49");
		Assert.assertEquals("+49", country.getCode());
		Assert.assertEquals(0, country.getNumWebsites());
	}

	@Test
	public void test123() throws CountryNotSupportedException {
		ReverseLookupCountry country = rlm.getCountry("+123");
		Assert.assertEquals("+123", country.getCode());
		Assert.assertEquals(0, country.getNumWebsites());
	}

	@Test
	public void test23456() throws CountryNotSupportedException {
		ReverseLookupCountry country = rlm.getCountry("+23456");
		Assert.assertEquals("+23456", country.getCode());
		Assert.assertEquals(0, country.getNumWebsites());
	}

	@Test(expected=CountryNotSupportedException.class)
	public void testCountryNotInMap() throws CountryNotSupportedException {
		rlm.getCountry("+500");
	}

	@Test(expected=CountryNotSupportedException.class)
	public void testNull() throws CountryNotSupportedException {
		rlm.getCountry(null);
	}

	@Test(expected=CountryNotSupportedException.class)
	public void testEmpty() throws CountryNotSupportedException {
		rlm.getCountry("");
	}

	@Test(expected=CountryNotSupportedException.class)
	public void testShort() throws CountryNotSupportedException {
		rlm.getCountry("+");
	}

	@Test
	public void testWrongFormat() throws CountryNotSupportedException {
		ReverseLookupCountry country = rlm.getCountry("49");
		Assert.assertEquals("+49", country.getCode());
		Assert.assertEquals(0, country.getNumWebsites());
	}

	@Test(expected=PhoneNumberNotSupportedException.class)
	public void testByPhoneNumberNull() throws PhoneNumberNotSupportedException {
		rlm.getCountryByPhoneNumber(null);
	}

	@Test(expected=PhoneNumberNotSupportedException.class)
	public void testByPhoneNumberEmpty() throws PhoneNumberNotSupportedException {
		rlm.getCountryByPhoneNumber("");
	}

	@Test(expected=PhoneNumberNotSupportedException.class)
	public void testByPhoneNumberShort() throws PhoneNumberNotSupportedException {
		rlm.getCountryByPhoneNumber("+");
	}

	@Test(expected=PhoneNumberNotSupportedException.class)
	public void testByPhoneNumberWrongFormatAndTooShort() throws PhoneNumberNotSupportedException {
		rlm.getCountryByPhoneNumber("4");
	}

	@Test
	public void testByPhoneNumber() throws PhoneNumberNotSupportedException {
		ReverseLookupCountry country = rlm.getCountryByPhoneNumber("+49721987654");
		Assert.assertEquals("+49", country.getCode());
		Assert.assertEquals(0, country.getNumWebsites());
	}

	@Test
	public void testByPhoneNumberWrongFormat() throws PhoneNumberNotSupportedException {
		ReverseLookupCountry country = rlm.getCountryByPhoneNumber("49721987654");
		Assert.assertEquals("+49", country.getCode());
		Assert.assertEquals(0, country.getNumWebsites());
	}

	@Test
	public void testByPhoneNumberLongerCountryCodePreferred() throws PhoneNumberNotSupportedException {
		ReverseLookupCountry country = rlm.getCountryByPhoneNumber("+12345678");
		Assert.assertEquals("+123", country.getCode());
		Assert.assertEquals(0, country.getNumWebsites());
	}


	private ReverseLookupCountry createCountry(final String code) {
		ReverseLookupCountry country = new ReverseLookupCountry();
		country.setCode(code);
		return country;
	}
}
