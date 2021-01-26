package com.capg.sbs.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.entity.Review;

public interface ProductBookingRepository extends JpaRepository<ProductBooking, Integer>
{
	ProductBooking findByBookingId(int bookingId);
	//Product findByproductId(int productId);
	
	@Transactional
	@Modifying
	@Query("UPDATE ProductBooking p set p.bookingCancelFlag='Y', p.bookingDeletedAt=?2  where p.bookingId=?1")
	public int deleteBooking(int bookingId, LocalDateTime bookingDeletedAt);
	
	@Query("select p from ProductBooking p where p.login.userId=?1 AND p.bookingCancelFlag='N'")
	List<ProductBooking> findByUserId(int userId);
	
	@Query("select p from ProductBooking p where p.bookingCancelFlag= 'N'")
	List<ProductBooking> findAllBooking();
	
	@Query("select p from ProductBooking p where p.product.productId= ?1")
	List<ProductBooking> findByProductId(int productId);
	

}
