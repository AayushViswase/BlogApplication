package com.Blog.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.Blog.Exceptions.ResourceNotFoundException;
import com.Blog.Model.User;
import com.Blog.Payload.Request.UserRequest;
import com.Blog.Payload.Response.UserResponse;
import com.Blog.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired

	private ModelMapper mapper;

	@Override
	public UserResponse createUser(UserRequest userRequest) {
		User user = this.mapper.map(userRequest, User.class);
		User savedUser= this.userRepository.save(user);
		return this.mapper.map(savedUser, UserResponse.class);
	}

	@Override
	public UserResponse updateUser(UserRequest userRequest, Long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		existingUser.setName(userRequest.getName());
		existingUser.setEmail(userRequest.getEmail());
		existingUser.setPassword(userRequest.getPassword());
		existingUser.setAbout(userRequest.getAbout());
		User updatedUser = this.userRepository.save(existingUser);
		return this.mapper.map(updatedUser, UserResponse.class);
	}


	@Override
	public UserResponse getUserById(Long userId) throws NotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.mapper.map(user, UserResponse.class);
	}

	@Override
	public List<UserResponse> getAllUsers() {
		List<User> users=this.userRepository.findAll();
		List<UserResponse> usersList = users.stream().map(user -> this.mapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
		return usersList;
	}

	@Override
	public void deleteUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);
	}

}
