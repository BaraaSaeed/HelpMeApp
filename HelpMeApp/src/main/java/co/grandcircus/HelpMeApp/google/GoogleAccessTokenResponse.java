package co.grandcircus.HelpMeApp.google;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleAccessTokenResponse {

	@JsonProperty("access_token")
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
