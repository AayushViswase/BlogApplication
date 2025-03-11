package com.Blog.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@EntityGraph(attributePaths = { "roles" })
	Optional<User> findByEmail(String email);
}
