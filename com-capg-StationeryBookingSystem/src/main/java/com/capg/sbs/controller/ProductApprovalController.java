package com.capg.sbs.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import com.capg.sbs.service.ProductApprovalService;


//@RestController
//@RequestMapping("productapproval")
public class ProductApprovalController 
{
		
		//@Autowired
	   // private ProductApprovalService approvalService;
		/*
		@GetMapping("/ProductBooking")
		private List<ProductBooking> getAllBookings() {
			return approvalService.getAllBookings();
		}
		
		@PostMapping("/ProductBooking")
		private ResponseEntity<String> settdata(@RequestBody ProductBooking productapproval)
		{
			approvalService.saveOrUpdate(productapproval); 
			return ResponseEntity.ok("Booking is Successfully!");

		}
		*/
		
		/*
		//conformBooking
		@PutMapping("/ConformBooking") 
		private ResponseEntity<String> Conformbooking(@RequestBody  ProductBooking productapproval)
		{ 
			 approvalService.Conformbooking(productapproval);
			 return ResponseEntity.ok("Booking Conform  Successfully!");
		}
		
		//cancelBooking
		@PutMapping("/CancelBooking") 
		private ResponseEntity<String> Cancelbooking(@RequestBody ProductBooking productapproval) 
		{
			approvalService.Cancelbooking(productapproval); 
			 return ResponseEntity.ok("Booking Is Cancelled");
			
		}
		
		*/
		
		//updateBooking
	/*	@PutMapping("/UpdateBooking") 
		private ProductBooking Updatebooking(@RequestBody ProductBooking productapproval ,Product product) 
		{
			return approvalService.Updatebooking(productapproval, product); 
		}
	*/
		
		/*
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
		*/
		
	}