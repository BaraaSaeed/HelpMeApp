/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp.places;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GooglePlacesService {

	@Value("${Geocoding.API_KEY}")
	private String key;

	/*
	 * This method requires a search text only... Baraa.
	 */
	public Result[] getListOfPlacesWithoutAddressBiased(String searchText) {
		String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + searchText + "&key=" + key;
		RestTemplate rest = new RestTemplate();
		GoogleTextSearchResponse response = rest.getForObject(url, GoogleTextSearchResponse.class);
		return response.getResults();
	}

	/*
	 * This method requires a search text, a latitude and a longitude, and it
	 * assumes a radius of 10000 meters... Baraa
	 */
	public Result[] getListOfPlacesWithAddressBiased(String searchText, Double latitude, Double longitude) {
		String location = Double.toString(latitude) + "," + Double.toString(longitude);
		String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + searchText + "&location="
				+ location + "&radius=" + 10000 + "&key=" + key;
		RestTemplate rest = new RestTemplate();
		GoogleTextSearchResponse response = rest.getForObject(url, GoogleTextSearchResponse.class);
		return response.getResults();
	}
}
