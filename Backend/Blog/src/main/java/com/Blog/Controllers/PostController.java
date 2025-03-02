package com.Blog.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Blog.Payload.Request.PostRequest;
import com.Blog.Payload.Response.PostPageResponse;
import com.Blog.Payload.Response.PostResponse;
import com.Blog.Services.FileService;
import com.Blog.Services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

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
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy) {
		PostPageResponse posts = this.postService.getAllPost(pageNumber, pageSize, sortBy);
		return new ResponseEntity<PostPageResponse>(posts, HttpStatus.OK);
	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<Map<String, String>> deletePost(@PathVariable Long postId) throws NotFoundException {
		this.postService.deletePost(postId);
		return new ResponseEntity<Map<String, String>>(Map.of("message", "Category Deleted Sussesfully"),
				HttpStatus.OK);
	}

	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostResponse>> searchPostByTitle(@PathVariable String keyword) {
		List<PostResponse> result = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostResponse>>(result, HttpStatus.OK);
	}

	@PostMapping("post/image/upload/{postId}")
	public ResponseEntity<PostResponse> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable Long postId) throws IOException {
		String fileName = this.fileService.uploadImage(path, image);
		PostResponse postResponse = this.postService.getPostById(postId);
		postResponse.setImageName(fileName);

		PostResponse updatedPost = this.postService.updatePost(postResponse, postId);
		return new ResponseEntity<PostResponse>(updatedPost, HttpStatus.OK);
	}

	@GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResources(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
