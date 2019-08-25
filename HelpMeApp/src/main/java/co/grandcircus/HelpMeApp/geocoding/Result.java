/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp.geocoding;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

	@JsonProperty("formatted_address")
	private String formattedAddress;
	private Geometry geometry;
	@JsonProperty("place_id")
	private String placeId;

	public Result() {
		super();
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	@Override
	public String toString() {
		return "Result [formattedAddress=" + formattedAddress + ", geometry=" + geometry + ", placeId=" + placeId + "]";
	}

}
