package com.ssadolda.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssadolda.model.dto.Gym;
import com.ssadolda.model.dto.GymReview;
@Repository
public interface GymReviewDao {
	
	public int insertGymReview(GymReview gymreview);

	public int deleteGymReview(int reveiwSeq);

	public List<GymReview> selectAllGymReviews(int gymSeq);
	
	public GymReview selectGymReviewsByReviewSeq(int reveiwSeq);

	void increaseTotalRate (GymReview gymreview);
	
	int getDecreaseRate (int reviewSeq);
	
	int getGymSeqByReviewSeq (int reviewSeq);
	
	void decreaseTotalRate(Map<String, Integer> map);
	
	void increaseGymReview(GymReview gymreview);
	
	void decreaseGymReview(int gymSeq);
}
