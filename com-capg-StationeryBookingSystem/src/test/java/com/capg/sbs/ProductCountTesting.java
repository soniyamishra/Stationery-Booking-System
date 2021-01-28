package com.capg.sbs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.capg.sbs.entity.Product;
import com.capg.sbs.repository.ProductRepository;

@SpringBootTest
public class ProductCountTesting {
	
	@Autowired
	ProductRepository repo;
		
	@Test
	public void testViewByProductName() {
		String url1="http://localhost:8080/product/viewby/productname/pen";
		RestTemplate restTemplate = new RestTemplate();

		Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);

		repo.save(product);
		Product[] products= restTemplate.getForObject(url1, Product[].class);
		Assertions.assertThat(products).extracting(Product::getProductName).contains("pen");
				
	}
	
	@Test
	public void testForViewByBrandName() {
		String url1="http://localhost:8080/product/viewby/brandname/doms";
		RestTemplate restTemplate = new RestTemplate();

		Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);

		repo.save(product);
		Product[] products= restTemplate.getForObject(url1, Product[].class);
		Assertions.assertThat(products).extracting(Product::getProductBrand).contains("doms");
				
	}
	
	@Test
	public void testForViewByModelName() {
		String url1="http://localhost:8080/product/viewby/modelname/dark";
		RestTemplate restTemplate = new RestTemplate();

		Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);

		repo.save(product);
		Product[] products= restTemplate.getForObject(url1, Product[].class);
		Assertions.assertThat(products).extracting(Product::getProductModel).contains("dark");
	}
	
	@Test
	public void testForProductUpdate() {
		String url1="http://localhost:8080/product";
		RestTemplate restTemplate = new RestTemplate();

		Product product = new Product(1,"pen","dark","doms",20.1,50,"N",null,null,null);

		repo.save(product);
		
		LocalDateTime now = LocalDateTime.now();

		repo.setProductByProductNameAndProductBrandAndProductModel("pen", "doms", "dark", 15.1,100, now);

		Product[] products= restTemplate.getForObject(url1, Product[].class);
		Assertions.assertThat(products).extracting(Product::getProductCount).contains(100);
	}
	
	@Test
	public void testErrorHandlingForProductName() {
		RestTemplate restTemplate= new RestTemplate();
		
		String url="http://localhost:8080/product/viewby/productname/pen,";
		try {
			restTemplate.getForEntity(url, String.class);
		}
		catch(HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}
		
	}

	@Test
	public void testErrorHandlingforBrandName() {
		RestTemplate restTemplate= new RestTemplate();
		
		String url="http://localhost:8080/product/viewby/brandname/doma";
		try {
			restTemplate.getForEntity(url, String.class);
		}
		catch(HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}}
	@Test
	public void testErrorHandlingforModelName() {
		RestTemplate restTemplate= new RestTemplate();

		String url="http://localhost:8080/product/viewby/modelname/dasrk";
		try {
			restTemplate.getForEntity(url, String.class);
		}
		catch(HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}
	}
	
	@Test
	public void testErrorHandlingforUpdateProductCountAndPrice() {
		RestTemplate restTemplate= new RestTemplate();

		String url="http://localhost:8080/product/update";
		try {
			restTemplate.getForEntity(url, String.class);
		}
		catch(HttpClientErrorException e) {
			assertEquals(HttpStatus.METHOD_NOT_ALLOWED, e.getStatusCode());
		}
	}
	
	
}
