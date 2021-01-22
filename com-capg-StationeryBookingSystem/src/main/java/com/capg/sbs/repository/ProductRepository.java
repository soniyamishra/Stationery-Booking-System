package com.capg.sbs.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.sbs.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	
	public Product findByProductNameAndProductModelAndProductBrand(String productName,String productModel,String productBrand);
	
	@Transactional
	@Modifying
	@Query("UPDATE Product p set p.productDeletedFlag='Y', p.productDeletedAt =?4 where p.productName= ?1 AND p.productBrand= ?3 AND p.productModel= ?2")
	public int setByProductNameAndProductModelAndProductBrand(String productName,String productModel,String productBrand, LocalDateTime productDeletedAt);
	
	public Product findByProductId(int productId);
	
	//
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.productCount=  ?5, p.productPrice = ?4, p.productUpdatedAt= ?6 where p.productName= ?1 "
		+ "AND p.productBrand= ?2 AND p.productModel= ?3 AND p.productDeletedFlag= 'N'")
	int setProductByProductNameAndProductBrandAndProductModel(String productName,String productBrand,String ProductModel,double productPrice,int productCount, LocalDateTime now);
	
	@Query("select d from Product d where d.productName LIKE %?1% AND d.productDeletedFlag= 'N'")
	List<Product> findByProductNameContaining(String productName);
	
	@Query("select d from Product d where d.productBrand LIKE %?1% AND d.productDeletedFlag= 'N'")
	List<Product> findByProductBrandContaining(String productBrand);
	
	@Query("select d from Product d where d.productModel LIKE %?1% AND d.productDeletedFlag= 'N'")
	List<Product> findByProductModelContaining(String productModel);
	
	@Query("select d from Product d where d.productDeletedFlag= 'N'")
	List<Product> findAllProductdata();
	}
	

	

 