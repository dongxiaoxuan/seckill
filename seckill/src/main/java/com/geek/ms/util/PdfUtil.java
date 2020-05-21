package com.geek.ms.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.geek.ms.pojo.vo.Product;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfUtil {

    public void createPDF(Document document, PdfWriter writer, List<Product> products, String courseName) throws IOException {
        //Document document = new Document(PageSize.A4);
        try {
            document.addTitle(courseName + "成绩单");
            document.addAuthor("scurry");
            document.addSubject("product sheet.");
            document.addKeywords("product.");
            document.open();
            PdfPTable table = createTable(writer,products, courseName);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
    public static PdfPTable createTable(PdfWriter writer,List<Product> products, String courseName) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(3);//生成一个两列的表格
        PdfPCell cell;
        int size = 30;
        Font font = new Font(BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED));
        Font fontChinese1 = new Font(BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED),18,Font.BOLD);
        Font fontChinese2 = new Font(BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED),14,Font.BOLD);
        cell = new PdfPCell(new Phrase(courseName,fontChinese1));
    	cell.setFixedHeight(50);
    	cell.setColspan(3);
    	cell.setUseAscender(true);
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中
    	cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
    	table.addCell(cell);
    	
    	cell = new PdfPCell(new Phrase("学号",fontChinese2));
        cell.setFixedHeight(size);
        cell.setUseAscender(true);
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("姓名",fontChinese2));
        cell.setFixedHeight(size);
        cell.setUseAscender(true);
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("分数",fontChinese2));
        cell.setFixedHeight(size);
        cell.setUseAscender(true);
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    	
    	
        for(int i = 0;i<products.size();i++) {
            cell = new PdfPCell(new Phrase(products.get(i).getNumber(),font));
            cell.setFixedHeight(size);
            cell.setUseAscender(true);
        	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(products.get(i).getRealName(),font));
            cell.setFixedHeight(size);
            cell.setUseAscender(true);
        	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(products.get(i).getScore()+"",font));
            cell.setFixedHeight(size);
            cell.setUseAscender(true);
        	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
        }
        return table;
    }
}