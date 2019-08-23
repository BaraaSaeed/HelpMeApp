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
	private Integer user_ratings_total;

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

	public Integer getUser_ratings_total() {
		return user_ratings_total;
	}

	public void setUser_ratings_total(Integer user_ratings_total) {
		this.user_ratings_total = user_ratings_total;
	}

	@Override
	public String toString() {
		return "Result [formattedAddress=" + formattedAddress + ", name=" + name + ", openingHours=" + openingHours
				+ ", placeId=" + placeId + ", rating=" + rating + ", types=" + Arrays.toString(types)
				+ ", user_ratings_total=" + user_ratings_total + "]";
	}

}
