package com.capg.sbs.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.repository.ProductBookingRepository;
import com.capg.sbs.repository.ProductRepository;
import com.capg.sbs.service.ProductApprovalService;
import com.capg.sbs.service.ProductBookingService;


@CrossOrigin
@RestController
public class ProductBookingController {
	
	@Autowired
	ProductBookingService productBookingService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductBookingRepository productbookingrepository;
	
	static Logger logger = Logger.getLogger(ProductController.class.getName());
	
	@GetMapping("/productbooking")
	public List<ProductBooking> getAllBooking1()
	{	
		logger.info("Getting  list of all ProductBooking");
		return productBookingService.getAllBooking();
		
	}
	
	@GetMapping("/productbooking/{id}")
	public List<ProductBooking> getBookingsById(@PathVariable("id") Long id)
	{	
		logger.info("Getting  list of all ProductBooking");
		return productBookingService.viewById(id);
		
	}
	
	@GetMapping("/productbooking/id/{bookingId}")
	private ProductBooking getBookingById(@PathVariable("bookingId") int bookingId) throws ValidationException{
		
		return productBookingService.getBookingById(bookingId);
	}

	@GetMapping("/productbooking/userid/{userId}")
	public List<ProductBooking> getBookingsByUserId(@PathVariable("userId") int userId)
	{	
		logger.info("Getting  list of all ProductBooking");
		return productBookingService.viewByUserId(userId);
		
	}
	
	
	
	
	@PostMapping("/productbooking")
	public ResponseEntity<String> addProductBooking(@Valid @RequestBody ProductBooking productBooking) throws ValidationException
	{	
		logger.info("Booking product");
		
		 if(productBooking.getAddress().isEmpty() || productBooking.getCity().isEmpty() || productBooking.getState().isEmpty() )
		 {
			 logger.warn("Fields cannot be blank");

			 throw new ValidationException("Fields cannot be blank");
		 }
		 
		 if(productBooking.getProductQuantity() == 0 )
		 {
			 logger.warn("Fields cannot be blank");

			 throw new ValidationException("Fields cannot be blank");
		 }
		 
		 
		if(productBooking.getZipcode() == 0)
			
		{
			logger.warn("Fields cannot be blank");
			throw new ValidationException("Zipcode is mandatory for product booking");
		}
		String str = String.valueOf(productBooking.getZipcode());
		
		if(str.length() != 6)
		{logger.warn("Fields cannot be blank");
			throw new ValidationException("Zipcode should be of 6 digit");
		}
		if(productBookingService.addProduct(productBooking) == 1)
		{	
			logger.info("Product has been successfully booked");
			return ResponseEntity.ok("Product has been successfully booked");
			
		}
		else
		{	
			logger.warn("Product is not available for time being");
			throw new ValidationException("Product is not available for time being");
		}
		
		
		
	}

	@PutMapping("productbooking/delete")
	public  ResponseEntity<String> deleteBooking(@RequestBody ProductBooking productBooking) throws ValidationException
	{   
		logger.info("deleting ProductBooking");
		
		if(productBooking.getBookingId() == 0)
		{
			logger.warn("Fields cannot be blank");
			throw new ValidationException("Product booking id is mandatory field to  cancel the booking");
		}
		
		if(productBookingService.findBookingById(productBooking.getBookingId()) == null)
			{	
			    logger.warn("Booking does not exist");
				throw new ValidationException("Booking does not exist");
			}
		productBookingService.deleteProductBooking(productBooking);
		return ResponseEntity.ok("Booking is cancel sucessfully");
		
	}
	
	
	/* @GetMapping("productbooking/{bookingId}")
		private ProductBooking  getbookingById(@PathVariable("bookingId") int bookingId) {
			return productBookingService.getbookingById(bookingId);
		}
	  
	
	@GetMapping("/productbooking")
	public List<ProductBooking> getAllBooking1()
	{	
		logger.info("Getting  list of all ProductBooking");
		return productBookingService.getAllBooking();
		
	}

	@PostMapping("/productbooking")
	public ResponseEntity<String> addProductBooking(@Valid @RequestBody ProductBooking productBooking) throws ValidationException
	{	
		logger.info("Booking product");
		
		
		if(productBooking.getZipcode() == 0)
		{
			throw new ValidationException("Zipcode is mandatory for product booking");
		}
		String str = String.valueOf(productBooking.getZipcode());
		
		if(str.length() != 6)
		{
			throw new ValidationException("Zipcode should be of 6 digit");
		}
		if(productBookingService.addProduct(productBooking) == 1)
		{	
			logger.info("Product has been successfully booked");
			return ResponseEntity.ok("Product has been successfully booked");
			
		}
		else
		{	
			logger.warn("Product is not available for time being");
			throw new ValidationException("Product is not available for time being");
		}
		
		
		
	}

	@PutMapping("productbooking/delete")
	public  ResponseEntity<String> deleteBooking(@RequestBody ProductBooking productBooking) throws ValidationException
	{   
		logger.info("deleting ProductBooking");
		
		if(productBooking.getBookingId() == 0)
		{
			throw new ValidationException("Product booking id is mandatory field to  cancel the booking");
		}
		
		if(productBookingService.findBookingById(productBooking.getBookingId()) == null)
			{	
			    logger.warn("Booking does not exist");
				throw new ValidationException("Booking does not exist");
			}
		productBookingService.deleteProductBooking(productBooking);
		return ResponseEntity.ok("Booking is cancel sucessfully");
		
	}
	*/
//===========================conformBooking==============================================================================================
	
	
			@PutMapping("/ConformBooking") 
			private ResponseEntity<String> Conformbooking(@RequestBody  ProductBooking productapproval) throws ValidationException 
			{ 	
				if(productapproval.getBookingId() == 0)
				 {
					logger.warn("Booking id does not exist");
					throw new ValidationException("Booking id is mandatory field");
				 }
				
			    if(productBookingService.findBookingById(productapproval.getBookingId()) == null)
			    {
			    	throw new ValidationException("Booking id does not exist");
			    }
				
				int flag =productBookingService.Conformbooking(productapproval);
				System.out.println(flag);
				if(flag == 2)
				{
					throw new ValidationException("Product is already" +productapproval.getApprovalStatus());
				}
				
				ProductBooking booking = productbookingrepository.findByBookingId(productapproval.getBookingId());
				int productId=booking.getProduct().getProductId();
				int ProductQuantity  = booking.getProductQuantity();
				int  ProductCount=booking.getProduct().getProductCount();
				if(flag==0)
				{
					throw new ValidationException("ProductQuantity: " +  ProductQuantity + " is " + " greater than Product Count:  " +  ProductCount  + "  Need to UpdateBooking for Comform Booking");
				}
					
				return ResponseEntity.ok("Booking Is Confirmed Successfully");
						 
			}
			
		//==========================cancelBooking====================================================================================
			
			
			@PutMapping("/CancelBooking") 
			private ResponseEntity<String> Cancelbooking(@RequestBody ProductBooking productapproval) throws ValidationException 
			{
				if(productapproval.getBookingId() == 0)
				{
					logger.warn("Booking id does not exist");
					throw new ValidationException("Booking id is mandatory field");
				}
				if(productBookingService.findBookingById(productapproval.getBookingId()) == null)
			    {
			    	throw new ValidationException("Booking id does not exist");
			    }
			    
			    int flag =productBookingService.Cancelbooking(productapproval);
				
				if(flag == 0)
				{
					throw new ValidationException("Product is already confirmed");
				}
			
				return ResponseEntity.ok("Booking Is Cancelled");
					 
			}
			
		//==========================UpdateBooking====================================================================================
			
			@PutMapping("/UpdateBooking") 
			private ResponseEntity<String> Updatebooking(@RequestBody ProductBooking productapproval)  throws ValidationException
			{
				if(productapproval.getBookingId() == 0)
				 {
					logger.warn("Booking id does not exist");
					throw new ValidationException("Booking id is mandatory field");
				 }
				
			    if(productBookingService.findBookingById(productapproval.getBookingId()) == null)
			    {
			    	throw new ValidationException("Booking id does not exist");
			    }
				
			    int flag=productBookingService.Updatebooking(productapproval);
				
				 if(flag ==0)
				{
					throw new ValidationException("Approval Status is Already: " +productapproval.getApprovalStatus());
				}
				
				 return ResponseEntity.ok(" Approval_status Updated.");
		}
			
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		    Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		    return errors;
		}
		
			
	
}