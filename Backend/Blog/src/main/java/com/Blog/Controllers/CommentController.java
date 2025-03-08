package com.Blog.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Payload.Response.CommentResponse;
import com.Blog.Services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentResponse> createComment(@RequestBody CommentResponse comment,
			@PathVariable Long postId)
	{
		CommentResponse createComment =this .commentService.createComment(comment, postId);
		return new ResponseEntity<CommentResponse>(createComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/commnets/{commentId}")
	public ResponseEntity<Map<String, String>> deleteComment(@PathVariable Long commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<Map<String, String>>(Map.of("message", "Comment Deleted Sussesfully"), HttpStatus.OK);
	}
}
