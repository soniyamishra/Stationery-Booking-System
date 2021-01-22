package com.capg.sbs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.capg.sbs.controller.ProductController;
import com.capg.sbs.entity.DeliveryTracking;
import com.capg.sbs.entity.Login;
import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.repository.ProductBookingRepository;
import com.capg.sbs.repository.ProductRepository;
import com.capg.sbs.service.ProductBookingService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;



@SpringBootTest
public class ProductApprovalTest {
	
	@Autowired
	ProductBookingService productbookingService;
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	ProductBookingRepository productBookingrepository;
	
	@Autowired
	ProductRepository productrepository;

	@Test
	void contextLoads() {
		Assert.notNull(productbookingService);
	}
	
	@Test
	public void test() throws Exception
	{ 
		 assertThat(productController).isNotNull();
	}
	
	
	@Test
	public void testProductbookingConform()
	{
		RestTemplate restTemplate = new RestTemplate();
		String urlConformbooking = "http://localhost:2211/productbooking";
		Product  product =new Product(1 , "pen" , "dark" , "doms" , 20.1 , 50 , "N" , null , null ,null);
		Login login=new Login(1 , "mona123" , "mona" ,"admin" , "Monika" ,"Desai");
		ProductBooking productbooking =new ProductBooking(1, 5, "ghatkopar", "Mumbai", "maharashtra", 4000, "PENDING FOR APPROVAL", "N", null, null, null, product, login);
		productBookingrepository.save(productbooking);
		ProductBooking booking=new ProductBooking(1);
		productbookingService.Conformbooking(booking);
		ProductBooking[] productbookings=restTemplate.getForObject(urlConformbooking, ProductBooking[].class);
		Assertions.assertThat(productbookings).extracting(ProductBooking::getApprovalStatus).contains("CONFORM");
	}

	@Test
	public void testProductbookingCancel()
	{
		RestTemplate restTemplate = new RestTemplate();
		String urlConformbooking = "http://localhost:2211/productbooking";
		Product  product =new Product(1 , "pen" , "dark" , "doms" , 20.1 , 50 , "N" , null , null ,null);
		Login login=new Login(1 , "mona123" , "mona" ,"admin" , "Monika" ,"Desai");
		ProductBooking productbooking =new ProductBooking(1, 5, "ghatkopar", "Mumbai", "maharashtra", 4000, "PENDING FOR APPROVAL", "N", null, null, null, product, login);		
		productBookingrepository.save(productbooking);
		ProductBooking booking=new ProductBooking(1);
		productbookingService.Cancelbooking(booking);
		ProductBooking[] productbookings=restTemplate.getForObject(urlConformbooking, ProductBooking[].class);
		Assertions.assertThat(productbookings).extracting(ProductBooking::getApprovalStatus).contains("CANCEL");
		
	}
	
	@Test 
	public void testProductbookingUpdate()
	{
		
		RestTemplate restTemplate = new RestTemplate();
		String urlConformbooking = "http://localhost:2211/productbooking";
		Product  product =new Product(1 , "pen" , "dark" , "doms" , 20.1 , 50 , "N" , null , null ,null);
		Login login=new Login(1 , "mona123" , "mona" ,"admin" , "Monika" ,"Desai");
		ProductBooking productbooking =new ProductBooking(1, 55, "ghatkopar", "Mumbai", "maharashtra", 4000, "PENDING FOR APPROVAL", "N", null, null, null, product, login);
		productBookingrepository.save(productbooking);
		ProductBooking booking=new ProductBooking(1);
		productbookingService.Updatebooking(booking);
		ProductBooking[] productbookings=restTemplate.getForObject(urlConformbooking, ProductBooking[].class);
		Assertions.assertThat(productbookings).extracting(ProductBooking::getApprovalStatus).contains("PENDING FOR CONFORM");
			
	}
	
}