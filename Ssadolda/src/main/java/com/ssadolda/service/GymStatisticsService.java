package com.ssadolda.service;

import com.ssadolda.model.dto.GymStatistics;

public interface GymStatisticsService {
	
    GymStatistics getGymStatistics(int gymSeq);
    
    void updateGymStatistics(GymStatistics gymStatistics);
}