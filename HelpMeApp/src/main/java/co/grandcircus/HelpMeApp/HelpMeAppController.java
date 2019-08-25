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
	public ModelAndView helplist(
			@SessionAttribute(name = "user", required = false) User user,
			@RequestParam("selection") String selection) {
		ModelAndView mv = new ModelAndView("helplist");
		if (user != null) {
			user.setSelection(selection);
		}
		System.out.println(selection);
		List<Org> orgs = new ArrayList<>();
		String hudUrl = "";
		String caaUrl = "";
		if (user == null) {
			hudUrl = miHudUrl;
			caaUrl = caaListBase + caaResults + "100" + caaRadius + "100";
		} else {
			hudUrl = hudlistBase + city + user.getCity() + state + hudlistEnd;
			caaUrl = caaListBase + caaResults + "100" + caaRadius + "100";
		}
		for (Org each : apiService.findAllHud(hudUrl)) {
			orgs.add(each);
		}
		for (Org each : apiService.findCaas(caaUrl)) {
			orgs.add(each);
		}
		if (selection.equals("All Services")) {
			mv.addObject("selectOrgs", orgs);
		} else {
			List<Org> selectOrgs = methods.getSelectOrgs(orgs, hudUrl, selection);
			mv.addObject("selectOrgs", selectOrgs);
		}
		mv.addObject("selection", selection);
		return mv;
	}

	@RequestMapping("/autorepo")
	public ModelAndView autorepo(
			@SessionAttribute(name = "user", required = false) User user,
			@RequestParam("apiId") String apiId,
			@RequestParam("selection") String selection) {
		ModelAndView mv = new ModelAndView("autorepo");
		Org org = apiService.findByApiId(apiId);
		List<String> serviceList = methods.translateServices(org.getServices());	
		mv.addObject("selection", selection);
		mv.addObject("serviceList", serviceList);
		mv.addObject("org", org);
		return mv;
	}

	@PostMapping("/autorepo")
	public ModelAndView autoPost(
			@SessionAttribute(name = "user", required = false) User user,
			@RequestParam("selection") String selection, 
			@RequestParam("apiId") String apiId,
			@RequestParam("content") String content) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/userpro");
		Org org = apiService.findByApiId(apiId);
		String issue;
		if (selection.equals("All Services")) {
			issue = "your services";
		} else {
			issue = selection;
		}
		email.sendMail(user, org, issue, content);
		return mv;
	}

	@RequestMapping("/userpro")
	public ModelAndView userPro(
			@SessionAttribute(name = "user") User user) {
		ModelAndView mv = new ModelAndView("userpro");
		List<Message> messageHistory = messageDao.findAllByUserId(user.getId());
		Map<String, String> orgSet = new HashMap<>();
		for (Message each : messageHistory) {
			orgSet.put(each.getApiId(), each.getOrgName());
		}
		System.out.println(orgSet);

		mv.addObject("user", user);
		mv.addObject("messageHistory", messageHistory);
		mv.addObject("orgSet", orgSet);

		return mv;
	}
	
	@RequestMapping("/user-message-detail")
	public ModelAndView messageDetail(
			@SessionAttribute(name = "user") User user, 
			@RequestParam("apiId") String apiId) {
		ModelAndView mv = new ModelAndView("user-message-detail");
		List<Message> messageHistory = messageDao.findAllByUserIdAndApiId(user.getId(), apiId);
		System.out.println(messageDao.findAllByUserIdAndApiId(user.getId(), apiId));
		for (Message each : messageHistory) {
		}
		Message lastMessage = messageHistory.get(messageHistory.size() - 1);
		String issue = lastMessage.getIssue();
		mv.addObject("messageHistory", messageHistory);
		mv.addObject("apiId", apiId);
		mv.addObject("issue", issue);
		return mv;
	}

	@RequestMapping("/org-message-detail")
	public ModelAndView orgMessageDetail(
			@RequestParam("apiId") String apiId, 
			@RequestParam("userId") Long userId) {
		ModelAndView mv = new ModelAndView("org-message-detail");
		String unwrappedApiId = methods.unWrapApiIdFromHtml(apiId);
		List<Message> messages = messageDao.findAllByUserIdAndApiId(
				userId, unwrappedApiId);

		mv.addObject("messageList", messages);
		System.out.println(messages);

		mv.addObject("userId", userId);
		mv.addObject("lastMessage", messages.get(messages.size() - 1));
		mv.addObject("apiId", unwrappedApiId);
		return mv;
	}

	@PostMapping("/org-message-detail")
	public ModelAndView orgSend(
			@RequestParam("apiId") String apiId, 
			@RequestParam("contentString") String contentString,
			@RequestParam("messageId") Long messageId) {

		ModelAndView mv = new ModelAndView("redirect:/orgpro");
		Message userMessage = messageDao.findByMessageId(messageId);
		String subject = "Re: " + userMessage.getSubject();
		String content = contentString.trim();
		Message message = new Message(userMessage.getUserId(), userMessage.getUserName(), userMessage.getOrgId(), userMessage.getOrgName(), userMessage.getApiId(), userMessage.getIssue(),
				email.getDate(), userMessage.getTo(), userMessage.getFrom(), subject, content);
		messageDao.save(message);
		mv.addObject("apiId", apiId);
		return mv;
	}

	@RequestMapping("/orgpro")
	public ModelAndView orgPro(
			@RequestParam("apiId") String apiId) {
		ModelAndView mv = new ModelAndView("orgpro");
		List<Message> messageHistory = messageDao.findAllByApiId(apiId);
		System.out.println("messageHist:" + messageHistory);
		Map<Long, String> userMap = new HashMap<>();
		for (Message each : messageHistory) {
			userMap.put(each.getUserId(), each.getUserName());
		}
		System.out.println("userMap:" + userMap);
		mv.addObject("userMap", userMap);
		mv.addObject("apiId", apiId);
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
}
