package com.capg.sbs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.sbs.entity.Product;
import com.capg.sbs.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	//getting all Employee record by using the method findaAll() of CrudRepository
	 	public List<Product> getAllProduct() {
	       	List<Product> product = new ArrayList<Product>();
	       	productRepository.findAll().forEach(p1 -> product.add(p1));
	       	return product;
	 	}
	 	
	 
		public List<Product> viewAllProduct(){
			return productRepository.findAllProductdata();
		}
	 	
	 	
	//saving a specific record by using the method save() of CrudRepository
	 	public void add(Product product) {
	 		productRepository.save(product);
	 	}
	 	
	 	public void deleteProduct(Product product)
	 	{	
	 		LocalDateTime productDeletedAt = LocalDateTime.now();
	 	    productRepository.setByProductNameAndProductModelAndProductBrand(product.getProductName(), product.getProductModel(), product.getProductBrand(),productDeletedAt);
	 	}
	 
	
	 	public Product findSpecificProduct(Product product)
	 	{
	 		
	 		return  productRepository.findByProductNameAndProductModelAndProductBrand(product.getProductName(), product.getProductModel(), product.getProductBrand());
			
	 	}
	 	
	 	//===================check working of update=======================
		public Product updateProductCountAndPrice(Product product) {
			
			 LocalDateTime now = LocalDateTime.now();
			   System.out.println((now));
			   
			int a=productRepository.setProductByProductNameAndProductBrandAndProductModel(product.getProductName(), product.getProductBrand(), product.getProductModel(),product.getProductPrice(),product.getProductCount(),now);
			if(a==1) {
				return productRepository.findByProductNameAndProductModelAndProductBrand(product.getProductName(), product.getProductModel(), product.getProductBrand());
			}
			else {
				return null;
			}
			//return null;
		}
		
	//===================module 2===================================
		
		
	//by product name	
		public List<Product> viewByProductName(String productName) {	
			
			return productRepository.findByProductNameContaining(productName.toLowerCase());	
		}

	//by product Brand name
		public List<Product> viewByProductBrandName(String productBrand) {
			return productRepository.findByProductBrandContaining(productBrand.toLowerCase());
		}
		
	//by product Model name
		public List<Product> viewByProductModelName(String productModel) {
			return  productRepository.findByProductModelContaining(productModel.toLowerCase());	
		}
		
		public Product getProductById(int productId)
		{
			return productRepository.findByProductId(productId);
		}

}
