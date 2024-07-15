package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Review;
import com.app.exception.ResourceNotFoundException;
import com.app.repositories.ProductRepository;
import com.app.repositories.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewService implements IReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void createReview(Review review) {
		log.info("ReviewService :: createReview {}", review.getComment());
		reviewRepository.save(review);
		log.info("ReviewService :: createReview :: Review created successfully for given Product {}",
				review.getProduct().getId());
	}

	@Override
	public Review fetchReviewById(Long id) {
		log.info("ReviewService :: fetchReviewById {}", id);
		Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
		log.info("ReviewService :: fetchReviewById::  Review fetched successfully with given ID {}", id);
		return review;
	}

	@Override
	public void fetchAllReview() {
		log.info("ReviewService :: fetchAllReview ");
		reviewRepository.findAll();
	}
	/*
	 * @Override public void updateReview(Long id, Review inputReview) {
	 * log.info("ReviewService :: updateReview {} {} ", id,
	 * inputReview.getProduct().getName()); Review review = fetchReviewById(id); if
	 * (review.getProduct().getId() == inputReview.getProduct().getId()) {
	 * review.setComment(inputReview.getComment()); reviewRepository.save(review);
	 * log.info("ReviewService :: updateReview :: Review updated successfully "); }
	 * else { log.
	 * error("ReviewService :: updateReview :: Review with given id for given Product is not available "
	 * ); throw new RuntimeException("Review with give ID is not available"); } }
	 */

	@Override
	public void updateReview(Long id, Review inputReview) {
		log.info("ReviewService :: updateReview {} {} ", id, inputReview.getProduct().getName());
		Long productId = inputReview.getProduct().getId();
		if (productRepository.existsById(productId)) {
			if (reviewRepository.existsById(id)) {
				Review review = fetchReviewById(id);
				review.setComment(inputReview.getComment());
				reviewRepository.save(review);
				log.info("ReviewService :: updateReview :: Review updated successfully ");
			} else {
				log.error("ReviewService :: updateReview :: Product with given Id not available in Database ");
				throw new ResourceNotFoundException("Product with give Id is not available");
			}
		} else {
			log.error("ReviewService :: updateReview :: Review with given id for given Product is not available ");
			throw new RuntimeException("Review with give Id is not available");
		}
	}

	@Override
	public void deleteReview(Long id) {
		log.info("ReviewService :: deleteReview {} ", id);
		if (reviewRepository.existsById(id)) {
			reviewRepository.deleteById(id);
			log.info("ReviewService :: deleteReview :: Review with given ID is deleted successfully {} ", id);
		} else {
			log.error("ReviewService :: deleteReview  ::Review with given ID is not found");
			throw new ResourceNotFoundException("Review with give ID is not available");
		}
	}

}
