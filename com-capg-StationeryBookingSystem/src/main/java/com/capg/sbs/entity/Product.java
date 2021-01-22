package com.capg.sbs.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product")

public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productId;
	
	@NotBlank(message = "Product Name is mandatory")
	@Size(min = 2 , message ="Product Name should have atleast two character")
	@Pattern(regexp = "[a-zA-Z0-9_.]*",message="Product Name cannot contain special characters")
	private String productName;
	
	@NotBlank(message = "Product Model Name is mandatory")
	@Size(min = 2 , message ="Product Model Name should have atleast two character")
	@Pattern(regexp = "[a-zA-Z0-9_.]*",message="Product Name cannot contain special characters")
	private String productModel;
	
	@NotBlank(message = "Product Brand Name is mandatory")
	@Size(min = 2 , message ="Product Brand Name should have atleast two character")
	@Pattern(regexp = "[a-zA-Z0-9_.]*",message="Product Name cannot contain special characters")
	private String productBrand;
	
	@NotNull(message = "Product Price is mandatory")
	@DecimalMin(value = "1.0", inclusive = false,message="Product Price should be greater than or equal to 1")
	private Double productPrice;
	
	@NotNull(message = "Product Count is mandatory")
	private Integer productCount;
	
	@Pattern(regexp ="[YyNn]",message = "Flag should be either 'Y' or 'N'")
	private String productDeletedFlag ="N";
	
	@CreationTimestamp
	private LocalDateTime productCreatedAt;
    
    private LocalDateTime productUpdatedAt;
    
	private LocalDateTime productDeletedAt;
	
	//@JsonBackReference("productBooking")
	//@OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	//private Set<ProductBooking> productBooking;
	
	  
    @JsonBackReference("review")
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Review> review;
	
	
    public Product()
    {
    	
    }
    
    

	public Product(int productId,
			@NotBlank(message = "Product Name is mandatory") @Size(min = 2, message = "Product Name should have atleast two character") @Pattern(regexp = "[a-zA-Z0-9_.]*", message = "Product Name cannot contain special characters") String productName,
			@NotBlank(message = "Product Model Name is mandatory") @Size(min = 2, message = "Product Model Name should have atleast two character") @Pattern(regexp = "[a-zA-Z0-9_.]*", message = "Product Name cannot contain special characters") String productModel,
			@NotBlank(message = "Product Brand Name is mandatory") @Size(min = 2, message = "Product Brand Name should have atleast two character") @Pattern(regexp = "[a-zA-Z0-9_.]*", message = "Product Name cannot contain special characters") String productBrand,
			@NotNull(message = "Product Price is mandatory") @DecimalMin(value = "1.0", inclusive = false, message = "Product Price should be greater than or equal to 1") Double productPrice,
			@NotNull(message = "Product Count is mandatory") @Min(value = 1, message = "Product Count must be equal or greater than 1") Integer productCount,
			@Pattern(regexp = "[YyNn]", message = "Flag should be either 'Y' or 'N'") String productDeletedFlag,
			LocalDateTime productCreatedAt, LocalDateTime productUpdatedAt, LocalDateTime productDeletedAt) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productModel = productModel;
		this.productBrand = productBrand;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.productDeletedFlag = productDeletedFlag;
		this.productCreatedAt = productCreatedAt;
		this.productUpdatedAt = productUpdatedAt;
		this.productDeletedAt = productDeletedAt;
	}
	
	
	public Set<Review> getReview() {
		return review;
	}

	public void setReview(Set<Review> review) {
		this.review = review;
	}

	/*public Set<ProductBooking> getProductBooking() {
		return productBooking;
	}

	public void setProductBooking(Set<ProductBooking> productBooking) {
		this.productBooking = productBooking;
	}*/

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductBrand() {
		return productBrand;
	}


	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public int getProductCount() {
		return productCount;
	}


	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}


	public String getProductDeletedFlag() {
		return productDeletedFlag;
	}


	public void setProductDeletedFlag(String productDeletedFlag) {
		this.productDeletedFlag = productDeletedFlag;
	}


	public LocalDateTime getProductCreatedAt() {
		return productCreatedAt;
	}


	public void setProductCreatedAt(LocalDateTime productCreatedAt) {
		this.productCreatedAt = productCreatedAt;
	}


	public LocalDateTime getProductUpdatedAt() {
		return productUpdatedAt;
	}


	public void setProductUpdatedAt(LocalDateTime productUpdatedAt) {
		this.productUpdatedAt = productUpdatedAt;
	}


	public LocalDateTime getProductDeletedAt() {
		return productDeletedAt;
	}


	public void setProductDeletedAt(LocalDateTime productDeletedAt) {
		this.productDeletedAt = productDeletedAt;
	}

}
