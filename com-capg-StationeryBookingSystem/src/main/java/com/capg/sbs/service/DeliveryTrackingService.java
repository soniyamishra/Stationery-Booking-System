package com.capg.sbs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capg.sbs.entity.DeliveryTracking;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.repository.DeliveryTrackingRepository;
import com.capg.sbs.repository.ProductBookingRepository;
@Service
public class DeliveryTrackingService {
	
	@Autowired
	DeliveryTrackingRepository deliveryTrackingRepository;
	
	@Autowired
	ProductBookingRepository productBookingRepository;
	
	public List<DeliveryTracking> getAllStatus() {
       	List<DeliveryTracking> deliveryTracking = new ArrayList<DeliveryTracking>();
       	deliveryTrackingRepository.findAll().forEach(deliveryTracking1 -> deliveryTracking.add(deliveryTracking1));
       	return deliveryTracking;
	}
	
	//getting a specific record by using the method findByBookingId() of CrudRepository
	
 	public DeliveryTracking getStatusByBookingId(int bookingId) {
       	return deliveryTrackingRepository.findByDeliveryBookingId(bookingId);
 	}
 	
	public int add(DeliveryTracking deliveryTracking) {
		
		ProductBooking booking = productBookingRepository.findByBookingId(deliveryTracking.getProductBooking().getBookingId());
		if(booking.getApprovalStatus().equals("CONFORM"))
			{
				deliveryTrackingRepository.save(deliveryTracking);
				return 1;
			}
		else
			{
				return 0;
			}

 	}
	
	public int update(DeliveryTracking deliveryTracking)
	{   
		LocalDateTime deliveryStatusUpdatedAt = LocalDateTime.now();
		DeliveryTracking deliveryTrackingUpdate = deliveryTrackingRepository.findByDeliveryTrackingId(deliveryTracking.getDeliveryTrackingId());
		String status = deliveryTrackingUpdate.getDelieveryStatus();
		System.out.println(status);
		String status2 =deliveryTracking.getDelieveryStatus();
		
		int flag =0;
		
		if(!(status.equals(status2)) || !(status.equals(status2)) || !(status.equals(status2)) || !(status.equals(status2)))
		{
			flag = 1;
			deliveryTrackingUpdate.setDelieveryStatus(deliveryTracking.getDelieveryStatus());
			deliveryTrackingUpdate.setDeliveryStatusUpdatedAt(deliveryStatusUpdatedAt);
			deliveryTrackingRepository.save(deliveryTrackingUpdate);
		}
		
		else
		{	
			flag = 0;
		}
		
		return flag;
	}
	
	public DeliveryTracking findDeliveryTrackingById(DeliveryTracking deliveryTracking)
	{
		return deliveryTrackingRepository.findByDeliveryTrackingId(deliveryTracking.getDeliveryTrackingId());
	}

}