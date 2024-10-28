package com.Blog.Services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Exceptions.ResourceNotFoundException;
import com.Blog.Model.Category;
import com.Blog.Model.Post;
import com.Blog.Model.User;
import com.Blog.Payload.Request.PostRequest;
import com.Blog.Payload.Response.PostResponse;
import com.Blog.Repositories.CategoryRepository;
import com.Blog.Repositories.PostRepository;
import com.Blog.Repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostResponse createPost(PostRequest postRequest, Long userId, Long categoryId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "User id", userId));
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));

		Post post = this.mapper.map(postRequest, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savePost = this.postRepository.save(post);
		return this.mapper.map(savePost, PostResponse.class);
	}

	@Override
	public PostResponse updatePost(PostRequest postRequest, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Long postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostResponse> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostResponse getPostById(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostResponse> getPostByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostResponse> getPostByUser(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostResponse> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
