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
		
//		return response;

		List<Org> orgs = new ArrayList<>();
		for (Hud each : response) {
			Org org = new Org(each.getNme(), each.getServices(), each.getAgcid(), each.getAdr1() + " " + each.getAdr2(), each.getCity(), each.getZipcd(), each.getPhone1(), each.getEmail(), each.getWeburl(), each.getStatecd(), each.getAgc_ADDR_LONGITUDE(), each.getAgc_ADDR_LATITUDE());
						//			String name, String services, String orgId, String address, String city, String zip, String phone,
						//			String email, String url, String state, Double longitude, Double latitude)
			orgs.add(org);
		}
		return orgs;
	}

	public HudService[] listServices(String url) {
		HudService[] services = restTemplate.getForObject(url, HudService[].class);
		return services;
	}

	public Caa[] findCaas(String url) {
		Caa[] response = restTemplate.getForObject(url, Caa[].class);
		return response;
	}
}
