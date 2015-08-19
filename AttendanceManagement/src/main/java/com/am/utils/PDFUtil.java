/*******************************************************************************
 * Copyright (c) 2013 P3ople4u Inc.  All Rights Reserved.
 *        DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *        
 *        This code is NOT free software; you cannot redistribute, modify, 
 *        decompile, copy or publish without the prior written consent of 
 *        P3ople4u Inc.
 *        
 *        
 *        This software is the confidential and proprietary information
 *        of P3ople4u Inc. ("Confidential Information").  You shall 
 *        not disclose such Confidential Information and shall use it 
 *        only in accordance with the terms of the license agreement
 *        you entered into with P3ople4u.
 *        
 *        Please contact P3ople4u Inc. 17th Floor PhilamLife Tower, 
 *        8767 Paseo de Roxas Avenue,Makati City, Philippines 
 *        if you need additional information or have any questions.
 ******************************************************************************/
package com.am.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.am.common.Constant;
import com.am.model.AttendeesSummaryView;
import com.am.model.AttendeesView;
import com.am.model.FirstTimer;
import com.am.model.ServiceAttendanceView;
import com.am.model.SundayService;
import com.am.model.SundayServiceAttendees;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * @author emersonsalvador
 * 
 */
public class PDFUtil {
	
	private static Logger LOGGER = Logger.getLogger(PDFUtil.class);

	public static Font AcctFont = new Font(Font.FontFamily.HELVETICA, 11,
			Font.BOLD);
	private static Font SmallBFont = new Font(Font.FontFamily.HELVETICA, 10,
			Font.BOLD);

	private static Font GeneralBFont = new Font(Font.FontFamily.HELVETICA, 9,
			Font.BOLD);

	private static Font GeneralFont = new Font(Font.FontFamily.HELVETICA, 9);

	private static final String DATE_FORMAT = "dd MMM YYYY HH:mm (z)";
	private static final String NO_TIME_DATE_FORMAT = "dd MMM YYYY ";
	private static final String AMOUNT_FORMAT = "#";

	public static final String SPACE = " ";
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String PDF_FILE_EXT = ".pdf";

	
	
	public static final String DateTitle = "Date: ";
	public static final String ReportTitle = "Report: ";
	public static final String SummaryTitle = "Summary: ";
	public static final String ServiceTitle = "Title: ";
	
	// ----------------- Attendees Report ------------- 
	public static final String TotalofKKB = "Total of KKB: ";
	public static final String TotalofKKBMale = "Total of KKB Male: ";
	public static final String TotalofKKBFemale = "Total of KKB Female: ";
	public static final String TotalofYAM = "Total of YAM: ";
	public static final String TotalofYAMMale = "Total of YAM Male: ";
	public static final String TotalofYAMFemale = "Total of YAM Female: ";
	public static final String TotalofChildren = "Total of Children: ";
	public static final String TotalofChildrenMale = "Total of Children Male: ";
	public static final String TotalofChildrenFemale = "Total of Children Female: ";
	public static final String TotalofMen = "Total of Men: ";
	public static final String TotalofWomen = "Total of Women: ";
	public static final String TotalofFirstTimer = "Total of First Timer: ";
	public static final String TotalofAllRecords = "Total of all records: ";
	
	public static final String AccountStatementAttendeesReport = "\n\n\n" + "ATTENDEES REPORT";
	
	public static final String NameHeader = "Name";
	public static final String AddressHeader = "Address";
	public static final String ContactNumberHeader = "Contact Number";
	public static final String StatusHeader = "Status";
	public static final String GenderHeader = "Gender";
	public static final String BirthdayHeader = "Birthday";
	public static final String AgeHeader = "Age";
	public static final String MinistryHeader = "Ministry";
	public static final String GroupHeader = "Group";
	
	public static final String RemarksHeader = "Remarks";
	public static final String InvitedByHeader = "Invited By";
	
	
	// ----------------- Sunday Service Report ------------- 
	
	public static final String AccountStatementSundayServiceProfileReport = "\n\n\n" + "SERVICE PROFILE REPORT";
	
	

	private PDFUtil() {

	}
	
	public static void main(String[] args){
		System.out.println("Hello World");
		String Name = "Andrei Andrei Andrei Andrei";
		String[] search = Name.trim().split(" ");
		if(search.length >= 3){
			System.out.println(search[0].trim());
			System.out.println(search[1].trim());
			System.out.println(search[2].trim());
		}else if(search.length == 2){
			System.out.println(search[0].trim());
			System.out.println(search[1].trim());
		}else{
			System.out.println(search[0].trim());
		}
		
	}

	
	
	// -- ATTENDEES REPORT Header | Content
	public static void addContentAttendeesReport(Document docu,
			AttendeesSummaryView attendeesSummaryView, String ReportQuery, String view, int listSize) {
		try {
			// content
			DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			formatter.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
			Date dateNow = Calendar.getInstance().getTime();
			String strDateNow = formatter.format(dateNow);
			Paragraph content = new Paragraph();
			content.add(new Paragraph(DateTitle + strDateNow, GeneralFont));
			content.add(new Paragraph(ReportTitle + ReportQuery, GeneralFont));
			content.add(new Paragraph(Constant.NEW_LINE + SummaryTitle,
					SmallBFont));

			if(view.equals("all")){ // all
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofKKB
						+ attendeesSummaryView.getTotalOfKkb(), GeneralBFont));
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofKKBMale
						+ attendeesSummaryView.getTotalOfKkbMale(), GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofKKBFemale
						+ attendeesSummaryView.getTotalOfKkbFemale(), GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofMen
						+ attendeesSummaryView.getTotalOfMen(), GeneralBFont));

				content.add(Constant.NEW_LINE);
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofYAM
						+ attendeesSummaryView.getTotalOfYam(), GeneralBFont));
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofYAMMale
						+ attendeesSummaryView.getTotalOfYamMale(), GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofYAMFemale
						+ attendeesSummaryView.getTotalOfYamFemale(), GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofWomen
						+ attendeesSummaryView.getTotalOfWomen(), GeneralBFont));

				content.add(Constant.NEW_LINE);
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofChildren
						+ attendeesSummaryView.getTotalOfChildren(), GeneralBFont));
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofChildrenMale
						+ attendeesSummaryView.getTotalOfChildrenMale(),
						GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofChildrenFemale
						+ attendeesSummaryView.getTotalOfChildrenFemale(),
						GeneralBFont));

				content.add(Constant.NEW_LINE);
				content.add(Constant.NEW_LINE);
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofAllRecords
						+ attendeesSummaryView.getTotal(), SmallBFont));
			}else if(view.equals("male")){ // male
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofKKBMale
						+ attendeesSummaryView.getTotalOfKkbMale(), GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofYAMMale
						+ attendeesSummaryView.getTotalOfYamMale(), GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofChildrenMale
						+ attendeesSummaryView.getTotalOfChildrenMale(),
						GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofMen
						+ attendeesSummaryView.getTotalOfMen(), GeneralBFont));
				
				content.add(Constant.NEW_LINE);
				content.add(Constant.NEW_LINE);
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofAllRecords
						+ attendeesSummaryView.getTotalOfMale(), SmallBFont));
				
			}else if(view.equals("female")){ // female
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofKKBFemale
						+ attendeesSummaryView.getTotalOfKkbFemale(), GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofYAMFemale
						+ attendeesSummaryView.getTotalOfYamFemale(), GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofChildrenFemale
						+ attendeesSummaryView.getTotalOfChildrenFemale(),
						GeneralBFont));
				content.setTabSettings(new TabSettings(90f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofWomen
						+ attendeesSummaryView.getTotalOfWomen(), GeneralBFont));
				
				
				content.add(Constant.NEW_LINE);
				content.add(Constant.NEW_LINE);
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofAllRecords
						+ attendeesSummaryView.getTotalOfFemale(), SmallBFont));
			}else{ //- others
				content.setTabSettings(new TabSettings(60f));
				content.add(Chunk.TABBING);
				content.add(new Chunk(TotalofAllRecords
						+ listSize, SmallBFont));
			}
		

			docu.add(content);
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

	}

	public static void addTableAttendeesReport(Document document,
			List<AttendeesView> attendeesViews, String GroupName)
			throws BadElementException, DocumentException {
		// table
		PdfPTable table = new PdfPTable(8);
		float[] w = new float[] { 20f, 45f, 20f, 7f, 10f, 4f, 10f, 10f };
		table.setWidths(w);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);

		Paragraph contentTitle = new Paragraph();
		contentTitle.add(new Paragraph(GroupName, SmallBFont));
		PdfPCell cell1 = createCell(NameHeader, 3, 3);
		PdfPCell cell2 = createCell(AddressHeader, 3, 3);
		PdfPCell cell3 = createCell(ContactNumberHeader, 3, 3);
		PdfPCell cell4 = createCell(GenderHeader, 3, 3);
		PdfPCell cell5 = createCell(BirthdayHeader, 3, 3);
		PdfPCell cell6 = createCell(AgeHeader, 3, 3);
		PdfPCell cell7 = createCell(MinistryHeader, 3, 3);
		PdfPCell cell8 = createCell(GroupHeader, 3, 3);

		cell1.setGrayFill(0.85f);
		cell2.setGrayFill(0.85f);
		cell3.setGrayFill(0.85f);
		cell4.setGrayFill(0.85f);
		cell5.setGrayFill(0.85f);
		cell6.setGrayFill(0.85f);
		cell7.setGrayFill(0.85f);
		cell8.setGrayFill(0.85f);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);
		table.addCell(cell7);
		table.addCell(cell8);

		DecimalFormat decimalFormat = new DecimalFormat(AMOUNT_FORMAT);
		SimpleDateFormat format = new SimpleDateFormat(NO_TIME_DATE_FORMAT);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		for (AttendeesView attendeesView : attendeesViews) {
			String FULL_NAME = attendeesView.getLastName() + ", "
					+ attendeesView.getFirstName() + " "
					+ attendeesView.getMiddleName();
			/*
			 * PdfPCell cell =
			 * createCell(format.format(accountInfo.getUpdateTime()).toString(),
			 * 3, 3);
			 */
			PdfPCell Cell1 = createCell(FULL_NAME, 3, 3);
			Cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell1);

			PdfPCell Cell2 = createCell(attendeesView.getAddress(), 3, 3);
			Cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell2);

			PdfPCell Cell3 = createCell(attendeesView.getContactNumber(), 3, 3);
			Cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell3);

			
			String GENDER = (attendeesView.getGender()) ? "Male" : "Female";
			PdfPCell Cell4 = createCell(GENDER, 2, 3);
			Cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell4);

			PdfPCell Cell5 = createCell(
					format.format(attendeesView.getBirthday()).toString(), 3, 3);
			Cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell5);

			PdfPCell Cell6 = createCell(String.valueOf(decimalFormat
					.format(attendeesView.getYearOld())), 3, 3);
			Cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell6);

			PdfPCell Cell7 = createCell(attendeesView.getMinistryName(), 3, 3);
			Cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell7);
			
			PdfPCell Cell8 = createCell(attendeesView.getGroupName(), 3, 3);
			Cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell8);

		}

		document.add(contentTitle);
		document.add(table);

	}
	
	public static void addTableAttendeesReportNotBreakdown(Document document,List<AttendeesView> attendeesViews) throws BadElementException, DocumentException {
		// table
		PdfPTable table = new PdfPTable(8);
		float[] w = new float[] { 20f, 45f, 20f, 7f, 10f, 4f, 10f, 10f };
		table.setWidths(w);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);

		Paragraph contentTitle = new Paragraph();
		
		PdfPCell cell1 = createCell(NameHeader, 3, 3);
		PdfPCell cell2 = createCell(AddressHeader, 3, 3);
		PdfPCell cell3 = createCell(ContactNumberHeader, 3, 3);
		PdfPCell cell4 = createCell(GenderHeader, 3, 3);
		PdfPCell cell5 = createCell(BirthdayHeader, 3, 3);
		PdfPCell cell6 = createCell(AgeHeader, 3, 3);
		PdfPCell cell7 = createCell(MinistryHeader, 3, 3);
		PdfPCell cell8 = createCell(GroupHeader, 3, 3);

		cell1.setGrayFill(0.85f);
		cell2.setGrayFill(0.85f);
		cell3.setGrayFill(0.85f);
		cell4.setGrayFill(0.85f);
		cell5.setGrayFill(0.85f);
		cell6.setGrayFill(0.85f);
		cell7.setGrayFill(0.85f);
		cell8.setGrayFill(0.85f);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);
		table.addCell(cell7);
		table.addCell(cell8);

		DecimalFormat decimalFormat = new DecimalFormat(AMOUNT_FORMAT);
		SimpleDateFormat format = new SimpleDateFormat(NO_TIME_DATE_FORMAT);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		for (AttendeesView attendeesView : attendeesViews) {
			String FULL_NAME = attendeesView.getLastName() + ", "
					+ attendeesView.getFirstName() + " "
					+ attendeesView.getMiddleName();
			/*
			 * PdfPCell cell =
			 * createCell(format.format(accountInfo.getUpdateTime()).toString(),
			 * 3, 3);
			 */
			PdfPCell Cell1 = createCell(FULL_NAME, 3, 3);
			Cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell1);

			PdfPCell Cell2 = createCell(attendeesView.getAddress(), 3, 3);
			Cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell2);

			PdfPCell Cell3 = createCell(attendeesView.getContactNumber(), 3, 3);
			Cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell3);

			String GENDER = (attendeesView.getGender()) ? "Male" : "Female";
			PdfPCell Cell4 = createCell(GENDER, 2, 3);
			Cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell4);

			PdfPCell Cell5 = createCell(
					format.format(attendeesView.getBirthday()).toString(), 3, 3);
			Cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell5);

			PdfPCell Cell6 = createCell(String.valueOf(decimalFormat
					.format(attendeesView.getYearOld())), 3, 3);
			Cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell6);

			PdfPCell Cell7 = createCell(attendeesView.getMinistryName(), 3, 3);
			Cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell7);
			
			PdfPCell Cell8 = createCell(attendeesView.getGroupName(), 3, 3);
			Cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell8);

		}

		document.add(contentTitle);
		document.add(table);

	}

	private static PdfPCell createCell(String string, int alignment, int span) {
		PdfPCell cell = new PdfPCell(new Phrase(string, GeneralFont));
		cell.setHorizontalAlignment(alignment);
		cell.setPadding(span);
		return cell;
	}

	
	// -- SUNDAY SERVICE PROFILE REPORT
	
	public static void addContentServiceProfileReport(Document docu, ServiceAttendanceView serviceAttendanceView,
			SundayService sundayService, int listSize, int firstTimer) {
		try {
			// content
			DateFormat formatter = new SimpleDateFormat(NO_TIME_DATE_FORMAT);
			formatter.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
			Date dateNow = Calendar.getInstance().getTime();
			String strDateNow = formatter.format(dateNow);
			Paragraph content = new Paragraph();
			content.add(new Paragraph(DateTitle + strDateNow, GeneralFont));
			content.add(new Paragraph(ServiceTitle + sundayService.getServiceTitle(), GeneralFont));
			content.add(new Paragraph(ReportTitle + sundayService.getServiceEntity().getServiceName()+" - "+formatter.format(sundayService.getCreateTime()), GeneralFont));
			content.add(new Paragraph(Constant.NEW_LINE + SummaryTitle,
					SmallBFont));

			content.setTabSettings(new TabSettings(60f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofKKB+ serviceAttendanceView.getTotalOfKkb(), GeneralBFont));
			content.setTabSettings(new TabSettings(60f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofKKBMale+ serviceAttendanceView.getTotalOfKkbMale(), GeneralBFont));
			content.setTabSettings(new TabSettings(90f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofKKBFemale+serviceAttendanceView.getTotalOfKkbFemale(), GeneralBFont));
			content.setTabSettings(new TabSettings(90f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofMen+ serviceAttendanceView.getTotalOfMen(), GeneralBFont));

			content.add(Constant.NEW_LINE);
			content.setTabSettings(new TabSettings(60f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofYAM+serviceAttendanceView.getTotalOfYam(), GeneralBFont));
			content.setTabSettings(new TabSettings(60f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofYAMMale
						+ serviceAttendanceView.getTotalOfYamMale(), GeneralBFont));
			content.setTabSettings(new TabSettings(90f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofYAMFemale
					+ serviceAttendanceView.getTotalOfYamFemale(), GeneralBFont));
			content.setTabSettings(new TabSettings(90f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofWomen
					+ serviceAttendanceView.getTotalOfWomen(), GeneralBFont));
				content.add(Constant.NEW_LINE);
			content.setTabSettings(new TabSettings(60f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofChildren
					+ serviceAttendanceView.getTotalOfChildren(), GeneralBFont));
			content.setTabSettings(new TabSettings(60f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofChildrenMale
					+ serviceAttendanceView.getTotalOfChildrenMale(),
					GeneralBFont));
			content.setTabSettings(new TabSettings(90f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofChildrenFemale
					+ serviceAttendanceView.getTotalOfChildrenFemale(),
					GeneralBFont));
			content.setTabSettings(new TabSettings(90f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofFirstTimer+firstTimer,
					GeneralBFont));
			
			
			content.add(Constant.NEW_LINE);
			content.add(Constant.NEW_LINE);
			content.setTabSettings(new TabSettings(60f));
			content.add(Chunk.TABBING);
			content.add(new Chunk(TotalofAllRecords
				+ serviceAttendanceView.getTotal(), SmallBFont));
			

			docu.add(content);
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

	}
	
	
	public static void addTableServiceProfileReport(Document document, List<SundayServiceAttendees> sundayServiceAttendees, String GroupName)
			throws BadElementException, DocumentException {
		// table
		PdfPTable table = new PdfPTable(7);
		float[] w = new float[] { 20f, 45f, 20f, 7f, 10f, 10f, 10f };
		table.setWidths(w);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);

		Paragraph contentTitle = new Paragraph();
		contentTitle.add(new Paragraph(GroupName, SmallBFont));

		PdfPCell cell1 = createCell(NameHeader, 3, 3);
		PdfPCell cell2 = createCell(AddressHeader, 3, 3);
		PdfPCell cell3 = createCell(ContactNumberHeader, 3, 3);
		
		PdfPCell cell4 = createCell(GenderHeader, 3, 3);
		PdfPCell cell5 = createCell(BirthdayHeader, 3, 3);
		/*PdfPCell cell6 = createCell(AgeHeader, 3, 3);*/
		PdfPCell cell7 = createCell(MinistryHeader, 3, 3);
		PdfPCell cell8 = createCell(GroupHeader, 3, 3);

		cell1.setGrayFill(0.85f);
		cell2.setGrayFill(0.85f);
		cell3.setGrayFill(0.85f);
		cell4.setGrayFill(0.85f);
		cell5.setGrayFill(0.85f);
		/*cell6.setGrayFill(0.85f);*/
		cell7.setGrayFill(0.85f);
		cell8.setGrayFill(0.85f);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		/*table.addCell(cell6);*/
		table.addCell(cell7);
		table.addCell(cell8);
		
		SimpleDateFormat format = new SimpleDateFormat(NO_TIME_DATE_FORMAT);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		for (SundayServiceAttendees sundayServiceAttendee : sundayServiceAttendees) {
			String FULL_NAME = sundayServiceAttendee.getAttendees().getLastName() + ", "
					+ sundayServiceAttendee.getAttendees().getFirstName() + " "
					+ sundayServiceAttendee.getAttendees().getMiddleName();
		
			PdfPCell Cell1 = createCell(FULL_NAME, 3, 3);
			Cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell1);

			PdfPCell Cell2 = createCell(sundayServiceAttendee.getAttendees().getAddress(), 3, 3);
			Cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell2);

			PdfPCell Cell3 = createCell(sundayServiceAttendee.getAttendees().getContactNumber(), 3, 3);
			Cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell3);

			String GENDER = (sundayServiceAttendee.getAttendees().getGender()) ? "Male" : "Female";
			PdfPCell Cell4 = createCell(GENDER, 2, 3);
			Cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell4);

			PdfPCell Cell5 = createCell(
					format.format(sundayServiceAttendee.getAttendees().getBirthday()).toString(), 3, 3);
			Cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell5);
			
			PdfPCell Cell7 = createCell(sundayServiceAttendee.getAttendees().getMinistry().getMinistryName(), 3, 3);
			Cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell7);
			
			PdfPCell Cell8 = createCell(sundayServiceAttendee.getAttendees().getGroup().getGroupName(), 3, 3);
			Cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell8);

		}

		document.add(contentTitle);
		document.add(table);

	}
	
	public static void addTableServiceProfileFirstTimerReport(Document document, List<FirstTimer> firstTimers, String GroupName)
			throws BadElementException, DocumentException {
		
		// table
		PdfPTable table = new PdfPTable(10);
		float[] w = new float[] { 20f, 30f, 20f, 25f, 10f, 10f, 10f, 10f, 10f,10f };
		table.setWidths(w);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);

		Paragraph contentTitle = new Paragraph();
		contentTitle.add(new Paragraph(GroupName, SmallBFont));

		PdfPCell cell1 = createCell(NameHeader, 3, 3);
		PdfPCell cell2 = createCell(AddressHeader, 3, 3);
		PdfPCell cell3 = createCell(ContactNumberHeader, 3, 3);
		PdfPCell cell11 = createCell(StatusHeader, 3, 3);
		PdfPCell cell4 = createCell(GenderHeader, 3, 3);
		PdfPCell cell5 = createCell(BirthdayHeader, 3, 3);
		PdfPCell cell7 = createCell(MinistryHeader, 3, 3);
		/*PdfPCell cell6 = createCell(AgeHeader, 3, 3);*/
		PdfPCell cell8 = createCell(GroupHeader, 3, 3);
		PdfPCell cell9 = createCell(RemarksHeader, 3, 3);
		PdfPCell cell10 = createCell(InvitedByHeader, 3, 3);
		
		cell1.setGrayFill(0.85f);
		cell2.setGrayFill(0.85f);
		cell3.setGrayFill(0.85f);
		cell11.setGrayFill(0.85f);
		cell4.setGrayFill(0.85f);
		cell5.setGrayFill(0.85f);
		/*cell6.setGrayFill(0.85f);*/
		cell7.setGrayFill(0.85f);
		cell8.setGrayFill(0.85f);
		cell9.setGrayFill(0.85f);
		cell10.setGrayFill(0.85f);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell11);
		table.addCell(cell4);
		table.addCell(cell5);
		/*table.addCell(cell6);*/
		table.addCell(cell7);
		table.addCell(cell8);
		table.addCell(cell9);
		table.addCell(cell10);
		
		SimpleDateFormat format = new SimpleDateFormat(NO_TIME_DATE_FORMAT);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		for (FirstTimer firstTimer : firstTimers) {
			String FULL_NAME = firstTimer.getAttendeesGuest().getLastName() + ", "
					+ firstTimer.getAttendeesGuest().getFirstName() + " "
					+ firstTimer.getAttendeesGuest().getMiddleName();
		
			PdfPCell Cell1 = createCell(FULL_NAME, 3, 3);
			Cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell1);

			PdfPCell Cell2 = createCell(firstTimer.getAttendeesGuest().getAddress(), 3, 3);
			Cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell2);

			PdfPCell Cell3 = createCell(firstTimer.getAttendeesGuest().getContactNumber(), 3, 3);
			Cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell3);

			PdfPCell Cell11 = createCell(firstTimer.getFirstTimerStatus().getFirstTimerStatus(), 3, 3);
			Cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell11);
			
			String GENDER = (firstTimer.getAttendeesGuest().getGender()) ? "Male" : "Female";
			PdfPCell Cell4 = createCell(GENDER, 2, 3);
			Cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell4);

			PdfPCell Cell5 = createCell(
					format.format(firstTimer.getAttendeesGuest().getBirthday()).toString(), 3, 3);
			Cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell5);
			
			PdfPCell Cell7 = createCell(firstTimer.getAttendeesGuest().getMinistry().getMinistryName(), 3, 3);
			Cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell7);
			
			PdfPCell Cell8 = createCell(firstTimer.getAttendeesGuest().getGroup().getGroupName(), 3, 3);
			Cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell8);
			
			PdfPCell Cell9 = createCell(firstTimer.getRemarks(), 3, 3);
			Cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(Cell9);
			
			if(firstTimer.getRemarks().equals("Invited")){
				PdfPCell Cell10 = createCell(firstTimer.getFirstNameInvite(), 3, 3);
				Cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(Cell10);
			}else{
				PdfPCell Cell10 = createCell("NONE", 3, 3);
				Cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(Cell10);
			}
			
			
		}

		document.add(contentTitle);
		document.add(table);

	}

	
}
