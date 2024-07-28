package com.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Product;
import com.app.responses.ProductBoResponse;
import com.app.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createProduct(@RequestBody Product product) {
		log.info("ProductController ::createProduct {} ", product.getName());
		productService.createProduct(product);
	}

	@GetMapping("{pid}")
	public Product fetchProduct(@PathVariable(name = "pid") Long prodId) {
		log.info("ProductController ::fetchProduct {} ", prodId);
		return productService.fetchProductInfo(prodId);
	}

	@PutMapping("{pid}")
	public void updateProduct(@PathVariable(name = "pid") Long prodId, @RequestBody Product inputProduct) {
		log.info("ProductController ::updateProduct {} {} {} ", prodId, inputProduct.getCategory(),
				inputProduct.getName());
		productService.updateProduct(prodId, inputProduct);
	}

	@DeleteMapping("{pid}")
	public void deleteProduct(@PathVariable(name = "pid") Long prodId) {
		log.info("ProductController ::deleteProduct {} ", prodId);
		productService.deleteProduct(prodId);
	}

	@GetMapping("/fetch/{categoryName}")
	public List<Product> fetchProductsUsingCategoryName(@PathVariable String categoryName) {
		log.info("ProductController ::fetchProductsUsingCategoryName {} ", categoryName);
		return productService.fetchProductsUsingCategoryName(categoryName);
	}
	
	@GetMapping("/get/{categoryName}")
	public List<Product> fetchProductsWithCategoryName(@PathVariable String categoryName) {
		log.info("ProductController ::fetchProductsWithCategoryName {} ", categoryName);
		return productService.fetchProductsWithCategoryName(categoryName);
	}
	
	@GetMapping("/fetch/barcode/{barCode}")
	public Product fetchProductUsingBarCode(@PathVariable String barCode) {
		log.info("ProductController ::fetchProductUsingBarCode {} ", barCode);
		return productService.fetchProductUsingBarCode(barCode);
	}
	
	@GetMapping("/get/barcode/{barCode}")
	public Product fetchProductWithBarCode(@PathVariable String barCode) {
		log.info("ProductController ::fetchProductWithBarCode {} ", barCode);
		return productService.fetchProductWithBarCode(barCode);
	}
	
	/**
	 * Pagination
	 * */
	@GetMapping("/pagination")
	public ProductBoResponse fetchProducts(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "DESC") String sortBy) {
		log.info("ProductController ::fetchProducts :: Pagination " );
		if(sortBy.equals("ASC")) {
			return productService.fetchProducts(PageRequest.of(pageNumber, pageSize, Direction.ASC,"price"));
		}else {
			return productService.fetchProducts(PageRequest.of(pageNumber, pageSize, Direction.DESC,"price"));
		}
	}
	
	/**
	 * Pagination:
	 * public static PageRequest of(int pageNumber, int pageSize, Direction direction, String... properties)
	 * */
//	@GetMapping("/pagination")
//	public ProductBoResponse fetchProducts(@RequestParam(defaultValue = "0") Integer pageNumber,
//			@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "DESC") String sortBy,
//			@RequestParam(defaultValue = "price") String[] stringArray) {
//		log.info("ProductController ::fetchProducts :: Pagination ");
//		List<String> sortingList = List.of("price","name");
//		stringArray = sortingList.stream().toArray(String[]::new);
//		
//		if (sortBy.equals("ASC")) {
//			return productService.fetchProducts(
//					PageRequest.of(pageNumber, pageSize, Direction.ASC, stringArray));
//		} else {
//			return productService.fetchProducts(
//					PageRequest.of(pageNumber, pageSize, Direction.DESC, stringArray));
//		}
//	}
}
