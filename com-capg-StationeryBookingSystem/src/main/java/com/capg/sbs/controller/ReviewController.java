package com.capg.sbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.Review;
import com.capg.sbs.service.ProductService;
import com.capg.sbs.service.ReviewService;

@CrossOrigin
@RestController
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService productService;
	
	static Logger logger = Logger.getLogger(ProductController.class.getName());
	    
	@GetMapping("/review")
	private List<Review> getAllReview() 
	{	
		logger.info("Getting all list of Reviews");
	    return reviewService.getAllReview();
	}
	
    @GetMapping("/review/product/{productId}") 
	private List<Review> getReview(@PathVariable("productId") int productId) throws ValidationException
	{   
    	logger.info("Getting list of Reviews of Specific ProductID");
    	if(productService.getProductById(productId)== null)
			{	
    			logger.warn("Product does not exist");
				throw new ValidationException("Product does not exist");
			}
	    return reviewService.getReviewByProductId(productId);
	} 
    
    @PostMapping("/review" ) 
    private ResponseEntity<String> saveReview(@Valid @RequestBody Review review) 
    {   
	  if(reviewService.insert(review)==1)
	  {  logger.info("Your Review has saved.");
          return ResponseEntity.ok("Your Review has saved");
	  }
	  else
	  {	  
		  logger.warn("You are not allowed to give rating to this product. ");
		  return ResponseEntity.ok("You are not allowed to give rating to this product. ");
		 
	  }
    }

    @PutMapping("/review/update")         
	private ResponseEntity<String> updateReview(@RequestBody Review review) throws ValidationException
	{		
		 if(!(review.getRatingNumber() <= 5 || review.getRatingNumber() >=1))
		 {   
			 logger.warn("Rating number should be between 1 to 5");

			 throw new ValidationException("Rating number should be between 1 to 5");
		 }
		 if(review.getReviewComment().isBlank())
		 {
			 logger.warn("Comment cannot be blank");

			 throw new ValidationException("Comment cannot be blank");
		 }
		 if(reviewService.findReviewById(review) == null)
		 {
			  logger.warn("The Review Id is Not Valid");

			 throw new ValidationException("The Review Id is Not Valid");
		 }
		 if(reviewService.updateCommentAndRating(review)==0)
		 {
			return ResponseEntity.ok("You are not Authorised to Update this Review ");
			 
		 }
		 
		 return ResponseEntity.ok("Review Updated Successfully");
//         return reviewService.updateCommentAndRating(review);
 
	}
	 
	 @GetMapping("/review/avg/{productId}")
	 public ResponseEntity<String> average(@PathVariable("productId") int productId) throws ValidationException {
		 
		 logger.info("Overall ratings of the product rating");
		 if(productService.getProductById(productId)== null)
			{	
			    logger.warn("Product does not exist");
				throw new ValidationException("Product does not exist");
			}
		 return ResponseEntity.ok("Overall rating is " + reviewService.average(productId));
			  
	 }
	 
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) 
	 {
	  Map<String, String> errors = new HashMap<>();
	  ex.getBindingResult().getAllErrors().forEach((error) -> {
	  String fieldName = ((FieldError) error).getField();
	  String errorMessage = error.getDefaultMessage();
	  errors.put(fieldName, errorMessage);
	  });
	  return errors;
	  }
	 
//		@GetMapping("/review/product/{productId}")
//		private List<Review>  addReview(@PathVariable("productId")int productId)
//		{
//			return reviewService.getReviewById(productId);
//		}
//		
	 
//	 @PutMapping("/review")                 
//	  private Review update(@RequestBody Review review ) 
//	  { 
////		  
//		 reviewService.update(review );
//
//	        return review ; 
//	  }

}
