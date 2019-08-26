package co.grandcircus.HelpMeApp.model;

public class Url {

	private String miHudUrl = "https://data.hud.gov/Housing_Counselor/search?AgencyName=&City=&State=mi&RowLimit=&Services=&Languages=";
	private String hudListBase = "https://data.hud.gov/Housing_Counselor/search?AgencyName=";
	private String hudListEnd = "&RowLimit=&Services=&Languages=";
	private String hudCity = "&City=";
	private String hudState = "&State=";
	private String hudAllServices = "https://data.hud.gov/Housing_Counselor/getServices";
	private String caaListBase = "https://communityactionpartnership.com/wp-admin/admin-ajax.php?action=store_search&lat=42.33143&lng=-83.04575";
	private String caaResults = "&max_results=";
	private String caaRadius = "&search_radius=";
	
}
