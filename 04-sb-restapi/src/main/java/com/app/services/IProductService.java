package com.app.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.app.entities.Product;
import com.app.responses.ProductBoResponse;

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
	
	ProductBoResponse fetchProducts(PageRequest pageRequest);
}
