package com.Blog.Controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Payload.Request.CategoryRequest;
import com.Blog.Payload.Response.CategoryPageResponse;
import com.Blog.Payload.Response.CategoryResponse;
import com.Blog.Services.CategoryService;

@RestController
@RequestMapping("api/category/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
		CategoryResponse response = this.categoryService.createCategory(categoryRequest);
		return new ResponseEntity<CategoryResponse>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryResponse> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest,
			@PathVariable Long categoryId) throws NotFoundException {
		CategoryResponse category = this.categoryService.updateCategory(categoryRequest, categoryId);
		return ResponseEntity.ok(category);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long categoryId) throws NotFoundException {
		CategoryResponse category = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryResponse>(category, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<CategoryPageResponse> getAllCategories(
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		CategoryPageResponse categories = this.categoryService.getAllCategories(pageNumber, pageSize);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long categoryId) throws NotFoundException {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<Map<String, String>>(Map.of("message", "Category Deleted Sussesfully"), HttpStatus.OK);
	}
}

