package com.am.controller;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;



@Controller
public class DashboardController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView mainTemplate(
			@RequestParam(value = "error", required = false) String error)
			throws AmazonServiceException, AmazonClientException, IOException {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("index");
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView mainTemplatePOST(
			@RequestParam(value = "error", required = false) String error)
			throws AmazonServiceException, AmazonClientException, IOException {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("index");
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			model.setViewName("index");
		}
		return model;
	}
	
	// for login_expired access denied page
		@RequestMapping(value = "/login_expired", method = RequestMethod.GET)
		public ModelAndView loginExpired() {
			ModelAndView model = new ModelAndView();
			model.setViewName("login_expired");
			return model;
		}

		// for 403 access denied page
		@RequestMapping(value = "/403", method = RequestMethod.GET)
		public ModelAndView accessDenied() {
			ModelAndView model = new ModelAndView();
			model.setViewName("403");
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