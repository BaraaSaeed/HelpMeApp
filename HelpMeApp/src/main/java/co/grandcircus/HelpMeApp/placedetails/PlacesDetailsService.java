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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PlacesDetailsService {

	@Value("${Geocoding.API_KEY}")
	private String key;

	public Result[] getPlaceDetails(String placeId) {
		String fields = "formatted_address,icon,name,permanently_closed,place_id,formatted_phone_number,opening_hours,website,rating,user_ratings_total";
		String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + placeId + "&fields=" + fields
				+ "&key=" + key;
		RestTemplate rest = new RestTemplate();
		PlaceDetailsResponse response = rest.getForObject(url, PlaceDetailsResponse.class);
		return response.getResults();
	}
}