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

import com.am.common.BaseResponse;
import com.am.common.BeanMapper;
import com.am.common.Constant;
import com.am.common.ErrorHandler;
import com.am.common.ResponseCode;
import com.am.service.GroupService;
import com.am.service.MinistryService;
import com.am.service.ServiceService;
import com.am.bean.GroupBean;
import com.am.bean.MinistryBean;
import com.am.bean.ServiceBean;
import com.am.operation.MaintenanceOperation;

@Controller
public class MaintenanceController extends BeanMapper{

	private static Logger LOGGER = Logger.getLogger(MaintenanceController.class);
	
	@Autowired
	private MinistryService ministryService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private MaintenanceOperation maintenanceOperation;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/maintenance", method = RequestMethod.GET)
	public ModelAndView maintenanceGet(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ministryList", prepareListOfMinistry(ministryService.listMinistryWithoutNone()));
		model.put("serviceList", prepareListOfService(serviceService.listService()));
		model.put("groupList", prepareListOfGroup(groupService.listGroup()));
		ErrorHandler.setupModelMapResponseMessage(modelMap, request);
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
			LOGGER.error(e);
		}
		return new ModelAndView("update_ministry", model);
	}
	
	@RequestMapping(value = "/insert_ministry", method = RequestMethod.POST)
	public ModelAndView insertMinistry(HttpServletResponse response, @ModelAttribute("ministry") MinistryBean ministryBean,
			BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = maintenanceOperation.insertMinistry(ministryBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	@RequestMapping(value = "/save_ministry", method = RequestMethod.POST)
	public ModelAndView saveMinistry(HttpServletResponse response, @ModelAttribute("ministry") MinistryBean ministryBean,
			BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = maintenanceOperation.updateMinistry(ministryBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
			
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/maintenance.html");
	}

	@RequestMapping(value = "/delete_ministry", method = RequestMethod.GET)
	public ModelAndView deleteMinistry(@ModelAttribute("ministry") MinistryBean ministryBean,
			HttpServletResponse response, BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = maintenanceOperation.deleteMinistry(ministryBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
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
	public ModelAndView insertGroup(HttpServletResponse response,@ModelAttribute("group") GroupBean groupBean,
			 BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = maintenanceOperation.insertGroup(groupBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	@RequestMapping(value = "/update_group", method = RequestMethod.GET)
	public ModelAndView updateGroupGet(@ModelAttribute("group") GroupBean groupBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("updateGroup", prepareGroupBean(groupService.getGroup(groupBean.getGroupId())));
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("update_group", model);
	}
	
	@RequestMapping(value = "/save_group", method = RequestMethod.POST)
	public ModelAndView saveGroup(HttpServletResponse response,@ModelAttribute("group") GroupBean groupBean,
			 BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = maintenanceOperation.updateGroup(groupBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	
	@RequestMapping(value = "/delete_group", method = RequestMethod.GET)
	public ModelAndView deleteGroup(HttpServletResponse response,@ModelAttribute("group") GroupBean groupBean,
			 BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = maintenanceOperation.deleteGroup(groupBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
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
	public ModelAndView insertService(HttpServletResponse response, @ModelAttribute("service") ServiceBean serviceBean,BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = maintenanceOperation.insertService(serviceBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	
	@RequestMapping(value = "/update_service", method = RequestMethod.GET)
	public ModelAndView updateService(HttpServletResponse response, @ModelAttribute("service") ServiceBean serviceBean,BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("updateService", prepareServiceBean(serviceService.getService(serviceBean.getServiceId())));
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("update_service", model);
	}
	
	
	@RequestMapping(value = "/save_service", method = RequestMethod.POST)
	public ModelAndView saveService(HttpServletResponse response, @ModelAttribute("service") ServiceBean serviceBean,BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = maintenanceOperation.updateService(serviceBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
	
	
	@RequestMapping(value = "/delete_service", method = RequestMethod.GET)
	public ModelAndView deleteService(HttpServletResponse response, @ModelAttribute("service") ServiceBean serviceBean,BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = maintenanceOperation.deleteService(serviceBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.MAINTENANCE_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/maintenance.html");
	}
}