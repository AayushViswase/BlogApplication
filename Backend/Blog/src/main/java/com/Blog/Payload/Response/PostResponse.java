package com.Blog.Payload.Response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	private Long postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryResponse category;
	private UserResponse user;
	private List<CommentResponse> comments;
}
