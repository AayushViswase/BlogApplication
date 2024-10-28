package com.Blog.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private Long userId;
	private String name;
	private String email;
	private String password;
	private String about;
}
