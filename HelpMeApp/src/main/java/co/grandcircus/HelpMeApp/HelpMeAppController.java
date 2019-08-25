/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali, Gerard Breitenbeck, Sienna Harris -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.HelpMeApp.Dao.MessageDao;
import co.grandcircus.HelpMeApp.Dao.UserDao;
import co.grandcircus.HelpMeApp.geocoding.GeocodingService;
import co.grandcircus.HelpMeApp.google.GoogleService;
import co.grandcircus.HelpMeApp.model.AutoEmail;
import co.grandcircus.HelpMeApp.model.HelpMeMethods;
import co.grandcircus.HelpMeApp.model.Message;
import co.grandcircus.HelpMeApp.model.Org;
import co.grandcircus.HelpMeApp.model.User;
import co.grandcircus.HelpMeApp.places.GooglePlacesService;
import co.grandcircus.HelpMeApp.places.Result;

@Controller
public class HelpMeAppController {

	private String miHudUrl = "https://data.hud.gov/Housing_Counselor/search?AgencyName=&City=&State=mi&RowLimit=&Services=&Languages=";
	private String hudlistBase = "https://data.hud.gov/Housing_Counselor/search?AgencyName=";
	private String hudlistEnd = "&RowLimit=&Services=&Languages=";
	private String city = "&City=";
	private String state = "&State=";
	private String allServices = "https://data.hud.gov/Housing_Counselor/getServices";
	private String caaListBase = "https://communityactionpartnership.com/wp-admin/admin-ajax.php?action=store_search&lat=42.33143&lng=-83.04575";
	private String caaResults = "&max_results=";
	private String caaRadius = "&search_radius=";

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ApiService apiService;
	@Autowired
	private AutoEmail email;
	@Autowired
	private GoogleService googleService;
	@Autowired
	HelpMeMethods methods;

	@Autowired
	private GeocodingService geocodingService;

	@Autowired
	private GooglePlacesService googlePlacesService;

	@RequestMapping("/")
	public ModelAndView showHome() throws Exception {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@RequestMapping("/signup")
	public ModelAndView showSignup() {
		return new ModelAndView("signup-form");
	}

	@RequestMapping("/signup-confirmation")
	public ModelAndView signupConfirm(User user, HttpSession session) {
		userDao.save(user);
		session.setAttribute("user", user);
		ModelAndView mav = new ModelAndView("thanks");
		return mav;
	}

	@PostMapping("/")
	public ModelAndView submitLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		User user = userDao.findAllByEmailAndPassowrd(email, password);

		if (user == null) {
			return new ModelAndView("redirect:/", "message", "Incorrect username or password.");
		}
		session.setAttribute("user", user);
		return new ModelAndView("redirect:/");
	}

	// This is the second step in OAuth. After the user approves the login on the
	// github site, it redirects back
	// to our site with a temporary code.
	@RequestMapping("/callback")
	public ModelAndView handleGithubCallback(@RequestParam("code") String code, HttpSession session) {
		// First we must exchange that code for an access token.
		String accessToken = googleService.getGoogleAccessToken(code);
		// Then we can use that access token to get information about the user and other
		// things.
		User googleUser = googleService.getUserFromGoogleApi(accessToken);

		// Check to see if the user is already in our database.
		User user = userDao.findByGoogleId(googleUser.getGoogleId());

		if (user == null) {
			// if not, add them.
			user = googleUser;
			userDao.save(user);
		}

		// Set the user on the session, just like the other type of login.
		session.setAttribute("user", user);
		// In some apps, you need the access token later, so throw that on the session,
		// too.
		session.setAttribute("googleAccessToken", accessToken);

		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/helplist")
	public ModelAndView helplist(@SessionAttribute(name = "user", required = false) User user,
			@RequestParam("selection") String selection) {
		ModelAndView mv = new ModelAndView("helplist");
		String hudUrl = "";
		String caaUrl = "";

		if (user == null) {

			hudUrl = hudlistBase + city + state + "mi" + hudlistEnd;
			hudUrl = miHudUrl;

			caaUrl = caaListBase + caaResults + "100" + caaRadius + "100";
		} else {
			hudUrl = hudlistBase + city + user.getCity() + state + hudlistEnd;
			caaUrl = caaListBase + caaResults + "100" + caaRadius + "100";
		}
		List<Org> orgs = new ArrayList<>();
		List<Org> selectOrgs = new ArrayList<>();
		for (Org each : apiService.findAllHud(hudUrl)) {
			orgs.add(each);
		}

		if (selection.equals("All Services")) {
			mv.addObject("selectOrgs", orgs);
		} else {
			for (Org each : orgs) {
				if (each.getServices() != null) {
					if (selection.equals("Credit Repair") && (each.getServices().contains("FBW"))
							|| (each.getServices().contains("FBC"))) {
						selectOrgs.add(each);
					} else if (selection.equals("Homelessness") && (each.getServices().contains("HMC"))) {
						selectOrgs.add(each);
					} else if (selection.equals("Mortgage Payments") && (each.getServices().contains("DFW"))
							|| (each.getServices().contains("DFC"))) {
						selectOrgs.add(each);
					} else if (selection.equals("Reverse Mortgages") && (each.getServices().contains("RMC"))) {
						selectOrgs.add(each);
					} else if (selection.equals("Renting a Home") && (each.getServices().contains("RHW"))
							|| (each.getServices().contains("RHC"))) {
						selectOrgs.add(each);
					} else if (selection.equals("Buying a Home") && (each.getServices().contains("PPW"))
							|| (each.getServices().contains("PPC")) || (each.getServices().contains("NDW"))
							|| (each.getServices().contains("LM"))) {
						selectOrgs.add(each);
					} else if (selection.equals("Home Improvements") && (each.getServices().contains("HIC"))) {
						selectOrgs.add(each);
					} else if (selection.equals("Preditory Lending") && (each.getServices().contains("PLW"))) {
						selectOrgs.add(each);
					}
				}
			}
			mv.addObject("selectOrgs", selectOrgs);

		}
		System.out.println(methods.capitalize("test string"));
		mv.addObject("selection", selection);
		return mv;
	}

	@RequestMapping("/autorepo")
	public ModelAndView autorepo(@SessionAttribute(name = "user", required = false) User user,
			@RequestParam("selection") String selection, @RequestParam("id") Long orgId,
			@RequestParam("nme") String nme, @RequestParam("city") String city, @RequestParam("address") String address,
			@RequestParam("phone") String phone, @RequestParam("services") String services) {
		ModelAndView mv = new ModelAndView("autorepo");
		Set<String> serviceSet = new HashSet<>();
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
		System.out.println(serviceSet);

		List<String> serviceList = new ArrayList<>();
		for (String each : serviceSet) {
			serviceList.add(each);
		}

		mv.addObject("id", orgId);
		mv.addObject("nme", nme);
		mv.addObject("selection", selection);
		mv.addObject("city", methods.capitalize(city));
		mv.addObject("address", address);
		mv.addObject("phone", phone);
		mv.addObject("serviceList", serviceList);
		return mv;
	}

	@PostMapping("/autorepo")
	public ModelAndView autoPost(@SessionAttribute(name = "user", required = false) User user,
			@RequestParam("selection") String selection, @RequestParam("id") Long orgId,
			@RequestParam("nme") String nme, @RequestParam("content") String content) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/userpro");
		String issue;
		if (selection.equals("All Services")) {
			issue = "your services";
		} else {
			issue = selection;
		}
		System.out.println(issue);
		email.sendMail(user, orgId, issue, nme, content);
		return mv;
	}

	@RequestMapping("/userpro")
	public ModelAndView userPro(@SessionAttribute(name = "user") User user) {
		ModelAndView mv = new ModelAndView("userpro");
		List<Message> messageHistory = messageDao.findAllByUserId(user.getId());
		Map<Long, String> orgSet = new HashMap<>();
		for (Message each : messageHistory) {
			orgSet.put(each.getOrgId(), each.getTo());
		}
		System.out.println(orgSet);

		mv.addObject("user", user);
		mv.addObject("messageHistory", messageHistory);
		mv.addObject("orgSet", orgSet);

		return mv;
	}

	@RequestMapping("/org-message-detail")
	public ModelAndView orgMessageDetail(@RequestParam("orgId") Long orgId, @RequestParam("userId") Long userId) {
		ModelAndView mv = new ModelAndView("org-message-detail");
		List<Message> messages = messageDao.findAllByUserIdAndOrgId(userId, orgId);

		mv.addObject("messageList", messages);
		System.out.println(messages);

		mv.addObject("userId", userId);
		mv.addObject("lastMessage", messages.get(messages.size() - 1));
		mv.addObject("orgId", orgId);
		return mv;
	}

	@PostMapping("/org-message-detail")
	public ModelAndView orgSend(@RequestParam("orgId") Long orgId, @RequestParam("contentString") String contentString,
			@RequestParam("messageId") Long messageId) {

		ModelAndView mv = new ModelAndView("redirect:/orgpro", "orgId", orgId);
		System.out.println(orgId);
		Message userMessage = messageDao.findByMessageId(messageId);
		System.out.println(userMessage);
		String subject = "Re: " + userMessage.getSubject();
		String content = contentString.trim();
		Message message = new Message(userMessage.getUserId(), userMessage.getOrgId(), userMessage.getIssue(),
				email.getDate(), userMessage.getTo(), userMessage.getFrom(), subject, content);
		messageDao.save(message);
		return mv;
	}

	@RequestMapping("/orgpro")
	public ModelAndView orgPro(@RequestParam("orgId") Long orgId) {
		ModelAndView mv = new ModelAndView("orgpro");
		List<Message> messageHistory = messageDao.findAllByOrgId(orgId);
		Map<Long, String> userMap = new HashMap<>();
		for (Message each : messageHistory) {
			userMap.put(each.getUserId(), each.getTo());
		}
		System.out.println(userMap);
		mv.addObject("userMap", userMap);
		mv.addObject("orgId", orgId);
		return mv;

	}

	@RequestMapping("/user-message-detail")
	public ModelAndView messageDetail(@SessionAttribute(name = "user") User user, @RequestParam("orgId") Long orgId) {
		ModelAndView mv = new ModelAndView("user-message-detail");
		List<Message> messageHistory = messageDao.findAllByUserIdAndOrgId(user.getId(), orgId);
		System.out.println(messageDao.findAllByUserIdAndOrgId(user.getId(), orgId));
		for (Message each : messageHistory) {
			System.out.println(each.getContent());
		}
		mv.addObject("messageHistory", messageHistory);

		return mv;
	}

	@RequestMapping("/geocode")
	public ModelAndView showOnMap() {
		Double latitude = geocodingService.getLatitudeCoordinate("1600", "Amphitheatre Parkway", "Mountain View", "CA");
		Double longitude = geocodingService.getLongitudeCoordinate("1600", "Amphitheatre Parkway", "Mountain View",
				"CA");
		Double[] coordinates = new Double[] { latitude, longitude };
		return new ModelAndView("show-geocode", "coordinates", coordinates);
	}

	@RequestMapping("/reverse-geocode")
	public ModelAndView getHumanReadableAddress() {
		String address = geocodingService.getRevereseGeocoding(40.714224, -73.961452);
		return new ModelAndView("show-reversegeocode", "address", address);
	}

	@RequestMapping("/places-text-search")
	public ModelAndView SearchForPlaces() {
		return new ModelAndView("text-search-form");
	}

	@PostMapping("/places-text-search")
	public ModelAndView displaySearchResults(@RequestParam(value = "searchText", required = true) String searchText,
			@RequestParam(value = "latitude", required = false) Double latitude,
			@RequestParam(value = "longitude", required = false) Double longitude) {
		Result[] places;
		if (latitude != null && longitude != null) {
			places = googlePlacesService.getListOfPlacesWithAdressBiased(searchText, latitude, longitude);
		} else {
			places = googlePlacesService.getListOfPlacesWithoutAdressBiased(searchText);
		}
		return new ModelAndView("display-places-of-interest", "places", places);
	}
}
