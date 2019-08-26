package co.grandcircus.HelpMeApp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.HelpMeApp.ApiService;
import co.grandcircus.HelpMeApp.places.Result;

@Component
public class HelpList {

	@Autowired
	private ApiService apiService;

	private String miHudUrl = "https://data.hud.gov/Housing_Counselor/search?AgencyName=&City=&State=mi&RowLimit=&Services=&Languages=";
	private String hudlistBase = "https://data.hud.gov/Housing_Counselor/search?AgencyName=";
	private String hudlistEnd = "&RowLimit=&Services=&Languages=";
	private String city = "&City=";
	private String state = "&State=";
	private String allServices = "https://data.hud.gov/Housing_Counselor/getServices";
	private String caaListBase = "https://communityactionpartnership.com/wp-admin/admin-ajax.php?action=store_search&lat=42.33143&lng=-83.04575";
	private String caaResults = "&max_results=";
	private String caaRadius = "&search_radius=";
	private String[] charityOrgs = { "salvation army", "focus hope", "st vincent de paul" };

	public List<Org> getCharitableOrgs(Double latitude, Double longitude) {
		List<Org> selectCharityOrgs = new ArrayList<>();
		String[] orgs = charityOrgs;
		for (String org : orgs) {
			List<Org> results = apiService.getListOfPlacesWithAddressBiased(org, latitude, longitude);
			for (Org each : results) {
				selectCharityOrgs.add(each);
			}
		}
		return selectCharityOrgs;
	}

	public boolean isUserPresent(User user) {
		boolean userPresent;
		if (user != null) {
			userPresent = true;
		} else {
			userPresent = false;
		}
		return userPresent;
	}

	public void setUserSelection(User user, String selection) {
		if (isUserPresent(user)) {
			user.setSelection(selection);
		}
	}

	public List<Org> getControllerOrgList(User user, String selection) {
		if (selection.equals("All Services")) {
			return getAllOrgs(user);
		} else {
			List<Org> selectOrgs = getSelectOrgs(getAllOrgs(user), selection);
			return selectOrgs;
		}
	}

	public List<Org> getAllOrgs(User user) {
		List<Org> orgs = new ArrayList<>();
		for (Org each : getCaaOrgs(user)) {
			orgs.add(each);
		}
//		for (Org each : getHudOrgs(user)) {
//			orgs.add(each);
//		}
		return orgs;
	}

	public List<Org> getCaaOrgs(User user) {
		List<Org> caaOrgs = apiService.findCaas(getCaaUrl(user));
		return caaOrgs;
	}

	public List<Org> getHudOrgs(User user, String selection) {
		List<Org> hudOrgs = apiService.findCaas(getCaaUrl(user));
		return hudOrgs;
	}

	public String getCaaUrl(User user) {
		String url;
		if (isUserPresent(user)) {
			url = caaListBase + caaResults + "100" + caaRadius + "100";
		} else {
			url = caaListBase + caaResults + "100" + caaRadius + "100";
		}
		return url;
	}

	public String getNoUserCaaUrl() {
		String url = caaListBase + caaResults + "100" + caaRadius + "100";
		return url;
	}

	public List<Org> getSelectOrgs(List<Org> orgs, String selection) {
		List<Org> selectOrgs = new ArrayList<>();
		for (Org each : orgs) {
			if (each.getServices() != null) {
				if (selection.equals("Credit Repair") && (each.getServices().contains("FBW"))
						|| (selection.equals("Credit Repair")) && (each.getServices().contains("FBC"))) {
					selectOrgs.add(each);
				} else if (selection.equals("Homelessness") && (each.getServices().contains("HMC"))) {
					selectOrgs.add(each);
				} else if (selection.equals("Mortgage Payments") && (each.getServices().contains("DFW"))
						|| (selection.equals("Mortgage Payments")) && (each.getServices().contains("DFC"))) {
					selectOrgs.add(each);
				} else if (selection.equals("Reverse Mortgages") && (each.getServices().contains("RMC"))) {
					selectOrgs.add(each);
				} else if (selection.equals("Renting a Home") && (each.getServices().contains("RHW"))
						|| (selection.equals("Renting a Home")) && (each.getServices().contains("RHC"))) {
					selectOrgs.add(each);
				} else if (selection.equals("Buying a Home") && (each.getServices().contains("PPW"))
						|| (selection.equals("Buying a Home")) && (each.getServices().contains("PPC"))
						|| (selection.equals("Buying a Home")) && (each.getServices().contains("NDW"))
						|| (selection.equals("Buying a Home")) && (each.getServices().contains("LM"))) {
					selectOrgs.add(each);
				} else if (selection.equals("Home Improvements") && (each.getServices().contains("HIC"))) {
					selectOrgs.add(each);
				} else if (selection.equals("Preditory Lending") && (each.getServices().contains("PLW"))) {
					selectOrgs.add(each);
				} else if (selection.equals("CAA Services") && (each.getServices().contains("CAA_SERVICES"))) {
					selectOrgs.add(each);
				}
			}
		}
		return selectOrgs;
	}

	public List<String> translateServices(String services) {
		Set<String> serviceSet = new HashSet<>();
		List<String> serviceList = new ArrayList<>();
		if (services.contains("FBW")) {
			serviceSet.add("Credit Repair");
		}
		if (services.contains("HMC")) {
			serviceSet.add("Homelessness");
		}
		if (services.contains("DFW") || services.contains("DFC")) {
			serviceSet.add("Mortgage Payments");
		}
		if (services.contains("RMC")) {
			serviceSet.add("Reverse Mortgages");
		}
		if (services.contains("RHC") || services.contains("RHW")) {
			serviceSet.add("Renting a Home");
		}
		if (services.contains("PPW") || services.contains("PPC") || services.contains("NDW")
				|| services.contains("LM")) {
			serviceSet.add("Buying a Home");
		}
		if (services.contains("NDW")) {
			serviceSet.add("Home Improvements");
		}
		if (services.contains("HIC")) {
			serviceSet.add("Credit Repair");
		}
		if (services.contains("Preditory Lending")) {
			serviceSet.add("Credit Repair");
		}
		for (String each : serviceSet) {
			serviceList.add(each);
		}
		return serviceList;
	}

	public String unWrapApiIdFromHtml(String apiId) {
		String unWrappedApi = apiId.replaceAll("_", " ");
		return unWrappedApi;
	}

	public String capitalize(String string) {
		String body = string.toLowerCase().substring(1);
		String newString = string.substring(0, 1).toUpperCase() + body;
		return newString;
	}
	
}
