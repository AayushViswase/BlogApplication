package com.Blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
