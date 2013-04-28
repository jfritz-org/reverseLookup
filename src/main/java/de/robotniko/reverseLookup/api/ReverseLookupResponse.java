package de.robotniko.reverseLookup.api;

import de.robotniko.reverseLookup.structs.Person;

public class ReverseLookupResponse implements Comparable<ReverseLookupResponse> {

	private String foundBy;

	protected Person person = new Person();

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (foundBy != null) {
			sb.append("Found by: ");
			sb.append(foundBy);
			sb.append(" ; ");
		}
		
		if (person != null) {
			sb.append(person.toString());
		}
		
		return sb.toString();
	}
	public String getFoundBy() {
		return foundBy;
	}
	public void setFoundBy(String foundBy) {
		this.foundBy = foundBy;
	}
	public String getFirstName() {
		return person.getFirstName();
	}
	public void setFirstName(String firstName) {
		this.person.setFirstName(firstName);
	}
	public String getLastName() {
		return person.getLastName();
	}
	public void setLastName(String lastName) {
		this.person.setLastName(lastName);
	}
	public String getCompany() {
		return person.getCompany();
	}
	public void setCompany(String company) {
		this.person.setCompany(company);
	}
	public String getStreet() {
		return person.getStreet();
	}
	public void setStreet(String street) {
		this.person.setStreet(street);
	}
	public String getHouseNumber() {
		return person.getHouseNumber();
	}
	public void setHouseNumber(String houseNumber) {
		this.person.setHouseNumber(houseNumber);
	}
	public String getZipCode() {
		return person.getZipCode();
	}
	public void setZipCode(String zipCode) {
		this.person.setZipCode(zipCode);
	}
	public String getCity() {
		return person.getCity();
	}
	public void setCity(String city) {
		this.person.setCity(city);
	}

	public int compareTo(ReverseLookupResponse o) {
		return (calculateFilledProperties(o) - calculateFilledProperties(this));
	}

	public static int calculateFilledProperties(final ReverseLookupResponse r) {
		int numFilledFields = 0;
		if (r.person.getCompany() != null && !r.person.getCompany().equals("")) {
			numFilledFields += 2;
		}
		if (r.person.getFirstName() != null && !r.person.getFirstName().equals("")) {
			numFilledFields++;
		}
		if (r.person.getLastName()!= null && !r.person.getLastName().equals("")) {
			numFilledFields++;
		}
		if (r.person.getStreet() != null && !r.person.getStreet().equals("")) {
			numFilledFields++;
		}
		if (r.person.getHouseNumber() != null && !r.person.getHouseNumber().equals("")) {
			numFilledFields++;
		}
		if (r.person.getZipCode() != null && !r.getZipCode().equals("")) {
			numFilledFields++;
		}
		if (r.person.getCity() != null && !r.getCity().equals("")) {
			numFilledFields++;
		}
		return numFilledFields;
	}
}
