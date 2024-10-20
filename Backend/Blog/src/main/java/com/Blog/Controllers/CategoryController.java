package com.Blog.Controllers;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Model.Category;
import com.Blog.Payload.Request.CategoryRequest;
import com.Blog.Services.CategoryService;

@RestController
@RequestMapping("api/category/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryRequest> createCategory(@RequestBody CategoryRequest categoryRequest) {
		CategoryRequest request=this.categoryService.createCategory(categoryRequest);
		return new ResponseEntity<CategoryRequest>(request, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryRequest> updateCategory(@RequestBody CategoryRequest categoryRequest,
			@PathVariable Long categoryId) throws NotFoundException {
		CategoryRequest category = this.categoryService.updateCategory(categoryRequest, categoryId);
		return ResponseEntity.ok(category);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) throws NotFoundException {
		Category category = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategory() {
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long categoryId) throws NotFoundException {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<Map<String, String>>(Map.of("message", "Category Deleted Sussesfully"), HttpStatus.OK);
	}
}

