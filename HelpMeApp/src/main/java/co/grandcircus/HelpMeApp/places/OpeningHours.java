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
package co.grandcircus.HelpMeApp.places;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpeningHours {

	@JsonProperty("open_now")
	private boolean openNow;

	public OpeningHours() {
		super();
	}

	public boolean isOpenNow() {
		return openNow;
	}

	public void setOpenNow(boolean openNow) {
		this.openNow = openNow;
	}

	@Override
	public String toString() {
		return "OpeningHours [openNow=" + openNow + "]";
	}

}
