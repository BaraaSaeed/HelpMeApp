package co.grandcircus.HelpMeApp.model;

public class MailingAddress {

	private String country;
	private String stateOrProvince;
	private String city;
	private String postalCode;
	private String streetAddress1;
	private String streetAddress2;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	@Override
	public String toString() {
		return "MailingAddress [country=" + country + ", stateOrProvince=" + stateOrProvince + ", city=" + city
				+ ", postalCode=" + postalCode + ", streetAddress1=" + streetAddress1 + ", streetAddress2="
				+ streetAddress2 + "]";
	}

}
