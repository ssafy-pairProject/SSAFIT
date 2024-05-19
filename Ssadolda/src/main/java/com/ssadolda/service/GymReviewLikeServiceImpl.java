package com.ssadolda.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssadolda.model.dao.GymReviewLikeDao;
import com.ssadolda.model.dao.GymStatisticsDao;
import com.ssadolda.model.dto.Gym;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymReviewLikeServiceImpl implements GymReviewLikeService {
private final GymReviewLikeDao dao;
private final GymStatisticsDao gymStatisticsDao;
	
	@Override
	public int createLike(HashMap<String, Integer> map) {
		return dao.addLike(map);
	}

	@Override
	public int deleteLike(HashMap<String, Integer> map) {
		
		return dao.cancelLike(map);
	}
	
	@Override
	public int countLike(int gymSeq) {
		return dao.gymLikeCount(gymSeq);
	}

	@Override
	public void incrementLike(int gymSeq) {
		gymStatisticsDao.incrementLike(gymSeq);
		
	}

	@Override
	public void decreaseLike(int gymSeq) {
		gymStatisticsDao.decreaseLike(gymSeq);
		
	}
	@Override
	 public List<Gym> readLikeByUserSeq(int userSeq) {
	        return dao.selectGymsByUserSeq(userSeq);
	    }

	   @Override
	    public boolean isUserLikedGym(int userSeq, int gymSeq) {
	        return dao.existsByUserSeqAndGymSeq(userSeq, gymSeq) > 0;
	    }

}
