package com.capg.sbs.controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.sbs.entity.Product;

import com.capg.sbs.service.ProductCountService;


//@RestController
public class ProductCountController {
	
	/*
	@Autowired
	ProductCountService productCountService;

//============================================================================
	@PutMapping("product/update")
	private Product update(@RequestBody Product product) throws ValidationException {
		if(productCountService.updates(product) == 1) {
		return product;
		}
		else {
			throw new ValidationException("Product not found");
		}
		
	}
	
//============================================================================	
	
	@GetMapping("product/viewby/productname/{productName}")
	private List<Product> viewByProductName(@PathVariable("productName") String productName) throws ValidationException {
		System.out.println(productName.length());
		if(productName.isBlank()) {
			throw new ValidationException("White space is not allowed");
		}
		if(productCountService.viewByProductName(productName).isEmpty()) {
			throw new ValidationException("No Details Found with this product name :"+productName);
		}
		return productCountService.viewByProductName(productName);	
	}
	
	
	@GetMapping("product/viewby/brandname/{productBrand}")
	private List<Product> viewByProductBrandName(@PathVariable("productBrand") String productBrand) throws ValidationException {
		if(productBrand.isBlank()) {
			throw new ValidationException("White space is not allowed");
		}
		if(productCountService.viewByProductBrandName(productBrand).isEmpty()) {
			throw new ValidationException("No Details Found with this Brand Name :"+productBrand);
		}
		return productCountService.viewByProductBrandName(productBrand);
		
	}
	
	@GetMapping("product/viewby/modelName/{productModel}")
	private List<Product> viewByProductModelName(@PathVariable("productModel") String productModel) throws ValidationException {
		if(productModel.isBlank()) {
			throw new ValidationException("White space is not allowed");
		}
		if(productCountService.viewByProductModelName(productModel).isEmpty()) {
			throw new ValidationException("No Details Found with this Model Name :"+productModel);
		}
		return productCountService.viewByProductModelName(productModel);//.orElseThrow(Exception::new);
		
	}

*/
 
}