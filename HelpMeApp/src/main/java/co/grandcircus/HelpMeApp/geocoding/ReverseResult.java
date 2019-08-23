/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/
/**
 * 
 */
package co.grandcircus.HelpMeApp.geocoding;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReverseResult {

	@JsonProperty("formatted_address")
	private String formattedAddress;

	public ReverseResult() {
		super();
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	@Override
	public String toString() {
		return "ReverseResult [formattedAddress=" + formattedAddress + "]";
	}

}
