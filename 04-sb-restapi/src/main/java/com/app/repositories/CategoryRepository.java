package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Category;

/**
 * It is optional to write when we are using JPA because It is inheriting from
 * SimpleJpaRepository.java(Class)
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
