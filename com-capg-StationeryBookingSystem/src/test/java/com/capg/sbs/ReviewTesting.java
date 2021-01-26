package com.capg.sbs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.capg.sbs.entity.Login2;
import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.Review;
import com.capg.sbs.repository.ProductRepository;
import com.capg.sbs.repository.ReviewRepository;
import com.capg.sbs.service.ReviewService;

@SpringBootTest
public class ReviewTesting {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ReviewService reviewService;
	
	@Test
	  public void reviewAddReviewTest() 
	     {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:2211/review";
			Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);
			Login2 login = new Login2(1,"AartiSA","1234","user","aarti","saroj");
			Review review= new Review(1,"Good",3,null,null,product,login);
		  
			reviewRepository.save(review);	
			Review[] reviews = restTemplate.getForObject(url, Review[].class);
			Assertions.assertThat(reviews).extracting(Review :: getReviewComment).contains("Good");
	     }
	
	@Test
	  public void testForReviewUpdate() 
	   {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:2211/review";
			Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);
			Login2 login = new Login2(1,"AartiSA","1234","user","aarti","saroj");
			Review review= new Review(4,"Good",3,null,null,product,login);
			reviewRepository.save(review);	
//			Review review= new Review(1,1,786,"good",3,null,null);
			Review reviewUpdate = new Review(4,"Bad",4);
			reviewService.updateCommentAndRating(reviewUpdate);
			//reviewRepository.setReviewByProductIdAndUserId(product, login, "bad", 4, null);	
			Review[] reviews = restTemplate.getForObject(url, Review[].class);
			Assertions.assertThat(reviews).extracting(Review :: getReviewComment).contains("Bad"); 
	  }
	
	
	@Test
	  public void testForGettingAllReviewOfExceptionHAndling() 
	  {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:2211/review";
			try {
				restTemplate.getForEntity(url, String.class);
				
			}
			catch(HttpClientErrorException  e)
			{
				assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
				
			}
	  }
	  
	 
	  
	  @Test
	  public void testForGettingSpecificReviewOfExceptionHAndling() 
	  {
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:2211/review/product/33";
			try {
				
				restTemplate.getForEntity(url, String.class);
				
			}
			catch(HttpClientErrorException  e)
			{
				assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
//				Assert.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
			}
	  }

}
