package co.grandcircus.HelpMeApp.model;

public class Caa {
	
	private String id;
	private String store;
	private Double distance;
	private String permalink;
	private String address;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String lat;
	private String lng;
	private String phone;
	private String fax;
	private String email;
	private String hours;
	private String url;
	private String director;
	private String director_title;
	private String terms;
	
	public Caa() {
		super();
	}
	public Caa(String id, String store, String address, String address2, String city, String state, String zip,
			String lat, String lng, String phone, String email, String url, String terms) {
		super();
		this.id = id;
		this.store = store;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.lat = lat;
		this.lng = lng;
		this.phone = phone;
		this.email = email;
		this.url = url;
		this.terms = terms;
	}
	
	public Caa(String id, String store, Double distance, String permalink, String address, String address2, String city,
			String state, String zip, String country, String lat, String lng, String phone, String fax, String email,
			String hours, String url, String director, String director_title, String terms) {
		super();
		this.id = id;
		this.store = store;
		this.distance = distance;
		this.permalink = permalink;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.lat = lat;
		this.lng = lng;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.hours = hours;
		this.url = url;
		this.director = director;
		this.director_title = director_title;
		this.terms = terms;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDirector_title() {
		return director_title;
	}
	public void setDirector_title(String director_title) {
		this.director_title = director_title;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	@Override
	public String toString() {
		return "Caa [address=" + address + ", store=" + store + ", id=" + id + ", distance=" + distance + ", permalink="
				+ permalink + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", country=" + country + ", lat=" + lat + ", lng=" + lng + ", phone=" + phone + ", fax=" + fax
				+ ", email=" + email + ", hours=" + hours + ", url=" + url + ", director=" + director
				+ ", director_title=" + director_title + ", terms=" + terms + "]";
	}
	
	
	
}
