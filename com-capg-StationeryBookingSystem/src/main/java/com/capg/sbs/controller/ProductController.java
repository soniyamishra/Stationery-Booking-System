package com.capg.sbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capg.sbs.entity.Product;
import com.capg.sbs.service.ProductCountService;
import com.capg.sbs.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
    static Logger logger = Logger.getLogger(ProductController.class.getName());
    
	//creating a get mapping that retrieves all the Product detail from the database
     // @PreAuthorize("hasRole('ROLE_MANAGER')")
	  @GetMapping("/product")
	  private List<Product> getAllProductList() {
		  logger.info("List of Products");
		return productService.getAllProduct();
	  }
	  
	  @GetMapping("product/{productId}")
		private Product  getProductById(@PathVariable("productId") int productId) {
			return productService.getProductById(productId);
		}
	  
		@GetMapping("product/allproduct")
		private List<Product> viewAllProduct(){
			return productService.viewAllProduct();
		}
	  
	  
	
	  @PostMapping("/product") 
	  ResponseEntity<String> createProduct(@Valid @RequestBody Product product) throws ValidationException
	   {  
		  logger.info("Adding Product with name" + product.getProductName());
		  if(product.getProductCount() == 0)
		  {
			  throw new ValidationException("Product Count should be greater than or equal to 1");
		  }
		  productService.add(product);
		  logger.info("Product added sucessfully");
	      return ResponseEntity.ok("Product is added sucessfully"); 
	   }
	  
	  @PutMapping("/product/delete")
	  private ResponseEntity<String> deleteProduct(@RequestBody Product product) throws NullPointerException, ValidationException
	  {		
		  
	  if(product.getProductName() == null || product.getProductModel() == null || product.getProductBrand() == null)
		{	logger.warn("Product name or model or brand cannot be blank");
	  		throw new ValidationException("Product name and model and brand is mandatory to delete the product");
	  	}
	  if(product.getProductName().isBlank() || product.getProductModel().isBlank() || product.getProductBrand().isBlank())
		{	logger.warn("Product name or model or brand cannot be blank");
	  		throw new ValidationException("Product name and model and brand cannot be blank");
		}
	  if(productService.findSpecificProduct(product) == null)
	   {  logger.warn("Product name or model or brand cannot be blank");	
		  throw new ValidationException("Product does not exist");
	   }
	  
	   logger.info("Deleting Product");
	   productService.deleteProduct(product);
	   logger.info("Product is deleted sucessfully");
	  	return ResponseEntity.ok("Product has been sucessfully deleted");
	  }
	  
	//============================================================================
	  
	  @PutMapping("product/update")
		private Product updateProductCountAndPrice(@RequestBody Product product) throws ValidationException {
		  
		    logger.info("Updating Product Count and Price");
		    
		  
		    if(product.getProductName() == null || product.getProductModel() == null || product.getProductBrand() == null)
			{	logger.warn("Product name or model or brand cannot be blank");
		  		throw new ValidationException("Product name and model and brand is mandatory to update the product");
		  	}
		    
		    if(product.getProductCount() == 0)
			  {
				  throw new ValidationException("Product Count should be greater than or equal to 1");
			  }
		    if(product.getProductPrice() == 0.0)
		    {
		    	throw new ValidationException("Product Price should be greater than or equal to 1");
		    }
		  
			if(product.getProductName().isBlank()||product.getProductBrand().isBlank()||product.getProductModel().isBlank()) {
				
				logger.warn("Product name and Product Brad and Model Field should not be null");
				throw new ValidationException("Product name and Product Brad and Model Field should not be null");
			}
			
			if(productService.updateProductCountAndPrice(product) != null) {
				
				 logger.info("Product is sucessfylyy updated");
				return productService.updateProductCountAndPrice(product);
			}
			else {
				
				logger.warn("Product not found");
				throw new ValidationException("Product not found");
				
			}
			
		}
	
		@GetMapping("product/viewby/productname/{productName}")
		private List<Product> viewByProductName(@PathVariable("productName") String productName) throws ValidationException {
			
			logger.info("View Product By their Name");
			if(productName.isBlank()) {
				logger.warn("White space is not allowed");
				throw new ValidationException("White space is not allowed");
			}
			if(productService.viewByProductName(productName).isEmpty()) {
				logger.warn("No Details Found with this product name :"+productName);
				throw new ValidationException("No Details Found with this product name :"+productName);
			}
			return productService.viewByProductName(productName);	
		}
		
		
		@GetMapping("product/viewby/brandname/{productBrand}")
		private List<Product> viewByProductBrandName(@PathVariable("productBrand") String productBrand) throws ValidationException {
			
			logger.info("View Product By their Brand Name");
			if(productBrand.isBlank()) {
				logger.warn("White space is not allowed");
				throw new ValidationException("White space is not allowed");
			}
			if(productService.viewByProductBrandName(productBrand).isEmpty()) {
				
				logger.warn("No Details Found with this product name :"+productBrand);
				throw new ValidationException("No Details Found with this Brand Name :"+productBrand);
			}
			return productService.viewByProductBrandName(productBrand);
			
		}
		
		@GetMapping("product/viewby/modelname/{productModel}")
		private List<Product> viewByProductModelName(@PathVariable("productModel") String productModel) throws ValidationException {
			
			logger.info("View Product By their Model Name");
			if(productModel.isBlank()) {
				logger.warn("White space is not allowed");
				throw new ValidationException("White space is not allowed");
			}
			if(productService.viewByProductModelName(productModel).isEmpty()) {
				
				logger.warn("No Details Found with this product name :"+productModel);
				throw new ValidationException("No Details Found with this Model Name :"+productModel);
			}
			return productService.viewByProductModelName(productModel);//.orElseThrow(Exception::new);
			
		}

	 
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		    Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		    return errors;
		}
		
		
}
