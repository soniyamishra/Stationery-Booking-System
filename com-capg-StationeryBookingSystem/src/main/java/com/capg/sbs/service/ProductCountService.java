package com.capg.sbs.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.sbs.entity.Product;
import com.capg.sbs.repository.ProductRepository;

//@Service
public class ProductCountService {
/*
	@Autowired
	ProductRepository productRepository;
//update model 1
//to update the value	

//===================check working of update=======================
		public int updates(Product product) {
			
			 LocalDateTime now = LocalDateTime.now();
			   System.out.println((now));
			   
			return productRepository.setProductByProductNameAndProductBrandAndProductModel(product.getProductName(), product.getProductBrand(), product.getProductModel(),product.getProductPrice(),product.getProductCount(),now);
			//return null;
		}
		
//===================module 2===================================
	
		//by product name	
		public List<Product> viewByProductName(String productName) {	
			
			return productRepository.findByProductName(productName);	
		}
		
		//by product Brand name
		public List<Product> viewByProductBrandName(String productBrand) {
			return productRepository.findByProductBrand(productBrand);
		}
		
		//by product Model name
		public List<Product> viewByProductModelName(String productModel) {
			return productRepository.findByProductModel(productModel);	
		}
	*/
}