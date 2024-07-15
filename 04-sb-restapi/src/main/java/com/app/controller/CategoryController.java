package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Category;
import com.app.services.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createCategory(@RequestBody Category category) {
		log.info("CategoryController :: createCategory {}", category.getName());
		categoryService.createCategory(category);
	}

	@GetMapping("{categoryId}")
	public Category getCategoryById(@PathVariable Long categoryId) {
		log.info("CategoryController :: getCategoryById {}", categoryId);
		return categoryService.fetchCategoryById(categoryId);
	}

	@GetMapping
	public List<Category> getAllCategory() {
		log.info("CategoryController :: getAllCategory");
		return categoryService.fetchAllCategory();
	}

	@PutMapping("{categoryId}")
	public void updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		log.info("CategoryController :: updateCategory {} {}", categoryId, category.getName());
		categoryService.updateCategory(categoryId, category);
	}

	@DeleteMapping("{categoryId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable Long categoryId) {
		log.info("CategoryController :: deleteCategory {}", categoryId);
		categoryService.deleteCategory(categoryId);
	}
}
