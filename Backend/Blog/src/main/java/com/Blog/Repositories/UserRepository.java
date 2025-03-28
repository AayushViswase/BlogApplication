package com.Blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
}
