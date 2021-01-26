package com.capg.sbs.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="review")
public class Review implements  Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reviewId;
	
	@NotBlank(message="Comment should not be blank ")
    private String reviewComment;
	
	
	@NotNull(message= "Rating number is mandatory")
	@Min(value=1, message="The minimum value of rating number should be one ")
	@Max(value=5, message="The Maximum value of rating number should be five")
	private int ratingNumber;
//	
//	@ManyToOne(fetch=FetchType.LAZY)  
//   private Product product;
	
	@CreationTimestamp
	private LocalDateTime reviewCreatedAt;
  
	private LocalDateTime reviewUpdatedAt;
	
    //@JsonBackReference("product")
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	//@JoinColumn(name = "product_id", nullable = false)
	//private Product product;
    
    @ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
    
    @ManyToOne
	@JoinColumn(name="user_id")
    private Login2 login;
	
    //@JsonBackReference("login")
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "user_id", nullable = false)
	//private Login login;
  
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	@OneToMany(cascade = { CascadeType.ALL})
//	private Product product;
//	
//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}
    
    public Review()
    {
    	
    }
    
   
	public Review(int reviewId, @NotBlank(message = "Comment should not be blank ") String reviewComment,
		@NotNull(message = "Rating number is mandatory") @Min(value = 1, message = "The minimum value of rating number should be one ") @Max(value = 5, message = "The Maximum value of rating number should be five") int ratingNumber,
		LocalDateTime reviewCreatedAt, LocalDateTime reviewUpdatedAt, Product product, Login2 login) {
	super();
	this.reviewId = reviewId;
	this.reviewComment = reviewComment;
	this.ratingNumber = ratingNumber;
	this.reviewCreatedAt = reviewCreatedAt;
	this.reviewUpdatedAt = reviewUpdatedAt;
	this.product = product;
	this.login = login;
    }
	
	public Review(int reviewId, @NotBlank(message = "Comment should not be blank ") String reviewComment,
			@NotNull(message = "Rating number is mandatory") @Min(value = 1, message = "The minimum value of rating number should be one ") @Max(value = 5, message = "The Maximum value of rating number should be five") int ratingNumber,
			LocalDateTime reviewCreatedAt, LocalDateTime reviewUpdatedAt) {
		super();
		this.reviewId = reviewId;
		this.reviewComment = reviewComment;
		this.ratingNumber = ratingNumber;
		this.reviewCreatedAt = reviewCreatedAt;
		this.reviewUpdatedAt = reviewUpdatedAt;
	}

	

	public Review(int reviewId, @NotBlank(message = "Comment should not be blank ") String reviewComment,
			@NotNull(message = "Rating number is mandatory") @Min(value = 1, message = "The minimum value of rating number should be one ") @Max(value = 5, message = "The Maximum value of rating number should be five") int ratingNumber) {
		super();
		this.reviewId = reviewId;
		this.reviewComment = reviewComment;
		this.ratingNumber = ratingNumber;
	}


	public Product getProduct() {
			return product;
		}


	public void setProduct(Product product) {
		this.product = product;
	}

    
	public Login2 getLogin() {
		return login;
	}

	
	public void setLogin(Login2 login) {
		this.login = login;
	}
	
	public LocalDateTime getReviewCreatedAt() {
		return reviewCreatedAt;
	}
	
	public void setReviewCreatedAt(LocalDateTime reviewCreatedAt) {
		this.reviewCreatedAt = reviewCreatedAt;
	}

	public LocalDateTime getReviewUpdatedAt() {
		return reviewUpdatedAt;
	}

	public void setReviewUpdatedAt(LocalDateTime reviewUpdatedAt) {
		this.reviewUpdatedAt = reviewUpdatedAt;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getRatingNumber() {
		return ratingNumber;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public void setRatingNumber(int ratingNumber) {
		this.ratingNumber = ratingNumber;
	}

	
	
	
}
