package com.Blog.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Exceptions.ResourceNotFoundException;
import com.Blog.Model.Category;
import com.Blog.Payload.Request.CategoryRequest;
import com.Blog.Payload.Response.CategoryResponse;
import com.Blog.Repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public CategoryResponse createCategory(CategoryRequest request) {
		Category category = this.mapper.map(request, Category.class);
		Category saveCategory = this.categoryRepository.save(category);
		return this.mapper.map(saveCategory, CategoryResponse.class);
	}

	@Override
	public CategoryResponse updateCategory(CategoryRequest request, Long categoryId) {
		Category existingCategory = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Categoty", "Id", categoryId));
		existingCategory.setCategoryTitle(request.getCategoryTitle());
		existingCategory.setCategoryDescription(request.getCategoryDescription());
		Category updatedCategory = this.categoryRepository.save(existingCategory);
		return this.mapper.map(updatedCategory, CategoryResponse.class);
	}

	@Override
	public CategoryResponse getCategoryById(Long categoryId) {
		Category existingCategory = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", categoryId));
		return this.mapper.map(existingCategory, CategoryResponse.class);
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryResponse> categoryList = categories.stream()
				.map(category -> this.mapper.map(category, CategoryResponse.class))
				.collect(Collectors.toList());
		return categoryList;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		this.categoryRepository.delete(category);
	}

}
