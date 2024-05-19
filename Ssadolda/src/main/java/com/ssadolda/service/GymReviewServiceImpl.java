package com.ssadolda.service;

import java.util.List;

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
		return gymrreviewdao.insertGymReview(gymreview);
	}

	@Override
	public int deleteGymReview(int reviewSeq) {
	
		return gymrreviewdao.deleteGymReview(reviewSeq);
	}

	@Override
	public List<GymReview> readAllGymReview(int gymSeq) {
		return gymrreviewdao.selectAllGymReviews(gymSeq);
	}

}
