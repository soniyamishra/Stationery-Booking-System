package com.capg.sbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.sbs.entity.DeliveryTracking;
import com.capg.sbs.entity.Review;

@Repository
public interface DeliveryTrackingRepository extends JpaRepository<DeliveryTracking, Integer>{
	
	@Query("select d from DeliveryTracking d where d.productBooking.bookingId= ?1")
	DeliveryTracking findByDeliveryBookingId(int bookingId);
	
	DeliveryTracking findByDeliveryTrackingId(int deliveryTrackingId);
	

}
