/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp.placedetails;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailResult implements Serializable {

	private static final long serialVersionUID = 1L;


	@JsonProperty("formatted_address")
	private String formattedAddress;
	private String icon;
	private Photo[] photos;
	private String name;
	@JsonProperty("permanently_closed")
	private Boolean perClosed;
	@JsonProperty("place_id")
	private String placeId;
	@JsonProperty("formatted_phone_number")
	private String phone;
	@JsonProperty("opening_hours")
	private BusinessHours businessHours;
	private String website;
	private Integer rating;
	@JsonProperty("user_ratings_total")
	private Integer userRatings;
	private Geometry geometry;

	public DetailResult() {
		super();
	}

	public DetailResult(String formattedAddress, String icon, Photo[] photos, String name, Boolean perClosed,
			String placeId, String phone, BusinessHours businessHours, String website, Integer rating,
			Integer userRatings, Geometry geometry) {
		super();
		this.formattedAddress = formattedAddress;
		this.icon = icon;
		this.photos = photos;
		this.name = name;
		this.perClosed = perClosed;
		this.placeId = placeId;
		this.phone = phone;
		this.businessHours = businessHours;
		this.website = website;
		this.rating = rating;
		this.userRatings = userRatings;
		this.geometry = geometry;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Photo[] getPhotos() {
		return photos;
	}

	public void setPhotos(Photo[] photos) {
		this.photos = photos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPerClosed() {
		return perClosed;
	}

	public void setPerClosed(Boolean perClosed) {
		this.perClosed = perClosed;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BusinessHours getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(BusinessHours businessHours) {
		this.businessHours = businessHours;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return "DetailResult [formattedAddress=" + formattedAddress + ", icon=" + icon + ", photos="
				+ Arrays.toString(photos) + ", name=" + name + ", perClosed=" + perClosed + ", placeId=" + placeId
				+ ", phone=" + phone + ", businessHours=" + businessHours + ", website=" + website + ", rating="
				+ rating + ", userRatings=" + userRatings + ", geometry=" + geometry + "]";
	}

}