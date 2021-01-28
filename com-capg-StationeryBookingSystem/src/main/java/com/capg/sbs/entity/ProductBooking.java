package com.capg.sbs.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product_booking")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductBooking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;

	@NotNull(message = "ProductQuantity is Mandatory")
	@Min(value = 1, message = "Product Quantity must be equal or greater than 1")
	private int productQuantity;

	@NotBlank(message = "Address is Mandatory")
	private String address;

	@NotBlank(message = "City is Mandatory")
	@Pattern(regexp = "[a-zA-Z_.]*", message = "City cannot contain special characters")
	private String city;

	@NotBlank(message = "State is Mandatory")
	@Pattern(regexp = "[a-zA-Z_.]*", message = "State cannot contain special characters")
	private String state;

	@NotNull(message = "Zipcode is Mandatory")
	private int zipcode;

	private String approvalStatus = "PENDING";
	private String bookingCancelFlag = "N";
	private LocalDateTime bookingDeletedAt;

	@CreationTimestamp
	private LocalDateTime bookingConformedAt;
	private LocalDateTime bookingUpdatedAt;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	 
    //@JsonBackReference("product")
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	//@JoinColumn(name = "product_id", nullable = false)
	//private Product product;
	
	@ManyToOne
	@JoinColumn(name="id")
	private User user;
	
	
	
    //@JsonBackReference("login")
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "user_id", nullable = false)
	//private Login login;
    
    //@JsonBackReference("deliveryTracking")
   	//@OneToOne(mappedBy = "productBooking", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   	//private DeliveryTracking deliveryTracking;
   	
	
    
    
	
	
	public ProductBooking()
	{
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ProductBooking(int bookingId) {
		super();
		this.bookingId = bookingId;
	}

	public ProductBooking(int bookingId,
			@NotNull(message = "ProductQuantity is Mandatory") @Min(value = 1, message = "Product Quantity must be equal or greater than 1") int productQuantity,
			@NotBlank(message = "Address is Mandatory") String address,
			@NotBlank(message = "City is Mandatory") @Pattern(regexp = "[a-zA-Z_.]*", message = "City cannot contain special characters") String city,
			@NotBlank(message = "State is Mandatory") @Pattern(regexp = "[a-zA-Z_.]*", message = "State cannot contain special characters") String state,
			@NotNull(message = "Zipcode is Mandatory") int zipcode, String approvalStatus, String bookingCancelFlag,
			LocalDateTime bookingDeletedAt, LocalDateTime bookingConformedAt, LocalDateTime bookingUpdatedAt,
			Product product, User user) {
		super();
		this.bookingId = bookingId;
		this.productQuantity = productQuantity;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.approvalStatus = approvalStatus;
		this.bookingCancelFlag = bookingCancelFlag;
		this.bookingDeletedAt = bookingDeletedAt;
		this.bookingConformedAt = bookingConformedAt;
		this.bookingUpdatedAt = bookingUpdatedAt;
		this.product = product;
		this.user = user;
	}


	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getBookingCancelFlag() {
		return bookingCancelFlag;
	}

	public void setBookingCancelFlag(String bookingCancelFlag) {
		this.bookingCancelFlag = bookingCancelFlag;
	}

	public LocalDateTime getBookingDeletedAt() {
		return bookingDeletedAt;
	}

	public void setBookingDeletedAt(LocalDateTime bookingDeletedAt) {
		this.bookingDeletedAt = bookingDeletedAt;
	}

	public LocalDateTime getBookingConformedAt() {
		return bookingConformedAt;
	}

	public void setBookingConformedAt(LocalDateTime bookingConformedAt) {
		this.bookingConformedAt = bookingConformedAt;
	}

	public LocalDateTime getBookingUpdatedAt() {
		return bookingUpdatedAt;
	}

	public void setBookingUpdatedAt(LocalDateTime bookingUpdatedAt) {
		this.bookingUpdatedAt = bookingUpdatedAt;
	}

}
