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

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.HelpMeApp.model.Caa;
import co.grandcircus.HelpMeApp.model.HudService;
import co.grandcircus.HelpMeApp.model.OrgObject;

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

	public OrgObject[] findAll(String url) {
		OrgObject[] response = restTemplate.getForObject(url, OrgObject[].class);
		return response;
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
