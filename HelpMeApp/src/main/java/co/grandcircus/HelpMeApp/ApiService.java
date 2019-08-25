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

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.HelpMeApp.model.Caa;
import co.grandcircus.HelpMeApp.model.Hud;
import co.grandcircus.HelpMeApp.model.HudService;
import co.grandcircus.HelpMeApp.model.Org;

@Component
public class ApiService {

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
			Org org = new Org(each.getNme(), each.getServices(), each.getAgcid(), each.getAdr1() + " " + each.getAdr2(),
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
			Org org = new Org(each.getNme(), each.getServices(), each.getAgcid(), each.getAdr1() + " " + each.getAdr2(),
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
			Org org = new Org(each.getStore(), "CAA_SERVICES", parseToLongWrapper(each.getId()),
					each.getAddress() + " " + each.getAddress2(), each.getCity(), each.getZip(), each.getPhone(),
					each.getEmail(), each.getUrl(), each.getState(), parseToDoubleWrapper(each.getLng()),
					parseToDoubleWrapper(each.getLat()));
			org.setApiId(makeApiId("CAA", org));
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
		return foundOrg;
	}

	private Org findByHudId(String apiId) {
		String id[] = apiId.split(":");
		List<Org> orgs = findHudByOrgName(hudNameSearch(id[1]));
		Org foundOrg = new Org();
		for (Org each : orgs) {
			if (each.getOrgId().equals(parseToLongWrapper(id[2]))) {
				foundOrg = each;
				break;
			}
		}
		return foundOrg;
	}

	private Org findByCaaId(String apiId) {
		String id[] = apiId.split(":");
		List<Org> orgs = findCaas(caaFindInRadiusDetroit(100, 100));
		Org foundOrg = new Org();
		for (Org each : orgs) {
			if (each.getOrgId().equals(parseToLongWrapper(id[2]))) {
				foundOrg = each;
				break;
			}
		}
		return foundOrg;
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

	public String makeApiId(String api, Org org) {
		String apiId = api + ":" + org.getName() + ":" + org.getOrgId();
		return apiId;
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

}
