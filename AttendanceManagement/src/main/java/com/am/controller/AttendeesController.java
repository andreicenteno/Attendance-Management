package com.am.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.am.bean.AttendeesBean;

import com.am.common.BeanMapper;
import com.am.model.Attendees;
import com.am.service.AttendeesService;



@Controller
public class AttendeesController extends BeanMapper{

	
	@Autowired
	AttendeesService attendeesService;
	
	@RequestMapping(value = "/attendees", method = RequestMethod.GET)
	public ModelAndView attendeeGet(){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("attendeesList", prepareListOfAttendees(attendeesService.listAttendees()));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("attendees", model);
	}
	
	@RequestMapping(value = "/add_attendees", method = RequestMethod.GET)
	public ModelAndView addAttendeesGet(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("add_attendees", model);
	}

	@RequestMapping(value = "/insert_attendees", method = RequestMethod.POST)
	public ModelAndView insertAttendees(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result){
		try{
			Attendees attendees = prepareAttendeesModel(attendeesBean);
			System.out.println(attendees.getFirstName()+attendees.getLastName()+attendees.getMiddleName()+attendees.getAddress()+attendees.getContactNumber()+attendees.getBirthday()+attendees.getGender()+attendees.getIsFirstTimer()+attendees.getIsMember());
			attendeesService.insert(attendees);
				
		}catch(Exception e){
			System.out.println(e);
		}
		
		return new ModelAndView("redirect:/attendees.html");
	}
	
	
	@RequestMapping(value = "/update_attendees", method = RequestMethod.GET)
	public ModelAndView updateAttendeesGet(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("updateAttendees", prepareAttendeesBean(attendeesService.getAttendees(attendeesBean.getAttendeesId())));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("update_attendees", model);
	}

	
	@RequestMapping(value = "/save_attendees", method = RequestMethod.POST)
	public ModelAndView saveAttendees(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result){
		try{
			Attendees entity = prepareAttendeesModel(attendeesBean);
			attendeesService.update(entity);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/attendees.html");
	}
	
}
