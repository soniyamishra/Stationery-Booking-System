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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capg.sbs.entity.DeliveryTracking;
import com.capg.sbs.repository.ProductBookingRepository;
import com.capg.sbs.service.DeliveryTrackingService;

@CrossOrigin
@RestController
public class DeliveryTrackingController {
	
	@Autowired
	DeliveryTrackingService deliveryTrackingService;
	
	@Autowired
	ProductBookingRepository productBookingRepository;
	
	static Logger logger = Logger.getLogger(DeliveryTrackingController.class.getName());
	
	//creating a get mapping that retrieves all the detail from the database
	@GetMapping("/deliverytracking")
	private List<DeliveryTracking> getAllStatus() {
		logger.info("View all Delivery Status");
		return deliveryTrackingService.getAllStatus();
	}
	
	
	
	//creating a get mapping that retrieves the detail of a specific DeliveryTracking
		@GetMapping("/deliverytracking/{bookingId}")
		private DeliveryTracking getStatus(@PathVariable("bookingId") int bookingId) throws ValidationException{
			
			logger.info("View Delivery Status by there Booking Id");
			
			if(productBookingRepository.findByBookingId(bookingId) == null)
			{   
				logger.warn("\"Delivery status does not exist for this booking id");
				throw new ValidationException("Delivery status does not exist for this booking id");
			}
			
			return deliveryTrackingService.getStatusByBookingId(bookingId);
		}
	
		@GetMapping("/deliverytracking/id/{deliveryTrackingId}")
		private DeliveryTracking getDeliveryByIdStatus(@PathVariable("deliveryTrackingId") int deliveryTrackingId) throws ValidationException{
			
			return deliveryTrackingService.getStatusByDeliveryId(deliveryTrackingId);
		}
	
	//creating post mapping that post the DeliveryTracking detail in the database
	@PostMapping("/deliverytracking") 
	ResponseEntity<String> create(@Valid @RequestBody DeliveryTracking deliveryTracking) throws ValidationException
	 { 	
		
		logger.info("Add Delivery Status ");
		
		int bookId = deliveryTracking.getProductBooking().getBookingId(); //getStatusByBookingId
		DeliveryTracking dt = deliveryTrackingService.getStatusByBookingId(bookId);
		
		if( deliveryTracking.getDelieveryStatus() == null || deliveryTracking.getDelieveryStatus().isBlank())
		{	
			logger.warn("Delivery Tracking Id and Status is mandatory for updating ");
			throw new ValidationException("Delivery Tracking Id and Status is mandatory for updating");
		}
		if(dt != null)
		{
			throw new ValidationException("Delivery Status is already added, you can update the delivery status ");
		}
		
		
		if(deliveryTrackingService.add(deliveryTracking) == 0)
		{
			throw new ValidationException("Booking is not conformed yet");
		}
		
		
		else {
			logger.info(" Delivery Status added successfully");
		    return ResponseEntity.ok("Sucessfully added"); 
		    
		}
		
		
	 }
	
	
	//creating put mapping that updates the Delivery status detail
	@PutMapping("/deliverytracking")
	private ResponseEntity<String> update(@RequestBody DeliveryTracking deliveryTracking) throws NullPointerException, ValidationException
	{	
		logger.info("Updating Delivery Status ");
		if(deliveryTracking.getDeliveryTrackingId() == 0 || deliveryTracking.getDelieveryStatus() == null)
		{	
			logger.warn("Delivery Tracking Id and Status is mandatory for updating ");
			throw new ValidationException("Delivery Tracking Id and Status is mandatory for updating");
		}
		
		if(deliveryTrackingService.findDeliveryTrackingById(deliveryTracking) == null)
		{	
			logger.warn("Delivery Tracking with this Id does not exist");
			throw new ValidationException("Delivery Tracking with this Id does not exist");
		}
		
		int flag = deliveryTrackingService.update(deliveryTracking);
		
		if(flag == 0)
		{	
			logger.info("Delivery status is already set to " + deliveryTracking.getDelieveryStatus());
			throw new ValidationException("Delivery status is already set to " + deliveryTracking.getDelieveryStatus());
		}
		
		return ResponseEntity.ok("Delivery status is updated sucessfully");

	}
	
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
