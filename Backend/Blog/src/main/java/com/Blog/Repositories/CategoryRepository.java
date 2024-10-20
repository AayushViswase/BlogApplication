package com.Blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
