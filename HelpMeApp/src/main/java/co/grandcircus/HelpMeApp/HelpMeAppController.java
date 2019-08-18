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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.HelpMeApp.model.Caa;
import co.grandcircus.HelpMeApp.model.HudService;
import co.grandcircus.HelpMeApp.model.OrgObject;

@Controller
public class HelpMeAppController {

	@Value("${sendGrid_KEY}")
	private String SENDGRID_KEY;
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
	private ApiService apiService;

	@RequestMapping("/")
	public ModelAndView showHome() {
		ModelAndView mv = new ModelAndView("index");
		String hudUrl = hudlistBase + city + state + "mi" + hudlistEnd;
		String caaUrl = caaListBase + caaResults + "100" + caaRadius + "100"; 
		
		List<OrgObject> orgs = new ArrayList<>();
		for(OrgObject each : apiService.findAll(hudUrl)) {
			orgs.add(each);
		}
		List<HudService> services = new ArrayList<>();
		for(HudService each : apiService.listServices(allServices)) {
			services.add(each);
		}
		List<Caa> caas = new ArrayList<>();
		for(Caa each : apiService.findCaas(caaUrl)) {
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
}
