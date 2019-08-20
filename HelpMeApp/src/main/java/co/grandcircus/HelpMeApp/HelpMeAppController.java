/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali, Gerard Breitenbeck, Sienna Harris -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

//	@Autowired
//	HelpMeAppDao dao;

	@Autowired
	MessageDao messageDao;

	@Autowired
	UserDao userDao;
	@Autowired
	OrgDao orgDao;
	@Autowired
	private ApiService apiService;
	@Autowired
	private AutoEmail email;

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
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/helplist")
	public ModelAndView helplist(HttpSession session) {
		ModelAndView mv = new ModelAndView("helplist");

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
	public ModelAndView orgPro(
			@RequestParam("orgId") Long orgId, 
			@RequestParam("userId") Long userId) {
		ModelAndView mv = new ModelAndView("orgpro", "orgId", orgId);
		mv.addObject("message", messageDao.findAllByUserIdAndOrgId(userId, orgId));
		mv.addObject("user", userDao.findAllById(userId));
		mv.addObject("org", orgDao.findAllByAgcid(orgId));
		System.out.println(messageDao.findAllByUserIdAndOrgId(userId, orgId));
		System.out.println(userDao.findAllById(userId));
		System.out.println(orgDao.findAllByAgcid(orgId));
		return mv;
	}
	
	@PostMapping("/orgpro")
	public ModelAndView orgSend()
//			@RequestParam("orgId") Long orgId, 
//			@RequestParam("contentString") String contentString)
//			@RequestParam("messageId") Long messageId) 
{
		ModelAndView mv = new ModelAndView("org-history");
		Message userMessage = messageDao.findByMessageId(1L);
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateObj = new Date();
		String subject = "Re: " + userMessage.getSubject(); 
		String contentString = "Thanks for reaching out to us!";
		Message message = new Message(userMessage.getUserId(), userMessage.getOrgId(), userMessage.getIssue(), dateObj, userMessage.getTo(), userMessage.getFrom(), subject, contentString);
		messageDao.save(message);
		System.out.println(message);
		return mv;
	}

	@RequestMapping("/userpro")
	public ModelAndView userPro() {
		ModelAndView mv = new ModelAndView("userpro");
		return mv;
	}
}
