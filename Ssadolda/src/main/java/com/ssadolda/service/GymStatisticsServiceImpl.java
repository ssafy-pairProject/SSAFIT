package com.ssadolda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssadolda.model.dao.GymStatisticsDao;
import com.ssadolda.model.dto.GymStatistics;

@Service
public class GymStatisticsServiceImpl implements GymStatisticsService {


	    @Autowired
	    private GymStatisticsDao gymStatisticsDao;

	    @Override
	    public GymStatistics getGymStatistics(int gymSeq) {
	        return gymStatisticsDao.getGymStatistics(gymSeq);
	    }

	    @Override
	    public void updateGymStatistics(GymStatistics gymStatistics) {
	        gymStatisticsDao.updateGymStatistics(gymStatistics);
	    }

		@Override
		public int gymCount() {
			return gymStatisticsDao.gymCount();
		}
	}
