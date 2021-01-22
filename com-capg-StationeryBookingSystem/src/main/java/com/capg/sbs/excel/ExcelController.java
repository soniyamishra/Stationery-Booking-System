package com.capg.sbs.excel;

import java.io.File;
import java.util.Scanner;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capg.sbs.excel.ExcelService;
import com.capg.sbs.pdf.ProductRestAPIs;

@CrossOrigin("http://localhost:2211")
@Controller
@RequestMapping("/api/excel")
public class ExcelController {

  @Autowired
  ExcelService fileService;
  
  static Logger logger = Logger.getLogger(ExcelController.class.getName());
  
  @GetMapping("/download")
  public ResponseEntity<Resource> getFile() {
    String filename = "Products.xlsx";
    //System.out.println("Enter the path to create excel file : ");
    //Scanner sc = new Scanner(System.in);
    //String path = sc.next();
    //File file= new File(path);
    InputStreamResource file = new InputStreamResource(fileService.load());
    logger.info("Excel generated successfully");
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
        .body(file);
  }

}