package com.Blog.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Exceptions.ResourceNotFoundException;
import com.Blog.Model.Comment;
import com.Blog.Model.Post;
import com.Blog.Payload.Response.CommentResponse;
import com.Blog.Repositories.CommentRepository;
import com.Blog.Repositories.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentResponse createComment(CommentResponse commentResponse, Long postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		Comment comment = this.mapper.map(commentResponse, Comment.class);
		comment.setPost(post);
		Comment saveComment = this.commentRepository.save(comment);
		return this.mapper.map(saveComment, CommentResponse.class);
	}

	@Override
	public void deleteComment(Long commentId) {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commnet id", commentId));
		this.commentRepository.delete(comment);
	}

}
