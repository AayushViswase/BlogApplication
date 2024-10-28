package com.Blog.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
	private Long categoryId;
	private String categoryTitle;
	private String categoryDescription;
}
