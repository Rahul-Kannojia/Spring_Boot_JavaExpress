package com.app.services;

import com.app.entities.Review;

public interface IReviewService {

	public void createReview(Review review);
	public Review fetchReviewById(Long id);
	public void fetchAllReview();
	public void updateReview(Long id,Review review);
	public void deleteReview(Long id);
}
