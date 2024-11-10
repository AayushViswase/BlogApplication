package com.Blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Payload.Request.PostRequest;
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

	//	@GetMapping("/user/{userId}/posts")
	//	public ResponseEntity<List<PostResponse>> getPostsbyCategory(@PathVariable Long userId) {
	//		List<PostResponse> posts = this.postService.getPostByCategory(userId);
	//		return new ResponseEntity<List<PostResponse>>(posts, HttpStatus.OK);
	//	}
}
