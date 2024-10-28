package com.Blog.Services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.Blog.Payload.Request.UserRequest;
import com.Blog.Payload.Response.UserResponse;

public interface UserService {
	UserResponse createUser(UserRequest user);

	UserResponse updateUser(UserRequest user, Long userId) throws NotFoundException;

	UserResponse getUserById(Long userID) throws NotFoundException;

	List<UserResponse> getAllUsers();

	void deleteUser(Long userID) throws NotFoundException;
}
