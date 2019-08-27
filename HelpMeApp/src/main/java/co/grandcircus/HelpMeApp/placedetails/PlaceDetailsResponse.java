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
package co.grandcircus.HelpMeApp.placedetails;

import java.util.Arrays;

public class PlaceDetailsResponse {

	private DetailResult result;

	public PlaceDetailsResponse() {
		super();
	}

	public PlaceDetailsResponse(DetailResult result) {
		super();
		this.result = result;
	}

	public DetailResult getResult() {
		return result;
	}

	public void setResult(DetailResult result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "PlaceDetailsResponse [result=" + result + "]";
	}

	
	
}
