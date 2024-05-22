package com.ssadolda.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssadolda.model.dto.GymStatistics;
import com.ssadolda.model.dto.Gymsort;
@Repository
public interface GymStatisticsDao {
	
    GymStatistics getGymStatistics(int gymSeq);
    
    void updateGymStatistics(GymStatistics gymStatistics);
    
    void incrementViewCount(int gymSeq);
    
	void incrementLike(int gymSeq);
	
	void decreaseLike(int gymSeq);
	
	int gymCount();
	
	List<Gymsort> sortGyms (@Param("sortBy") String sortBy, @Param("order") String order);

	
}