package com.Blog.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Model.User;
import com.Blog.Payload.Request.UserRequest;
import com.Blog.Services.UserService;

@RestController
@RequestMapping("api/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserRequest> createUser(@RequestBody UserRequest userRequest) {

		UserRequest createUserDto = this.userService.createUser(userRequest);
		return new ResponseEntity<UserRequest>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long userId)
			throws NotFoundException {
		UserRequest updatedUser = this.userService.updateUser(userRequest, userId);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long userId) throws NotFoundException {
		this.userService.deleteUser(userId);
		return new ResponseEntity<Map<String, String>>(Map.of("message", "User Deleted Sussesfully"), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) throws NotFoundException {
		User user = this.userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
