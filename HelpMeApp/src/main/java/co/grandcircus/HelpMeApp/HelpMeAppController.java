/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali, Gerard Breitenbeck, Sienna Harris -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.HelpMeApp.Dao.MessageDao;
import co.grandcircus.HelpMeApp.Dao.OrgDao;
import co.grandcircus.HelpMeApp.Dao.OrgSelectionDao;
import co.grandcircus.HelpMeApp.Dao.UserDao;
import co.grandcircus.HelpMeApp.geocoding.GeocodingService;
import co.grandcircus.HelpMeApp.google.GoogleService;
import co.grandcircus.HelpMeApp.model.AutoEmail;
import co.grandcircus.HelpMeApp.model.HelpList;
import co.grandcircus.HelpMeApp.model.Message;
import co.grandcircus.HelpMeApp.model.Org;
import co.grandcircus.HelpMeApp.model.User;
import co.grandcircus.HelpMeApp.placedetails.PlacesDetailsService;
import co.grandcircus.HelpMeApp.places.GooglePlacesService;
import co.grandcircus.HelpMeApp.places.Result;

@Controller
public class HelpMeAppController {

	@Value("${HOST}")
	private String host;

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrgSelectionDao selectDao;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private ApiService apiService;
	@Autowired
	private AutoEmail email;
	@Autowired
	private GoogleService googleService;
	@Autowired
	private HelpList helpList;
	@Autowired
	private GeocodingService geocodingService;
	@Autowired
	private GooglePlacesService googlePlacesService;
	@Autowired
	private PlacesDetailsService placesDetailsService;
	@Value("${Geocoding.API_KEY}")
	private String geoKey;

	@RequestMapping("/")
	public ModelAndView showHome() throws Exception {
		ModelAndView mv = new ModelAndView("index", "host", host);
		return mv;
	}

	@RequestMapping("/signup")
	public ModelAndView showSignup() {
		return new ModelAndView("signup-form");
	}

	@RequestMapping("/signup-confirmation")
	public ModelAndView signupConfirm(@RequestParam("email") String email, User user, HttpSession session,
			RedirectAttributes redir) {
		User existingUser = userDao.findByEmail(email);
		if (existingUser != null) {
			redir.addFlashAttribute("message", "This email is already used, use a different email.");
			return new ModelAndView("redirect:/signup");
		} else {
			userDao.save(user);
			session.setAttribute("user", user);
			ModelAndView mav = new ModelAndView("thanks");
			return mav;
		}
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

	@RequestMapping("/callback")
	public ModelAndView handleGoogleCallback(@RequestParam("code") String code, HttpSession session) {
		String accessToken = googleService.getGoogleAccessToken(code);
		User googleUser = googleService.getUserFromGoogleApi(accessToken);
		// Check to see if the user is already in our database.
		User user = userDao.findByGoogleId(googleUser.getGoogleId());
		if (user == null) {
			user = googleUser;
			userDao.save(user);
		}
		session.setAttribute("user", user);
		session.setAttribute("googleAccessToken", accessToken);

		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/helplist")
	public ModelAndView helplist(
			@SessionAttribute(name = "user", required = false) User user,
			@RequestParam("city") String city,
			@RequestParam("state")String state, 
			@RequestParam("service") String service,
			@RequestParam("orgSelection") String orgSelection) {

		ModelAndView mv = new ModelAndView("helplist");
		List<Org> orgs = helpList.getControllerOrgList(service, orgSelection, city, user);
		for (Org each : orgs) {
		}
		mv.addObject("selectOrgs", orgs);
		mv.addObject("selection", service);
		mv.addObject("state", state);

		return mv;
	}

	@RequestMapping("/autorepo")
	public ModelAndView selectFromHelpList(
			@SessionAttribute(name = "user", required = false) User user,
			@RequestParam("apiId") String apiId, 
			@RequestParam("selection") String selection) {
		ModelAndView mv = new ModelAndView("autorepo");
		Org org = apiService.findByApiId(apiId);
		Set<String> services = helpList.getServicesFromOrg(org);
		System.out.println(services);
		mv.addObject("user", user);
		mv.addObject("selection", selection);
		mv.addObject("services", services);
		mv.addObject("org", org);
		mv.addObject("geoKey", geoKey);
		
		return mv;
	}

	@PostMapping("/autorepo")
	public ModelAndView emailHelpListSelection(
			@SessionAttribute(name = "user", required = false) User user,
			@RequestParam("apiId") String apiId, 
			@RequestParam("content") String content) throws Exception {
		ModelAndView mv;
		mv = new ModelAndView("redirect:/userpro");
		
		// Create Org in database if not exists.
		Org org = orgDao.findByApiId(apiId);
		if (org == null) {
			org = apiService.findByApiId(apiId);
			orgDao.save(org);
		}
		
		email.sendMailFromUserToOrg(user, org, content);
		return mv;
	}

	@RequestMapping("/userpro")
	public ModelAndView userPro(@SessionAttribute(name = "user") User user) {
		ModelAndView mv = new ModelAndView("userpro");
		mv.addObject("user", user);
		mv.addObject("messageHistory", messageDao.findAllByUserId(user.getId()));
		mv.addObject("orgs", email.getUserMessageHistory(user));
		return mv;
	}

	@RequestMapping("/user-message-detail")
	public ModelAndView messageDetail(@SessionAttribute(name = "user") User user, @RequestParam("apiId") String apiId) {
		ModelAndView mv = new ModelAndView("user-message-detail");
		List<Message> messageHistory = messageDao.findAllByUserIdAndApiId(user.getId(), apiId);
		mv.addObject("messageHistory", messageHistory);
		mv.addObject("apiId", apiId);
		mv.addObject("issue", email.getLastMessageIssue(messageHistory));
		return mv;
	}

	@RequestMapping("/org-message-detail")
	public ModelAndView orgMessageDetail(
			@RequestParam("orgId") String orgId, 
			@RequestParam("userId") Long userId,
			@RequestParam("secret") String secret) {
		
		ModelAndView mv;
		Org org = orgDao.findByOrgId(orgId);
		if(org.getSecret().equals(secret.trim())) {
			mv = new ModelAndView("org-message-detail");
		} else {
			return new ModelAndView("redirect:/");
		}
		List<Message> messages = messageDao.findAllByUserIdAndOrgId(userId, orgId);
//		
		
		System.out.println(org.getName());
		mv.addObject("org", org);
		mv.addObject("secret", secret);
		mv.addObject("messageList", messages);
		mv.addObject("lastMessage", messages.get(messages.size() - 1));
		mv.addObject("orgId", messages.get(0).getOrgId());
		return mv;
	}

	@PostMapping("/org-message-detail")
	public ModelAndView orgSend(
			@RequestParam("orgId") String orgId, 
			@RequestParam("content") String content,
			@RequestParam("messageId") Long messageId, 
			@RequestParam("secret") String secret) {	
		ModelAndView mv;
		Org org = orgDao.findByOrgId(orgId);
		if(org.getSecret().equals(secret.trim())) {
			mv = new ModelAndView("redirect:/orgpro");
		} else {
			return new ModelAndView("redirect:/");
		}	
		email.sendMailFromOrgToUser(messageId, content);
		
		mv.addObject("orgId", orgId);
		mv.addObject("secret", secret);
		return mv;
	}

	@RequestMapping("/orgpro")
	public ModelAndView orgPro(
			@RequestParam("orgId") String orgId, 
			@RequestParam("secret") String secret) {
		ModelAndView mv;
		Org org = orgDao.findByOrgId(orgId);
		if(org.getSecret().equals(secret.trim())) {
			mv = new ModelAndView("orgpro");
		} else {
			return new ModelAndView("redirect:/");
		}	
		mv.addObject("userMap", email.getOrgMessageHistory(orgId));
		System.out.println(email.getOrgMessageHistory(orgId));
		mv.addObject("orgId", orgId);
		mv.addObject("secret", secret);
		return mv;

	}

//	@RequestMapping("/geocode")
//	public ModelAndView showOnMap() {
//		Double latitude = geocodingService.getLatitudeCoordinate("1600", "Amphitheatre Parkway", "Mountain View", "CA");
//		Double longitude = geocodingService.getLongitudeCoordinate("1600", "Amphitheatre Parkway", "Mountain View",
//				"CA");
//		Double[] coordinates = new Double[] { latitude, longitude };
//		return new ModelAndView("show-geocode", "coordinates", coordinates);
//	}
//
//	@RequestMapping("/reverse-geocode")
//	public ModelAndView getHumanReadableAddress() {
//		String address = geocodingService.getRevereseGeocoding(40.714224, -73.961452);
//		return new ModelAndView("show-reversegeocode", "address", address);
//	}
//
//	@RequestMapping("/places-text-search")
//	public ModelAndView SearchForPlaces() {
//		return new ModelAndView("text-search-form");
//	}
//
//	@PostMapping("/places-text-search")
//	public ModelAndView displaySearchResults(@RequestParam(value = "searchText", required = true) String searchText,
//			@RequestParam(value = "latitude", required = false) Double latitude,
//			@RequestParam(value = "longitude", required = false) Double longitude) {
//		Result[] places;
//		if (latitude != null && longitude != null) {
//			places = googlePlacesService.getListOfPlacesWithAddressBiased(searchText, latitude, longitude);
//		} else {
//			places = googlePlacesService.getListOfPlacesWithoutAddressBiased(searchText);
//		}
//		return new ModelAndView("display-places-of-interest", "places", places);
//	}

	/*
	 * @RequestMapping("/display-place-details") public ModelAndView
	 * displayPlaceDetails() { Result[] placeDetails =
	 * placesDetailsService.getPlaceDetails(placeId); return new ModelAndView("",
	 * "placeDetails", placeDetails); }
	 */

//	@RequestMapping("/display-place-details")
//	public ModelAndView displayPlaceDetails() {
//		DetailResult[] placeDetails = placesDetailsService.getPlaceDetails("ChIJi9Uq7qvqIogRUUxE3sZSlOU");
//		System.out.println(placeDetails.toString());
//		return new ModelAndView("", "placeDetails", placeDetails);
//	}

//	private String getPlacesUrl(String searchText, Double latitude, Double longitude) {
//		String fields = "formatted_address,name,place_id,rating,user_ratings_total";
//		String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=" + searchText + "&inputtype=textquery&fields="
//				+ fields + "&locationbias=circle:4000@" + buildLocation(latitude, longitude) + "&key=" + geoKey;
//		return url;
//	}
	
}
