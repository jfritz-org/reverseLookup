package de.robotniko.reverseLookup.api;

public class ReverseLookupResponse implements Comparable<ReverseLookupResponse> {

	private String foundBy;

	private String firstName;
	private String lastName;
	private String company;
	private String street;
	private String houseNumber;
	private String zipCode;
	private String city;

	public String getFoundBy() {
		return foundBy;
	}
	public void setFoundBy(String foundBy) {
		this.foundBy = foundBy;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int compareTo(ReverseLookupResponse o) {
		return (calculateFilledProperties(o) - calculateFilledProperties(this));
	}

	public static int calculateFilledProperties(final ReverseLookupResponse r) {
		int numFilledFields = 0;
		if (r.company != null && !r.company.equals("")) {
			numFilledFields += 2;
		}
		if (r.firstName != null && !r.firstName.equals("")) {
			numFilledFields++;
		}
		if (r.lastName != null && !r.lastName.equals("")) {
			numFilledFields++;
		}
		if (r.street != null && !r.street.equals("")) {
			numFilledFields++;
		}
		if (r.houseNumber != null && !r.houseNumber.equals("")) {
			numFilledFields++;
		}
		if (r.zipCode != null && !r.zipCode.equals("")) {
			numFilledFields++;
		}
		if (r.city != null && !r.city.equals("")) {
			numFilledFields++;
		}
		return numFilledFields;
	}
}
