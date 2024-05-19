package com.ssadolda.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssadolda.model.dto.Gym;
import com.ssadolda.model.dto.GymReview;
@Repository
public interface GymReviewDao {
	
	public int insertGymReview(GymReview gym);

	public int deleteGymReview(int reveiwSeq);

	public List<GymReview> selectAllGymReviews(int gymSeq);
	
	public GymReview selectGymReviewsByReviewSeq(int reveiwSeq);

	void increaseTotalRate (GymReview gym);
	
	void decreaseTotalRate (int decreaseRating);
}
