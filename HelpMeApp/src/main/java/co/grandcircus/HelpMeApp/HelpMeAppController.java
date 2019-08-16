/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali, Gerard Breitenbeck, Sienna Harris -  API and implementation.
 * All rights reserved.
*/

package co.grandcircus.HelpMeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.HelpMeApp.Dao.HelpMeAppDao;

@Controller
public class HelpMeAppController {

	@Autowired
	HelpMeAppDao dao;
//	@Autowired
//	private ApiService apiService;

	@RequestMapping("/")
	public ModelAndView showHome() {
		return new ModelAndView("index");
	}
}
