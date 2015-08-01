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

import com.am.bean.AttendeesReportBean;
import com.am.common.BeanMapper;
import com.am.service.GroupService;
import com.am.service.MinistryService;



@Controller
public class ReportController extends BeanMapper{

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MinistryService ministryService;
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ModelAndView reportGet(@ModelAttribute("attendees_report") AttendeesReportBean attendeesReportBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ministryList", prepareListOfMinistry(ministryService.listMinistry()));
		model.put("groupList", prepareListOfGroup(groupService.listGroup()));
		return new ModelAndView("reports", model);
	}
	
	@RequestMapping(value = "/view_attendees_report", method = RequestMethod.GET)
	public ModelAndView viewAttendeesReportGet(@ModelAttribute("attendees_report") AttendeesReportBean attendeesReportBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		
		return new ModelAndView("reports", model);
	}
	

}