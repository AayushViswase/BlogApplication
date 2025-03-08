package com.Blog.Services;

import com.Blog.Payload.Response.CommentResponse;

public interface CommentService {
	CommentResponse createComment(CommentResponse commentResponse, Long postId);

	void deleteComment(Long commentId);
}
