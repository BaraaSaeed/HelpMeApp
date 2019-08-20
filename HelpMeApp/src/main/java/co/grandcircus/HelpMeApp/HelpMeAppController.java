/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali, Gerard Breitenbeck, Sienna Harris -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.HelpMeApp.Dao.MessageDao;
import co.grandcircus.HelpMeApp.Dao.OrgDao;
import co.grandcircus.HelpMeApp.Dao.UserDao;
import co.grandcircus.HelpMeApp.model.AutoEmail;

import co.grandcircus.HelpMeApp.google.GoogleService;
import co.grandcircus.HelpMeApp.model.Caa;
import co.grandcircus.HelpMeApp.model.HudService;
import co.grandcircus.HelpMeApp.model.Message;
import co.grandcircus.HelpMeApp.model.OrgObject;
import co.grandcircus.HelpMeApp.model.User;

@Controller
public class HelpMeAppController {

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
	private OrgDao orgDao;
	@Autowired
	private ApiService apiService;
	@Autowired
	private AutoEmail email;
	@Autowired
	private GoogleService googleService;

	@RequestMapping("/")
	public ModelAndView showHome() throws Exception {
		ModelAndView mv = new ModelAndView("index");
		String hudUrl = hudlistBase + city + state + "mi" + hudlistEnd;
		String caaUrl = caaListBase + caaResults + "100" + caaRadius + "100";

		List<OrgObject> orgs = new ArrayList<>();

		for (OrgObject each : apiService.findAll(hudUrl)) {
			orgs.add(each);
		}
		List<HudService> services = new ArrayList<>();
		for (HudService each : apiService.listServices(allServices)) {
			services.add(each);
		}
		List<Caa> caas = new ArrayList<>();
		for (Caa each : apiService.findCaas(caaUrl)) {
			caas.add(each);
		}

		System.out.println(orgs);
		System.out.println(services);
		System.out.println(caas);
		System.out.println(services);
		System.out.println(caas);
		System.out.println(services);
		System.out.println(caas);

		mv.addObject("organizations", orgs);
		mv.addObject("services", services);
		mv.addObject("caas", caas);
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
	public ModelAndView helplist(User user, HttpSession session) {
		ModelAndView mv = new ModelAndView("helplist");
		String hudUrl = "";
		String caaUrl = "";
		if (user == null) {
		hudUrl = hudlistBase + city + state + "mi" + hudlistEnd;
		caaUrl = caaListBase + caaResults + "100" + caaRadius + "100";
		} else {
			hudUrl = hudlistBase + city + user.getCity() + state + "mi" + hudlistEnd;
			caaUrl = caaListBase + caaResults + "100" + caaRadius + "100";
		}
		List<OrgObject> orgs = new ArrayList<>();

		for (OrgObject each : apiService.findAll(hudUrl)) {
			orgs.add(each);
		}
		List<HudService> services = new ArrayList<>();
		for (HudService each : apiService.listServices(allServices)) {
			services.add(each);
		}
		List<Caa> caas = new ArrayList<>();
		for (Caa each : apiService.findCaas(caaUrl)) {
			caas.add(each);
		}

		System.out.println(orgs);
		System.out.println(services);
		System.out.println(caas);

		mv.addObject("organizations", orgs);
		mv.addObject("services", services);
		mv.addObject("caas", caas);
		return mv;
	}

	@RequestMapping("/autorepo")
	public ModelAndView autorepo(@SessionAttribute(name = "user") User user, @RequestParam("id") Long orgId) {
		ModelAndView mv = new ModelAndView("autorepo", "id", orgId);
		return mv;
	}

	@PostMapping("/autorepo")
	public ModelAndView autoPost(@SessionAttribute(name = "user") User user,
//			@RequestParam("issue") String issue,
			@RequestParam("id") Long orgId) throws Exception {
		ModelAndView mv = new ModelAndView("userpro");
		email.sendMail(user, orgId, "Filler Issue");
		return mv;
	}

	@RequestMapping("/orgpro")
	public ModelAndView orgPro(@RequestParam("orgId") Long orgId, @RequestParam("userId") Long userId) {
		ModelAndView mv = new ModelAndView("orgpro", "orgId", orgId);
		List<Message> messages = messageDao.findAllByUserIdAndOrgId(userId, orgId);
		System.out.println(userId);
		System.out.println(messages);
		mv.addObject("messages", messages);
		mv.addObject("org", orgDao.findAllByAgcid(orgId));
		mv.addObject("lastMessage", messages.get(messages.size() - 1));

		System.out.println(messageDao.findAllByUserIdAndOrgId(userId, orgId));
		System.out.println(messages.get(messages.size() - 1));
		System.out.println(userDao.findAllById(userId));

		return mv;
	}

	@PostMapping("/orgpro")
	public ModelAndView orgSend(
//			@RequestParam("orgId") Long orgId, 
			@RequestParam("contentString") String contentString, @RequestParam("messageId") Long messageId) {
		ModelAndView mv = new ModelAndView("org-history");
		Message userMessage = messageDao.findByMessageId(messageId);
		String subject = "Re: " + userMessage.getSubject();
		String content = contentString.trim();
		Message message = new Message(userMessage.getUserId(), userMessage.getOrgId(), userMessage.getIssue(),
				email.getDate(), userMessage.getTo(), userMessage.getFrom(), subject, content);
		messageDao.save(message);
		System.out.println(messageId);
		return mv;
	}

	@RequestMapping("/userpro")
	public ModelAndView userPro() {
		ModelAndView mv = new ModelAndView("userpro");
		return mv;
	}
}