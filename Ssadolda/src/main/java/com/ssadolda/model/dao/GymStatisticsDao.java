package com.ssadolda.model.dao;

import org.springframework.stereotype.Repository;

import com.ssadolda.model.dto.GymStatistics;
@Repository
public interface GymStatisticsDao {
	
    GymStatistics getGymStatistics(int gymSeq);
    
    void updateGymStatistics(GymStatistics gymStatistics);
    
    void incrementViewCount(int gymSeq);
    
	void incrementLike(int gymSeq);
	
	void decreaseLike(int gymSeq);

	
}