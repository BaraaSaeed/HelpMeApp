/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali, Gerard Breitenbeck, Sienna Harris -  API and implementation.
 * All rights reserved.
*/
/**
 * 
 */
package co.grandcircus.HelpMeApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.HelpMeApp.geocoding.GeocodingCoordinatesResponse;
import co.grandcircus.HelpMeApp.geocoding.ReverseGeocodingCoordinatesResponse;
import co.grandcircus.HelpMeApp.model.Caa;
import co.grandcircus.HelpMeApp.model.Hud;
import co.grandcircus.HelpMeApp.model.HudService;
import co.grandcircus.HelpMeApp.model.Org;
import co.grandcircus.HelpMeApp.model.User;
import co.grandcircus.HelpMeApp.placedetails.DetailResult;
import co.grandcircus.HelpMeApp.placedetails.PlaceDetailsResponse;
import co.grandcircus.HelpMeApp.places.GoogleTextSearchResponse;
import co.grandcircus.HelpMeApp.places.Result;

@Component
public class ApiService {

	@Value("${Geocoding.API_KEY}")
	private String geoKey;
	private RestTemplate restTemplate = new RestTemplate();

	{
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "Spring");
			return execution.execute(request, body);
		};
		restTemplate = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}

	public List<Org> findAllHud(String url) {
		Hud[] response = restTemplate.getForObject(url, Hud[].class);

		List<Org> orgs = new ArrayList<>();
		for (Hud each : response) {
			Org org = new Org(each.getNme(), each.getServices(), Long.toString(each.getAgcid()),
					each.getAdr1() + " " + each.getAdr2() + " " + capitalize(each.getCity()) + " " + each.getStatecd() + " " + each.getZipcd(),
					each.getPhone1(), each.getEmail(), each.getWeburl(), 
					each.getAgc_ADDR_LONGITUDE(), each.getAgc_ADDR_LATITUDE());
			org.setApiId(makeApiId("HUD", org));
			orgs.add(org);
		}
		return orgs;
	}

	public List<Org> findHudByOrgName(String url) {
		Hud[] response = restTemplate.getForObject(url, Hud[].class);

		List<Org> orgs = new ArrayList<>();
		for (Hud each : response) {
			Org org = new Org(each.getNme(), each.getServices(), Long.toString(each.getAgcid()),
					each.getAdr1() + " " + each.getAdr2() + " " + capitalize(each.getCity()) + " " + each.getStatecd() + " " + each.getZipcd(),
					each.getPhone1(), each.getEmail(), each.getWeburl(),
					each.getAgc_ADDR_LONGITUDE(), each.getAgc_ADDR_LATITUDE());
			org.setApiId(makeApiId("HUD", org));
			orgs.add(org);
		}
		return orgs;
	}

	public HudService[] listServices(String url) {
		HudService[] services = restTemplate.getForObject(url, HudService[].class);
		return services;
	}

	public List<Org> findCaas(String url) {
		Caa[] response = restTemplate.getForObject(url, Caa[].class);
		List<Org> orgs = new ArrayList<>();
		for (Caa each : response) {
			Org org = new Org(each.getStore(), "CAA_SERVICES", each.getId(),
					each.getAddress() + " " + each.getAddress2() + " " + each.getCity() + " " + each.getState() + " " + each.getZip(), each.getPhone(),
					each.getEmail(), each.getUrl(), parseToDoubleWrapper(each.getLng()),
					parseToDoubleWrapper(each.getLat()));
			org.setApiId(makeApiId("CAA", org));
			orgs.add(org);
		}
		return orgs;
	}

	public Double getLatitudeCoordinate(String url) {
		GeocodingCoordinatesResponse response = restTemplate.getForObject(url, GeocodingCoordinatesResponse.class);
		return response.getResults()[0].getGeometry().getLocation().getLatitude();
	}

	public Double getLongitudeCoordinate(String url) {
		GeocodingCoordinatesResponse response = restTemplate.getForObject(url, GeocodingCoordinatesResponse.class);
		return response.getResults()[0].getGeometry().getLocation().getLongitude();
	}

	public String getRevereseGeocoding(Double latitude, Double longitude) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + buildLocation(latitude, longitude)
				+ "&key=" + geoKey;
		ReverseGeocodingCoordinatesResponse response = restTemplate.getForObject(url,
				ReverseGeocodingCoordinatesResponse.class);
		return response.getResults()[0].getFormattedAddress();
	}
	
	public List<Org> getListOfPlacesWithAddressBiased(String searchText, Double latitude, Double longitude) {
		String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + searchText + "&location="
				+ buildLocation(latitude, longitude) + "&radius=" + 5000 + "&key=" + geoKey;
		GoogleTextSearchResponse response = restTemplate.getForObject(url, GoogleTextSearchResponse.class);
		List<Org> orgs = new ArrayList<>();
		for (Result each : response.getResults()) {
			System.out.println("Getting places: " + latitude + longitude);
			Org org = new Org(each.getName(), each.getPlaceId(),
					each.getFormattedAddress(), latitude, longitude);
			org.setApiId(makeApiId("GOOGLE", org));
			orgs.add(org);
			System.out.println(org.getApiId());
		}
		return orgs;
	}
	


	public Org getPlaceDetails(String url) {
		PlaceDetailsResponse response = restTemplate.getForObject(url, PlaceDetailsResponse.class);
		DetailResult result = response.getResult();
		Org org = new Org(result.getName(), result.getPlaceId(), result.getFormattedAddress(),
				 result.getPhone(), result.getWebsite(),result.getGeometry().getLocation().getLat(), 
				 result.getGeometry().getLocation().getLng(),
				result.getIcon(), result.getPerClosed(), result.getRating());
		// ParseAddress Key: Number and street[0], City[1], State[2], zip[3]
		org.setApiId(makeApiId("GOOGLE", org));
		return org;
	}

	public Org findByApiId(String apiId) {
		Org foundOrg = new Org();
		if (apiId.startsWith("HUD")) {
			foundOrg = findHudByApiId(apiId);
		} else if (apiId.startsWith("CAA")) {
			foundOrg = findCaaByApiId(apiId);
		} else if (apiId.startsWith("GOOGLE")) {
			foundOrg = findGoogleByApiId(apiId);
		}
		return foundOrg;
	}

	private Org findHudByApiId(String apiId) {
		String id[] = apiId.split(":::");
		List<Org> orgs = findHudByOrgName(hudNameSearch(id[1]));
		Org foundOrg = new Org();
		for (Org each : orgs) {
			if (each.getOrgId().equals(id[2])) {
				foundOrg = each;
				break;
			}
		}
		return foundOrg;
	}

	private Org findCaaByApiId(String apiId) {
		String id[] = apiId.split(":::");
		List<Org> orgs = findCaas(caaFindInRadiusDetroit(100, 100));
		Org foundOrg = new Org();
		for (Org each : orgs) {
			if (each.getOrgId().equals(id[2])) {
				foundOrg = each;
				break;
			}
		}
		return foundOrg;
	}

	private Org findGoogleByApiId(String apiId) {
		System.out.println(apiId);
		String id[] = apiId.split(":::");
		for (String each : id) {
			System.out.println(each);
		}

		Org foundOrg = getPlaceDetails(getDetailsUrl(id[2]));

		return foundOrg;
	}

	private String getDetailsUrl(String placeId) {
		String fields = "formatted_address,icon,name,permanently_closed,place_id,formatted_phone_number,photos,geometry,website,rating";
		String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + placeId + "&fields=" + fields
				+ "&key=" + geoKey;
		System.out.println(url);
		return url;
	}

	public String makeApiId(String api, Org org) {
		String apiId = api + ":::" + org.getName() + ":::" + org.getOrgId();
		return apiId;
	}

	public String capitalize(String string) {
		String body = string.toLowerCase().substring(1);
		String newString = string.substring(0, 1).toUpperCase() + body;
		return newString;
	}

	private Double parseToDoubleWrapper(String string) {
		Double parsed = Double.parseDouble(string);
		return parsed;
	}

	private Long parseToLongWrapper(String string) {
		Long parsed = Long.parseLong(string);
		return parsed;
	}

	private String hudNameSearch(String orgName) {
		String url = "https://data.hud.gov/Housing_Counselor/search?AgencyName=" + orgName
				+ "&City=&State=&RowLimit=&Services=&Languages=";
		return url;
	}

	private String caaFindInRadiusDetroit(int maxResults, int searchRadius) {
		String url = "https://communityactionpartnership.com/wp-admin/admin-ajax.php?action=store_search&lat=42.33143&lng=-83.04575&max_results="
				+ maxResults + "&search_radius=" + searchRadius;
		return url;
	}

	private String caaFindInRadius(Double latitude, Double longitude, int maxResults, int searchRadius) {
		String url = "https://communityactionpartnership.com/wp-admin/admin-ajax.php?action=store_search&lat="
				+ latitude + "&lng=" + longitude + "&max_results=" + maxResults + "&search_radius=" + searchRadius;
		return url;
	}

	private String buildAddress(User user) {
		String address = user.getAddress() + "," + user.getCity() + "," + "MI";
		System.out.println(address);
		return address;
	}

	private String buildLocation(Double latitude, Double longitude) {
		String location = Double.toString(latitude) + "," + Double.toString(longitude);
		return location;
	}

}
