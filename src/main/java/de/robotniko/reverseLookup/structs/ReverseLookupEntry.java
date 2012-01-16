package de.robotniko.reverseLookup.structs;

public class ReverseLookupEntry {

	private String namePattern;
	private String firstNamePattern;
	private String lastNamePattern;
	private String streetPattern;
	private String houseNumberPattern;
	private String cityPattern;
	private String zipPattern;
	private String companyPattern;

	private String firstOccurance;
	private boolean swapFirstAndLastName = false;

	public String getNamePattern() {
		return namePattern;
	}

	public void setNamePattern(String namePattern) {
		this.namePattern = namePattern;
	}

	public String getFirstNamePattern() {
		return firstNamePattern;
	}

	public void setFirstNamePattern(String firstNamePattern) {
		this.firstNamePattern = firstNamePattern;
	}

	public String getLastNamePattern() {
		return lastNamePattern;
	}

	public void setLastNamePattern(String lastNamePattern) {
		this.lastNamePattern = lastNamePattern;
	}

	public String getStreetPattern() {
		return streetPattern;
	}

	public void setStreetPattern(String streetPattern) {
		this.streetPattern = streetPattern;
	}

	public String getHouseNumberPattern() {
		return houseNumberPattern;
	}

	public void setHouseNumberPattern(String houseNumberPattern) {
		this.houseNumberPattern = houseNumberPattern;
	}

	public String getCityPattern() {
		return cityPattern;
	}

	public void setCityPattern(String cityPattern) {
		this.cityPattern = cityPattern;
	}

	public String getZipPattern() {
		return zipPattern;
	}

	public void setZipPattern(String zipPattern) {
		this.zipPattern = zipPattern;
	}

	public String getCompanyPattern() {
		return this.companyPattern;
	}

	public void setCompanyPattern(String companyPattern) {
		this.companyPattern = companyPattern;
	}

	public String getFirstOccurance() {
		return firstOccurance;
	}

	public void setFirstOccurance(String firstOccurance) {
		this.firstOccurance = firstOccurance;
	}

	public void setSwapFirstAndLastName(final boolean value) {
		this.swapFirstAndLastName = value;
	}

	public boolean shouldSwapFirstAndLastName() {
		return this.swapFirstAndLastName;
	}
}
