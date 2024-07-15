package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Review;
import com.app.services.ReviewService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/review")
@Slf4j
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createReview(@RequestBody Review review) {
		log.info("ReviewController :: createReview {}", review.getComment());
		reviewService.createReview(review);
	}

	@GetMapping("{reviewId}")
	public Review fetchReviewById(@PathVariable(name = "reviewId") Long id) {
		log.info("ReviewController :: fetchReviewById {}", id);
		return reviewService.fetchReviewById(id);
	}

	@PutMapping("{reviewId}")
	public void uodateReview(@PathVariable(name = "reviewId") Long id, @RequestBody Review review) {
		log.info("ReviewController :: uodateReview {}", id, review.getComment());
		reviewService.updateReview(id, review);
	}

	@DeleteMapping("{reviewId}")
	public void deleteReview(@PathVariable(name = "reviewId") Long id) {
		log.info("ReviewController :: deleteReview {}", id);
		reviewService.deleteReview(id);
	}

}
