package org.jfritz.reverseLookup.structs;

public class Person {

	private String firstName;
	private String lastName;
	private String company;
	private String street;
	private String houseNumber;
	private String zipCode;
	private String city;

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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (firstName != null) {
			sb.append("first name: ");
			sb.append(firstName);
			sb.append(" ; ");
		}
		
		if (lastName != null) {
			sb.append("last name: ");
			sb.append(lastName);
			sb.append(" ; ");
		}
		
		if (company != null) {
			sb.append("company: ");
			sb.append(company);
			sb.append(" ; ");
		}
		
		if (street != null) {		
			sb.append("street: ");
			sb.append(street);
			sb.append(" ; ");
		}
		
		if (houseNumber != null) {
			sb.append("house number: ");
			sb.append(houseNumber);
			sb.append(" ; ");
		}
		
		if (zipCode != null) {
			sb.append("zipCode: ");
			sb.append(zipCode);
			sb.append(" ; ");
		}
		
		if (city != null) {
			sb.append("city: ");
			sb.append(city);
			sb.append(" ; ");
		}

		return sb.toString();
	}
}
