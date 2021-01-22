package com.capg.sbs.pdf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capg.sbs.pdf.PdfGenerator;
import com.capg.sbs.controller.ProductController;
import com.capg.sbs.entity.Product;
import com.capg.sbs.repository.ProductRepository;

@RestController
@RequestMapping("/api/pdf")
public class ProductRestAPIs {
	 
	@Autowired
	ProductRepository productRepository;
	
	static Logger logger = Logger.getLogger(ProductRestAPIs.class.getName());
	 
	    @GetMapping(value = "/products",
	            produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> productsReport() throws IOException {
	        List<Product> products = (List<Product>) productRepository.findAllProductdata();
	        
	        ByteArrayInputStream bis = PdfGenerator.productPDFReport(products);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=products.pdf");
	        logger.info("PDF generated successfully");
	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
	    }
}
