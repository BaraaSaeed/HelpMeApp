package co.grandcircus.HelpMeApp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.HelpMeApp.ApiService;
import co.grandcircus.HelpMeApp.places.Result;

@Component
public class HelpList {

	@Autowired
	private ApiService apiService;
	@Value("${Geocoding.API_KEY}")
	private String geoKey;

	private String miHudUrl = "https://data.hud.gov/Housing_Counselor/search?AgencyName=&City=&State=mi&RowLimit=&Services=&Languages=";
	private String hudListBase = "https://data.hud.gov/Housing_Counselor/search?AgencyName=";
	private String hudListEnd = "&RowLimit=&Services=&Languages=";
	private String hudCity = "&City=";
	private String hudState = "&State=";
	private String hudAllServices = "https://data.hud.gov/Housing_Counselor/getServices";
	private String caaListBase = "https://communityactionpartnership.com/wp-admin/admin-ajax.php?action=store_search&lat=42.33143&lng=-83.04575";
	private String caaResults = "&max_results=";
	private String caaRadius = "&search_radius=";
	private String[] charityOrgs = { "salvation army", "focus hope", "st vincent de paul", "jewish family service",
			"catholic social services" };

	public List<Org> getControllerOrgList(String city, User user, String services, Set<String> matchingOrgs) {
		if (services.equalsIgnoreCase("All Services")) {
			return getAllServices(city, user, matchingOrgs);
		} else {
			List<Org> selectOrgs = getSelectOrgs(getAllServices(city, user, matchingOrgs), services);
			return selectOrgs;
		}
	}

	public List<Org> getAllServices(String city, User user, Set<String> matchingOrgs) {
		List<Org> orgs = new ArrayList<>();
//		for (Org each : getCaaOrgs(user)) {
//			orgs.add(each);
//		}
//		for (Org each : getHudOrgs(user)) {
//			orgs.add(each);
//		}
		for (Org each : getGoogleOrgs(apiService.getLatitudeCoordinate(getLatAndLngUrl(city, user)),
				apiService.getLongitudeCoordinate(getLatAndLngUrl(city, user)), matchingOrgs)) {
//			if(each.getCity().equalsIgnoreCase(user.getCity())) {
			orgs.add(each);
//			}
		}
		return orgs;
	}

	private String getLatAndLngUrl(String city, User user) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + getCityForUrl(city, user) + "&key="
				+ geoKey;
		return url;
	}

	private String getCityForUrl(String city, User user) {
		if (isUserPresent(user) && (city.equalsIgnoreCase("All cities"))) {
			city = user.getCity();
		} else if (city.equals("All cities")) {
			city = "detroit";
		}
		return city;
	}

	public List<Org> getGoogleOrgs(Double latitude, Double longitude, Set<String> matchingOrgs) {
		List<Org> results;
		List<Org> selectCharityOrgs = new ArrayList<>();
		for (String org : matchingOrgs) {
//			if (orgSelection.equalsIgnoreCase("All Organizations")) {
//				results = apiService.getListOfPlacesWithAddressBiased(org, latitude, longitude);
//				for (Org each : results) {
//					selectCharityOrgs.add(each);
//				}
//			} else {
			results = apiService.getListOfPlacesWithAddressBiased(org, latitude, longitude);
			for (Org each : results) {
				selectCharityOrgs.add(each);
			}
		}
		return selectCharityOrgs;
	}

	public List<Org> getCaaOrgs(User user) {
		List<Org> caaOrgs = apiService.findCaas(getCaaUrl(user));
		return caaOrgs;
	}

	public List<Org> getHudOrgs(User user) {
		List<Org> hudOrgs = apiService.findAllHud(getHudUrl(user));
		return hudOrgs;
	}

	public String getCaaUrl(User user) {
		String url;
		if (isUserPresent(user)) {
			url = caaListBase + caaResults + "100" + caaRadius + "25";
		} else {
			url = caaListBase + caaResults + "100" + caaRadius + "25";
		}
		return url;
	}

	public String getHudUrl(User user) {
		String url;
		if (isUserPresent(user)) {
			url = hudListBase + hudCity + user.getCity() + hudState + hudListEnd;
		} else {
			url = miHudUrl;
		}
		return url;
	}

	public List<Org> getSelectOrgs(List<Org> orgs, String service) {
		List<Org> selectOrgs = new ArrayList<>();
		for (Org each : orgs) {
			if (each.getServices() != null) {
				if (service.equals("Credit Repair") && (each.getServices().contains("FBW"))
						|| (service.equals("Credit Repair")) && (each.getServices().contains("FBC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Homelessness") && (each.getServices().contains("HMC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Mortgage Payments") && (each.getServices().contains("DFW"))
						|| (service.equals("Mortgage Payments")) && (each.getServices().contains("DFC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Reverse Mortgages") && (each.getServices().contains("RMC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Renting a Home") && (each.getServices().contains("RHW"))
						|| (service.equals("Renting a Home")) && (each.getServices().contains("RHC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Buying a Home") && (each.getServices().contains("PPW"))
						|| (service.equals("Buying a Home")) && (each.getServices().contains("PPC"))
						|| (service.equals("Buying a Home")) && (each.getServices().contains("NDW"))
						|| (service.equals("Buying a Home")) && (each.getServices().contains("LM"))) {
					selectOrgs.add(each);
				} else if (service.equals("Home Improvements") && (each.getServices().contains("HIC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Preditory Lending") && (each.getServices().contains("PLW"))) {
					selectOrgs.add(each);
				} else if (service.equals("CAA Services") && (each.getServices().contains("CAA_SERVICES"))) {
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

	public int getOrgSelectionIndex(String selection) {
		System.out.println("orgSelection: " + selection);
		String[] orgs = charityOrgs;
		int index = 0;
		for (int i = 0; i < orgs.length; i++) {
			index = i;
			if (orgs[i].equalsIgnoreCase(selection)) {
				break;
			}
		}
		System.out.println("Index: " + index);
		return index;
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
}
