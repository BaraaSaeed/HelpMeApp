package co.grandcircus.HelpMeApp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "orgs")
public class Org implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long databaseId;
	private String name;
	private String services;
	private String orgId;
	private String apiId;
	private String secret;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	private String url;
	private String state;
	private Double longitude;
	private Double latitude;
	private String img;

	public Org() {
		super();
	}

	public Org(Long databaseId, String name, String services, String orgId, String apiId, String secret, String address,
			String city, String zip, String phone, String email, String url, String state, Double longitude,
			Double latitude, String img) {
		super();
		this.databaseId = databaseId;
		this.name = name;
		this.services = services;
		this.orgId = orgId;
		this.apiId = apiId;
		this.secret = secret;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.url = url;
		this.state = state;
		this.longitude = longitude;
		this.latitude = latitude;
		this.img = img;
	}

	public Org(String name, String services, String orgId, String address, String city, String zip, String phone,
			String email, String url, String state, Double longitude, Double latitude) {
		super();
		this.name = name;
		this.services = services;
		this.orgId = orgId;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.url = url;
		this.state = state;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Long getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Long databaseId) {
		this.databaseId = databaseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Org [databaseId=" + databaseId + ", name=" + name + ", services=" + services + ", orgId=" + orgId
				+ ", apiId=" + apiId + ", secret=" + secret + ", address=" + address + ", city=" + city + ", zip=" + zip
				+ ", phone=" + phone + ", email=" + email + ", url=" + url + ", state=" + state + ", longitude="
				+ longitude + ", latitude=" + latitude + ", img=" + img + "]";
	}
	
}