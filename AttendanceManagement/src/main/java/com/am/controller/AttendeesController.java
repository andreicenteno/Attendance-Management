package com.am.controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
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
import com.am.bean.Tag;
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
import com.am.utils.ReadCVS;
import com.google.gson.Gson;



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
	
	List<String> attendeesFinalList = new ArrayList<String>();
	 
	
	@RequestMapping(value = "/attendees", method = RequestMethod.GET)
	public ModelAndView attendeeGet(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			model.put("attendeesList", prepareListOfAttendees(attendeesService.listAttendees()));
			model.put("groupList", prepareListOfGroup(groupService.listGroup()));
			attendeesFinalList = loadAttendees();
			ErrorHandler.setupModelMapResponseMessage(modelMap, request);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("attendees", model);
	}
	
	 @RequestMapping(value = "/getAttendeesName", method = RequestMethod.POST)
		public void getAttendees(@RequestParam String term, HttpServletResponse response) {
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
	
	@RequestMapping(value = "/search_attendees", method = RequestMethod.GET)
	public ModelAndView searchAttendeesPost(HttpServletResponse response, @ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result
			,ModelMap modelMap){
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
			model.put("attendeesList", prepareListOfAttendees(attendeesService.findAttendeesByName(firstName, lastName, middleName, search.length)));
			List<Attendees> listAttendees =  attendeesService.findAttendeesByName(firstName, lastName, middleName, search.length);
			modelMap.addAttribute(Constant.RESPONSE, Constant.SEARCH_RESULT+Constant.QUOTE+attendeesBean.getKeywords().trim()+
					Constant.QUOTE+" - "+listAttendees.size()+Constant.SPACE+Constant.TOTAL_OF_RECORDS);
			response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
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
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
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
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
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
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS));
			}else{
				ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
			}
		}catch(Exception e){
			LOGGER.error(e);
		}
		return new ModelAndView("redirect:/attendees.html");
	}
	
	@RequestMapping(value = "/import_attendees", method = RequestMethod.GET)
	public ModelAndView importAttendees(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			ErrorHandler.setupModelMapResponseMessage(modelMap, request);
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("import_attendees", model);
	}
	
	@RequestMapping(value = "/validate_import_file", method = RequestMethod.POST)
	public ModelAndView validateImportAttendees(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			File file = null;
			String path_file = UploadFileToServer(file, Constant.EMPTY_STRING, request, response);
			if(StringUtils.isNotEmpty(path_file)){
				String extension = FilenameUtils.getExtension(path_file);
				if(extension.equals("csv")){
					System.out.println(path_file);
					Boolean isPassed = ReadCVS.ValidateCsvFile(path_file, modelMap);
					if(isPassed){
						String filename = FilenameUtils.getBaseName(path_file);
						modelMap.addAttribute("LOGS_SUCCESS","FILE NAME: "+filename+".csv is SUCCESSFULLY VALIDATED! It is now ready for uploading. Thanks!");
						modelMap.addAttribute(Constant.RESPONSE, "FILE NAME: "+filename+".csv is SUCCESSFULLY VALIDATED! It is now ready for uploading. Thanks!");
						modelMap.addAttribute("PATH_FILE", path_file);
						response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS_IMPORT));
					}else{
						String filename = FilenameUtils.getBaseName(path_file);
						modelMap.addAttribute("LOGS_FAILED","FILE NAME: "+filename+".csv has an ERROR! Need to fixed first the right format before uploading it. Check logs for your references ");
						modelMap.addAttribute(Constant.RESPONSE, "FILE NAME: "+filename+".csv has an ERROR! Need to fixed first the right format before uploading it. Check logs for your references");
						response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_ERROR_FIXED));
					}
				}else{
					modelMap.addAttribute("LOGS_FAILED","PLEASE ATTACHED FILE (.CSV) FORMAT! Please Check your File Format");
					modelMap.addAttribute(Constant.RESPONSE, "PLEASE ATTACHED FILE (.CSV) FORMAT! Please Check your File Format");
					response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_ERROR_FIXED));
				}
				
			}else{
				modelMap.addAttribute("LOGS_FAILED","PLEASE ATTACHED FILE (.CSV) FOR VALIDATING THE RECORDS");
				modelMap.addAttribute(Constant.RESPONSE, "PLEASE ATTACHED FILE (.CSV) FOR VALIDATING THE RECORDS");
				response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_ERROR_FIXED));
			}
					
			
		}catch(Exception e){
			System.out.println(e);
		}
		return new ModelAndView("import_attendees", model);
	}
	
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public String UploadFileToServer(final File attendeesFile, String path_file,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		File file = new File(System.getProperty("java.io.tmpdir"));
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		List fileItems = upload.parseRequest(request);
		// Process the uploaded file items
		Iterator i = fileItems.iterator();
		String folder = file.getAbsolutePath();
		File uploaded = null;
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			if (!fi.isFormField()) {
				// Get the uploaded file parameters
				String fileName = FilenameUtils.getName(fi.getName());
				if (StringUtils.isNotEmpty(fileName)) {
					LOGGER.debug("Uploaded Filename: " + folder
							+ FILE_SEPARATOR + fileName);
					uploaded = new File(folder + FILE_SEPARATOR + fileName);
					fi.write(uploaded);
				}
			}
		}
		String path = "";
		
		try {
			path = uploaded.getPath();
		} catch (Exception e) {
			path = "";
			LOGGER.error(e);
		}

		return path;
	}
	
	@RequestMapping(value = "/import_attendees_record", method = RequestMethod.POST)
	public ModelAndView ImportAttendeesRecord(@ModelAttribute("attendees") AttendeesBean attendeesBean,BindingResult result,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			
				if(StringUtils.isNotEmpty(attendeesBean.getKeywords().trim())){
					String extension = FilenameUtils.getExtension(attendeesBean.getKeywords().trim());
					if(extension.equals("csv")){
						BaseResponse baseResponse = attendeesOperation.insertCsvFileRecord(attendeesBean.getKeywords().trim(), modelMap);
						if(baseResponse.getResponseCode() == ResponseCode.SUCCESSFUL.getCode()){
							String filename = FilenameUtils.getBaseName(attendeesBean.getKeywords().trim());
							modelMap.addAttribute("LOGS_SUCCESS","FILE NAME: "+filename+".csv is SUCCESSFULLY ADDED IN OUR DATABASE! You can now check attendees information on Attendees tab.");
							modelMap.addAttribute(Constant.RESPONSE, "FILE NAME: "+filename+".csv is SUCCESSFULLY ADDED IN OUR DATABASE! You can now check attendees information on Attendees tab.");
							modelMap.addAttribute("HIDE_IMPORT","YES");
							response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_SUCCESS_FIXED));
						}else{
							ErrorHandler.HandleErrorMessageRedirect(response, baseResponse, modelMap);
						}
					}else{
						modelMap.addAttribute("LOGS_FAILED","PLEASE ATTACHED FILE (.CSV) FORMAT! Please Check your File Format");
						modelMap.addAttribute(Constant.RESPONSE, "PLEASE ATTACHED FILE (.CSV) FORMAT! Please Check your File Format");
						response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_ERROR_FIXED));
					}
				}else{
					modelMap.addAttribute("LOGS_FAILED","PLEASE ATTACHED FILE (.CSV) FOR VALIDATING THE RECORDS");
					modelMap.addAttribute(Constant.RESPONSE, "PLEASE ATTACHED FILE (.CSV) FOR VALIDATING THE RECORDS");
					response.addCookie(new Cookie(Constant.NOTIFICATION, Constant.DIV_ERROR_FIXED));
				}
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		return new ModelAndView("redirect:/import_attendees.html");
	}
	
	
}
