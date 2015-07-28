package com.am.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;



@Controller
public class ReportController {

	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ModelAndView reportGet(){
		ModelAndView model = new ModelAndView();
		model.setViewName("reports");
		
		return model;
	}

}