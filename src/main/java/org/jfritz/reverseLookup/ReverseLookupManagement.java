package org.jfritz.reverseLookup;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import org.jfritz.reverseLookup.exceptions.CountryNotSupportedException;
import org.jfritz.reverseLookup.exceptions.PhoneNumberNotSupportedException;
import org.jfritz.reverseLookup.structs.ReverseLookupCountry;

public class ReverseLookupManagement {
	private final static Logger LOG = Logger.getLogger(ReverseLookupManagement.class);

	private Map<String, ReverseLookupCountry> map = new HashMap<String, ReverseLookupCountry>();
	@SuppressWarnings({ "rawtypes" })
	private Map[] mapArray = new Map[6];

	public ReverseLookupManagement() {
		Map<String, ReverseLookupCountry> map5 = new HashMap<String, ReverseLookupCountry>();
		Map<String, ReverseLookupCountry> map4 = new HashMap<String, ReverseLookupCountry>();
		Map<String, ReverseLookupCountry> map3 = new HashMap<String, ReverseLookupCountry>();
		Map<String, ReverseLookupCountry> map2 = new HashMap<String, ReverseLookupCountry>();

		mapArray[0] = null;
		mapArray[1] = null;
		mapArray[2] = map2;
		mapArray[3] = map3;
		mapArray[4] = map4;
		mapArray[5] = map5;
	}

	@SuppressWarnings("unchecked")
	public void addReverseLookupCountry(final ReverseLookupCountry country) {
		if (!map.containsKey(country.getCode())) {
			map.put(country.getCode(), country);
		}

		int codeLength = country.getCode().length();
		if (codeLength > 1 && codeLength<6) {
			if (!mapArray[codeLength].containsKey(country.getCode())) {
				mapArray[codeLength].put(country.getCode(), country);
			}
		} else {
			LOG.error("Found country with unexpected length of country code: " + country.getCode());
		}
	}

	public ReverseLookupCountry getCountry(final String countryCode) throws CountryNotSupportedException {
		String cCode = countryCode;
		if (countryCode != null && !countryCode.startsWith("+")) {
			LOG.warn("Got country code in wrong format. Missing + in front of country code. Appending + now to fix this");
			cCode = "+" + countryCode;
		}

		if (map.containsKey(cCode)) {
			return map.get(cCode);
		} else {
			throw new CountryNotSupportedException(cCode);
		}
	}


	public ReverseLookupCountry getCountryByPhoneNumber(final String phoneNumber) throws PhoneNumberNotSupportedException {
		String number = phoneNumber;
		if (number != null && !number.startsWith("+")) {
			number = "+" + number;
			LOG.warn("Got number in wrong format. Missing + in front of phone number. Appending + now to fix this");
		}
		if (number != null && number.length() > 1) {
			for (int i=number.length()-1; i>1; i--) {
				String key = number.substring(0, i);
				if (key.length()>1 && key.length()<6) {
					if (mapArray[key.length()].containsKey(key)) {
						return (ReverseLookupCountry)mapArray[key.length()].get(key);
					}
				}
			}
		}
		throw new PhoneNumberNotSupportedException(number);
	}
}
