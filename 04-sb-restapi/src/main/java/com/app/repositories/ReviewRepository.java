package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
