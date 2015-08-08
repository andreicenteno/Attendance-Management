package com.am.controller;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.am.model.AttendeesSummaryView;
import com.am.model.AttendeesView;
import com.am.model.Group;
import com.am.model.Ministry;
import com.am.service.AttendeesSummaryViewService;
import com.am.service.AttendeesViewService;
import com.am.service.GroupService;
import com.am.service.MinistryService;
import com.am.utils.HeaderFooter;
import com.am.utils.PDFUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;


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
	
	private void mapAttendeesSummary(ModelMap modelMap, AttendeesSummaryViewBean attendeesSummaryViewBean, AttendeesReportBean attendeesReportBean, int totalOfRecord){
		
		modelMap.addAttribute("GENDER", attendeesReportBean.getGender());
		modelMap.addAttribute("GROUP_ID", attendeesReportBean.getGroupId());
		modelMap.addAttribute("MINISTRY_ID", attendeesReportBean.getMinistryId());
		
		modelMap.addAttribute("TOTAL_OF_ALL", totalOfRecord);
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
					List<AttendeesView> listAttendeesSummaryViews =  attendeesViewService.listAttendeesSummaryView();
					mapAttendeesSummary(modelMap, attendeesSummaryViewBean, attendeesReportBean, listAttendeesSummaryViews.size());
				}
				else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() == 0 && attendeesReportBean.getGender().equals("all")){ // group
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGroup(attendeesReportBean.getGroupId())));
					List<AttendeesView> listAttendeesSummaryViews =  attendeesViewService.listAttendeesViewByGroup(attendeesReportBean.getGroupId());
					mapAttendeesSummary(modelMap, attendeesSummaryViewBean, attendeesReportBean, listAttendeesSummaryViews.size());
				}
				else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() != 0 && attendeesReportBean.getGender().equals("all")){ // ministry
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByMinistry(attendeesReportBean.getMinistryId())));
					List<AttendeesView> listAttendeesSummaryViews =  attendeesViewService.listAttendeesViewByMinistry(attendeesReportBean.getMinistryId());
					mapAttendeesSummary(modelMap, attendeesSummaryViewBean, attendeesReportBean, listAttendeesSummaryViews.size());
				}
				else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() == 0 && !attendeesReportBean.getGender().equals("all")){ // gender
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGender(Boolean.parseBoolean(attendeesReportBean.getGender()))));
					List<AttendeesView> listAttendeesSummaryViews =  attendeesViewService.listAttendeesViewByGender(Boolean.parseBoolean(attendeesReportBean.getGender()));
					mapAttendeesSummary(modelMap, attendeesSummaryViewBean, attendeesReportBean, listAttendeesSummaryViews.size());
				}
				else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() != 0 && attendeesReportBean.getGender().equals("all")){ // group ministry
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewwByGroupMinistry(attendeesReportBean.getGroupId(), attendeesReportBean.getMinistryId())));
					List<AttendeesView> listAttendeesSummaryViews =  attendeesViewService.listAttendeesViewwByGroupMinistry(attendeesReportBean.getGroupId(), attendeesReportBean.getMinistryId());
					mapAttendeesSummary(modelMap, attendeesSummaryViewBean, attendeesReportBean, listAttendeesSummaryViews.size());
				}
				else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() == 0 && !attendeesReportBean.getGender().equals("all")){ // group gender
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGenderGroup(Boolean.parseBoolean(attendeesReportBean.getGender()), attendeesReportBean.getGroupId())));
					List<AttendeesView> listAttendeesSummaryViews =  attendeesViewService.listAttendeesViewByGenderGroup(Boolean.parseBoolean(attendeesReportBean.getGender()), attendeesReportBean.getGroupId());
					mapAttendeesSummary(modelMap, attendeesSummaryViewBean, attendeesReportBean, listAttendeesSummaryViews.size());
				}
				else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() != 0 && !attendeesReportBean.getGender().equals("all")){ // ministry gender
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGenderMinistry(Boolean.parseBoolean(attendeesReportBean.getGender()), attendeesReportBean.getMinistryId())));
					List<AttendeesView> listAttendeesSummaryViews =  attendeesViewService.listAttendeesViewByGenderMinistry(Boolean.parseBoolean(attendeesReportBean.getGender()), attendeesReportBean.getMinistryId());
					mapAttendeesSummary(modelMap, attendeesSummaryViewBean, attendeesReportBean, listAttendeesSummaryViews.size());
				}
				else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() != 0 && !attendeesReportBean.getGender().equals("all")){ // group ministry gender
					model.put("attendeesList", prepareListOfAttendeesView(attendeesViewService.listAttendeesViewByGroupMinistryGender(attendeesReportBean.getGroupId(), attendeesReportBean.getMinistryId(), Boolean.parseBoolean(attendeesReportBean.getGender()))));
					List<AttendeesView> listAttendeesSummaryViews =  attendeesViewService.listAttendeesViewByGroupMinistryGender(attendeesReportBean.getGroupId(), attendeesReportBean.getMinistryId(), Boolean.parseBoolean(attendeesReportBean.getGender()));
					mapAttendeesSummary(modelMap, attendeesSummaryViewBean, attendeesReportBean, listAttendeesSummaryViews.size());
				}
			}
			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return new ModelAndView("view_attendees_report", model);
	}
	
	
	@RequestMapping(value = "/generate_attendees", method = RequestMethod.POST)
	public ModelAndView generateAttendees(@ModelAttribute("attendees_report") AttendeesReportBean attendeesReportBean,BindingResult result,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
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
	      List<AttendeesSummaryView> listAttendeesSummaryViews = attendeesSummaryViewService.listAttendeesSummaryView();
	      if(listAttendeesSummaryViews != null){
	    	  AttendeesSummaryView attendeesSummaryView = listAttendeesSummaryViews.get(0);
	    	  document.add(new Paragraph(com.am.utils.PDFUtil.AccountStatementAttendeesReport, com.am.utils.PDFUtil.AcctFont));
	    	  String ReportQuery = null, views= null;
	    	  List<AttendeesView> listAttendeesViews = null;
	    	  int recordSize = 0;
	    	  System.out.println(attendeesReportBean.getGender()+" - "+attendeesReportBean.getGroupId()+" - "+attendeesReportBean.getMinistryId());
	    	  if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() == 0 && attendeesReportBean.getGender().equals("all")){ // all
					ReportQuery = "All Attendees";
					views = "all";
			     }else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() == 0 && !attendeesReportBean.getGender().equals("all")){ // gender
			    	  if(attendeesReportBean.getGender().equals("true")){
			    		  ReportQuery = "All Male Attendees";
			    		  views = "male";
			    	  }else{
			    		  ReportQuery = "All Female Attendees";
			    		  views = "female";
			    	  }
			     }else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() == 0 && attendeesReportBean.getGender().equals("all")){ // group
			    	  Group group = groupService.getGroup(attendeesReportBean.getGroupId());
			    	  ReportQuery = "All "+group.getGroupName()+" Attendees";
			    	  views = "others";
			     }
			     else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() != 0 && attendeesReportBean.getGender().equals("all")){ // ministry
			    	  Ministry ministry = ministryService.getMinistry(attendeesReportBean.getMinistryId());
			    	  ReportQuery = "All "+ministry.getMinistryName()+" Attendees";
			    	  views = "others";
			     }else{
			    	 views = "others";
			    	 Group group = new Group();
			    	 if(attendeesReportBean.getGroupId()!= 0){
			    		 group = groupService.getGroup(attendeesReportBean.getGroupId());
			    	 }else{
			    		 group.setGroupName("All");
			    	 }
			    	 Ministry ministry = new Ministry();
			    	 if(attendeesReportBean.getMinistryId()!=0)
			    		 ministry = ministryService.getMinistry(attendeesReportBean.getMinistryId());	 
			    	 else
			    		 ministry.setMinistryName("All");
			    	 
			    	 String gender = null;
			    	 if(attendeesReportBean.getGender().equals("true")){
			    		 gender = "Male";
			    	  }else if(attendeesReportBean.getGender().equals("false")){
			    		  gender = "Female";
			    	  }else{
			    		  gender = "All";
			    	  }
			    	 ReportQuery = "Group: "+group.getGroupName()+" - Ministry: "+ministry.getMinistryName()+" - Gender: "+gender+" Attendees";
			    	 listAttendeesViews = prepareListAttendeesView(attendeesReportBean);
			    	 recordSize = listAttendeesViews.size();
			     }
				
		      PDFUtil.addContentAttendeesReport(document, attendeesSummaryView, ReportQuery, views, recordSize);
		  }
	      
	      if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() == 0 && attendeesReportBean.getGender().equals("all")){ // all
	    	  List<Group> listGroups = groupService.listGroup();
		      if(listGroups!= null){
		    	  for(Group group: listGroups){
		    		  List<AttendeesView> listAttendeesViews = attendeesViewService.listAttendeesViewByGroup(group.getId());
		    	      PDFUtil.addTableAttendeesReport(document, listAttendeesViews, group.getGroupName());
		    	  }
		      }
	      }else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() == 0 && !attendeesReportBean.getGender().equals("all")){ // gender
	    	  if(attendeesReportBean.getGender().equals("true")){
	    		  List<Group> listGroups = groupService.listGroup();
			      if(listGroups!= null){
			    	  for(Group group: listGroups){
			    		  if(group.getId()!=5){
			    			  List<AttendeesView> listAttendeesViews =  attendeesViewService.listAttendeesViewByGenderGroup(Boolean.parseBoolean(attendeesReportBean.getGender()), group.getId());
			    			  PDFUtil.addTableAttendeesReport(document, listAttendeesViews, group.getGroupName());
			    		  }
			    	  }
			      }
	    	  }else{
	    		  List<Group> listGroups = groupService.listGroup();
			      if(listGroups!= null){
			    	  for(Group group: listGroups){
			    		  if(group.getId()!=4){
			    			  List<AttendeesView> listAttendeesViews =  attendeesViewService.listAttendeesViewByGenderGroup(Boolean.parseBoolean(attendeesReportBean.getGender()), group.getId());
			    			  PDFUtil.addTableAttendeesReport(document, listAttendeesViews, group.getGroupName());
			    		  }
			    	  }
			      }
	    	  }
	      }else{
	    	  List<AttendeesView> listAttendeesViews = null;
	    	 listAttendeesViews = prepareListAttendeesView(attendeesReportBean);
	    	 PDFUtil.addTableAttendeesReportNotBreakdown(document, listAttendeesViews);
	      }
	      
	      
	      document.close();
	      response.setHeader("Expires", "0");
	      response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
	      response.setHeader("Pragma", "public");
	      response.setHeader("Content-disposition",
                  "attachment; filename="+"Jesus is Lord AttendanceManagement- [test].pdf" );
	      response.setContentType("application/pdf");
	      response.setContentLength(baos.size());
	      ServletOutputStream out = response.getOutputStream();
	      baos.writeTo(out);
	      baos.close();
		}catch(Exception e){
			System.out.println(e);
		}
		
		return new ModelAndView("reports", model);
	
	}
	
	
	private List<AttendeesView> prepareListAttendeesView(AttendeesReportBean attendeesReportBean){
		 List<AttendeesView> listAttendeesViews = null;
		  if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() == 0 && attendeesReportBean.getGender().equals("all")){ // group
	   		  listAttendeesViews =  attendeesViewService.listAttendeesViewByGroup(attendeesReportBean.getGroupId());
	   	  }else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() != 0 && attendeesReportBean.getGender().equals("all")){ // ministry
	   		  listAttendeesViews =  attendeesViewService.listAttendeesViewByMinistry(attendeesReportBean.getMinistryId());
	   	  }else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() != 0 && attendeesReportBean.getGender().equals("all")){ // group ministry
	   		  listAttendeesViews =  attendeesViewService.listAttendeesViewwByGroupMinistry(attendeesReportBean.getGroupId(), attendeesReportBean.getMinistryId());
	   	  }else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() == 0 && !attendeesReportBean.getGender().equals("all")){ // group gender
	   		  listAttendeesViews =  attendeesViewService.listAttendeesViewByGenderGroup(Boolean.parseBoolean(attendeesReportBean.getGender()), attendeesReportBean.getGroupId());
	   	  }else if(attendeesReportBean.getGroupId() == 0 && attendeesReportBean.getMinistryId() != 0 && !attendeesReportBean.getGender().equals("all")){ // ministry gender
	   		  listAttendeesViews =  attendeesViewService.listAttendeesViewByGenderMinistry(Boolean.parseBoolean(attendeesReportBean.getGender()), attendeesReportBean.getMinistryId());
	   	  }else if(attendeesReportBean.getGroupId() != 0 && attendeesReportBean.getMinistryId() != 0 && !attendeesReportBean.getGender().equals("all")){ // group ministry gender
	   		  listAttendeesViews =  attendeesViewService.listAttendeesViewByGroupMinistryGender(attendeesReportBean.getGroupId(), attendeesReportBean.getMinistryId(), Boolean.parseBoolean(attendeesReportBean.getGender()));
	   	  }
		 return listAttendeesViews;
	}

	

}