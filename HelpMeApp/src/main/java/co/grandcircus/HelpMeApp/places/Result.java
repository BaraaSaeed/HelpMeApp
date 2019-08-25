/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp.places;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

	@JsonProperty("formatted_address")
	private String formattedAddress;

	private String name;

	@JsonProperty("opening_hours")
	private OpeningHours openingHours;

	@JsonProperty("place_id")
	private String placeId;

	private Integer rating;
	private String[] types;

	@JsonProperty("user_ratings_total")
	private Integer userRatingsTotal;

	public Result() {
		super();
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OpeningHours getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(OpeningHours openingHours) {
		this.openingHours = openingHours;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public Integer getUserRatingsTotal() {
		return userRatingsTotal;
	}

	public void setUserRatingsTotal(Integer userRatingsTotal) {
		this.userRatingsTotal = userRatingsTotal;
	}

	@Override
	public String toString() {
		return "Result [formattedAddress=" + formattedAddress + ", name=" + name + ", openingHours=" + openingHours
				+ ", placeId=" + placeId + ", rating=" + rating + ", types=" + Arrays.toString(types)
				+ ", userRatingsTotal=" + userRatingsTotal + "]";
	}

}
