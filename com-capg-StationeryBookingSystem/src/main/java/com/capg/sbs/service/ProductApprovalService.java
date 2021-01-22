package com.capg.sbs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.repository.ProductBookingRepository;

//@Service
public class ProductApprovalService {
	/*
	@Autowired
	ProductBookingRepository productingbookingrepository;
	
	public List<ProductBooking> getAllBookings() {
       	List<ProductBooking> productbooking = new ArrayList<ProductBooking>();
       	productingbookingrepository.findAll().forEach(productbooking1 -> productbooking.add(productbooking1));
       	return productbooking;
 	}

		public ProductBooking saveOrUpdate(ProductBooking pb) 
		{
			return productingbookingrepository.save(pb);
		}
	
		//Booking Conform 
		public ProductBooking Conformbooking(ProductBooking productapproval) 
		{ 
			LocalDateTime conformedAt =LocalDateTime.now(); 
			ProductBooking pb=productingbookingrepository.findByBookingId(productapproval.getBookingId());
			if(pb.getApprovalStatus().equals("CANCEL")) 
			{
				System.out.println("The Product Booking is Already CANCELED.");
				System.out.println("Approval Status: " +pb.getApprovalStatus()); 
			}
			else 
			{
				pb.setApprovalStatus("CONFORM"); 
				pb.setBookingConformedAt(conformedAt);
				productingbookingrepository.save(pb); 
				System.out.println("Approval Status: " +pb.getApprovalStatus()); 
			}
			return pb;
		}
		//Booking Cancel
		public ProductBooking Cancelbooking(ProductBooking productapproval)
		{
			LocalDateTime deletedAt = LocalDateTime.now();
			ProductBooking pb=productingbookingrepository.findByBookingId(productapproval.getBookingId());
			if(pb.getApprovalStatus().equals("CONFORM"))
			{
				System.out.println("The Product Booking is Already CONFORMED.");
				System.out.println("Approval Status: " +pb.getApprovalStatus());
			}
			else 
			{
				pb.setApprovalStatus("CANCEL");
				pb.setBookingCancelFlag("Y");
				pb.setBookingDeletedAt(deletedAt);
				productingbookingrepository.save(pb);
				System.out.println("Approval Stattus:" +pb.getApprovalStatus());
			}
			return pb;
		}
		*\

	/*
	//Booking Update
		public ProductBooking Updatebooking(ProductBooking productapproval , Product product) 
		{
			LocalDateTime updateAt = LocalDateTime.now();
			
			
			ProductBooking pb=productingbookingrepository.findByBookingId(productapproval.getBookingId());
			
			Product product1=productingbookingrepository.findByproductId(product.getProductId());
			
			if(pb.getApprovalStatus().equals("CANCEL") || pb.getApprovalStatus().equals("CONFORM"))
			{
					System.out.println("Approval Status is Already: " +pb.getApprovalStatus());
			}
			else 
			{
				int a =pb.getProductQuantity();
				int b=product1.getProductCount();
				System.out.println("Product_Quantity "+ a);
				System.out.println("Available Count "+ b);
				if(a<b)
				{
					pb.setApprovalStatus("PENDING");
					b=b-a;
					System.out.println("Available count become  : "+b);
					product1.setProductCount(b);
				
				}
				else if(a > b)
				{
					pb.setApprovalStatus("PENDING");
					System.out.println("Sorry .Your product Quantity " +a +"  is change to " + b);
					pb.setProductQuantity(b);
				
					a=b;
					System.out.println("Product Quantity" +b);
					b=b-a;
					System.out.println("Available count become  : "+b);
					product1.setProductCount(b);
				}
				pb.setBookingUpdatedAt(updateAt);
				productingbookingrepository.save(pb);
				System.out.println("Approval Status: " +pb.getApprovalStatus());

			}
			return pb;
		}
	 */
			
}