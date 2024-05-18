package com.ssadolda.service;

import java.util.List;

import com.ssadolda.model.dto.Gym;
import com.ssadolda.model.dto.GymReview;

public interface GymReviewService {
	
	// 리뷰 등록
	public int registGymReview(GymReview gymreview);	
	
	// 리뷰삭제
	public int deleteGymReview(int reviewSeq);
	
	// 리뷰 모든 리스트
	public List<GymReview> readAllGymReview(int gymSeq);	
	


}
