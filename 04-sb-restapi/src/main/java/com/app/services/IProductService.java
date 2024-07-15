package com.app.services;

import java.util.List;

import com.app.entities.Product;

public interface IProductService {

	public void createProduct(Product product);

	public Product fetchProductInfo(Long prodId);

	public void updateProduct(Long prodId, Product inputProduct);

	public void deleteProduct(Long prodId);

	// DSL methods
	List<Product> fetchProductsUsingCategoryName(String categoryName);
	
	List<Product> fetchProductsWithCategoryName(String categoryName);
	
	Product fetchProductUsingBarCode(String barCode);
	
	Product fetchProductWithBarCode(String barCode);
}
