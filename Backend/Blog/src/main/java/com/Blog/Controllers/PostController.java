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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Payload.Request.PostRequest;
import com.Blog.Payload.Response.PostPageResponse;
import com.Blog.Payload.Response.PostResponse;
import com.Blog.Services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest, @PathVariable Long userId,
			@PathVariable Long categoryId) {
		PostResponse createPost = this.postService.createPost(postRequest, userId, categoryId);
		return new ResponseEntity<PostResponse>(createPost, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostResponse>> getPostsbyUser(@PathVariable Long userId) {
		List<PostResponse> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostResponse>>(posts, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostResponse>> getPostsbyCategory(@PathVariable Long categoryId) {
		List<PostResponse> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostResponse>>(posts, HttpStatus.OK);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostResponse> getPostsbyId(@PathVariable Long postId) {
		PostResponse postResponse = this.postService.getPostById(postId);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostPageResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
		PostPageResponse posts = this.postService.getAllPost(pageNumber, pageSize);
		return new ResponseEntity<PostPageResponse>(posts, HttpStatus.OK);
	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<Map<String, String>> deletePost(@PathVariable Long postId) throws NotFoundException {
		this.postService.deletePost(postId);
		return new ResponseEntity<Map<String, String>>(Map.of("message", "Category Deleted Sussesfully"),
				HttpStatus.OK);
	}

}
