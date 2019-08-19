package co.grandcircus.HelpMeApp.model;

public class OrgObject {
	private String agcid;
	private String adr1;
	private String adr2;
	private String city;
	private String email;
	private String fax;
	private String nme;
	private String phone1;
	private String statecd;
	private String weburl;
	private String zipcd;
	private double agc_ADDR_LONGITUDE;
	private double agc_ADDR_LATITUDE;
	private String languages;
	private String services;
	

	public String getAgcid() {
		return agcid;
	}

	public void setAgcid(String agcid) {
		this.agcid = agcid;
	}

	public String getAdr1() {
		return adr1;
	}

	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}

	public String getAdr2() {
		return adr2;
	}

	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNme() {
		return nme;
	}

	public void setNme(String nme) {
		this.nme = nme;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getStatecd() {
		return statecd;
	}

	public void setStatecd(String statecd) {
		this.statecd = statecd;
	}

	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public String getZipcd() {
		return zipcd;
	}

	public void setZipcd(String zipcd) {
		this.zipcd = zipcd;
	}

	public double getAgc_ADDR_LONGITUDE() {
		return agc_ADDR_LONGITUDE;
	}

	public void setAgc_ADDR_LONGITUDE(double agc_ADDR_LONGITUDE) {
		this.agc_ADDR_LONGITUDE = agc_ADDR_LONGITUDE;
	}

	public double getAgc_ADDR_LATITUDE() {
		return agc_ADDR_LATITUDE;
	}

	public void setAgc_ADDR_LATITUDE(double agc_ADDR_LATITUDE) {
		this.agc_ADDR_LATITUDE = agc_ADDR_LATITUDE;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "OrgObject [agcid=" + agcid + ", adr1=" + adr1 + ", adr2=" + adr2 + ", city=" + city + ", email=" + email
				+ ", fax=" + fax + ", nme=" + nme + ", phone1=" + phone1 + ", statecd=" + statecd + ", weburl=" + weburl
				+ ", zipcd=" + zipcd + ", agc_ADDR_LONGITUDE=" + agc_ADDR_LONGITUDE + ", agc_ADDR_LATITUDE="
				+ agc_ADDR_LATITUDE + ", languages=" + languages + ", services=" + services + "]";
	}

}
