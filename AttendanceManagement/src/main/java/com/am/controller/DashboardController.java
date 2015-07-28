package com.am.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;



@Controller
public class DashboardController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexPost(){
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		return model;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	public ModelAndView dashboardPost(){
		ModelAndView model = new ModelAndView();
		model.setViewName("dashboard");
		
		return model;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboardGet(){
		ModelAndView model = new ModelAndView();
		model.setViewName("dashboard");
		
		return model;
	}

}