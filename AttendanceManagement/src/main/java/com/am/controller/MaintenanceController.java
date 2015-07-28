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

import com.am.common.BeanMapper;
import com.am.service.GroupService;
import com.am.service.MinistryService;
import com.am.service.ServiceService;
import com.am.bean.GroupBean;
import com.am.bean.MinistryBean;
import com.am.bean.ServiceBean;
import com.am.model.Group;
import com.am.model.Ministry;
import com.am.model.ServiceEntity;



@Controller
public class MaintenanceController extends BeanMapper{

	@Autowired
	private MinistryService ministryService;
	
	@Autowired
	private ServiceService serviceService;
	
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/maintenance", method = RequestMethod.GET)
	public ModelAndView maintenanceGet(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ministryList", prepareListOfMinistry(ministryService.listMinistry()));
		model.put("serviceList", prepareListOfService(serviceService.listService()));
		model.put("groupList", prepareListOfGroup(groupService.listGroup()));
		return new ModelAndView("maintenance", model);
	}
	
	 /*============= MINISTRY ===================== */

	@RequestMapping(value = "/add_ministry", method = RequestMethod.GET)
	public ModelAndView addMinistryGet(@ModelAttribute("ministry") MinistryBean ministryBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("add_ministry", model);
	}
	
	@RequestMapping(value = "/update_ministry", method = RequestMethod.GET)
	public ModelAndView updateMinistryGet(@ModelAttribute("ministry") MinistryBean ministryBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("updateMinistry", prepareMinistryBean(ministryService.getMinistry(ministryBean.getMinistryId())));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("update_ministry", model);
	}
	
	@RequestMapping(value = "/insert_ministry", method = RequestMethod.POST)
	public ModelAndView insertMinistry(@ModelAttribute("ministry") MinistryBean ministryBean,BindingResult result){
		try{
			Ministry ministry = prepareMinistryModel(ministryBean);
			ministryService.insert(ministry);
				
		}catch(Exception e){
			System.out.println(e);
		}
		
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	@RequestMapping(value = "/save_ministry", method = RequestMethod.POST)
	public ModelAndView saveMinistry(@ModelAttribute("ministry") MinistryBean ministryBean,BindingResult result){
		try{
			Ministry ministry = prepareMinistryModel(ministryBean);
			ministryService.update(ministry);
		}catch(Exception e){
			
		}
		return new ModelAndView("redirect:/maintenance.html");
	}

	@RequestMapping(value = "/delete_ministry", method = RequestMethod.GET)
	public ModelAndView deleteMinistry(@ModelAttribute("ministry") MinistryBean ministryBean,BindingResult result){
		try{
			Ministry ministry = prepareMinistryModel(ministryBean);
			ministryService.delete(ministry);
		}catch(Exception e){
			
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
	
/*=============== GROUPS ================== */		
	
	@RequestMapping(value = "/add_group", method = RequestMethod.GET)
	public ModelAndView addGroupGet(@ModelAttribute("group") GroupBean groupBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("add_group", model);
	}
	
	@RequestMapping(value = "/insert_group", method = RequestMethod.POST)
	public ModelAndView insertGroup(@ModelAttribute("group") GroupBean groupBean,BindingResult result){
		try{
			Group group= prepareGroupModel(groupBean);
			groupService.insert(group);
				
		}catch(Exception e){
			System.out.println(e);
		}
		
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	@RequestMapping(value = "/update_group", method = RequestMethod.GET)
	public ModelAndView updateGroupGet(@ModelAttribute("group") GroupBean groupBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("updateGroup", prepareGroupBean(groupService.getGroup(groupBean.getGroupId())));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("update_group", model);
	}
	
	@RequestMapping(value = "/save_group", method = RequestMethod.POST)
	public ModelAndView saveGroup(@ModelAttribute("group") GroupBean groupBean,BindingResult result){
		try{
			Group group= prepareGroupModel(groupBean);
			groupService.update(group);
		}catch(Exception e){
			
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	
	@RequestMapping(value = "/delete_group", method = RequestMethod.GET)
	public ModelAndView deleteGroup(@ModelAttribute("group") GroupBean groupBean,BindingResult result){
		try{
			Group group = prepareGroupModel(groupBean);
			groupService.delete(group);
		}catch(Exception e){
			
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	
 /*=============== SERVICE ================== */	
	
	@RequestMapping(value = "/add_service", method = RequestMethod.GET)
	public ModelAndView addServiceGet(@ModelAttribute("service") ServiceBean serviceBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("add_service", model);
	}
	
	@RequestMapping(value = "/insert_service", method = RequestMethod.POST)
	public ModelAndView insertService(@ModelAttribute("service") ServiceBean serviceBean,BindingResult result){
		try{
			ServiceEntity serviceEntity = prepareServiceModel(serviceBean);
			serviceService.insert(serviceEntity);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	
	@RequestMapping(value = "/update_service", method = RequestMethod.GET)
	public ModelAndView updateService(@ModelAttribute("service") ServiceBean serviceBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("updateService", prepareServiceBean(serviceService.getService(serviceBean.getServiceId())));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("update_service", model);
	}
	
	
	@RequestMapping(value = "/save_service", method = RequestMethod.POST)
	public ModelAndView saveService(@ModelAttribute("service") ServiceBean serviceBean,BindingResult result){
		try{
			ServiceEntity entity = prepareServiceModel(serviceBean);
			serviceService.update(entity);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	
	@RequestMapping(value = "/delete_service", method = RequestMethod.GET)
	public ModelAndView deleteService(@ModelAttribute("service") ServiceBean serviceBean,BindingResult result){
		try{
			ServiceEntity entity = prepareServiceModel(serviceBean);
			serviceService.delete(entity);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
}