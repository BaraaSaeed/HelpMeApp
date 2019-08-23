/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp.geocoding;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeocodingService {

	@Value("${Geocoding.API_KEY}")
	private String key;

	public Double getLatitudeCoordinate(String buildingNumber, String street, String city, String state) {
		String address = buildingNumber + "," + street + "," + city + "," + state;
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + key;
		RestTemplate rest = new RestTemplate();
		GeocodingCoordinatesResponse response = rest.getForObject(url, GeocodingCoordinatesResponse.class);
		return response.getResults()[0].getGeometry().getLocation().getLatitude();
	}

	public Double getLongitudeCoordinate(String buildingNumber, String street, String city, String state) {
		String address = buildingNumber + "," + street + "," + city + "," + state;
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + key;
		RestTemplate rest = new RestTemplate();
		GeocodingCoordinatesResponse response = rest.getForObject(url, GeocodingCoordinatesResponse.class);
		return response.getResults()[0].getGeometry().getLocation().getLongitude();
	}

	public String getRevereseGeocoding(Double latitude, Double longitude) {
		String latlng = Double.toString(latitude) + "," + Double.toString(longitude);
		String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latlng + "&key=" + key;
		RestTemplate rest = new RestTemplate();
		ReverseGeocodingCoordinatesResponse response = rest.getForObject(url,
				ReverseGeocodingCoordinatesResponse.class);
		return response.getResults()[0].getFormattedAddress();
	}
}
