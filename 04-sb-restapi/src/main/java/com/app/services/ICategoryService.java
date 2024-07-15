package com.app.services;

import java.util.List;
import com.app.entities.Category;

public interface ICategoryService {

	public void createCategory(Category category);
	public Category fetchCategoryById(Long id);
	public List<Category> fetchAllCategory();
	public void updateCategory(Long id, Category categoryInput);
	public void deleteCategory(Long id);
	void deleteCategory_another(Long id);
}
