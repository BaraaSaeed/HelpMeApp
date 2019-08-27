/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp.placedetails;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessHours {

	@JsonProperty("open_now")
	private Boolean openNow;
	@JsonProperty("weekday_text")
	private String[] weekdayText;
	
	public BusinessHours() {
		super();
	}

	public BusinessHours(Boolean openNow, String[] weekdayText) {
		super();
		this.openNow = openNow;
		this.weekdayText = weekdayText;
	}

	public Boolean getOpenNow() {
		return openNow;
	}

	public void setOpenNow(Boolean openNow) {
		this.openNow = openNow;
	}

	public String[] getWeekdayText() {
		return weekdayText;
	}

	public void setWeekdayText(String[] weekdayText) {
		this.weekdayText = weekdayText;
	}

	@Override
	public String toString() {
		return "BusinessHours [openNow=" + openNow + ", weekdayText=" + Arrays.toString(weekdayText) + "]";
	}

	

}
