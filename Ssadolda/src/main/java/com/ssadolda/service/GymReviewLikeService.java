package com.ssadolda.service;

import java.util.HashMap;
import java.util.List;

import com.ssadolda.model.dto.Gym;

public interface GymReviewLikeService {
	
	int createLike(HashMap<String,Integer> map);
	
	int deleteLike(HashMap<String, Integer> map);
	
	 public List<Gym> readLikeByUserSeq(int userSeq) ;
	
	int countLike(int gymSeq);
	
	void incrementLike(int gymSeq); //gymstatistsic 에서 증가
	
	void decreaseLike(int gymSeq); // ""           에서 감소
	
	boolean isUserLikedGym(int userSeq, int gymSeq);
	
	List<Integer> likeGymsByUserSeq(int userSeq);

}
