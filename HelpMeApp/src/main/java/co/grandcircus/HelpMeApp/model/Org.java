package co.grandcircus.HelpMeApp.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import co.grandcircus.HelpMeApp.placedetails.BusinessHours;
import co.grandcircus.HelpMeApp.placedetails.Photo;

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
	private String formattedAddress;
	private String phone;
	private String email;
	private String url;
	private Double longitude;
	private Double latitude;
	private String icon;
//	private Photo[] photos;
	private Long avgResponseTimeInMinutes;
	private Boolean perClosed;
//	private String businessHours;
	private Integer rating;
	private Integer userRatings;

	public Org() {
		super();
	}
	
	//GooglePlaces
	public Org(String name, String orgId, String formattedAddress, Double longitude, Double latitude) {
		super();
		this.name = name;
		this.orgId = orgId;
		this.formattedAddress = formattedAddress;
		this.longitude = longitude;
		this.latitude = latitude;
	}


	//GoogleDetails Constructor
	public Org(String name, String orgId, String formattedAddress, String phone,
			String url, Double longitude, Double latitude, String icon, Boolean perClosed,
			 Integer rating) {
		super();
		this.name = name;
		this.orgId = orgId;
		this.formattedAddress = formattedAddress;
		this.phone = phone;
		this.url = url;
		this.longitude = longitude;
		this.latitude = latitude;
		this.icon = icon;
//		this.photos = photos;
		this.perClosed = perClosed;
//		this.businessHours = businessHours;
		this.rating = rating;
	}

	// HudAndCAA Constructor
	public Org(String name, String services, String orgId, String formattedAddress,
			String phone, String email, String url, Double longitude, Double latitude) {
		super();
		this.name = name;
		this.services = services;
		this.orgId = orgId;
		this.formattedAddress = formattedAddress;
		this.phone = phone;
		this.email = email;
		this.url = url;
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

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getAvgResponseTimeInMinutes() {
		return avgResponseTimeInMinutes;
	}

	public void setAvgResponseTimeInMinutes(Long avgResponseTimeInMinutes) {
		this.avgResponseTimeInMinutes = avgResponseTimeInMinutes;
	}

	public Boolean getPerClosed() {
		return perClosed;
	}

	public void setPerClosed(Boolean perClosed) {
		this.perClosed = perClosed;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(Integer userRatings) {
		this.userRatings = userRatings;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Org [databaseId=" + databaseId + ", name=" + name + ", services=" + services + ", orgId=" + orgId
				+ ", apiId=" + apiId + ", secret=" + secret + ", formattedAddress=" + formattedAddress + ", phone="
				+ phone + ", email=" + email + ", url=" + url + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", icon=" + icon + ", avgResponseTimeInMinutes=" + avgResponseTimeInMinutes + ", perClosed="
				+ perClosed + ", rating=" + rating + ", userRatings=" + userRatings + "]";
	}

}