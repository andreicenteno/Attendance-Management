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

import com.am.bean.AttendeesBean;
import com.am.bean.ServiceAttendanceViewBean;
import com.am.bean.SundayServiceAttendeesBean;
import com.am.bean.SundayServiceBean;
import com.am.common.BeanMapper;
import com.am.model.ServiceAttendanceView;
import com.am.model.SundayService;
import com.am.service.AttendeesService;
import com.am.service.ServiceAttendanceViewService;
import com.am.service.ServiceService;
import com.am.service.SundayServiceAttendeesService;
import com.am.service.SundayServiceService;





@Controller
public class SundayServiceController extends BeanMapper {

	@Autowired
	private SundayServiceService sundayServiceService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private AttendeesService attendeesService;
	
	@Autowired
	private ServiceAttendanceViewService serviceAttendanceViewService;
	
	
	@Autowired
	private SundayServiceAttendeesService sundayServiceAttendeesService;
	
	@RequestMapping(value = "/sunday_service", method = RequestMethod.GET)
	public ModelAndView sundayServiceGet(@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sundayServiceList", prepareListOfSundayService(sundayServiceService.listSundayService()));
		return new ModelAndView("sunday_service", model);
	}
	
	
	@RequestMapping(value = "/add_sunday_service", method = RequestMethod.GET)
	public ModelAndView addSundayServiceGet(@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("servicesList", prepareListOfService(serviceService.listService()));
		return new ModelAndView("add_sunday_service", model);
		
	}
	
	@RequestMapping(value = "/insert_sunday_service", method = RequestMethod.POST)
	public ModelAndView insertSundayService(@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,BindingResult result){
		try{
			SundayService sundayService = prepareSundayServiceModel(sundayServiceBean);
			sundayServiceService.insert(sundayService);
				
		}catch(Exception e){
			System.out.println(e);
		}
		
		return new ModelAndView("redirect:/sunday_service.html");
	}
	
	
	@RequestMapping(value = "/update_sunday_service", method = RequestMethod.GET)
	public ModelAndView updateSundayServiceGet(@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("updateSundayService", prepareSundayServiceBean(sundayServiceService.getSundayService(sundayServiceBean.getSundayServiceId())));
			model.put("servicesList", prepareListOfService(serviceService.listService()));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("update_sunday_service", model);
	}
	
	@RequestMapping(value = "/save_sunday_service", method = RequestMethod.POST)
	public ModelAndView saveSundayService(@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,BindingResult result){
		try{
			SundayService sundayService = prepareSundayServiceModel(sundayServiceBean);
			sundayServiceService.update(sundayService);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/sunday_service.html");
	}
	
	@RequestMapping(value = "/delete_sunday_service", method = RequestMethod.GET)
	public ModelAndView deleteSundayService(@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,BindingResult result){
		try{
			
			SundayService entity = new SundayService();
			entity.setId(sundayServiceBean.getSundayServiceId());
			sundayServiceService.delete(entity);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/sunday_service.html");
	}
	
	
	@RequestMapping(value = "/sunday_service_attendees", method = RequestMethod.GET)
	public ModelAndView sundayServiceAttendeesGet(@ModelAttribute("attendees") AttendeesBean attendeesBean,
			@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
		model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(sundayServiceBean.getSundayServiceId())));
		model.put("sundayServiceAttendeesList", prepareListOfAttendees(attendeesService.findAttendeesOnSundayService(sundayServiceBean.getSundayServiceId())));
		modelMap.addAttribute("SUNDAY_SERVICE_ID", sundayServiceBean.getSundayServiceId());
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("sunday_service_attendees", model);
	}
	
	@RequestMapping(value = "/search_service_attendees", method = RequestMethod.GET)
	public ModelAndView searchsundayServiceGet(@ModelAttribute("attendees") AttendeesBean attendeesBean,
			@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(attendeesBean.getSundayServiceId())));
			model.put("sundayServiceAttendeesList", prepareListOfAttendees(attendeesService.findAttendeesOnSundayServiceByName(attendeesBean.getSundayServiceId(), attendeesBean.getKeywords())));
			modelMap.addAttribute("SUNDAY_SERVICE_ID", attendeesBean.getSundayServiceId());
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("sunday_service_attendees", model);
	}
	
	
	
	@RequestMapping(value = "/sunday_service_profile", method = RequestMethod.GET)
	public ModelAndView sundayServiceProfileGet(@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
		model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(sundayServiceBean.getSundayServiceId())));
		model.put("sundayServiceAttendeesList", prepareListOfSundayServiceAttendees(sundayServiceAttendeesService.findSundayServiceAttendeesById(sundayServiceBean.getSundayServiceId())));
		modelMap.addAttribute("SUNDAY_SERVICE_ID", sundayServiceBean.getSundayServiceId());
		ServiceAttendanceViewBean serviceAttendanceViewBean = prepareListOfServiceAttendanceView(serviceAttendanceViewService.listServiceAttendanceView(sundayServiceBean.getSundayServiceId())).get(0);
		if(serviceAttendanceViewBean != null){
			modelMap.addAttribute("TOTAL_OF_ALL_ATTENDEES", serviceAttendanceViewBean.getTotal());
			modelMap.addAttribute("TOTAL_OF_KKB", serviceAttendanceViewBean.getTotalOfKkb());
			modelMap.addAttribute("TOTAL_OF_YAM", serviceAttendanceViewBean.getTotalOfYam());
			modelMap.addAttribute("TOTAL_OF_MEN", serviceAttendanceViewBean.getTotalOfMen());
			modelMap.addAttribute("TOTAL_OF_WOMEN", serviceAttendanceViewBean.getTotalOfWomen());
			modelMap.addAttribute("TOTAL_OF_CHILDREN", serviceAttendanceViewBean.getTotalOfChildren());
		}
		
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("sunday_service_profile", model);
	}
	
	

	@RequestMapping(value = "/insert_service_attendees", method = RequestMethod.POST)
	public ModelAndView insertSundayServiceAttendees(@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,BindingResult result){
		try{
			sundayServiceAttendeesService.insert(prepareSundayServiceAttendeesModel(sundayServiceAttendeesBean));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/sunday_service_attendees.html?sundayServiceId="+sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
	}
	
	@RequestMapping(value = "/remove_service_attendees", method = RequestMethod.POST)
	public ModelAndView removeSundayServiceAttendees(@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,BindingResult result){
		try{
			sundayServiceAttendeesService.delete(prepareSundayServiceAttendeesModel(sundayServiceAttendeesBean));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/sunday_service_profile.html?sundayServiceId="+sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
	}
	

}