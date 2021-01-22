package com.capg.sbs.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.capg.sbs.entity.Product;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "productId", "productName", "productModel", "productBrand", "productPrice","productCount",
			  						"productCreatedAt", "productUpdatedAt", "productDeletedAt", "productDeletedFlag"};
	  static String SHEET = "Products";

	  public static ByteArrayInputStream productsToExcel(List<Product> products) {

	    try (Workbook workbook = new XSSFWorkbook(); 
	    		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    		) 
	    {
	      Sheet sheet = workbook.createSheet(SHEET);

	      // Header
	      Row headerRow = sheet.createRow(0);

	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	      }

	      int rowIdx = 1;
	      for (Product product : products) {
	        Row row = sheet.createRow(rowIdx++);

	        row.createCell(0).setCellValue(product.getProductId());
	        row.createCell(1).setCellValue(product.getProductName());
	        row.createCell(2).setCellValue(product.getProductModel());
	        row.createCell(3).setCellValue(product.getProductBrand());
	        row.createCell(4).setCellValue(product.getProductPrice());
	        row.createCell(5).setCellValue(product.getProductCount());
	        row.createCell(6).setCellValue(product.getProductCreatedAt());
	        row.createCell(7).setCellValue(product.getProductUpdatedAt());
	        row.createCell(8).setCellValue(product.getProductDeletedAt());
	        row.createCell(9).setCellValue(product.getProductDeletedFlag());
	        
	      }

	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    }
	  }

	
	
}
