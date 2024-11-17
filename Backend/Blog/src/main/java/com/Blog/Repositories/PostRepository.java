package com.Blog.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Model.Category;
import com.Blog.Model.Post;
import com.Blog.Model.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);

}
