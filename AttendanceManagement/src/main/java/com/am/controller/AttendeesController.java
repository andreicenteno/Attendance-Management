package com.am.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.am.bean.AttendeesBean;
import com.am.common.BaseResponse;
import com.am.common.BeanMapper;
import com.am.common.Constant;
import com.am.common.ErrorHandler;
import com.am.common.ResponseCode;
import com.am.model.Attendees;
import com.am.operation.AttendeesOperation;
import com.am.service.AttendeesService;
import com.am.service.GroupService;
import com.am.service.MinistryService;



@Controller
public class AttendeesController extends BeanMapper{

	private static Logger LOGGER = Logger.getLogger(AttendeesController.class);
	
	@Autowired
	private AttendeesService attendeesService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private MinistryService ministryService;
	
	@Autowired
	private AttendeesOperation attendeesOperation;
	
	@RequestMapping(value = "/attendees", method = RequestMethod.GET)
	public ModelAndView attendeeGet(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("attendeesList", prepareListOfAttendees(attendeesService.listAttendees()));
			model.put("groupList", prepareListOfGroup(groupService.listGroup()));
			ErrorHandler.setupModelMapResponseMessage(modelMap, request);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("attendees", model);
	}
	
	@RequestMapping(value = "/search_attendees", method = RequestMethod.GET)
	public ModelAndView searchAttendeesPost(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			System.out.println(attendeesBean.getKeywords());
			model.put("attendeesList", prepareListOfAttendees(attendeesService.findAttendeesByName(attendeesBean.getKeywords().trim())));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("attendees", model);
	}
	
	
	
	@RequestMapping(value = "/add_attendees", method = RequestMethod.GET)
	public ModelAndView addAttendeesGet(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("groupList", prepareListOfGroup(groupService.listGroup()));
		model.put("ministryList", prepareListOfMinistry(ministryService.listMinistry()));
		return new ModelAndView("add_attendees", model);
	}

	@RequestMapping(value = "/insert_attendees", method = RequestMethod.POST)
	public ModelAndView insertAttendees(HttpServletResponse response, @ModelAttribute("attendees") AttendeesBean attendeesBean,
			 BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = attendeesOperation.insertAttendees(attendeesBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/attendees.html");
	}
	
	
	@RequestMapping(value = "/update_attendees", method = RequestMethod.GET)
	public ModelAndView updateAttendeesGet(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("updateAttendees", prepareAttendeesBean(attendeesService.getAttendees(attendeesBean.getAttendeesId())));
			model.put("groupList", prepareListOfGroup(groupService.listGroup()));
			model.put("ministryList", prepareListOfMinistry(ministryService.listMinistry()));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("update_attendees", model);
	}
	
	@RequestMapping(value = "/delete_attendees", method = RequestMethod.GET)
	public ModelAndView deleteAttendees(HttpServletResponse response,  @ModelAttribute("attendees") AttendeesBean attendeesBean,
			BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = attendeesOperation.deleteAttendees(attendeesBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/attendees.html");
	}

	
	@RequestMapping(value = "/save_attendees", method = RequestMethod.POST)
	public ModelAndView saveAttendees(HttpServletResponse response,  @ModelAttribute("attendees") AttendeesBean attendeesBean,
			BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = attendeesOperation.updateAttendees(attendeesBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/attendees.html");
	}
	
	
	
}
