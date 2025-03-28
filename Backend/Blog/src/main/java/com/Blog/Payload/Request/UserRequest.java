package com.Blog.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	private Long userId;
	private String name;
	private String email;
	private String password;
	private String role;
	private String about;

}
