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
import co.grandcircus.HelpMeApp.Dao.OrgSelectionDao;
import co.grandcircus.HelpMeApp.places.Result;

@Component
public class HelpList {

	@Autowired
	private ApiService apiService;
	@Autowired
	private OrgSelectionDao selectDao;
	@Value("${Geocoding.API_KEY}")
	private String geoKey;

	private String miHudUrl = "https://data.hud.gov/Housing_Counselor/search?AgencyName=&City=&State=mi&RowLimit=&Services=&Languages=";
	private String hudListBase = "https://data.hud.gov/Housing_Counselor/search?AgencyName=";
	private String hudListEnd = "&RowLimit=&Services=&Languages=";
	private String hudCity = "&City=";
	private String hudState = "&State=";
	private String hudAllServices = "https://data.hud.gov/Housing_Counselor/getServices";

	public List<Org> getControllerOrgList(String service, String orgSelection, String city, User user) {
		User tempUser;
		if (user != null) {
			user.setServiceSelection(service);
			user.setOrgSelection(orgSelection);
			tempUser = user;
			
		} else { 
			tempUser = new User();
			tempUser.setServiceSelection(service);
			tempUser.setOrgSelection(orgSelection);
			tempUser.setCity(city);
		}
		return getAllServices(tempUser, getMatchingOrgs(tempUser));
	}

	public List<Org> getAllServices(User user, Set<String> matchingOrgs) {
		List<Org> orgs = new ArrayList<>();
		for (Org each : getCaaOrgs(user.getCity())) {
			orgs.add(each);
		}
		for (Org each : getHudOrgs(user)) {
			orgs.add(each);
		}
		for (Org each : getGoogleOrgs(apiService.getLatitudeCoordinate(getLatAndLngUrl(user.getCity())),
				apiService.getLongitudeCoordinate(getLatAndLngUrl(user.getCity())), matchingOrgs)) {
			orgs.add(each);
		}
		return orgs;
	}
	
	
	private Set<String> getMatchingOrgs(User user) {
		List<OrgSelection> orgs;
		if (!user.getOrgSelection().equals("All Organizations") && (!user.getServiceSelection().equals("All Services"))) {
			orgs = selectDao.findAllByNameAndKeyWords(user.getOrgSelection(), user.getServiceSelection());
		}
		else if (user.getServiceSelection().equals("All Services")) {
			orgs = selectDao.findAllByName(user.getOrgSelection());
		} else {
		orgs = selectDao.findAllNameByKeyWords(user.getServiceSelection());
		}
		Set<String> matchingOrgs = new HashSet<>();
		for (OrgSelection each : orgs) {
			matchingOrgs.add(each.getName());
		}
		return matchingOrgs;
	}

	private String getLatAndLngUrl(String city) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city + "&key=" + geoKey;
		return url;
	}

	public List<Org> getGoogleOrgs(Double latitude, Double longitude, Set<String> matchingOrgs) {
		List<Org> results;
		List<Org> selectCharityOrgs = new ArrayList<>();
		for (String org : matchingOrgs) {
			results = apiService.getListOfPlacesWithAddressBiased(org, latitude, longitude);
			for (Org each : results) {
				selectCharityOrgs.add(each);
			}
		}
		return selectCharityOrgs;
	}

	public List<Org> getCaaOrgs(String city) {
		List<Org> caaOrgs = apiService.findCaas(getCaaUrl(city));
		return caaOrgs;
	}

	public List<Org> getHudOrgs(User user) {
		List<Org> hudOrgs = apiService.findAllHud(getHudUrl(user.getCity()));
		getSelectOrgs(hudOrgs, user.getServiceSelection());
		
		return hudOrgs;
	}

	public String getCaaUrl(String city) {
		String url = "https://communityactionpartnership.com/wp-admin/admin-ajax.php?action=store_search&lat="
				+ apiService.getLatitudeCoordinate(getLatAndLngUrl(city)) + "&lng="
				+ apiService.getLongitudeCoordinate(getLatAndLngUrl(city)) + "&max_results=" + "5"
				+ "&search_radius=50";
		return url;
	}

	public String getHudUrl(String city) {
		String url = "https://data.hud.gov/Housing_Counselor/search?AgencyName=&City=" + city + "&State=" + "mi"
				+ "&RowLimit=&Services=&Languages=";
		return url;
	}

	public List<Org> getSelectOrgs(List<Org> orgs, String service) {
		List<Org> selectOrgs = new ArrayList<>();
		for (Org each : orgs) {
			if (each.getServices() != null) {
				if (service.equals("DebtAndTemporaryAssistance") && (each.getServices().contains("FBW"))
						|| (service.equals("DebtAndTemporaryAssistance")) && (each.getServices().contains("FBC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Homeless") && (each.getServices().contains("HMC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Housing") && (each.getServices().contains("DFW"))
						|| (service.equals("Housing")) && (each.getServices().contains("DFC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Housing") && (each.getServices().contains("RMC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Housing") && (each.getServices().contains("RHW"))
						|| (service.equals("Housing")) && (each.getServices().contains("RHC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Housing") && (each.getServices().contains("PPW"))
						|| (service.equals("Housing")) && (each.getServices().contains("PPC"))
						|| (service.equals("Housing")) && (each.getServices().contains("NDW"))
						|| (service.equals("Housing")) && (each.getServices().contains("LM"))) {
					selectOrgs.add(each);
				} else if (service.equals("Housing") && (each.getServices().contains("HIC"))) {
					selectOrgs.add(each);
				} else if (service.equals("Debt") && (each.getServices().contains("PLW"))) {
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
