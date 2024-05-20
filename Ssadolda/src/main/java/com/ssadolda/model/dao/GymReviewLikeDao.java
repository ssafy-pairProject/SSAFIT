package com.ssadolda.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssadolda.model.dto.Gym;

@Repository
public interface GymReviewLikeDao {
	
	int addLike (HashMap<String, Integer> map);
	
	int cancelLike (HashMap<String, Integer> map);
	
	int gymLikeCount(int gymSeq);
	
	 List<Gym> selectGymsByUserSeq(int userSeq);
	 
	 int existsByUserSeqAndGymSeq(int userSeq, int gymSeq);

}
