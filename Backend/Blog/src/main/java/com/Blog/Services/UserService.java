package com.Blog.Services;

import java.util.List;

import com.Blog.Payload.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Long userId);

	UserDto getUserById(Long userID);

	List<UserDto> getAllUsers();

	void deleteUser(Long userID);
}
