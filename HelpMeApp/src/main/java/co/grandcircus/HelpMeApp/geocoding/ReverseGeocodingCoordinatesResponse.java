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

import java.util.Arrays;

public class ReverseGeocodingCoordinatesResponse {

	private ReverseResult[] results;

	public ReverseGeocodingCoordinatesResponse() {
		super();
	}

	public ReverseResult[] getResults() {
		return results;
	}

	public void setResults(ReverseResult[] results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "ReverseGeocodingCoordinatesResponse [results=" + Arrays.toString(results) + "]";
	}

}
