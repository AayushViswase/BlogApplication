package com.Blog.Services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.Blog.Model.User;
import com.Blog.Payload.Request.UserRequest;

public interface UserService {
	UserRequest createUser(UserRequest user);

	UserRequest updateUser(UserRequest user, Long userId) throws NotFoundException;

	User getUserById(Long userID) throws NotFoundException;

	List<User> getAllUsers();

	void deleteUser(Long userID) throws NotFoundException;
}
