package co.grandcircus.HelpMeApp.model;

import org.springframework.stereotype.Component;

@Component
public class HelpMeMethods {

	public String capitalize(String string) {
		String body = string.toLowerCase().substring(1);
		String newString =  string.substring(0, 1).toUpperCase() + body;
		return newString;
	}
}
