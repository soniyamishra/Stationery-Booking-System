package com.capg.sbs.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Stream;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capg.sbs.entity.Product;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {
	private static Logger logger = LoggerFactory.getLogger(PdfGenerator.class);
	  
	  public static ByteArrayInputStream productPDFReport(List<Product> products) {
	    Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        
	        try {
	          
	          PdfWriter.getInstance(document, out);
	            document.open();
	          
	            // Add Text to PDF file ->
	          Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
	          Paragraph para = new Paragraph( "Product Table", font);
	          para.setAlignment(Element.ALIGN_CENTER);
	          document.add(para);
	          document.add(Chunk.NEWLINE);
	          
	          PdfPTable table = new PdfPTable(10);
	          // Add PDF Table Header 
	            Stream.of("productId", "productName", "productModel","productBrand","productCount"
	           ,"productPrice","productCreatedAt","productUpdatedAt","productDeletedAt","productDeletedFlag")
	              .forEach(headerTitle -> {
	                  PdfPCell header = new PdfPCell();
	                  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	                  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	                  header.setHorizontalAlignment(Element.ALIGN_CENTER);
	                  header.setBorderWidth(2);
	                  header.setPhrase(new Phrase(headerTitle, headFont));
	                  table.addCell(header);
	              });
	            for (Product product : products) 
                {
	            	PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(product.getProductId())));
	            	idCell.setPaddingLeft(4);
	            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            	table.addCell(idCell);
	 
	                PdfPCell NameCell = new PdfPCell(new Phrase(product.getProductName()));
	                NameCell.setPaddingLeft(4);
	                NameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                NameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(NameCell);
	 
	                PdfPCell ModelCell = new PdfPCell(new Phrase(product.getProductModel()));
	                ModelCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                ModelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                ModelCell.setPaddingRight(4);
	                table.addCell(ModelCell);
	                
	                PdfPCell BrandCell = new PdfPCell(new Phrase(product.getProductBrand()));
	                BrandCell.setPaddingLeft(4);
	                BrandCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                BrandCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(BrandCell);
	                
	                PdfPCell CountCell = new PdfPCell(new Phrase(String.valueOf(product.getProductCount())));
	                CountCell.setPaddingLeft(4);
	                CountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                CountCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(CountCell);
	                
	                 PdfPCell PriceCell = new PdfPCell(new Phrase(String.valueOf(product.getProductPrice())));
	                PriceCell.setPaddingLeft(4);
	                PriceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                PriceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(PriceCell);
	             
	                PdfPCell CreatedAtCell = new PdfPCell(new Phrase(String.valueOf(product.getProductCreatedAt())));
	                CreatedAtCell.setPaddingLeft(4);
	                CreatedAtCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                CreatedAtCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(CreatedAtCell);
	                
	                PdfPCell UpdatedAtCell = new PdfPCell(new Phrase(String.valueOf(product.getProductUpdatedAt())));
	                UpdatedAtCell.setPaddingLeft(4);
	                UpdatedAtCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                UpdatedAtCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(UpdatedAtCell);
	                
	                PdfPCell DeletedAtCell = new PdfPCell(new Phrase(String.valueOf(product.getProductDeletedAt())));
	                DeletedAtCell.setPaddingLeft(4);
	                DeletedAtCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                DeletedAtCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(DeletedAtCell);
	                
	                PdfPCell DeletedFlagCell = new PdfPCell(new Phrase(String.valueOf(product.getProductDeletedFlag())));
	                DeletedFlagCell.setPaddingLeft(4);
	                DeletedFlagCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                DeletedFlagCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(DeletedFlagCell);
	                	
	                }
	          
	            document.add(table);
	            
	            document.close();
	        }catch(DocumentException e) {
	          logger.error(e.toString());
	        }
	        
	    return new ByteArrayInputStream(out.toByteArray());
	  }
}
