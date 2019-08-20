/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp.google;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.HelpMeApp.model.User;

@Component
public class GoogleService {

	@Value("${google.client_secret}")
	private String clientSecret;

	/**
	 * Make an HTTP request to Google's server. Get an access token using the
	 * provided code.
	 */
	public String getGoogleAccessToken(String code) {
		// We'll talk more about rest template in the coming days.
		Map<String, String> params = new HashMap<>();
		params.put("code", code);
		params.put("client_id", "855747263310-23km4ggb7ckj25amm0hvpaedag4t6ur6.apps.googleusercontent.com");
		params.put("client_secret", clientSecret);
		params.put("redirect_uri", "http://localhost:8080/callback");
		params.put("grant_type", "authorization_code");
		RestTemplate rest = new RestTemplate();
		GoogleAccessTokenResponse response = rest.postForObject("https://www.googleapis.com/oauth2/v4/token", params,
				GoogleAccessTokenResponse.class);
		return response.getAccessToken();
	}

	/**
	 * Make an HTTP request to Google's server. Use the access token to get the user
	 * details.
	 */
	public User getUserFromGoogleApi(String accessToken) {
		// We'll talk more about rest template in the coming days.
		RestTemplate rest = new RestTemplate();
		String uri = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
		System.out.println(uri);
		GoogleUser response = rest.getForObject(uri, GoogleUser.class);
		User user = new User();
		response.copyToUser(user);
		return user;
	}
}
