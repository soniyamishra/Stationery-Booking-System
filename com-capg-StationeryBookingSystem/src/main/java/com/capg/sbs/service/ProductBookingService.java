package com.capg.sbs.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.repository.ProductBookingRepository;
import com.capg.sbs.repository.ProductRepository;

@Service
public class ProductBookingService {
	
	@Autowired
	ProductBookingRepository productBookingRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public List<ProductBooking> getAllBooking()
	{
		List<ProductBooking> productBooking=new ArrayList<ProductBooking>();
		productBookingRepository.findAllBooking().forEach(productBooking1 -> productBooking.add(productBooking1));
		return productBooking;
	}
	
	
	public int addProduct(ProductBooking productBooking)
	{   
		Product product = productRepository.findByProductId(productBooking.getProduct().getProductId());
		if(product.getProductDeletedFlag().equalsIgnoreCase("Y"))
		{
			return 0;
		}
		else
		{	productBookingRepository.save(productBooking);
			return 1;
		}
		
	}

	  public int deleteProductBooking(ProductBooking productBooking) 
	  { 
		  LocalDateTime bookingDeletedAt= LocalDateTime.now();
	      int a=productBookingRepository.deleteBooking(productBooking.getBookingId(),bookingDeletedAt);
	      System.out.println(a); 
	  return a;
	  }
	  
	  public List<ProductBooking> getAllBookings() {
	       	List<ProductBooking> productbooking = new ArrayList<ProductBooking>();
	       	productBookingRepository.findAll().forEach(productbooking1 -> productbooking.add(productbooking1));
	       	return productbooking;
	 	}

	 public ProductBooking saveOrUpdate(ProductBooking pb) 
	 {
		return productBookingRepository.save(pb);
	 }

	 public ProductBooking findBookingById(int bookingId)
	 {
		  return productBookingRepository.findByBookingId(bookingId);
	 }
		
	//==========================Conform Booking===============================================================================================
	  	
	 
		public int Conformbooking(ProductBooking productapproval) 
		{ 
			LocalDateTime conformedAt =LocalDateTime.now(); 
			ProductBooking productbooking=productBookingRepository.findByBookingId(productapproval.getBookingId());
			Product product = productRepository.findByProductId(productbooking.getProduct().getProductId());
			int ProductQuantity  = productbooking.getProductQuantity();
			int ProductCount  = product.getProductCount();
			int flag =0;
			
			if(productbooking.getApprovalStatus().equals("CANCEL")) 
			{
				System.out.println("Approval Status: " +productbooking.getApprovalStatus());
				flag = 2;
			}
			else 
				if(ProductQuantity < ProductCount)
				{
				
					ProductCount=ProductCount-ProductQuantity;
					//System.out.println("Available count become  : "+ ProductCount);
					product.setProductCount(ProductCount);
					productbooking.setApprovalStatus("CONFORM");
					productbooking.setBookingConformedAt(conformedAt);
					productBookingRepository.save(productbooking);
					flag = 1;
				}
				
			
				else if(ProductQuantity > ProductCount)
				{
					
					System.out.println("ProductQuantity : " + ProductQuantity + "is" + "greater than Product Count : " + ProductCount ); 
					System.out.println("Need to UpdateBooking for Conform Booking");
					flag = 0;
				}
			return flag;
		}
		
	////==========================Cancel Booking===============================================================================================
	  	
		
		public int Cancelbooking(ProductBooking productapproval)
		{
			LocalDateTime deletedAt = LocalDateTime.now();
			ProductBooking productbooking=productBookingRepository.findByBookingId(productapproval.getBookingId());
			int flag =0;
			if(productbooking.getApprovalStatus().equals("CONFORM"))
			{
				//System.out.println("The Product Booking is Already CONFORMED.");
				System.out.println("Approval Status: " +productbooking.getApprovalStatus());
				flag = 0;
			}
			else 
			{
				productbooking.setApprovalStatus("CANCEL");
				//productbooking.setBookingCancelFlag("Y");
				productbooking.setBookingDeletedAt(deletedAt);
				productBookingRepository.save(productbooking);
				flag = 1;
			}
			return flag;
		}

	//==========================Update Booking===============================================================================================
	  	
		
		public int Updatebooking(ProductBooking productapproval) 
		{
			LocalDateTime updateAt = LocalDateTime.now();
			ProductBooking productbooking=productBookingRepository.findByBookingId(productapproval.getBookingId());
			Product product1 = productRepository.findByProductId(productbooking.getProduct().getProductId());
			int ProductQuantity  = productbooking.getProductQuantity();
			int ProductCount  = product1.getProductCount();
			int flag =0;
			
			if(productbooking.getApprovalStatus().equals("CANCEL") || productbooking.getApprovalStatus().equals("CONFORM"))
			{
					System.out.println("Approval Status is Already: " +productbooking.getApprovalStatus());
					flag=0;
			}
			 if(ProductQuantity > ProductCount)
			{
					productbooking.setApprovalStatus("CONFORM");
					
					ProductQuantity=ProductCount;
					productbooking.setProductQuantity(ProductCount);
					ProductCount=ProductCount-ProductQuantity;
					product1.setProductCount(ProductCount);
					productbooking.setBookingUpdatedAt(updateAt);
					productBookingRepository.save(productbooking);
					flag=1;
			}
				
			return flag;
		}
	 
}