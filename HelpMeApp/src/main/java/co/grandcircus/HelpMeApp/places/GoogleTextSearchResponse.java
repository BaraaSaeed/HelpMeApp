/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp.places;

import java.util.Arrays;

public class GoogleTextSearchResponse {

	private Result[] results;

	public GoogleTextSearchResponse() {
		super();
	}

	public Result[] getResults() {
		return results;
	}

	public void setResults(Result[] results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "GoogleTextSearchResponse [results=" + Arrays.toString(results) + "]";
	}

}
