package co.grandcircus.HelpMeApp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelpMeMethods {

	public String capitalize(String string) {
		String body = string.toLowerCase().substring(1);
		String newString = string.substring(0, 1).toUpperCase() + body;
		return newString;
	}

	public List<Org> getSelectOrgs(List<Org> orgs, String url, String selection) {
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

}
