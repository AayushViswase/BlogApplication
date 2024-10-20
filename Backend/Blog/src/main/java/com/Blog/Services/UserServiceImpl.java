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
import com.Blog.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired

	private ModelMapper mapper;

	@Override
	public UserRequest createUser(UserRequest userRequest) {
		User user = this.mapper.map(userRequest, User.class);
		User savedUser= this.userRepository.save(user);
		return this.mapper.map(savedUser, UserRequest.class);
	}

	@Override
	public UserRequest updateUser(UserRequest userRequest, Long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		existingUser.setName(userRequest.getName());
		existingUser.setEmail(userRequest.getEmail());
		existingUser.setPassword(userRequest.getPassword());
		existingUser.setAbout(userRequest.getAbout());
		User updatedUser = this.userRepository.save(existingUser);
		return this.mapper.map(updatedUser, UserRequest.class);
	}


	@Override
	public User getUserById(Long userId) throws NotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users=this.userRepository.findAll();
		List<User> usersList = users.stream().map(user -> this.mapper.map(user, User.class))
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
