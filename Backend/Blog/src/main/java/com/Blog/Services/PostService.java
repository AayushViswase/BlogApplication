package com.Blog.Services;

import java.util.List;

import com.Blog.Payload.Request.PostRequest;
import com.Blog.Payload.Response.PostResponse;

public interface PostService {
	PostResponse createPost(PostRequest postRequest, Long userId, Long categoryId);

	PostResponse updatePost(PostRequest postRequest, Long postId);

	void deletePost(Long postId);

	List<PostResponse> getAllPost();

	PostResponse getPostById(Long postId);

	List<PostResponse> getPostByCategory(Long categoryId);

	List<PostResponse> getPostByUser(Long userID);

	List<PostResponse> searchPost(String keyword);
}
