package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * Native SQL is Database dependent like: MySQL, Oracle etc.
	 * 
	 * Native SQL: @Query(value="select p.* from product p INNER JOIN category c ON
	 * p.category_id=c.id WHERE c.name=:categoryName", nativeQuery=true)
	 * 
	 * JPQL: Database Independent JPQL: @Query("select p from Product p INNER JOIN
	 * p.category c WHERE c.name=:categoryName")
	 */

	/**
	 * Requirement: Fetch Product using CategoryName
	 */
	//JPQL
	@Query("select p from Product p INNER JOIN p.category c WHERE c.name=:categoryName")
	List<Product> fetchProductsByCategoryName(String categoryName);

	//DSL Method(Product->Category have name, so that "CategoryName")
	List<Product> findByCategoryName(String categoryName);

	/**
	 * Requirement: Fetch Product Using BarCode
	 */
	// DSL Method(barCode variable is of Product class)  
	Product findByBarCode(String barCode);
	
	//Native SQL
	@Query(value="select * from product p WHERE p.bar_code=:barCode",nativeQuery=true)
	Product fetchProductByBarCode(String barCode);
}
