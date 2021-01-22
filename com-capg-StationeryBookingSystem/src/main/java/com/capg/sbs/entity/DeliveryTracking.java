package com.capg.sbs.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="delivery_tracking")
public class DeliveryTracking implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int deliveryTrackingId;
	
	@NotBlank(message = "DelieveryStatus is mandatory")
	//@Pattern(regexp = "[a-zA-Z_.]*",message="Delivery status cannot contain special characters or numbers")
	private String deliveryStatus;
	
	// foreign key from productBooking tablee
    //@JsonBackReference("productBooking")
	//@OneToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "booking_id", nullable = false)
    
	@OneToOne
	@JoinColumn(name="booking_id")
	private ProductBooking productBooking;
  
	private LocalDateTime deliveryStatusUpdatedAt;
	
	public DeliveryTracking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public DeliveryTracking(
			@NotBlank(message = "DelieveryStatus is mandatory") String deliveryStatus, ProductBooking productBooking,
			LocalDateTime deliveryStatusUpdatedAt) {
		super();
		this.deliveryStatus = deliveryStatus;
		this.productBooking = productBooking;
		this.deliveryStatusUpdatedAt = deliveryStatusUpdatedAt;
	}

	

	public DeliveryTracking(int deliveryTrackingId,
			@NotBlank(message = "DelieveryStatus is mandatory") String deliveryStatus,
			LocalDateTime deliveryStatusUpdatedAt) {
		super();
		this.deliveryTrackingId = deliveryTrackingId;
		this.deliveryStatus = deliveryStatus;
		this.deliveryStatusUpdatedAt = deliveryStatusUpdatedAt;
	}



	public ProductBooking getProductBooking() {
		return productBooking;
	}

	public void setProductBooking(ProductBooking productBooking) {
		this.productBooking = productBooking;
	}

	public LocalDateTime getDeliveryStatusUpdatedAt() {
		return deliveryStatusUpdatedAt;
	}

	public void setDeliveryStatusUpdatedAt(LocalDateTime deliveryStatusUpdatedAt) {
		this.deliveryStatusUpdatedAt = deliveryStatusUpdatedAt;
	}

	public int getDeliveryTrackingId() {
		return deliveryTrackingId;
	}

	public void setDeliveryTrackingId(int deliveryTrackingId) {
		this.deliveryTrackingId = deliveryTrackingId;
	}

	public String getDelieveryStatus() {
		return deliveryStatus;
	}

	public void setDelieveryStatus(String delieveryStatus) {
		this.deliveryStatus = delieveryStatus;
	}


}