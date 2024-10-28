package com.Blog.Payload.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
	private Long categoryId;
	@NotBlank
	@Size(min = 4)
	private String categoryTitle;
	@NotBlank
	@Size(min = 10)
	private String categoryDescription;

}
