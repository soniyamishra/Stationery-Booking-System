package com.capg.sbs.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.sbs.entity.Login;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.entity.Review;
import com.capg.sbs.repository.LoginRepository;
import com.capg.sbs.repository.ProductBookingRepository;
import com.capg.sbs.repository.ProductRepository;
import com.capg.sbs.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ProductBookingRepository productBookingRepository;
	
	public List<Review> getAllReview()                                                       
	   {
    	List<Review> review= new ArrayList<Review>();
    	reviewRepository.findAll().forEach(review1 -> review.add(review1));
    	return review;
	   }
	
     //getting a specific data 
	  public List<Review> getReviewByProductId(int productId)             
	  { 
		return reviewRepository.findByReviewProductId(productId);
	  }
	
//	public Review addA(int productId)                                                    //getting a specific data
//	  { 
//  	List<Review> review= new ArrayList<Review>();
//
//	 return reviewRepository.findByProductId(productId);
//	  return review;
//  .findByProductId(productId); 
//	  }
	
	 public int insert(Review review)  
	  {	 int id = review.getProduct().getProductId();
	     List<ProductBooking> productBooking = productBookingRepository.findByProductId(id);
	     int reviewApplicable=0;
	     for(int i=0; i< productBooking.size() ; i++)
	     {
	    	 if(productBooking.get(i).getApprovalStatus().equals("CONFORM"))
	    	 {
	    		 reviewApplicable=1;
	    		 reviewRepository.save(review); 
	    		 break;
	    	 }
	     }
	  
		return reviewApplicable;
	  }
	

//	 @UpdateTimestamp
//		public  int updates(Review review) {
////	    LocalDateTime  reviewUpdatedAt =LocalDateTime.now();
//	    int a= reviewRepository.setReviewByProductIdAndUserId(review.getProductId(),review.getUserId(),review.getReviewComment(),review.getRatingNumber());
//		    
//	    return a;
//			
//		}
	
//	public void update(Review review )                                                 //update data
//	  {
//		reviewRepository.save(review); 
//	  }
//	@UpdateTimestamp
	
	 public int updateCommentAndRating(Review review) {
			
			LocalDateTime  reviewUpdatedAt =LocalDateTime.now();
			Review reviewUpdate = reviewRepository.findByReviewId(review.getReviewId());
			int inputUserId=review.getLogin().getUserId();
			int userId=reviewUpdate.getLogin().getUserId();
			if(inputUserId==userId) 
			{
				reviewUpdate.setReviewComment(review.getReviewComment());
				reviewUpdate.setRatingNumber(review.getRatingNumber());
				reviewUpdate.setReviewUpdatedAt(reviewUpdatedAt);
				reviewRepository.save(reviewUpdate);
				return 1;
			}
			else 
				return 0;
			
		}
	
	public double average(int productId) {
		 return reviewRepository.getAverage(productId);
		
	 }
	
	public Review findReviewById(Review review)
	{
		return reviewRepository.findByReviewId(review.getReviewId());
	}
	 
	  
}