package com.am.controller;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.am.bean.AttendeesBean;
import com.am.bean.FirstTimerBean;
import com.am.bean.ServiceAttendanceViewBean;
import com.am.bean.SundayServiceAttendeesBean;
import com.am.bean.SundayServiceBean;
import com.am.bean.Tag;
import com.am.common.BaseResponse;
import com.am.common.BeanMapper;
import com.am.common.Constant;
import com.am.common.ErrorHandler;
import com.am.common.ResponseCode;
import com.am.model.Attendees;
import com.am.model.FirstTimer;
import com.am.model.Group;
import com.am.model.ServiceAttendanceView;
import com.am.model.SundayService;
import com.am.model.SundayServiceAttendees;
import com.am.operation.ServiceAttendeesOperation;
import com.am.operation.SundayServiceOperation;
import com.am.service.AttendeesService;
import com.am.service.FirstTimerService;
import com.am.service.GroupService;
import com.am.service.MinistryService;
import com.am.service.ServiceAttendanceViewService;
import com.am.service.ServiceService;
import com.am.service.SundayServiceAttendeesService;
import com.am.service.SundayServiceService;
import com.am.utils.HeaderFooter;
import com.am.utils.PDFUtil;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;


@Controller
public class SundayServiceController extends BeanMapper {

	private static Logger LOGGER = Logger.getLogger(SundayServiceController.class);
	@Autowired
	private SundayServiceService sundayServiceService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private AttendeesService attendeesService;
	
	@Autowired
	private ServiceAttendanceViewService serviceAttendanceViewService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private FirstTimerService firstTimerService;
	
	@Autowired
	private MinistryService ministryService;
	
	@Autowired
	private SundayServiceOperation sundayServiceOperation;
	
	@Autowired
	private SundayServiceAttendeesService sundayServiceAttendeesService;
	
	@Autowired ServiceAttendeesOperation serviceAttendeesOperation;
	
	List<String> attendeesFinalList = new ArrayList<String>();
	
	@RequestMapping(value = "/sunday_service", method = RequestMethod.GET)
	public ModelAndView sundayServiceGet(@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sundayServiceList", prepareListOfSundayService(sundayServiceService.listSundayService()));
		ErrorHandler.setupModelMapResponseMessage(modelMap, request);
		return new ModelAndView("sunday_service", model);
	}
	
	
	@RequestMapping(value = "/add_sunday_service", method = RequestMethod.GET)
	public ModelAndView addSundayServiceGet(@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,BindingResult result){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("servicesList", prepareListOfService(serviceService.listService()));
		return new ModelAndView("add_sunday_service", model);
	}
	
	@RequestMapping(value = "/insert_sunday_service", method = RequestMethod.POST)
	public ModelAndView insertSundayService(HttpServletResponse response, @ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = sundayServiceOperation.insertSundayService(sundayServiceBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
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
	public ModelAndView saveSundayService(HttpServletResponse response, @ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = sundayServiceOperation.updateSundayService(sundayServiceBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		
		return new ModelAndView("redirect:/sunday_service.html");
	}
	
	@RequestMapping(value = "/delete_sunday_service", method = RequestMethod.GET)
	public ModelAndView deleteSundayService(HttpServletResponse response, @ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = sundayServiceOperation.deleteSundayService(sundayServiceBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/sunday_service.html");
	}
	
	
	@RequestMapping(value = "/sunday_service_attendees", method = RequestMethod.GET)
	public ModelAndView sundayServiceAttendeesGet(HttpServletRequest request, @ModelAttribute("attendees") AttendeesBean attendeesBean,
			@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
		model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(sundayServiceBean.getSundayServiceId())));
		model.put("sundayServiceAttendeesList", prepareListOfAttendees(attendeesService.findAttendeesOnSundayService(sundayServiceBean.getSundayServiceId())));
		modelMap.addAttribute("SUNDAY_SERVICE_ID", sundayServiceBean.getSundayServiceId());
		attendeesFinalList = loadAttendees();
		ErrorHandler.setupModelMapResponseMessage(modelMap, request);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("sunday_service_attendees", model);
	}
	
	@RequestMapping(value = "/service_first_timer", method = RequestMethod.GET)
	public ModelAndView firstTimerAttendeesGET(HttpServletRequest request, @ModelAttribute("attendees") AttendeesBean attendeesBean,
			@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
		model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(sundayServiceBean.getSundayServiceId())));
		model.put("firstTimerList", prepareListOfFirstTimer(firstTimerService.findFirstTimerBySundayServiceId(sundayServiceBean.getSundayServiceId())));
		modelMap.addAttribute("SUNDAY_SERVICE_ID", sundayServiceBean.getSundayServiceId());
		attendeesFinalList = loadAttendees();
		ErrorHandler.setupModelMapResponseMessage(modelMap, request);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("service_first_timer", model);
	}
	
	@RequestMapping(value = "/add_first_timer", method = RequestMethod.GET)
	public ModelAndView addfirstTimerAttendeesGET(@ModelAttribute("first_timer") FirstTimerBean firstTimerBean,
			@ModelAttribute("attendees") AttendeesBean attendeesBean,
			@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("groupList", prepareListOfGroup(groupService.listGroup()));
			model.put("ministryList", prepareListOfMinistry(ministryService.listMinistry()));
			model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(sundayServiceBean.getSundayServiceId())));
			modelMap.addAttribute("SUNDAY_SERVICE_ID", sundayServiceBean.getSundayServiceId());
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("add_first_timer", model);
	}
	
	@RequestMapping(value = "/insert_first_timer", method = RequestMethod.POST)
	public ModelAndView insertfirstTimerAttendeesGET(HttpServletResponse response, @ModelAttribute("first_timer") FirstTimerBean firstTimerBean,
			@ModelAttribute("attendees") AttendeesBean attendeesBean,
			@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		BaseResponse baseResponse = null;
		try{
			model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(firstTimerBean.getSundayServiceBean().getSundayServiceId())));
			modelMap.addAttribute("SUNDAY_SERVICE_ID", firstTimerBean.getSundayServiceBean().getSundayServiceId());
			//-- Insert to attendees table
			baseResponse = prepareAttendeesFirstTimerModel(firstTimerBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/service_first_timer.html?sundayServiceId="+sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
	}
	
	@RequestMapping(value = "/search_first_timer", method = RequestMethod.GET)
	public ModelAndView searchFirstTimerAttendeesGet(HttpServletResponse response, @ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			String[] search = sundayServiceBean.getKeywords().trim().split(" ");
			String firstName, lastName, middleName = null;
			if(search.length >= 3){
				firstName = search[0].trim();
				middleName = search[1].trim();
				lastName = search[2].trim();
			}else if(search.length == 2){
				firstName = search[0].trim();
				lastName = search[1].trim();
				middleName = "#";
			}else{
				middleName = lastName = firstName = search[0].trim();
			}
			
			model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(sundayServiceBean.getSundayServiceId())));
			System.out.println(sundayServiceBean.getKeywords());
			model.put("firstTimerList", prepareListOfFirstTimer(firstTimerService.findFirstTimerByName(firstName, lastName, middleName, search.length, sundayServiceBean.getSundayServiceId())));
			modelMap.addAttribute("SUNDAY_SERVICE_ID", sundayServiceBean.getSundayServiceId());
			
			List<FirstTimer> listFirstTimers = firstTimerService.findFirstTimerByName(firstName, lastName, middleName, search.length, sundayServiceBean.getSundayServiceId());
			modelMap.addAttribute(Constant.RESPONSE, Constant.SEARCH_RESULT+Constant.QUOTE+sundayServiceBean.getKeywords()+
					Constant.QUOTE+" - "+listFirstTimers.size()+Constant.SPACE+Constant.TOTAL_OF_RECORDS);
			response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("service_first_timer", model);
	}
	
	
	@RequestMapping(value = "/search_service_attendees", method = RequestMethod.GET)
	public ModelAndView searchsundayServiceGet(HttpServletResponse response, @ModelAttribute("attendees") AttendeesBean attendeesBean,
			@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			String[] search = attendeesBean.getKeywords().trim().split(" ");
			String firstName, lastName, middleName = null;
			if(search.length >= 3){
				firstName = search[0].trim();
				middleName = search[1].trim();
				lastName = search[2].trim();
			}else if(search.length == 2){
				firstName = search[0].trim();
				lastName = search[1].trim();
				middleName = "#";
			}else{
				middleName = lastName = firstName = search[0].trim();
			}
			model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(attendeesBean.getSundayServiceId())));
			model.put("sundayServiceAttendeesList", prepareListOfAttendees(attendeesService.findAttendeesOnSundayServiceByName(firstName, lastName, middleName, search.length, attendeesBean.getSundayServiceId())));
			List<Attendees> listAttendees = attendeesService.findAttendeesOnSundayServiceByName(firstName, lastName, middleName, search.length, attendeesBean.getSundayServiceId());
				modelMap.addAttribute(Constant.RESPONSE, Constant.SEARCH_RESULT+Constant.QUOTE+attendeesBean.getKeywords()+
						Constant.QUOTE+" - "+listAttendees.size()+Constant.SPACE+Constant.TOTAL_OF_RECORDS);
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
			
			modelMap.addAttribute("SUNDAY_SERVICE_ID", attendeesBean.getSundayServiceId());
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("sunday_service_attendees", model);
	}
	
	
	 @RequestMapping(value = "/getAttendeesService", method = RequestMethod.POST)
		public void getAttendeesService(@RequestParam String term, HttpServletResponse response) {
		 try {
			 response.setContentType("application/json");
			 String keyWords = term.substring(term.lastIndexOf(",") + 1).trim();
			 response.getWriter().write(new Gson().toJson(simulateSearchResult(keyWords)));
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 	}
	 
	 @RequestMapping(value = "/getAttendeesServiceProfile", method = RequestMethod.POST)
		public void getAttendeesServiceProfile(@RequestParam String term, HttpServletResponse response) {
		 try {
			 response.setContentType("application/json");
			 String keyWords = term.substring(term.lastIndexOf(",") + 1).trim();
			 response.getWriter().write(new Gson().toJson(simulateSearchResult(keyWords)));
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 	}
	
	 @RequestMapping(value = "/getAttendeesFirstTimer", method = RequestMethod.POST)
		public void getAttendeesFirstTimer(@RequestParam String term, HttpServletResponse response) {
		 try {
			 response.setContentType("application/json");
			 String keyWords = term.substring(term.lastIndexOf(",") + 1).trim();
			 response.getWriter().write(new Gson().toJson(simulateSearchResult(keyWords)));
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 	}
	
	 private List<Tag> simulateSearchResult(String empName) {
			List<Tag> result = new ArrayList<Tag>();
			// iterate a list and filter by tagName
			 for (final String emp : attendeesFinalList) {
				 if (emp.toLowerCase().contains(empName.toLowerCase())) {
					 result.add(new Tag(1, emp));
					 System.out.println(emp);
				 }	
			 }
			return result;
		 }
	
	private List<String> loadAttendees(){
		List<String> attList = new ArrayList<String>();
	 	List<Attendees>  listAttendees = attendeesService.listAttendees();
	 	for(Attendees attendees: listAttendees){
	 		String FullName = "";
	 		if(StringUtils.isNotEmpty(attendees.getMiddleName()) || !Constant.EMPTY_STRING.equals(attendees.getMiddleName())){
	 			FullName = attendees.getFirstName()+" "+attendees.getMiddleName()+" "+attendees.getLastName();	
	 		}else{
	 			FullName = attendees.getFirstName()+" "+attendees.getLastName();
	 		}
	 		
	 		attList.add(FullName);
	 	}
	 	return attList;
	}
	
	
	
	@RequestMapping(value = "/sunday_service_profile", method = RequestMethod.GET)
	public ModelAndView sundayServiceProfileGet(HttpServletRequest request, @ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
		model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(sundayServiceBean.getSundayServiceId())));
		model.put("sundayServiceAttendeesList", prepareListOfSundayServiceAttendees(sundayServiceAttendeesService.findSundayServiceAttendeesById(sundayServiceBean.getSundayServiceId())));
		modelMap.addAttribute("SUNDAY_SERVICE_ID", sundayServiceBean.getSundayServiceId());
		List<ServiceAttendanceViewBean> serviceAttendanceViewBeans =  prepareListOfServiceAttendanceView(serviceAttendanceViewService.listServiceAttendanceView(sundayServiceBean.getSundayServiceId()));
		if(serviceAttendanceViewBeans!=null){
			ServiceAttendanceViewBean serviceAttendanceViewBean = serviceAttendanceViewBeans.get(0);
					if(serviceAttendanceViewBean != null){
						modelMap.addAttribute("TOTAL_OF_ALL_ATTENDEES", serviceAttendanceViewBean.getTotal());
						modelMap.addAttribute("TOTAL_OF_KKB", serviceAttendanceViewBean.getTotalOfKkb());
						modelMap.addAttribute("TOTAL_OF_YAM", serviceAttendanceViewBean.getTotalOfYam());
						modelMap.addAttribute("TOTAL_OF_MEN", serviceAttendanceViewBean.getTotalOfMen());
						modelMap.addAttribute("TOTAL_OF_WOMEN", serviceAttendanceViewBean.getTotalOfWomen());
						modelMap.addAttribute("TOTAL_OF_CHILDREN", serviceAttendanceViewBean.getTotalOfChildren());
					}
		}else{
			String ZERO = "0";
			modelMap.addAttribute("TOTAL_OF_ALL_ATTENDEES", ZERO);
			modelMap.addAttribute("TOTAL_OF_KKB", ZERO);
			modelMap.addAttribute("TOTAL_OF_YAM", ZERO);
			modelMap.addAttribute("TOTAL_OF_MEN", ZERO);
			modelMap.addAttribute("TOTAL_OF_WOMEN", ZERO);
			modelMap.addAttribute("TOTAL_OF_CHILDREN", ZERO);
		}
		
		attendeesFinalList = loadAttendees();
		ErrorHandler.setupModelMapResponseMessage(modelMap, request);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("sunday_service_profile", model);
	}
	
	
	@RequestMapping(value = "/search_profile_attendees", method = RequestMethod.GET)
	public ModelAndView searchServiceAttendeesGet(HttpServletResponse response, @ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			
			String[] search = sundayServiceBean.getKeywords().trim().split(" ");
			String firstName, lastName, middleName = null;
			if(search.length >= 3){
				firstName = search[0].trim();
				middleName = search[1].trim();
				lastName = search[2].trim();
			}else if(search.length == 2){
				firstName = search[0].trim();
				lastName = search[1].trim();
				middleName = "#";
			}else{
				middleName = lastName = firstName = search[0].trim();
			}
			
			
			model.put("sundayServiceDetails", prepareSundayServiceBean(sundayServiceService.getSundayService(sundayServiceBean.getSundayServiceId())));
			System.out.println(sundayServiceBean.getKeywords());
			model.put("sundayServiceAttendeesList", prepareListOfSundayServiceAttendees(sundayServiceAttendeesService.findSundayServiceAttendeesByName(firstName, lastName, middleName, search.length, sundayServiceBean.getSundayServiceId())));
			modelMap.addAttribute("SUNDAY_SERVICE_ID", sundayServiceBean.getSundayServiceId());
			List<ServiceAttendanceViewBean> serviceAttendanceViewBeans =  prepareListOfServiceAttendanceView(serviceAttendanceViewService.listServiceAttendanceView(sundayServiceBean.getSundayServiceId()));
			if(serviceAttendanceViewBeans!=null){
				ServiceAttendanceViewBean serviceAttendanceViewBean = serviceAttendanceViewBeans.get(0);
						if(serviceAttendanceViewBean != null){
							modelMap.addAttribute("TOTAL_OF_ALL_ATTENDEES", serviceAttendanceViewBean.getTotal());
							modelMap.addAttribute("TOTAL_OF_KKB", serviceAttendanceViewBean.getTotalOfKkb());
							modelMap.addAttribute("TOTAL_OF_YAM", serviceAttendanceViewBean.getTotalOfYam());
							modelMap.addAttribute("TOTAL_OF_MEN", serviceAttendanceViewBean.getTotalOfMen());
							modelMap.addAttribute("TOTAL_OF_WOMEN", serviceAttendanceViewBean.getTotalOfWomen());
							modelMap.addAttribute("TOTAL_OF_CHILDREN", serviceAttendanceViewBean.getTotalOfChildren());
						}
			}else{
				String ZERO = "0";
				modelMap.addAttribute("TOTAL_OF_ALL_ATTENDEES", ZERO);
				modelMap.addAttribute("TOTAL_OF_KKB", ZERO);
				modelMap.addAttribute("TOTAL_OF_YAM", ZERO);
				modelMap.addAttribute("TOTAL_OF_MEN", ZERO);
				modelMap.addAttribute("TOTAL_OF_WOMEN", ZERO);
				modelMap.addAttribute("TOTAL_OF_CHILDREN", ZERO);
			}
			
			List<SundayServiceAttendees> listSundayServiceAttendees = sundayServiceAttendeesService.findSundayServiceAttendeesByName(firstName, lastName, middleName, search.length, sundayServiceBean.getSundayServiceId());
			modelMap.addAttribute(Constant.RESPONSE, Constant.SEARCH_RESULT+Constant.QUOTE+sundayServiceBean.getKeywords()+
					Constant.QUOTE+" - "+listSundayServiceAttendees.size()+Constant.SPACE+Constant.TOTAL_OF_RECORDS);
			response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
			
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("sunday_service_profile", model);
	}
	
	
	@RequestMapping(value = "/insert_service_attendees", method = RequestMethod.POST)
	public ModelAndView insertSundayServiceAttendees(HttpServletResponse response, @ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,BindingResult result, ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = serviceAttendeesOperation.insertSundayServiceAttendees(sundayServiceAttendeesBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/sunday_service_attendees.html?sundayServiceId="+sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
	}
	
	@RequestMapping(value = "/remove_service_attendees", method = RequestMethod.POST)
	public ModelAndView removeSundayServiceAttendees(HttpServletResponse response, @ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,BindingResult result,
			ModelMap modelMap){
		BaseResponse baseResponse = null;
		try{
			baseResponse = serviceAttendeesOperation.deleteSundayServiceAttendees(sundayServiceAttendeesBean);
			if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
				modelMap.addAttribute(Constant.RESPONSE, ResponseCode.SUCCESSFUL.getCode());
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("redirect:/sunday_service_profile.html?sundayServiceId="+sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
	}
	
	@RequestMapping(value = "/generate_service_report", method = RequestMethod.POST)
	public ModelAndView generateServiceReport(@ModelAttribute("attendees") AttendeesBean attendeesBean,
			@ModelAttribute("sunday_services") SundayServiceBean sundayServiceBean,
			@ModelAttribute("sunday_services_attendees") SundayServiceAttendeesBean sundayServiceAttendeesBean,
			BindingResult result, HttpServletRequest request, ModelMap modelMap, HttpServletResponse response){
		Map<String, Object> model = new HashMap<String, Object>();
		PdfWriter writer =null;
		PdfReader reader = null;
		try{
			 
			  ServletContext context = request.getServletContext();
			  String outputFile = context.getRealPath("/resources/files/JILAttendanceManagement.pdf");
			  Document document = new Document();
			  /*Document document = new Document(PageSize.A5, leftmargin, right margin, top margin, bottom);*/
			  document = new Document(PageSize.A4.rotate(), 20f, 20f, 10f, 35f);
			  ByteArrayOutputStream baos = new ByteArrayOutputStream();
			  baos.writeTo(new FileOutputStream(outputFile));
			  writer = PdfWriter.getInstance(document,baos);
		      writer.setPageEvent(new HeaderFooter());
		      document.open();
		      
		      String realpath = context.getRealPath("/resources/files/jil_report_baseline_landscape.pdf");
		      
		      // copying the template
		      PdfContentByte cb = writer.getDirectContent();
				
		      //Get file from resources folder
		      reader = new PdfReader(realpath);
		      PdfImportedPage page = writer.getImportedPage(reader, 1);
		      document.newPage();
		      cb.addTemplate(page, 0, 0);
		      
		      
		      // writing on the document
		      List<ServiceAttendanceView> listServiceAttendanceViews = serviceAttendanceViewService.listServiceAttendanceView(sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
		     if(listServiceAttendanceViews!=null){
		    	 SundayService sundayService = sundayServiceService.getSundayService(sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
			     ServiceAttendanceView serviceAttendanceView = listServiceAttendanceViews.get(0);
		    	 document.add(new Paragraph(com.am.utils.PDFUtil.AccountStatementSundayServiceProfileReport, com.am.utils.PDFUtil.AcctFont));
		    	 int recordSize = listServiceAttendanceViews.size();
		    	 List<FirstTimer> listFirstTimers = firstTimerService.findFirstTimerBySundayServiceId(sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
		    	 int firstTimer = (listFirstTimers!=null) ? listFirstTimers.size(): 0;
		    	 
		    	 PDFUtil.addContentServiceProfileReport(document, serviceAttendanceView, sundayService, recordSize, firstTimer);
			 }
		     
		     
		     List<Group> listGroups = groupService.listGroup();
			 if(listGroups!= null){
			 		for(Group group: listGroups){
			    		  List<SundayServiceAttendees> listSundayServiceAttendees = sundayServiceAttendeesService.findSundayServiceAttendeesByServiceIdGroupId(sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId(), group.getId());
			    		  PDFUtil.addTableServiceProfileReport(document, listSundayServiceAttendees, group.getGroupName());
			 		}
			 }
			 
			 List<FirstTimer> listFirstTimers = firstTimerService.findFirstTimerBySundayServiceId(sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
			 if(listFirstTimers!=null){
				 PDFUtil.addTableServiceProfileFirstTimerReport(document, listFirstTimers, "First Timers");
			 }
			 
		      document.close();
		      response.setHeader("Expires", "0");
		      response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
		      response.setHeader("Pragma", "public");
		      response.setHeader("Content-disposition",
	                  "attachment; filename="+"Jesus is Lord Service Profile Report- [test].pdf" );
		      response.setContentType("application/pdf");
		      response.setContentLength(baos.size());
		      ServletOutputStream out = response.getOutputStream();
		      baos.writeTo(out);
		      baos.close();
		      
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("sunday_service_attendees", model);
	}
	

}