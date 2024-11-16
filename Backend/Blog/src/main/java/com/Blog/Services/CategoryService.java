package com.Blog.Services;

import com.Blog.Payload.Request.CategoryRequest;
import com.Blog.Payload.Response.CategoryPageResponse;
import com.Blog.Payload.Response.CategoryResponse;

public interface CategoryService {
	CategoryResponse createCategory(CategoryRequest request);

	CategoryResponse updateCategory(CategoryRequest request, Long categoryId);

	CategoryResponse getCategoryById(Long categoryId);

	CategoryPageResponse getAllCategories(Integer pageNumber, Integer pageSize);

	void deleteCategory(Long categoryId);
}
