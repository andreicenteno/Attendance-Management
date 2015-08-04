package com.am.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.am.bean.AttendeesReportBean;
import com.am.bean.AttendeesSummaryViewBean;
import com.am.common.BeanMapper;

import com.am.service.AttendeesSummaryViewService;
import com.am.service.AttendeesViewService;
import com.am.service.GroupService;
import com.am.service.MinistryService;



@Controller
public class ReportController extends BeanMapper{

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MinistryService ministryService;
	
	@Autowired
	private AttendeesViewService attendeesViewService;
	
	@Autowired
	private AttendeesSummaryViewService attendeesSummaryViewService;
	
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ModelAndView reportGet(@ModelAttribute("attendees_report") AttendeesReportBean attendeesReportBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ministryList", prepareListOfMinistry(ministryService.listMinistry()));
		model.put("groupList", prepareListOfGroup(groupService.listGroup()));
		return new ModelAndView("reports", model);
	}
	
	private void mapAttendeesSummary(ModelMap modelMap, AttendeesSummaryViewBean attendeesSummaryViewBean){
		
		modelMap.addAttribute("TOTAL_OF_ALL", attendeesSummaryViewBean.getTotal());
		modelMap.addAttribute("TOTAL_OF_KKB", attendeesSummaryViewBean.getTotalOfKkb());
		modelMap.addAttribute("TOTAL_OF_KKB_MALE", attendeesSummaryViewBean.getTotalOfKkbMale());
		modelMap.addAttribute("TOTAL_OF_KKB_FEMALE", attendeesSummaryViewBean.getTotalOfKkbFemale());
		modelMap.addAttribute("TOTAL_OF_YAM", attendeesSummaryViewBean.getTotalOfYam());
		modelMap.addAttribute("TOTAL_OF_YAM_MALE", attendeesSummaryViewBean.getTotalOfYamMale());
		modelMap.addAttribute("TOTAL_OF_YAM_FEMALE", attendeesSummaryViewBean.getTotalOfYamFemale());
		modelMap.addAttribute("TOTAL_OF_CHILDREN", attendeesSummaryViewBean.getTotalOfChildren());
		modelMap.addAttribute("TOTAL_OF_CHILDREN_MALE", attendeesSummaryViewBean.getTotalOfChildrenMale());
		modelMap.addAttribute("TOTAL_OF_CHILDREN_FEMALE", attendeesSummaryViewBean.getTotalOfChildrenFemale());
		modelMap.addAttribute("TOTAL_OF_MEN", attendeesSummaryViewBean.getTotalOfMen());
		modelMap.addAttribute("TOTAL_OF_WOMEN", attendeesSummaryViewBean.getTotalOfWomen());
		
	}
	
	@RequestMapping(value = "/view_attendees_report", method = RequestMethod.GET)
	public ModelAndView viewAttendeesReportGet(@ModelAttribute("attendees_report") AttendeesReportBean attendeesReportBean,BindingResult result,
			ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		AttendeesSummaryViewBean attendeesSummaryViewBean;
		try{
			List<AttendeesSummaryViewBean> attendeesSummaryView = prepareListOfAttendeesSummaryView(attendeesSummaryViewService.listAttendeesSummaryView());
			if(attendeesSummaryView!=null){
				attendeesSummaryViewBean = attendeesSummaryView.get(0);
				
				if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() == 0 && attendeesReportBean.getGender().equals("all")){ // all
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesSummaryView()));
					mapAttendeesSummary(modelMap, attendeesSummaryViewBean);
				}
				else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() == 0 && attendeesReportBean.getGender().equals("all")) // group
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGroup(attendeesReportBean.getGroupId())));
				else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() != 0 && attendeesReportBean.getGender().equals("all")) // ministry
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByMinistry(attendeesReportBean.getMinistryId())));
				else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() == 0 && !attendeesReportBean.getGender().equals("all")) // gender
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGender(Boolean.parseBoolean(attendeesReportBean.getGender()))));
				else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() != 0 && attendeesReportBean.getGender().equals("all")) // group ministry
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewwByGroupMinistry(attendeesReportBean.getGroupId(), attendeesReportBean.getMinistryId())));
				else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() == 0 && !attendeesReportBean.getGender().equals("all")) // group gender
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGenderGroup(Boolean.parseBoolean(attendeesReportBean.getGender()), attendeesReportBean.getGroupId())));
				else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() != 0 && !attendeesReportBean.getGender().equals("all")) // ministry gender
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGenderMinistry(Boolean.parseBoolean(attendeesReportBean.getGender()), attendeesReportBean.getMinistryId())));
				else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() != 0 && !attendeesReportBean.getGender().equals("all")) // group ministry gender
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGroupMinistryGender(attendeesReportBean.getGroupId(), attendeesReportBean.getMinistryId(), Boolean.parseBoolean(attendeesReportBean.getGender()))));
			}
			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return new ModelAndView("view_attendees_report", model);
	}
	

}