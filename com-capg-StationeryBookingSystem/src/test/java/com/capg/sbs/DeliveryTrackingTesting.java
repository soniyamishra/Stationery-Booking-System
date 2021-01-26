package com.capg.sbs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.capg.sbs.controller.DeliveryTrackingController;
import com.capg.sbs.entity.DeliveryTracking;
import com.capg.sbs.entity.Login2;
import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.repository.DeliveryTrackingRepository;
import com.capg.sbs.service.DeliveryTrackingService;

@SpringBootTest
public class DeliveryTrackingTesting {
	

	@Autowired
	DeliveryTrackingRepository deliveryTrackingRepository;
	

	@Autowired
	DeliveryTrackingService deliveryTrackingService;
	
	@Autowired
	DeliveryTrackingController deliveryController;
	
	
	@Test
	void contextLoads() {
		Assert.notNull(deliveryTrackingService);
	}
	
	@Test
	public void test() throws Exception
	{ 
		 assertThat(deliveryController).isNotNull();
	}
	
	
	
	
//	@Test
//	public void testAddDeleteProduct()
//	{	
//		RestTemplate restTemplate = new RestTemplate();
//		String urlAddDeliveryTracking = "http://localhost:2211//deliverytracking";
//		Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);
//		Login login = new Login(1,"priya_yadav","2335","user","priya","yadav");
//		
//		ProductBooking booking = new ProductBooking(1,50,"Kurla","mumbai","maharashtra",400070,"CONFORM",
//		"N",null,null,null,product,login);
//		
//		DeliveryTracking deliveryTracking = new DeliveryTracking(1,"Packed",booking,1);
//		deliveryTrackingRepository.save(deliveryTracking);
//		DeliveryTracking[] deliveryTrackings = restTemplate.getForObject(urlAddDeliveryTracking,DeliveryTracking[].class);
//		Assertions.assertThat(deliveryTrackings).extracting(DeliveryTracking :: getDelieveryStatus).contains("Packed");
//	
//	}
	
	
	@Test
	  public void addDeliveryStatusTest() 
	     {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:2211/deliverytracking";
			
			Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);
			Login2 login = new Login2(1,"AartiSA","1234","user","aarti","saroj");
			
			ProductBooking productBooking= new ProductBooking(30,3,"ghatkoper","mumbai","maharshtra",3434,"PENDING","N",null,null,null,product,login);
			DeliveryTracking deliveryTracking = new DeliveryTracking("NOT APPROVED",productBooking,null);
			deliveryTrackingRepository.save(deliveryTracking);	
			DeliveryTracking[] dt = restTemplate.getForObject(url, DeliveryTracking[].class);
			
			Assertions.assertThat(dt).extracting(DeliveryTracking :: getDelieveryStatus).contains("NOT APPROVED");
	     }
	@Test
	  public void testForDeliveryTrackingUpdate() 
	   {
		    RestTemplate restTemplate = new RestTemplate();
		    String url = "http://localhost:2211/deliverytracking";
			Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);
			Login2 login = new Login2(1,"AartiSA","1234","user","aarti","saroj");
			ProductBooking productBooking= new ProductBooking(31,3,"ghatkoper","mumbai","maharshtra",3434,"PENDING","N",null,null,null,product,login);
			DeliveryTracking deliveryTracking = new DeliveryTracking("NOT APPROVED",productBooking,null);
			deliveryTrackingRepository.save(deliveryTracking);	
			DeliveryTracking deliveryTrackingUpdate = new DeliveryTracking(61,"Packed",null);
			deliveryTrackingService.update(deliveryTrackingUpdate);
			DeliveryTracking[] dt = restTemplate.getForObject(url, DeliveryTracking[].class);
			Assertions.assertThat(dt).extracting(DeliveryTracking :: getDelieveryStatus).contains("Packed");

//			Review review= new Review(1,1,786,"good",3,null,null);
//			reviewRepository.setReviewByProductIdAndUserId(product, login, "bad", 4, null);	
//			Review[] reviews = restTemplate.getForObject(url, Review[].class);
//			Assertions.assertThat(reviews).extracting(Review :: getReviewComment).contains("bad"); 
	  }
	
	@Test
	  public void testForGettingAllBookingForExceptionHAndling() 
	  {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:2211/";
			try {
				restTemplate.getForEntity(url, String.class);
				
			}
			catch(HttpClientErrorException  e)
			{
				assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
				
			}
	  }
	  
	  @Test
	  public void testForGettingSpecificBookingIdOfExceptionHAndling() 
	  {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:2211/DeliveryTracking/998";
			try {
				restTemplate.getForEntity(url, String.class);
				
			}
			catch(HttpClientErrorException  e)
			{
				assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
				
			}
	  }
}
