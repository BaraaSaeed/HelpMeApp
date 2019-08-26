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
import co.grandcircus.HelpMeApp.places.GoogleTextSearchResponse;
import co.grandcircus.HelpMeApp.places.OpeningHours;
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
			Org org = new Org(each.getNme(), each.getServices(), Long.toString(each.getAgcid()), each.getAdr1() + " " + each.getAdr2(),
					capitalize(each.getCity()), each.getZipcd(), each.getPhone1(), each.getEmail(), each.getWeburl(),
					each.getStatecd(), each.getAgc_ADDR_LONGITUDE(), each.getAgc_ADDR_LATITUDE());
			org.setApiId(makeApiId("HUD", org));
			orgs.add(org);
		}
		return orgs;
	}

	public List<Org> findHudByOrgName(String url) {
		Hud[] response = restTemplate.getForObject(url, Hud[].class);

		List<Org> orgs = new ArrayList<>();
		for (Hud each : response) {
			Org org = new Org(each.getNme(), each.getServices(), Long.toString(each.getAgcid()), each.getAdr1() + " " + each.getAdr2(),
					capitalize(each.getCity()), each.getZipcd(), each.getPhone1(), each.getEmail(), each.getWeburl(),
					each.getStatecd(), each.getAgc_ADDR_LONGITUDE(), each.getAgc_ADDR_LATITUDE());
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
					each.getAddress() + " " + each.getAddress2(), each.getCity(), each.getZip(), each.getPhone(),
					each.getEmail(), each.getUrl(), each.getState(), parseToDoubleWrapper(each.getLng()),
					parseToDoubleWrapper(each.getLat()));
			org.setApiId(makeApiId("CAA", org));
			orgs.add(org);
		}
		return orgs;
	}

	public Double getLatitudeCoordinate(User user) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + buildAddress(user) + "&key=" + geoKey;
		GeocodingCoordinatesResponse response = restTemplate.getForObject(url, GeocodingCoordinatesResponse.class);
		return response.getResults()[0].getGeometry().getLocation().getLatitude();
	}
	
	public Double getLongitudeCoordinate(User user) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + buildAddress(user) + "&key=" + geoKey;
		GeocodingCoordinatesResponse response = restTemplate.getForObject(url, GeocodingCoordinatesResponse.class);
		return response.getResults()[0].getGeometry().getLocation().getLongitude();
	}
	
	public String getRevereseGeocoding(Double latitude, Double longitude) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + buildLocation(latitude, longitude) + "&key=" + geoKey;
		ReverseGeocodingCoordinatesResponse response = restTemplate.getForObject(url,
				ReverseGeocodingCoordinatesResponse.class);
		return response.getResults()[0].getFormattedAddress();
	}
	
	public List<Org> getListOfPlacesWithAddressBiased(String searchText, Double latitude, Double longitude) {		
		String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + searchText + "&location="
				+ buildLocation(latitude, longitude) + "&radius=" + 10000 + "&key=" + geoKey;
		GoogleTextSearchResponse response = restTemplate.getForObject(url, GoogleTextSearchResponse.class);	
		List<Org> orgs = new ArrayList<>();
		for (Result each : response.getResults()) {
			Org org = new Org(each.getName(), "GoogleSearch_SERVICES", each.getPlaceId(), parseAddress(each.getFormattedAddress(), 0),
					parseAddress(each.getFormattedAddress(), 1), parseAddress(each.getFormattedAddress(), 2), "GooglePhone", "GoogleEmail", "GoogleURL",
					"GoogleSTATE", latitude, longitude);
			org.setApiId(makeApiId("GOOGLE", org));
			orgs.add(org);
		}
		return orgs;
	}

	protected Org findByApiId(String apiId) {
		Org foundOrg = new Org();
		if (apiId.startsWith("HUD")) {
			foundOrg = findByHudId(apiId);
		} else if (apiId.startsWith("CAA")) {
			foundOrg = findByCaaId(apiId);
		}
//		 else if (apiId.startsWith("GOOGLE")) {
//			foundOrg = findByGoogleId(apiId);
//		}
		return foundOrg;
	}

	private Org findByHudId(String apiId) {
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

	private Org findByCaaId(String apiId) {
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
	
//	private Org findByGoogleId(String apiId) {
//		String id[] = apiId.split(":");
//		List<Org> orgs = findHudByOrgName(hudNameSearch(id[1]));
//		Org foundOrg = new Org();
//		for (Org each : orgs) {
//			if (each.getOrgId().equals(id[2])) {
//				foundOrg = each;
//				break;
//			}
//		}
//		return foundOrg;
//	}

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
		String address = user.getAddress() + "," + user.getCity() + "," + user.getState();
		System.out.println(address);
		return address;
	} 
	
	private String buildLocation(Double latitude, Double longitude) {
		String location = Double.toString(latitude) + "," + Double.toString(longitude);
		return location;
	}
	
	private String parseAddress(String address, int parsedIndex) {
		String[] splitAddress = address.split(",");
		//still commas in parsed, State and zip divided by space, wait for Details 
		String parsedAddress = splitAddress[parsedIndex];
		return parsedAddress;
	}
	
}
