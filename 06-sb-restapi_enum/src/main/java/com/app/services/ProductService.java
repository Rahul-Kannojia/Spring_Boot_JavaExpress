package com.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.exception.ResourceNotFoundException;
import com.app.repositories.CategoryRepository;
import com.app.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public void createProduct(Product product) {
		log.info("ProductService :: createProduct {}", product.toString());
		product.setIsStock(true);
		product.setBarCode(UUID.randomUUID().toString());
		productRepository.save(product);
		log.info("Product saved Successfully in Database");
	}

	public Product fetchProductInfo(Long prodId) {
		log.info("ProductService :: fetchProductInfo {}", prodId);
		return productRepository.findById(prodId).orElseThrow(() -> new ResourceNotFoundException("Product Not Available"));
	}

	/*
	 * public void updateProduct(Long prodId, Product inputProduct) {
	 * log.info("ProductService :: updateProduct {} {} {}", prodId,
	 * inputProduct.getName(), inputProduct.getCategory()); Product product =
	 * fetchProductInfo(prodId); if (product.getCategory().getId() ==
	 * inputProduct.getCategory().getId()) {
	 * product.setName(inputProduct.getName());
	 * product.setPrice(inputProduct.getPrice());
	 * product.setDescription(inputProduct.getDescription());
	 * product.setQuantity(inputProduct.getQuantity());
	 * product.setBarCode(UUID.randomUUID().toString()); product.setIsStock(true);
	 * productRepository.save(product); } else {
	 * log.error("Category belong to given Product is not available"); throw new
	 * RuntimeException("Product does not belongs to given Category "); } }
	 */

	public void updateProduct(Long prodId, Product inputProduct) {
		log.info("ProductService :: updateProduct {} {} {}", prodId, inputProduct.getName(),
				inputProduct.getCategory());
		Long categoryId = inputProduct.getCategory().getId();
		if (categoryRepository.existsById(categoryId)) {
			// Category category = categoryService.fetchCategoryById(categoryId);
			if (productRepository.existsById(prodId)) {
				Product product = fetchProductInfo(prodId);
				product.setName(inputProduct.getName());
				product.setPrice(inputProduct.getPrice());
				product.setDescription(inputProduct.getDescription());
				product.setQuantity(inputProduct.getQuantity());
				product.setBarCode(UUID.randomUUID().toString());
				product.setIsStock(true);
				productRepository.save(product);
			} else {
				log.error("Product with given Id is not available in Database");
				throw new RuntimeException("Product Not Found in Database");
			}
		} else {
			log.error("Category with given Id is not available in Database");
			throw new ResourceNotFoundException("Category Not Found in Database");
		}

	}

	public void deleteProduct(Long prodId) {
		if (productRepository.existsById(prodId)) {
			log.info("ProductService :: fetchProductInfo {}", prodId);
			productRepository.deleteById(prodId);
			log.info("ProductService :: fetchProductInfo :: Product with given ID is deleted successfully");
		} else {
			log.error("ProductService :: fetchProductInfo :: Product is not available with this ID {}", prodId);
			throw new ResourceNotFoundException("Product with given ID is not available");
		}

	}

	//JPQL
	@Override
	public List<Product> fetchProductsUsingCategoryName(String categoryName) {
		log.info("ProductService :: fetchProductsUsingCategoryName {}", categoryName);
		return productRepository.fetchProductsByCategoryName(categoryName);
	}

	//DSL Method
	@Override
	public List<Product> fetchProductsWithCategoryName(String categoryName) {
		log.info("ProductService :: fetchProductsWithCategoryName {}", categoryName);
		return productRepository.findByCategoryName(categoryName);
	}

	//JPQL
	@Override
	public Product fetchProductUsingBarCode(String barCode) {
		log.info("ProductService :: fetchProductUsingBarCode {}", barCode);
		return productRepository.fetchProductByBarCode(barCode);
	}

	//DSL Method
	@Override
	public Product fetchProductWithBarCode(String barCode) {
		log.info("ProductService :: fetchProductWithBarCode {}", barCode);
		return productRepository.findByBarCode(barCode);
	}
}
