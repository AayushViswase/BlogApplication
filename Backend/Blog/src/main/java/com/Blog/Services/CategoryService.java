package com.Blog.Services;

import java.util.List;

import com.Blog.Payload.Request.CategoryRequest;
import com.Blog.Payload.Response.CategoryResponse;

public interface CategoryService {
	CategoryResponse createCategory(CategoryRequest request);

	CategoryResponse updateCategory(CategoryRequest request, Long categoryId);

	CategoryResponse getCategoryById(Long categoryId);

	List<CategoryResponse> getAllCategories();

	void deleteCategory(Long categoryId);
}
