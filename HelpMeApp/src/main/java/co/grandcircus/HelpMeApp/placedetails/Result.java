/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp.placedetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.grandcircus.HelpMeApp.places.OpeningHours;

public class Result {

	@JsonProperty("formatted_address")
	private String address;
	private String icon;
	private String name;
	/*
	 * If the place is not permanently closed, the permanently_closed flag is absent
	 * from the response.
	 */
	@JsonProperty("permanently_closed")
	private boolean perClosed;
	@JsonProperty("place_id")
	private String placeId;
	@JsonProperty("formatted_phone_number")
	private String phone;
	@JsonProperty("opening_hours")
	private OpeningHours businessHours;
	private String website;
	private Integer rating;
	@JsonProperty("user_ratings_total")
	private Integer userRatings;

	public Result() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPerClosed() {
		return perClosed;
	}

	public void setPerClosed(boolean perClosed) {
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

	public OpeningHours getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(OpeningHours businessHours) {
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

	@Override
	public String toString() {
		return "Result [address=" + address + ", icon=" + icon + ", name=" + name + ", perClosed=" + perClosed
				+ ", placeId=" + placeId + ", phone=" + phone + ", businessHours=" + businessHours + ", website="
				+ website + ", rating=" + rating + ", userRatings=" + userRatings + "]";
	}

}
