package com.ssadolda.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssadolda.model.dao.GymReviewDao;
import com.ssadolda.model.dto.GymReview;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymReviewServiceImpl implements GymReviewService {

	private final GymReviewDao gymrreviewdao;
	@Override
	public int registGymReview(GymReview gymreview) {
		//레이팅 총점 더해줌 
		gymrreviewdao.increaseTotalRate(gymreview);
		//리뷰 총 갯수 더해줌
		gymrreviewdao.increaseGymReview(gymreview);
		return gymrreviewdao.insertGymReview(gymreview);
	}

	@Override
	public int deleteGymReview(int reviewSeq) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int decreaseRating = gymrreviewdao.getDecreaseRate(reviewSeq);
		int gymSeq = gymrreviewdao.getGymSeqByReviewSeq(reviewSeq);
		map.put("decreaseRating", decreaseRating);
		map.put("gymSeq", gymSeq);
		gymrreviewdao.decreaseGymReview(gymSeq);
		gymrreviewdao.decreaseTotalRate(map);

		return gymrreviewdao.deleteGymReview(reviewSeq);
	}

	@Override
	public List<GymReview> readAllGymReview(int gymSeq) {
		return gymrreviewdao.selectAllGymReviews(gymSeq);
	}

}
