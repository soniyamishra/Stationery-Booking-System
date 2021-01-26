package com.capg.sbs.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="login")
public class Login2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	private String username;
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	
	//@JsonBackReference("login")
	//@OneToMany(mappedBy = "login", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	//private Set<ProductBooking> productBooking;
	
    //@JsonBackReference("review")
	//@OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	//private Set<Review> review;
    
    public Login2()
    {
    	
    }
    
	public Login2(int userId, String username, String password, String role, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/*public Set<Review> getReview() {
		return review;
	}
	public void setReview(Set<Review> review) {
		this.review = review;
	}
	public Set<ProductBooking> getProductBooking() {
		return productBooking;
	}
	public void setProductBooking(Set<ProductBooking> productBooking) {
		this.productBooking = productBooking;
	}*/
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
