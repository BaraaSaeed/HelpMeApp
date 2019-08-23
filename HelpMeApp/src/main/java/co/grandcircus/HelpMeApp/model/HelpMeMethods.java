package co.grandcircus.HelpMeApp.model;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelpMeMethods {

//	@Autowired
//	Org org;
	
	public String capitalize(String string) {
		String body = string.toLowerCase().substring(1);
		String newString =  string.substring(0, 1).toUpperCase() + body;
		return newString;
	}
	
//	public String makeApiId(String api) {
//		String apiId = api + ":" + org.getName() + ":" + org.getOrgId();
//	}
}
