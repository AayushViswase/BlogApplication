package com.Blog.Services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Blog.Exceptions.ResourceNotFoundException;
import com.Blog.Model.Category;
import com.Blog.Model.Post;
import com.Blog.Model.User;
import com.Blog.Payload.Request.PostRequest;
import com.Blog.Payload.Response.PostPageResponse;
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
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
		post.setTitle(postRequest.getTitle());
		post.setContent(postRequest.getContent());
		post.setImageName(postRequest.getPostImageName());
		this.postRepository.save(post);
		return this.mapper.map(post, PostResponse.class);
	}

	@Override
	public void deletePost(Long postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
		this.postRepository.delete(post);
	}

	@Override
	@Transactional
	public PostPageResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> pagePost = this.postRepository.findAll(pageable);
		List<Post> allPost = pagePost.getContent();

		List<PostResponse> list = allPost.stream().map((post) -> this.mapper.map(post, PostResponse.class))
				.collect(Collectors.toList());
		PostPageResponse pageResponse = new PostPageResponse();
		pageResponse.setContent(list);
		pageResponse.setPageNumber(pagePost.getNumber());
		pageResponse.setPageSize(pagePost.getSize());
		pageResponse.setTotalElements(pagePost.getTotalElements());
		pageResponse.setTotalPages(pagePost.getTotalPages());
		pageResponse.setLastPage(pagePost.isLast());

		return pageResponse;

	}

	@Override
	@Transactional
	public PostResponse getPostById(Long postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
		return this.mapper.map(post, PostResponse.class);
	}

	@Override
	@Transactional
	public List<PostResponse> getPostByCategory(Long categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category ID", categoryId));
		List<Post> postList = this.postRepository.findByCategory(category);
		return postList.stream().map((post) -> this.mapper.map(post, PostResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<PostResponse> getPostByUser(Long userID) {
		User user = this.userRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user ID", userID));
		List<Post> postList = this.postRepository.findByUser(user);
		return postList.stream().map(post -> this.mapper.map(post, PostResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<PostResponse> searchPost(String keyword) {
		List<Post> posts= this.postRepository.findByTitleContaining(keyword);
		return posts.stream().map(post -> this.mapper.map(post, PostResponse.class))
				.collect(Collectors.toList());
	}

}
