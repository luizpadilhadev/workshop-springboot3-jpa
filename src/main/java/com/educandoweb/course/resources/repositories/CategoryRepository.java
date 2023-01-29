package com.educandoweb.course.resources.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.User;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
