package com.capg.sbs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.capg.sbs.controller.ProductBookingController;
import com.capg.sbs.entity.Login2;
import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.entity.User;
import com.capg.sbs.repository.ProductBookingRepository;
import com.capg.sbs.service.ProductBookingService;

@SpringBootTest
public class ProductBookingTesting {
	
	@Autowired
	private ProductBookingRepository  productBookingRepository;

	@Autowired
	private ProductBookingService productBookingService;

	
	@Autowired
	private ProductBookingController productBookingController;
	
	@Test
	  public void testProductBookingByAddress() 
	     {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/productbooking";
			Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);
			User user = new User(1L,"admin" , "soniya" ,"Arti" ,"Saroj", "ROLE_ADMIN");
			ProductBooking pb= new ProductBooking(1,22,"chembure","mumbai","maharashtra",4090,"PENDING","N",null,null,null,product,user);
			productBookingRepository.save(pb);
     		//Review review= new Review(1,"Good",3,null,null,product,login);
			ProductBooking[] pbs = restTemplate.getForObject(url, ProductBooking[].class);
			Assertions.assertThat(pbs).extracting(ProductBooking :: getAddress).contains("chembure");
	
	     }
	
	
	@Test
	  public void testProductBookingForDeleting() 
	     {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/productbooking";
			Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);
			User user = new User(1L,"admin" , "soniya" ,"Arti" ,"Saroj", "ROLE_ADMIN");
			ProductBooking pb= new ProductBooking(10,22,"chembure","mumbai","maharashtra",4090,"PENDING","N",null,null,null,product,user);
			
			productBookingRepository.save(pb);
//			Review review= new Review(1,"Good",3,null,null,product,login);
			
			productBookingRepository.deleteBooking(10, null);
			ProductBooking[] pbs = restTemplate.getForObject(url, ProductBooking[].class);
			
			ProductBooking booking = productBookingRepository.findByBookingId(10);
			
			assertEquals(booking.getBookingCancelFlag(), "Y");
		
	     }
	
	@Test
	  public void testForGettingAllProductBookingForExceptionHandling() 
	  {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/productbooking";
			try {
				restTemplate.getForEntity(url, String.class);
				
			}
			catch(HttpClientErrorException  e)
			{
				assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
				
			}
	  }
	
	
	

}
