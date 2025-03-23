package com.Blog.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Payload.Request.UserRequest;
import com.Blog.Payload.Response.UserPageResponse;
import com.Blog.Payload.Response.UserResponse;
import com.Blog.Services.UserService;

@RestController
@RequestMapping("api/user/")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		UserResponse createUserDto = this.userService.createUser(userRequest);
		return new ResponseEntity<UserResponse>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long userId)
			throws NotFoundException {
		UserResponse updatedUser = this.userService.updateUser(userRequest, userId);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long userId) throws NotFoundException {
		this.userService.deleteUser(userId);
		return new ResponseEntity<Map<String, String>>(Map.of("message", "User Deleted Sussesfully"), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<UserPageResponse> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		UserPageResponse users = this.userService.getAllUsers(pageNumber, pageSize);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}


	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) throws NotFoundException {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
