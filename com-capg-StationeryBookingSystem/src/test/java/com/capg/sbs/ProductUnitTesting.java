package com.capg.sbs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.capg.sbs.controller.ProductBookingController;
import com.capg.sbs.controller.ProductController;
import com.capg.sbs.entity.Product;
import com.capg.sbs.entity.ProductBooking;
import com.capg.sbs.repository.ProductBookingRepository;
import com.capg.sbs.repository.ProductRepository;
import com.capg.sbs.service.ProductBookingService;
import com.capg.sbs.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductUnitTesting {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	ProductRepository repo;
	

	@Test
	void contextLoads() {
		Assert.notNull(productService);
	}
	
	@Test
	public void test() throws Exception
	{ 
		 assertThat(productController).isNotNull();
	}
	
	@Test
	public void testAddDeleteProduct()
	{	
		RestTemplate restTemplate = new RestTemplate();
		String urlAddProduct = "http://localhost:8080/product";
		Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);
		repo.save(product);
		Product[] products = restTemplate.getForObject(urlAddProduct,Product[].class);
		Assertions.assertThat(products).extracting(Product :: getProductName).contains("pen");
		
		repo.setByProductNameAndProductModelAndProductBrand("pen","dark","doms", null);
		Assertions.assertThat(products).extracting(Product :: getProductDeletedFlag).contains("Y");
		
		
	}
	
	@Test
	public void testErrorHandlingForAddProduct()
	{
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:2211/product";
		try {
			restTemplate.getForEntity(url,String.class);
		}
		catch(HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}
		
	
	}
	
	@Test
	public void testErrorHandlingForDeleteProduct()
	{
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/product/delete";
		try {
			restTemplate.getForEntity(url,String.class);
		}
		catch(HttpClientErrorException e) {
			assertEquals(HttpStatus.METHOD_NOT_ALLOWED, e.getStatusCode());
		}
	
	}
}
