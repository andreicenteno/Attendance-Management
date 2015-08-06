package com.am.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooter extends PdfPageEventHelper {
	static int pagenumber;
	private static Font FootFont = new Font(Font.FontFamily.HELVETICA, 8, pagenumber, BaseColor.GRAY);
	private final static String footerPhrase = "Jesus Is Lord Church, Area 23 Bulihan Silang Cavite- Attendance and Monitoring System";

	
	protected Phrase footer;
    
    public HeaderFooter() {
       
       footer = new Phrase("**** Footer ****");
   }
    
	
	
	public void onEndPage(PdfWriter writer, Document document)
	{
		 
		 Rectangle pg = document.getPageSize();
		 
		 String page = "Page: " +  writer.getPageNumber() ;
			
	      PdfPTable foot = new PdfPTable(1);
	      PdfPCell cell2 = new PdfPCell(new Phrase(page, FootFont));
	      PdfPCell cell = new PdfPCell(new Phrase(footerPhrase, FootFont));
	      cell.setBorder(Rectangle.NO_BORDER);
	      
	      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	      cell2.setBorder(Rectangle.NO_BORDER);
	      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	      foot.setTotalWidth(pg.getWidth() - document.leftMargin() - document.rightMargin());
	      foot.addCell(cell);
	      foot.addCell(cell2);
	      foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
		
	    
	}
}
