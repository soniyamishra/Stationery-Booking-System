package com.capg.sbs.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.sbs.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
    
	Review findByReviewId(int reviewId);
	
//@Transactional
//@Modifying
//@Query("UPDATE Review p SET p.reviewComment=?3, p.ratingNumber= ?4 ,p.reviewUpdatedAt=?5 where p.productId= ?1 AND p.userId= ?2")
//int setReviewByProductIdAndUserId(Product product,Login login, String reviewComment, int ratingNumber ,LocalDateTime reviewUpdatedAt);

//@Query("UPDATE Review p SET p.reviewComment=?3, p.ratingNumber= ?4 , p.reviewUpdatedAt=?5 where p.productId= ?1 AND p.userId= ?2")
//int setReviewByProductIdAndUserId(int productId ,int userId, String reviewComment, int ratingNumber, LocalDateTime reviewUpdatedAt );

	
	@Query("select r from Review r where r.product.productId= ?1")
	List<Review> findByReviewProductId(int productId);
	
	@Transactional
	@Query(value = "SELECT AVG(e.rating_number) FROM review e WHERE e.product_id = ?1" , nativeQuery = true)
	int getAverage(int productId);

}