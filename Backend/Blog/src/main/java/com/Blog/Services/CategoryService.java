package com.Blog.Services;

import java.util.List;

import com.Blog.Model.Category;
import com.Blog.Payload.Request.CategoryRequest;

public interface CategoryService {
	CategoryRequest createCategory(CategoryRequest request);

	CategoryRequest updateCategory(CategoryRequest request, Long categoryId);

	Category getCategoryById(Long categoryId);

	List<Category> getAllCategories();

	void deleteCategory(Long categoryId);
}
