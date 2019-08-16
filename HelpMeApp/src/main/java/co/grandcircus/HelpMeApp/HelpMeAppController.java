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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.HelpMeApp.model.OrgObject;

@Controller
public class HelpMeAppController {

//	@Autowired
//	HelpMeAppDao dao;
	@Autowired
	private ApiService apiService;

	@RequestMapping("/")
	public ModelAndView showHome() {
		ModelAndView mv = new ModelAndView("index");
		List<OrgObject> orgs = new ArrayList<>();
		for(OrgObject each : apiService.findAll()) {
			orgs.add(each);
		}
		System.out.println(orgs);
				
		mv.addObject("organizations", orgs);
		return mv;
	}
}
