package co.grandcircus.HelpMeApp.google;

import co.grandcircus.HelpMeApp.model.User;

/**
 * Used internally by GithubService.
 * 
 * Java model for API JSON response
 */
class GoogleUser {
	private String id;
	private String name;
	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void copyToUser(User user) {
		user.setGoogleId(id);
		user.setFirstName(name);
		user.setEmail(email);
	}
}